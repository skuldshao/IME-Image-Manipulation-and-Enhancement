package controller.commands;

import model.ImageModel;

/**
 * A class represents the command to get blue-component image.
 */
public class BlueComponentCommands extends CommandsImpl {
  //INVARIANT: oldFilename isn't null
  //INVARIANT: newfilename isn't null

  /**
   * Constructs the command to create a blue-component image with the given newFilename as its name
   * of the original image with oldfilename as its name.
   *
   * @param oldFilename the name of original image to be used for creating a blue-component image
   * @param newFilename the name of a new blue-component image created from the old image
   * @throws IllegalArgumentException if oldFilename or newFilename is null
   */
  public BlueComponentCommands(String oldFilename, String newFilename) {
    super(oldFilename, newFilename);
  }

  @Override
  public void run(ImageModel model) {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null.");
    }
    model.blueComponent(this.oldFilename, this.newFilename);
  }
}
