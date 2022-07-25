package controller.commands;

import model.ImageModel;

/**
 * This class represents the command to sharpen a given image.
 */
public class SharpenCommands extends CommandsImpl {
  //INVARIANT: oldFilename isn't null
  //INVARIANT: newfilename isn't null

  /**
   * Constructs the command to create a sharpened image with the given newFilename as its name
   * of the original image with oldfilename as its name.
   *
   * @param oldFilename the name of original image to be used for creating a sharpened image
   * @param newFilename the name of a new sharpened image created from the old image
   * @throws IllegalArgumentException if oldFilename or newFilename is null
   */
  public SharpenCommands(String oldFilename, String newFilename) {
    super(oldFilename, newFilename);
  }

  @Override
  public void run(ImageModel model) {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null.");
    }
    model.sharpen(oldFilename, newFilename);
  }
}

