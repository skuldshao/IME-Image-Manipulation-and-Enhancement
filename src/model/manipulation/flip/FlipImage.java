package model.manipulation.flip;

import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.manipulation.ManipulationModel;

/**
 * This class is the operation class to flip a image vertically or horizontally.
 */
public class FlipImage extends ManipulationModel {
  private Flip flip; // the flip types
  //INVARIANT: the name isn't null

  /**
   * Constructs a new FlipImage operation object to flip a image that has the given name with the
   * given flip type.
   *
   * @param flip the flip operation for a image, either vertically or horizontally
   * @param name the name of the image to be flipped
   * @throws IllegalArgumentException if the given image name is null
   */
  public FlipImage(Flip flip, String name) {
    super(name);
    this.flip = flip;
  }

  @Override
  public Image edit(Image image) {
    if (image == null) {
      throw new IllegalArgumentException("The given image cannot be null");
    }
    int imageHeight = image.getHeight();
    int imageWidth = image.getWidth();
    int maxColor = image.getMaxColor();
    Image newImage = new ImageImpl(imageWidth, imageHeight, maxColor, this.name);
    for (int row = 0; row < imageHeight; row++) {
      for (int col = 0; col < imageWidth; col++) {
        Pixel pixel = image.getPixel(col, row);
        if (this.flip.equals(Flip.Horizontal)) {
          newImage.updatePixel(imageWidth - 1 - col, row, pixel);
        }
        if (this.flip.equals(Flip.Vertical)) {
          newImage.updatePixel(col, imageHeight - 1 - row, pixel);
        }
      }
    }
    return newImage;
  }

  // Since FlipImage class extents the Manipulation Model, then it has to implement the abstract
  // method editHelper in its class. However, since the edit main method can handle all the
  // necessary operation, this method here is a placeholder
  @Override
  protected void editHelper(Pixel pixel) {
    return;
  }
}