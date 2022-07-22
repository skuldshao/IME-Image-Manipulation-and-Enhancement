package model.image;

/**
 * The interface represents a Pixel, the unit of a image, and some operations to change/get its
 * current states, including get the color of a channel, change one channel color's to the given
 * color and get the Max color value of a given pixel. The current Pixel adopts the
 * R-G-B model(3 channels).
 */
public interface Pixel {
  /** represents red/green/blue channel of a Pixel.
   */
  enum Channel { Red, Green, Blue }

  /**
   * Get a color at the given channel.
   *
   * @param channel the channel of a Pixel required to get the color
   * @return a color at the given channel
   * @throws IllegalArgumentException if the Channel is not part of R-G-B model
   */
  int getChannel(Channel channel);

  /**
   * Set the given channel's color to the given color.
   *
   * @param channel the channel to be changed color
   * @param color   the new color for the given channel
   * @throws IllegalArgumentException if the Channel is not part of R-G-B model
   * @throws IllegalArgumentException If the given color is greater than the pixel's maxcolor or
   *                                  less than 0.
   */

  void setChannel(Channel channel, int color);

  /**
   * Get the max color value of this Pixel.
   *
   * @return the max color value of this Pixel
   */

  int getMaxPixelColor();

}
