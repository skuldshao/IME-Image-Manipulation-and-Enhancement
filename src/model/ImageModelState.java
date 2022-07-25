package model;

import java.util.List;

import model.image.Image;

/**
 * This is a read-only model state interface where the user can quickly check the current image the
 * program is working on and all the images they loaded into the program. This interface would
 * also provide necessary information for GUI view part to get the deep copy of the current image
 * so that it can render all this edited image in front of the user.
 */
public interface ImageModelState {

  /**
   * Get a deep copy of current image the user/program is working on.
   *
   * @return a deep copy the current image the user/program is working on
   */
  Image getImage();

  /**
   * Get  a deep copy all the loaded image the user put into the program.
   *
   * @return a deep copy all the loaded image the user put into the program
   */
  List<Image> getAllLoadedImage();

  /**
   * Get the name of the current image the program is editing.
   *
   * @return name of the current image the program is editing
   * @throws IllegalArgumentException if the current image is null
   */
  String getCurrentImageName();
}
