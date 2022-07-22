package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import controller.TextScriptController;
import controller.TextScriptControllerImpl;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;


/**
 * This class contains utility methods for this program, including read a PPM image from file and
 * turn it into a Image, Write the given Image object to a PPM image with the given filepath, and
 * a demo main method to run this program.
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
   * This is main method to run the image editor, the user to choose script or  text modes to
   * interactive with the program.
   *
   * @param args the commandline arguements to run this program
   */
  public static void main(String[] args) {
    ImageModel model = new ImageModelImpl();
    Appendable ap = System.out;
    TextScriptController controller;
    switch (args[0]) {
      case "Text":
        Readable rd = new InputStreamReader(System.in);
        controller = new TextScriptControllerImpl(model, rd, ap);
        controller.editImage();
        break;
      case "Script":
        String filepath = args[1];
        File file = new File(filepath);
        controller = new TextScriptControllerImpl(model, file, ap);
        controller.editImage();
        break;
      default:
        System.out.println("Invalid command!");
        break;
    }
  }
}

