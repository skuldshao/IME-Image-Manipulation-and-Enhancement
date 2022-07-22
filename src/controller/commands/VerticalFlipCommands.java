package controller.commands;

import model.ImageModel;

/**
 * A class represents a command to flip an image vertically.
 */
public class VerticalFlipCommands extends CommandsImpl {
  //INVARIANT: oldFilename isn't null
  //INVARIANT: newfilename isn't null

  /**
   * Constructs the command to create a flipped vertically image with the given newFilename as
   * its name of the original image with oldfilename as its name.
   *
   * @param oldFilename the name of original image to be used for creating a flipped vertically
   *                    image
   * @param newFilename the name of a new flipped vertically image created from the old image
   * @throws IllegalArgumentException if oldFilename or newFilename is null
   */
  public VerticalFlipCommands(String oldFilename, String newFilename) {
    super(oldFilename, newFilename);
  }

  @Override
  public void run(ImageModel model) {
    model.verticalFlip(this.oldFilename, this.newFilename);
  }
}
