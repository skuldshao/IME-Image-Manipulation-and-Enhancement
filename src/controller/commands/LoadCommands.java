package controller.commands;

import model.ImageModel;

/**
 * A class represents the command to load a new image or overwrite the existing loaded image.
 */
public class LoadCommands implements Commands {
  private String filepath;
  //INVARIANT: filepath isn't null
  private String filename;
  //INVARIANT: filename isn't null

  /**
   * Constructs the command to load a new image/overwrite the existing image from a folder
   * that has the given path.
   *
   * @param filepath the name of the folder containing the new image.
   * @param filename the name of new image referred in the program
   * @throws IllegalArgumentException if the given filename or filepath is null.
   */
  public LoadCommands(String filepath, String filename) {
    if (filename == null) {
      throw new IllegalArgumentException("The given name cannot be null");
    }
    this.filename = filename;
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
    model.load(this.filepath, this.filename);
  }
}
