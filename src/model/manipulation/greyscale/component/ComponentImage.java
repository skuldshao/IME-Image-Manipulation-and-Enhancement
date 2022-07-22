package model.manipulation.greyscale.component;

import model.image.Pixel;
import model.manipulation.greyscale.GreyScaleImage;

/**
 * This is a abstract class for product a component image of a given image.
 */
abstract public class ComponentImage extends GreyScaleImage {
  protected Pixel.Channel currentChannel;
  // INVARIANT: name isn't null.

  /**
   * Constructs a ComponentImage operation class with the given current channel and given file name.
   * The current channel would decide which channel's color value would be used to set other two
   * channels.
   *
   * @param currentChannel the current channel would decide which channel's color value would be
   *                       used to set other two channels.
   * @param name           the image of new component image
   * @throws IllegalArgumentException if the given name is null
   */
  protected ComponentImage(Pixel.Channel currentChannel, String name) {
    super(name);
    this.currentChannel = currentChannel;
  }

  @Override
  protected void editHelper(Pixel pixel) {
    int currentColor = pixel.getChannel(this.currentChannel);
    this.updateAllColor(pixel, currentColor);
  }
}

