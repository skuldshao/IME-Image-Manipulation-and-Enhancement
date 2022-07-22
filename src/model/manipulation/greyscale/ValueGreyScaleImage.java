package model.manipulation.greyscale;

import model.image.Pixel;

/**
 * This is the  class that represents the operation for producing a luma grey sale image of
 * a given image,where all the pixel red,green,blue value would be set as  the maximum value of the
 * three components.
 **/

public class ValueGreyScaleImage extends GreyScaleImage {
  // INVARIANT: name isn't null.

  /**
   * Constructs a ValueGreyScaleImage operation class with the given file name.
   *
   * @param name the name of the new image after editing
   * @throws IllegalArgumentException if the given name is null
   */

  public ValueGreyScaleImage(String name) {
    super(name);
  }

  @Override
  protected void editHelper(Pixel pixel) {
    int maxColor = 0;
    for (Pixel.Channel oneChannel : this.channels) {
      int currentColor = pixel.getChannel(oneChannel);
      if (currentColor >= maxColor) {
        maxColor = currentColor;
      }
    }
    this.updateAllColor(pixel, maxColor);
  }
}
