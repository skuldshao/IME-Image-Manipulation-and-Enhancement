package model.manipulation.changebrigntness;

import model.ImageUtil;
import model.image.Pixel;
import model.manipulation.ManipulationModel;

/**
 * This class represents a operation to change the brigntness of a image.
 */
public class ChangeBrightness extends ManipulationModel {
  private double value; // the value to be changed
  //INVARIENT: the name isn't null

  /**
   * Constucts changeBrightness operation object to change the brighness of the image that has
   * the given name with the given value.
   *
   * @param value the brighness to be increased/decreased
   * @param name  the name of image to be edited
   * @throws IllegalArgumentException if the given name is null
   */
  public ChangeBrightness(double value, String name) {
    super(name);
    this.value = value;
  }

  protected void editHelper(Pixel pixel) {
    int newRedColor = (int) Math.round(pixel.getChannel(Pixel.Channel.Red) + this.value);
    int newGreenColor = (int) Math.round(pixel.getChannel(Pixel.Channel.Green) + this.value);
    int newBlueColor = (int) Math.round(pixel.getChannel(Pixel.Channel.Blue) + this.value);
    newRedColor = ImageUtil.clampColor(newRedColor, pixel.getMaxPixelColor());
    newGreenColor = ImageUtil.clampColor(newGreenColor, pixel.getMaxPixelColor());
    newBlueColor = ImageUtil.clampColor(newBlueColor, pixel.getMaxPixelColor());
    pixel.setChannel(Pixel.Channel.Red, newRedColor);
    pixel.setChannel(Pixel.Channel.Green, newGreenColor);
    pixel.setChannel(Pixel.Channel.Blue, newBlueColor);

  }
}
