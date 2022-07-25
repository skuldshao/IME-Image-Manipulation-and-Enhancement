package controller.commands;

import model.ImageModel;

/**
 * This class represents the command to blur a given image.
 */
public class BlurCommands extends CommandsImpl {
  //INVARIANT: oldFilename isn't null
  //INVARIANT: newfilename isn't null

  /**
   * Constructs the command to create a blurred image with the given newFilename as its name
   * of the original image with oldfilename as its name.
   *
   * @param oldFilename the name of original image to be used for creating a blurred image
   * @param newFilename the name of a new blurred image created from the old image
   * @throws IllegalArgumentException if oldFilename or newFilename is null
   */
  public BlurCommands(String oldFilename, String newFilename) {
    super(oldFilename, newFilename);
  }

  @Override
  public void run(ImageModel model) {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null.");
    }
    model.blur(oldFilename, newFilename);
  }
}
