package controller.commands;

import model.ImageModel;

/**
 * A class represents the command to save a current image to the given filepath.
 */
public class SaveCommands implements Commands {
  private String filepath;
  //INVARIANT: filepath isn't null
  private String oldFilename;
  //INVARIANT: filename isn't null

  /**
   * Constructs the command to save a current image to a folder that has the given path.
   *
   * @param filepath    the name of the folder containing the new image.
   * @param oldFilename the name of image to be exported
   * @throws IllegalArgumentException if the given filepath or old file name is null.
   */
  public SaveCommands(String filepath, String oldFilename) {
    if (oldFilename == null) {
      throw new IllegalArgumentException("The given filename cannot be null");
    }
    this.oldFilename = oldFilename;
    if (filepath == null) {
      throw new IllegalArgumentException("Filename is null");
    }
    this.filepath = filepath;
  }

  @Override
  public void run(ImageModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model is null.");
    }
    model.save(this.filepath, this.oldFilename);
  }
}
