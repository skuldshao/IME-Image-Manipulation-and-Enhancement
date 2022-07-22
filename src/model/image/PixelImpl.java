package model.image;

import java.util.Objects;

/**
 * The class represents the simple implementation of a Pixel interface, the unit of a image,
 * and some operations to change/get its current states, including get the color of a channel,
 * change one channel color's to the given color and get the Max color value of a given pixel.
 * The current Pixel adopts the R-G-B model(3 channels). All the color of three channels cannot be
 * negative or greater than the max color value. The max color value cannot be negative.
 */
public class PixelImpl implements Pixel {
  private int red; // the color value for the red channel
  //INVARIANT: the red color value is greater than or equal to 0 and less or equal to the
  // max color value
  private int green; // the green value for the green channel
  //INVARIANT: the green color value is greater than or equal to 0 and less or equal to the
  // max color value
  private int blue; // the blue value for the blue channel
  //INVARIANT: the blue color value is greater than or equal to 0 and less or equal to the
  // max color value
  private int maxColorValue; // the max color for this pixel
  // INVARIANT:  max color value is not negative.

  /**
   * Constructs a pixel with the given red , blue ,green value and the max color value.
   *
   * @param red           the red color value of this pixel
   * @param green         the green color value of this pixel
   * @param blue          the blue color value of this pixel
   * @param maxColorValue the max color value of this pixel
   * @throws IllegalArgumentException if the max color value is negative
   * @throws IllegalArgumentException if the red,green and blue color value is greater than the
   *                                  max color value or less than 0.
   */
  public PixelImpl(int red, int green, int blue, int maxColorValue) {
    if (maxColorValue < 0) {
      throw new IllegalArgumentException("The max color value for this image cannot be negative");
    }
    this.maxColorValue = maxColorValue;
    if (this.isInvalidColor(red, maxColorValue) || this.isInvalidColor(green, maxColorValue)
            || this.isInvalidColor(blue, maxColorValue)) {
      throw new IllegalArgumentException("Given colors are invalid!");
    }
    this.red = red;
    this.blue = blue;
    this.green = green;


  }

  private boolean isInvalidColor(int color, int maxColorValue) {
    return color < 0 || color > maxColorValue;
  }

  @Override
  public int getChannel(Channel channel) {
    switch (channel) {
      case Blue:
        return this.blue;
      case Red:
        return this.red;
      case Green:
        return this.green;
      default:
        throw new IllegalArgumentException("This color is not part of the pixel unit.");
    }
  }

  @Override
  public void setChannel(Channel channel, int color) {
    if (isInvalidColor(color, this.maxColorValue)) {
      throw new IllegalArgumentException("The given color is invalid");
    }
    switch (channel) {
      case Blue:
        this.blue = color;
        break;
      case Red:
        this.red = color;
        break;
      case Green:
        this.green = color;
        break;
      default:
        throw new IllegalArgumentException("This color is not part of the pixel unit.");
    }
  }

  @Override
  public int getMaxPixelColor() {
    return this.maxColorValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pixel pixel = (Pixel) o;
    return red == pixel.getChannel(Channel.Red) && green == pixel.getChannel(Channel.Green) &&
            blue == pixel.getChannel(Channel.Blue) && maxColorValue == pixel.getMaxPixelColor();
  }

  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue, maxColorValue);
  }
}
