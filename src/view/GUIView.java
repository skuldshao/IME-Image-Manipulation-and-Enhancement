package view;

import java.awt.event.ActionListener;

/**
 * This interface represents operations that should be offered by a view for the image editor
 * under the GUi. This includes refresh the screen(reset the panels and redraw the necessaru
 * images), set a lister to the GUI, returning the filepath for load and save command in the
 * controller, based on the user's choice, setting the preferred name for new current image
 * after the user does some, image processing operations, and getting the brightness the user
 * put into the text box.
 */
public interface GUIView {
  /**
   * Refresh the screen. This is called when the something on the
   * screen is updated and therefore it must be redrawn.
   */
  void refresh();

  /**
   * Set as the given listener as the lister of the view.
   *
   * @param listener the listener for the GUI window.
   */
  void setViewLister(ActionListener listener);

  /**
   * Get the file path of an image which the user trys to load into the program.
   *
   * @return Get the file path of an image which the user trys to load into the program.
   */
  String getLoadFilePath();

  /**
   * Get the file path where the user trys to save for the current image.
   *
   * @return Get the file path where the user trys to save for the current image.
   */
  String getSaveFilePath();

  /**
   * Get the preferred name for new current image after the user does some, image
   * processing operations.
   *
   * @return the preferred name for the new current image;
   */
  String preferName();

  /**
   * Get the brightness the user tried to update for the current image.
   *
   * @return the brightness the user tried to update for the current image.
   */
  double getBrightness();

  /**
   * Display a message in a suitable area of the GUI.
   *
   * @param message the message to be displayed.
   */
  void renderMessage(String message);
}
