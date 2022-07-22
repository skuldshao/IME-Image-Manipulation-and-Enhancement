package model.manipulation.greyscale.component;

import model.image.Pixel;

/**
 * This class represent an implementation which could produce a blue-component image of the
 * passing-in image.
 */
public class BlueComponent extends ComponentImage {
  // INVARIANT: name isn't null.

  /**
   * Constructs a BlueComponent operation class with the given file name.
   * The blue channel would decide the blue channel's color value would be used to set other two
   * channels: red and green.
   *
   * @param name the image of new component image
   * @throws IllegalArgumentException if the given name is null
   */
  public BlueComponent(Pixel.Channel currentChannel, String name) {
    super(currentChannel, name);
  }
}
