package model.filter;

import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;
import utils.ImageUtil;

/**
 * This is the an class for a simple implement of a filter operation. This filter operation
 * is made by the filterMatrix field in the given class. The class places the center of the kernel
 * at the particular pixel  and then  aligns each number in the kernel with a corresponding number
 * in that channel.The result of the filter is calculated by multiplying together corresponding
 * numbers in the kernel and the pixels and adding them. If some portions of the kernel do not
 * overlap any pixels, those pixels are not included in the computation.
 */
public class FilterImpl implements Filter {
  protected final double[][] filterMatrix; // the filter maxtrix used for filter
  protected String name; // the name of the new image(the one after filtering)
  // INVARIANT: filterMatrix must be a matrix with odd dimensions
  // INVARIANT: filterMatrix  cannot be null
  // INVARIANT: name cannot be null

  /**
   * Constructs a given FilterImpl object with the given filtermatrix to filter an image and
   * a given filename to name the new image after filtering.
   *
   * @param filterMatrix the given filtermatrix to filter a image
   * @param name         the name of a new image after imaging
   * @throws IllegalArgumentException if the given filter matrix and name is null
   * @throws IllegalArgumentException if the given filter matrix doesn't have odd dimensions
   */
  public FilterImpl(double[][] filterMatrix, String name) {
    if (filterMatrix == null || filterMatrix.length % 2 != 1 || filterMatrix[0].length % 2 != 1) {
      throw new IllegalArgumentException("Invalid filter matrix");
    }
    this.filterMatrix = filterMatrix;
    if (name == null) {
      throw new IllegalArgumentException("The given name cannot be null");
    }
    this.name = name;
  }


  @Override
  public Image applyFilter(Image image) {
    if (image == null) {
      throw new IllegalArgumentException("The given image cannot be null");
    }
    int imageHeight = image.getHeight();
    int imageWidth = image.getWidth();
    int maxColor = image.getMaxColor();
    Image newImage = new ImageImpl(imageWidth, imageHeight, maxColor, this.name);
    for (int row = 0; row < imageHeight; row++) {
      for (int col = 0; col < imageWidth; col++) {
        newImage.updatePixel(col, row, applyFilterMatrix(image, col, row));
      }
    }
    return newImage;
  }

  /**
   * return a new pixel at the given x and y position after filtering with the filter matrix.
   * @param image the original image before filter
   * @param pixelx the x position of the pixel to be filtered
   * @param pixely the y position of the pixel to be filtered
   * @return a new pixel at the given x and y position after filtering with the filter matrix
   */
  protected Pixel applyFilterMatrix(Image image, int pixelx, int pixely) {
    int newRed = 0;
    int newGreen = 0;
    int newBlue = 0;
    for (int x = 0; x < filterMatrix.length; x++) {
      // compute the x position of the pixel overlapped at the current matrix position in the
      // original image
      int xPosition = (pixelx + x) - ((filterMatrix.length - 1) / 2);
      // check if the x position is out of the boundary to the original image
      if (xPosition >= 0 && xPosition < image.getWidth()) {
        for (int y = 0; y < filterMatrix[0].length; y++) {
          // compute the y position of the pixel overlapped at the current matrix position in the
          // original image
          int yPosition = (pixely + y) - ((filterMatrix[0].length - 1) / 2);
          // check if the y position is out of the boundary to the original image
          if (yPosition >= 0 && yPosition < image.getHeight()) {
            // get the pixel at the given x ,y position from the original image
            Pixel pixel = image.getPixel(xPosition, yPosition);
            // update the new red,green,blue value for the pixel at the kennel position
            newRed += pixel.getChannel(Pixel.Channel.Red) * filterMatrix[x][y];
            newGreen += pixel.getChannel(Pixel.Channel.Green) * filterMatrix[x][y];
            newBlue += pixel.getChannel(Pixel.Channel.Blue) * filterMatrix[x][y];
          }
        }
      }
    }
    // clamp to new value for three channels in the max color range
    newRed = ImageUtil.clampColor(newRed, image.getMaxColor());
    newGreen = ImageUtil.clampColor(newGreen, image.getMaxColor());
    newBlue = ImageUtil.clampColor(newBlue, image.getMaxColor());
    return new PixelImpl(newRed, newGreen, newBlue, image.getMaxColor());
  }
}
