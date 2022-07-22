package controller;

/**
 * This interface represents a simple controller for Image Editor under the text-script based mode
 * While the user initializes this controller, they could edit image by passing the commands in the
 * menu shown. The commands can either be inputted in an interactive way (the user types the script
 * manually) or a file-based way (the user specifies a file that contains the script).
 */
public interface TextScriptController {
  /**
   * Run the Image Editor controller under the text-script based mode so that the user can start to
   * edit the image with some pre-built commands. In the command the user gives is not part of
   * command pool of this program. It would tell user the current command in not valid
   * and wait for the user's input on the next line
   *
   * @throws IllegalStateException if the user inputs/commands cannot be read or the message cannot
   *                               be appended  to the view
   */
  void editImage() throws IllegalStateException;
}
