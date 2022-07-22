package controller.commands;

import model.ImageModel;

/**
 * A class represents a command to get the value grey scale image of the loaded image.
 */
public class ValueGreyScaleCommands extends CommandsImpl {
  //INVARIANT: oldFilename isn't null
  //INVARIANT: newfilename isn't null

  /**
   * Constructs the command to create a value grey scale image with the given newFilename as
   * its name of the original image with oldfilename as its name.
   *
   * @param oldFilename the name of original image to be used for creating a value grey scale
   *                    image
   * @param newFilename the name of a new value grey scale image created from the old image
   * @throws IllegalArgumentException if oldFilename or newFilename is null
   */
  public ValueGreyScaleCommands(String oldFilename, String newFilename) {
    super(oldFilename, newFilename);
  }

  @Override
  public void run(ImageModel model) {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null.");
    }
    model.valueGreyImage(this.oldFilename, this.newFilename);
  }
}
