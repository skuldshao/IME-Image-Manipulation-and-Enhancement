package model.manipulation.greyscale;

import model.image.Pixel;

/**
 * This is the  class that represents the operation for producing a luma grey sale image of
 * a given image,where all the pixel red,green,blue value would be set as  the weighted sum
 * 0.2126𝑟+0.7152𝑔+0.0722𝑏
 * */
public class LumaGreyScaleImage extends GreyScaleImage {
  private final double[] transformationValue = {0.2126, 0.7152, 0.0722};
  // INVARIANT: name isn't null.

  /**
   * Constructs a lumaGreyScaleImage operation class with the given file name.
   *
   * @param name the name of the new image after editing
   * @throws IllegalArgumentException if the given name is null
   */
  public LumaGreyScaleImage(String name) {
    super(name);
  }

  @Override
  protected void editHelper(Pixel pixel) {
    int redColor = pixel.getChannel(Pixel.Channel.Red);
    int greenColor = pixel.getChannel(Pixel.Channel.Green);
    int blueColor = pixel.getChannel(Pixel.Channel.Blue);

    int finalColor = (int) Math.round(redColor * this.transformationValue[0] +
            greenColor * this.transformationValue[1] +
            blueColor * this.transformationValue[2]);
    this.updateAllColor(pixel, finalColor);
  }
}
