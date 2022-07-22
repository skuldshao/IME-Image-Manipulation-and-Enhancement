package model.manipulation.greyscale;

import model.image.Pixel;
import model.manipulation.ManipulationModel;

/**
 * This is the abstract class that represents the operation for producing a grey sale image of
 * a given image.
 */
abstract public class GreyScaleImage extends ManipulationModel {
  protected final Pixel.Channel[] channels =
      {Pixel.Channel.Red, Pixel.Channel.Green, Pixel.Channel.Blue};
  // INVARIANT: name isn't null.

  /**
   * Constructs a abstract GreyScaleImage with the given file name.
   *
   * @param name the name of the new iamge after editing
   * @throws IllegalArgumentException if the given name is null
   */
  protected GreyScaleImage(String name) {
    super(name);
  }

  /**
   * Set the green,red, blue color value of the given pixel as the given color value.
   *
   * @param pixel the pixel to be set
   * @param color the color to set for the green,red and blue channel of the given pixel
   * @throws IllegalArgumentException if the given pixel is null
   * @throws IllegalArgumentException IF the given color is less than 0 or greater than the
   *                                  max value of the given pixel.
   */
  protected void updateAllColor(Pixel pixel, int color) {
    if (pixel == null) {
      throw new IllegalArgumentException("The given Pixel cannot be null");
    }
    if (color < 0 || color > pixel.getMaxPixelColor()) {
      throw new IllegalArgumentException("The given color is out of bound.");
    }
    for (Pixel.Channel oneChannel : channels) {
      pixel.setChannel(oneChannel, color);
    }
  }
}
