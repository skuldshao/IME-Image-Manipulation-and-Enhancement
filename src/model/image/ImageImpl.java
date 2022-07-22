package model.image;

/**
 * This class represents the simple implementation of an image and some operations to get and change
 * its current state, including, get the height/width of an image, get a pixel(the unit of a image)
 * at a given position, get the max color value of an image and change a pixel at the given position
 * with the given pixel, get the name of an image referred in the program.
 */
public class ImageImpl implements Image {
  private int height; // height of this image
  //INVARIANT: height isn't zero or negative
  private int width; // weight of this image
  //INVARIANT: height isn't zero or negative
  private int maxColorValue; // max color value of this image
  //INVARIANT: height isn't negative
  private Pixel[][] contents; // the contents of this image, represented by 2D-array of pixel
  private String name; // the name of this image referred in the program
  //INVARIANT: name isn't null.

  /**
   * Constructor a new Image with the given height, width and maxcolor value, and a name as its
   * elements. This constructor initializes all pixel's color as black. All the pixels can be
   * updated later.
   *
   * @param width         the width of the image
   * @param height        the height of the image
   * @param maxColorValue the max color value of this image
   * @param name          the name of this image referred in the program
   * @throws IllegalArgumentException if given width and height are zero or negative
   * @throws IllegalArgumentException If the given max color value is negative
   * @throws IllegalArgumentException IF the given name is null;
   */
  public ImageImpl(int width, int height, int maxColorValue, String name) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid width and height.");
    }
    this.height = height;
    this.width = width;
    if (maxColorValue < 0) {
      throw new IllegalArgumentException("The max color value for this image cannot be negative");
    }
    this.maxColorValue = maxColorValue;
    if (name == null) {
      throw new IllegalArgumentException("The name cannot be null");
    }
    this.name = name;
    contents = new PixelImpl[height][width];
    // initialize all the pixel with black color, all pixel color can be updated later
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        contents[row][col] = new PixelImpl(0, 0, 0, this.maxColorValue);
      }
    }
  }

  /**
   * Constructor a new Image with the given height, width and maxcolor value, and a name as its
   * elements. This constructor initializes all pixel's color as black. All the pixels can be
   * updated later.
   *
   * @param width         the width of the image
   * @param height        the height of the image
   * @param maxColorValue the max color value of this image
   * @param name          the name of this image referred in the program
   * @param contents      the pixel contents of this image
   * @throws IllegalArgumentException if given width and height are zero or negative
   * @throws IllegalArgumentException If the given max color value is negative
   * @throws IllegalArgumentException IF the given name is null;
   * @throws IllegalArgumentException IF the given contents is null;
   */
  public ImageImpl(int width, int height, int maxColorValue, String name, Pixel[][] contents) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid width and height.");
    }
    this.height = height;
    this.width = width;
    if (maxColorValue < 0) {
      throw new IllegalArgumentException("The max color value for this image cannot be negative");
    }
    this.maxColorValue = maxColorValue;
    if (name == null) {
      throw new IllegalArgumentException("The name cannot be null");
    }
    this.name = name;
    if (contents == null) {
      throw new IllegalArgumentException("All the pixels cannot be null");
    }
    this.contents = contents;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public Pixel getPixel(int x, int y) {
    if (x < 0 || x > this.width || y < 0 || y > this.height) {
      throw new IllegalArgumentException("The x or y coordinate is out of bound.");
    }
    Pixel pixel = contents[y][x];
    return new PixelImpl(pixel.getChannel(Pixel.Channel.Red),
            pixel.getChannel(Pixel.Channel.Green),
            pixel.getChannel(Pixel.Channel.Blue), maxColorValue);
  }

  @Override
  public int getMaxColor() {
    return this.maxColorValue;
  }

  @Override
  public void updatePixel(int x, int y, Pixel pixel) {
    if (pixel == null) {
      throw new IllegalArgumentException("This pixel cannot be null");
    }
    if (x < 0 || x > this.width || y < 0 || y > this.height) {
      throw new IllegalArgumentException("The x or y coordinate is out of bound.");
    }
    contents[y][x] = pixel;
  }

  @Override
  public String getName() {
    return this.name;
  }

}
