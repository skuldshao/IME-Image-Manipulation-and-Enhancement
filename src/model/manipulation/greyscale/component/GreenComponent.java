package model.manipulation.greyscale.component;

import model.image.Pixel;

/**
 * This class represent an implementation which could produce a green-component image of the
 * passing-in image.
 */
public class GreenComponent extends ComponentImage {
  // INVARIANT: name isn't null.

  /**
   * Constructs a GreenComponent operation class with the given file name.
   * The green channel would decide the green channel's color value would be used to set other two
   * channels: red and blue.
   *
   * @param name the image of new component image
   * @throws IllegalArgumentException if the given name is null
   */
  public GreenComponent(Pixel.Channel currentChannel, String name) {
    super(currentChannel, name);
  }
}
