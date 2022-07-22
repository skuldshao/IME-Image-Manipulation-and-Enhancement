package model.manipulation.greyscale;

import model.image.Pixel;

/**
 * This is the  class that represents the operation for producing a intensity grey sale image of
 * a given image, where all the pixel red,green,blue value would be set as the average of the three
 * components in the given image.
 */
public class IntensityGreyScaleImage extends GreyScaleImage {
  // INVARIANT: name isn't null.

  /**
   * Constructs a IntensityGreyScaleImage opertion class with the given file name.
   *
   * @param name the name of the new iamge after editing
   * @throws IllegalArgumentException if the given name is null
   */
  public IntensityGreyScaleImage(String name) {
    super(name);
  }

  @Override
  protected void editHelper(Pixel pixel) {
    int numberofColor = this.channels.length;
    int sumofColor = 0;
    for (Pixel.Channel oneChannel : channels) {
      sumofColor += pixel.getChannel(oneChannel);
    }
    int avgColor = Math.round(sumofColor / numberofColor);
    this.updateAllColor(pixel, avgColor);
  }
}
