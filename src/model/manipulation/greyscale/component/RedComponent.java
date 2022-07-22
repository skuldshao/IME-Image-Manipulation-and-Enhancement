package model.manipulation.greyscale.component;

import model.image.Pixel;

/**
 * This class represent an implementation which could produce a red-component image of the
 * passing-in image.
 */
public class RedComponent extends ComponentImage {
  // INVARIANT: name isn't null.

  /**
   * Constructs a RedComponent operation class with the given file name.
   * The red channel would decide the green channel's color value would be used to set other two
   * channels: green and blue.
   *
   * @param name the image of new component image
   * @throws IllegalArgumentException if the given name is null
   */
  public RedComponent(Pixel.Channel currentChannel, String name) {
    super(currentChannel, name);
  }
}