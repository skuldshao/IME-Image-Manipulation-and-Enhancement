package controller.commands;

import model.ImageModel;

/**
 * A class represents the command to get red-component image.
 */
public class RedComponentCommands extends CommandsImpl {
  //INVARIANT: oldFilename isn't null
  //INVARIANT: newfilename isn't null

  /**
   * Constructs the command to create a red-component image with the given newFilename as its name
   * of the original image with oldfilename as its name.
   *
   * @param oldFilename the name of original image to be used for creating a red-component image
   * @param newFilename the name of a new red-component image created from the old image
   * @throws IllegalArgumentException if oldFilename or newFilename is null
   */
  public RedComponentCommands(String oldFilename, String newFilename) {
    super(oldFilename, newFilename);
  }

  @Override
  public void run(ImageModel model) {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null.");
    }
    model.redComponent(this.oldFilename, this.newFilename);
  }
}
