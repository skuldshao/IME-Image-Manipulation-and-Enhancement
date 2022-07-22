package model.manipulation;

import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;

/**
 * This is the abstract class for the simple implementation of ImageManipulationModel interface.
 * This class would edit an image by going over all the pixel of the original pixel, and update
 * the pixel in the new image based on purpose of all the edit operation class that extends this
 * abstract class.
 */
public abstract class ManipulationModel implements ImageManipulationModel {
  // INVARIANT: name isn't null.
  protected String name; // the name of new image after editing

  /**
   * Constructs a ManipulationModel with the given file name.
   *
   * @param name the name of the new iamge after editing
   * @throws IllegalArgumentException if the given name is null
   */
  protected ManipulationModel(String name) {
    if (name == null) {
      throw new IllegalArgumentException("The name cannot be null");
    }
    this.name = name;
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
        this.editHelper(pixel);
        newImage.updatePixel(col, row, pixel);

      }
    }
    return newImage;
  }

  /**
   * This helper helps to update a given pixel with some operations, these operations would be
   * implemented based on the purposed edit operational class that extends this abstract class.
   *
   * @param pixel the pixel to be edited
   */
  protected abstract void editHelper(Pixel pixel);

}
