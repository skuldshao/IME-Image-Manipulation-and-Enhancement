package model.colortransformations;

import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;
import utils.ImageUtil;

/**
 * This class represents a simple implementation of color transformation on a given image.
 * A color transformation results in the new color of this pixel to be (r',g',b') such
 * that each of them are dependent only on the values (r,g,b).
 */
public class ColorTransformationImpl implements ColorTransformation {
  protected final double[][] colorTransformationMatrix;
  protected final String name;
  // INVARIANT: colorTransformationMatrix must be a 3*3matrix
  // INVARIANT: colorTransformationMatrix  cannot be null
  // INVARIANT: colorTransformationMatrix cannot be null

  /**
   * Construct a colorTransformationImpl object to color transform an image with given color
   * transformation matrix, and name the name image after transforming with the given name.
   *
   * @param colorTransformationMatrix the color transformation matrix to do a simple linear
   *                                  color transformation on a image
   * @param name                      the name of a new image after imaging
   * @throws IllegalArgumentException if the given matrix and name is null
   * @throws IllegalArgumentException If the given matrix is not a 3*3 matrix(because the pixel has
   *                                  3 channels to transform: blue,red,green)
   */
  public ColorTransformationImpl(double[][] colorTransformationMatrix, String name) {
    if (colorTransformationMatrix == null) {
      throw new IllegalArgumentException("The given matrix cannot be null");
    }
    if (colorTransformationMatrix.length != 3 || colorTransformationMatrix[0].length != 3) {
      throw new IllegalArgumentException("The given matrix must be a 3x3 matrix.");
    }
    this.colorTransformationMatrix = colorTransformationMatrix;

    if (name == null) {
      throw new IllegalArgumentException("The given name cannot be null");
    }
    this.name = name;
  }

  @Override
  public Image applyColorTransformation(Image image) {
    if (image == null) {
      throw new IllegalArgumentException("The given image cannot be null");
    }
    int imageHeight = image.getHeight();
    int imageWidth = image.getWidth();
    int maxColor = image.getMaxColor();
    Image newImage = new ImageImpl(imageWidth, imageHeight, maxColor, this.name);
    for (int row = 0; row < imageHeight; row++) {
      for (int col = 0; col < imageWidth; col++) {
        Pixel pixel = image.getPixel(col, row);
        newImage.updatePixel(col, row, colorTransformPixel(pixel));
      }
    }
    return newImage;
  }

  /**
   * color transform a given pixel with the color transformation matrix in the field.
   *
   * @param pixel the pixel to be color transform
   * @return a new pixel after color transforming with the given color transformation matrix
   */
  private Pixel colorTransformPixel(Pixel pixel) {
    int redColor = pixel.getChannel(Pixel.Channel.Red);
    int greenColor = pixel.getChannel(Pixel.Channel.Green);
    int blueColor = pixel.getChannel(Pixel.Channel.Blue);
    int newRedColor = (int) Math.round(redColor * this.colorTransformationMatrix[0][0] +
            greenColor * this.colorTransformationMatrix[0][1] +
            blueColor * this.colorTransformationMatrix[0][2]);
    int newGreenColor = (int) Math.round(redColor * this.colorTransformationMatrix[1][0] +
            greenColor * this.colorTransformationMatrix[1][1] +
            blueColor * this.colorTransformationMatrix[1][2]);
    int newBlueColor = (int) Math.round(redColor * this.colorTransformationMatrix[2][0] +
            greenColor * this.colorTransformationMatrix[2][1] +
            blueColor * this.colorTransformationMatrix[2][2]);
    // clamp to new value for three channels in the max color range
    newRedColor = ImageUtil.clampColor(newRedColor, pixel.getMaxPixelColor());
    newGreenColor = ImageUtil.clampColor(newGreenColor, pixel.getMaxPixelColor());
    newBlueColor = ImageUtil.clampColor(newBlueColor, pixel.getMaxPixelColor());
    return new PixelImpl(newRedColor, newGreenColor, newBlueColor, pixel.getMaxPixelColor());
  }
}
