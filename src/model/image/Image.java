package model.image;

/**
 * This interface represents an image and some operations to get and change its current state,
 * including, get the height/width of an image, get a pixel(the unit of a image) at a given
 * position, get the max color value of an image and change a pixel at the given position with the
 * given pixel, get the name of an image referred in the program.
 */
public interface Image {

  /**
   * Get the height of an image.
   *
   * @return the height of an image.
   */
  int getHeight();

  /**
   * Get the width of an image.
   *
   * @return the width of in image.
   */
  int getWidth();

  /**
   * Get the deep copy of the Pixel at the given x and y coordinate.
   *
   * @param x the x coordinate of required position.
   * @param y the y coordinate of a required position
   * @return a Pixel at the given x and y coordinate.
   * @throws IllegalArgumentException If the x or y coordiniate is out of the bound of the given
   *                                  photo
   */
  Pixel getPixel(int x, int y);

  /**
   * Get the max color value of an image.
   *
   * @return the max color value of an image
   */
  int getMaxColor();

  /**
   * Set the Pixel at the given x and y coordinate to the given Pixel.
   *
   * @param x     the x coordinate of the pixel to be updated.
   * @param y     the y coordinate of the pixel to be updated.
   * @param pixel the pixel used to update for the original Pixel
   * @throws IllegalArgumentException If the x or y coordiniate is out of the bound of the given
   *                                  photo
   */
  void updatePixel(int x, int y, Pixel pixel);

  /**
   * Get the name of an image referred in the program.
   *
   * @return the name of an image referred in the program
   */
  String getName();
}
