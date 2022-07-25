package controller;

/**
 * This interface represents a simple controller for Image Editor.
 * While the user initializes this controller, they could edit image by passing the commands in the
 * menu shown. The commands can either be inputted in an interactive way (the user types the script
 * manually) or a file-based way (the user specifies a file that contains the script), or
 * interactive with a GUI window directly.
 */
public interface ImageEditorController {
  /**
   * Run the Image Editor controller so that the user can start to
   * edit the image with some pre-built commands. In the command the user gives is not part of
   * command pool of this program. It would tell user the current command in not valid
   * and wait for the user's next input.
   *
   * @throws IllegalStateException if the user inputs/commands cannot be read or the message cannot
   *                               be appended  to the view under the text/script mode.
   */
  void editImage();
}
