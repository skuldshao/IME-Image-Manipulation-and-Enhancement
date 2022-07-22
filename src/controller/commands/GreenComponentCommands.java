package controller.commands;

import model.ImageModel;

/**
 * A class represents the command to get green-component image.
 */
public class GreenComponentCommands extends CommandsImpl {
  //INVARIANT: oldFilename isn't null
  //INVARIANT: newfilename isn't null

  /**
   * Constructs the command to create a green-component image with the given newFilename as its name
   * of the original image with oldfilename as its name.
   *
   * @param oldFilename the name of original image to be used for creating a green-component image
   * @param newFilename the name of a new green-component image created from the old image
   * @throws IllegalArgumentException if oldFilename or newFilename is null
   */
  public GreenComponentCommands(String oldFilename, String newFilename) {
    super(oldFilename, newFilename);
  }

  @Override
  public void run(ImageModel model) {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null.");
    }
    model.greenComponent(this.oldFilename, this.newFilename);
  }
}
