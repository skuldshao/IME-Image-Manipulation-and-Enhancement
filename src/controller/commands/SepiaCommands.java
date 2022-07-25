package controller.commands;

import model.ImageModel;

/**
 * This class represents the command to get the sepia-toned image of a given image.
 */
public class SepiaCommands extends CommandsImpl {
  //INVARIANT: oldFilename isn't null
  //INVARIANT: newfilename isn't null

  /**
   * Constructs the command to create a sepia-toned image with the given newFilename as its name
   * of the original image with oldfilename as its name.
   *
   * @param oldFilename the name of original image to be used for creating a sepia-toned image
   * @param newFilename the name of a new sepia-toned image created from the old image
   * @throws IllegalArgumentException if oldFilename or newFilename is null
   */
  public SepiaCommands(String oldFilename, String newFilename) {
    super(oldFilename, newFilename);
  }

  @Override
  public void run(ImageModel model) {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null.");
    }
    model.sepia(oldFilename, newFilename);
  }
}

