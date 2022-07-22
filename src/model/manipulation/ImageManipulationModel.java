package model.manipulation;

import model.image.Image;

/**
 * This interface represents the edit operation on a given image. Since this interface has only
 * method called edit, it is implemented by multiple edit image operation classes so that the edit
 * method can be overridden  in each class in detail based on the purpose of each edit image
 * operation classes.
 */
public interface ImageManipulationModel {
  /**
   * Representing two flip type: a image can be flipped vertically or horizontally.
   */
  enum Flip { Vertical, Horizontal }

  /**
   * Edit the given image and store the changed image as a new image.
   *
   * @param image the old image to be edited
   * @return a new image after edited.
   * @throws IllegalArgumentException If the given image is null.
   */
  Image edit(Image image);
}
