package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import controller.GuiViewControllerImpl;
import controller.ImageEditorController;
import controller.TextScriptControllerImpl;

import javax.imageio.ImageIO;

import model.ImageModel;
import model.ImageModelImpl;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;
import view.GUIView;
import view.GuiViewWindow;


/**
 * This class contains utility methods for this program, including read a PPM/PNG/JPEG
 * image from file and turn it into a Image, Write the given Image object to a PPM/PNG/JPEG image
 * with the given filepath, and a demo main method to run this program.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and convert it into an Image and then return/store it in
   * the program with the given name.
   *
   * @param filename the path of the file.
   * @param name     the name of the image reference in the program
   * @return an image is converted from the given PPM file
   * @throws IllegalArgumentException If the given filepath is invalid(the file cannot be found)
   * @throws IllegalArgumentException If  the given plain RAW file doesn't begin with P3
   * @throws IllegalArgumentException If  the given name is null
   */
  public static Image readPPM(String filename, String name) {
    if (name == null) {
      throw new IllegalArgumentException("The name cannot be null");
    }
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    Image image = new ImageImpl(width, height, maxValue, name);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        Pixel pixel = new PixelImpl(r, g, b, maxValue);
        image.updatePixel(j, i, pixel);
      }
    }
    return image;
  }

  /**
   * Writes an image to a PPM file and creates it if and only it doesn't exist and the file is
   * writeable.
   *
   * @param filename path of the file.
   * @param image    Image to be written to file
   * @throws IllegalArgumentException if the given path is invalid
   * @throws IllegalArgumentException the file is not writeable.
   */
  public static void writePPM(String filename, Image image) {
    File outputFile;

    // check if the given path is invalid
    try {
      outputFile = new File(filename);
      // the program will only create a new, empty file named by its filename if and only if this
      // file with this name does not yet exist, and return a true, otherwise return false
      outputFile.createNewFile();
    } catch (IOException e) {
      // if the given path is invalid
      throw new IllegalArgumentException("Cannot create a file with the given path");
    }

    //check if the created file with the given path is writeable, if so, start to write out the
    // image
    if (outputFile.canWrite()) {
      writePPMHelper(outputFile, image);
    } else {
      throw new IllegalArgumentException("This Program cannot write out this file.");
    }

  }

  /**
   * Help to write a image to a PPM file.
   *
   * @param outputFile The PPM file the user would like to store
   * @param image      Image to be written to file
   * @throws IllegalArgumentException the file is not writeable.
   */
  private static void writePPMHelper(File outputFile, Image image) {
    FileWriter outputImage;
    try {
      if (image == null) {
        throw new IllegalArgumentException("This image is null");
      }
      outputImage = new FileWriter(outputFile);
      outputImage.write("P3\n");
      outputImage.write(image.getWidth() + " " + image.getHeight() + "\n");
      outputImage.write(image.getMaxColor() + "\n");

      for (int i = 0; i < image.getHeight(); i++) {
        for (int j = 0; j < image.getWidth(); j++) {
          outputImage.write(image.getPixel(j, i).getChannel(Pixel.Channel.Red) + "\n");
          outputImage.write(image.getPixel(j, i).getChannel(Pixel.Channel.Green) + "\n");
          outputImage.write(image.getPixel(j, i).getChannel(Pixel.Channel.Blue) + "\n");
        }
      }
      outputImage.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot to write out the file.");
    }
  }

  /**
   * Ensures a pixel channel value remains between 0 and the maxValue after some image manipulation
   * so that the color value is valid.
   *
   * @param color    value of channel to be clamped
   * @param maxValue maximum channel value for image
   * @return clamped channel value
   */
  public static int clampColor(int color, int maxValue) {
    if (color > maxValue) {
      return maxValue;
    } else if (color < 0) {
      return 0;
    } else {
      return color;
    }
  }

  /**
   * Read an image file in the PNG/JPEG format and convert it into an Image and then return/store
   * it in the program with the given name.
   *
   * @param filename the path of the file.
   * @param name     the name of the image reference in the program
   * @return an image is converted from the given png/jpng/bmp file
   * @throws IllegalArgumentException if the given filepath is invalid
   * @throws IllegalArgumentException IF the file with the given filepath is non-readable
   */
  public static Image readNormalPhoto(String filename, String name)
          throws IllegalArgumentException {
    File readfile = new File(filename);
    if (readfile == null) {
      throw new IllegalArgumentException("File cannot be null.");
    }
    for (int i = filename.length() - 1; i >= 0; i--) {
      if (filename.charAt(i) == '.' && i != filename.length() - 1) {
        if (filename.substring(i + 1).equalsIgnoreCase("ppm")) {
          return ImageUtil.readPPM(filename, name);
        }
      }
    }
    try {
      BufferedImage readImage = ImageIO.read(readfile);
      Image image = new ImageImpl(readImage.getWidth(), readImage.getHeight(),
              255, name);
      for (int i = 0; i < readImage.getHeight(); i++) {
        for (int j = 0; j < readImage.getWidth(); j++) {
          Color color = new Color(readImage.getRGB(j, i));
          int red = color.getRed();
          int green = color.getGreen();
          int blue = color.getBlue();
          Pixel currentPixel = new PixelImpl(red, green, blue, 255);
          image.updatePixel(j, i, currentPixel);
        }
      }
      return image;
    } catch (IOException e) {
      throw new IllegalArgumentException("This file is non-readable");
    }
  }

  /**
   * Export an image to the given filepath if and only it doesn't exist and the file is
   * writeable, the image format is determined by the extension provided in the given file path
   * the method supports to export a image in the program as ppm/png/jpng/bmp etc format.
   *
   * @param filepath path of the file.
   * @param image    Image to be written to file
   * @throws IllegalArgumentException if the given path is invalid
   * @throws IllegalArgumentException the file is not writeable.
   * @throws IllegalArgumentException if the given filepath and image is null
   * @throws IllegalArgumentException if the given image cannot be export
   */
  public static void exportwithMutipleType(String filepath, Image image) {
    if (image == null || filepath == null) {
      throw new IllegalArgumentException("The given arguments cannot be null");
    }
    String photoType = "ppm";
    for (int i = filepath.length() - 1; i >= 0; i--) {
      if (filepath.charAt(i) == '.' && i != filepath.length() - 1) {
        photoType = filepath.substring(i + 1);
        break;
      }
    }

    if (photoType.equals("ppm")) {
      writePPM(filepath, image);
      return;
    }
    BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    File file = new File(filepath);
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Pixel currentPixel = image.getPixel(j, i);
        int red = currentPixel.getChannel(Pixel.Channel.Red);
        int green = currentPixel.getChannel(Pixel.Channel.Green);
        int blue = currentPixel.getChannel(Pixel.Channel.Blue);
        int rgb = (red << 16 | green << 8 | blue);
        img.setRGB(j, i, rgb);
      }
    }

    try {
      ImageIO.write(img, photoType, file);
    } catch (IOException e) {
      throw new IllegalArgumentException("cannot export image.");
    }
  }

  /**
   * This is main method to run the image editor, the user to choose script or text modes to
   * interactive with the program. The program also provides the GUI for the user to interactive
   * with. If the user doesn't give an invalid command, the program would give that hint and shun
   * down the program immediately.
   *
   * @param args the commandline arguments to run this program
   */
  public static void main(String[] args) {
    ImageModel model = new ImageModelImpl();
    Appendable ap = System.out;
    ImageEditorController controller;
    switch (args.length) {
      case 2:
        if (args[0].equalsIgnoreCase("-file")) {
          String filepath = args[1];
          File file = new File(filepath);
          controller = new TextScriptControllerImpl(model, file, ap);
          controller.editImage();
          new TextScriptControllerImpl(model, new StringReader("q"), ap);
        } else {
          System.out.println("Invalid command.");
          try {
            Runtime.getRuntime().exec("pgrep '6.jar' | xargs kill");
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        break;
      case 1:
        if (args[0].equalsIgnoreCase("-text")) {
          Readable rd = new InputStreamReader(System.in);
          controller = new TextScriptControllerImpl(model, rd, ap);
          controller.editImage();
        } else {
          System.out.println("Invalid command!");
          new TextScriptControllerImpl(model, new StringReader("q"), ap);
        }
        break;
      default:
        GUIView view = new GuiViewWindow(model);
        controller = new GuiViewControllerImpl(model, view);
        controller.editImage();
        break;
    }
  }
}





