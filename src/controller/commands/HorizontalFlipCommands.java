package controller.commands;

import model.ImageModel;

/**
 * A class represents a command to flip a image horizontally.
 */
public class HorizontalFlipCommands extends CommandsImpl {
  //INVARIANT: oldFilename isn't null
  //INVARIANT: newfilename isn't null

  /**
   * Constructs the command to create a flipped horizontally image with the given newFilename as
   * its name of the original image with oldfilename as its name.
   *
   * @param oldFilename the name of original image to be used for creating a flipped horizontally
   *                    image
   * @param newFilename the name of a new flipped horizontally image created from the old image
   * @throws IllegalArgumentException if oldFilename or newFilename is null
   */
  public HorizontalFlipCommands(String oldFilename, String newFilename) {
    super(oldFilename, newFilename);
  }

  @Override
  public void run(ImageModel model) {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null.");
    }
    model.horizontalFlip(this.oldFilename, this.newFilename);
  }
}
