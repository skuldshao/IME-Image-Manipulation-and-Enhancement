package controller.commands;

import model.ImageModel;

/**
 * A abstract class represent a command to brighten or darken an image.
 */
public abstract class UpdateBrightnessCommands extends CommandsImpl {
  protected double value;
  //INVARIANT: oldFilename isn't null
  //INVARIANT: newfilename isn't null

  /**
   * Constructs the command to create a dorker/brigher image with the given newFilename as its name
   * of the original image with oldfilename as its name.
   *
   * @param oldFilename the name of original image to be used for creating a dorker/brigher image
   * @param newFilename the name of a new dorker/brigher  image created from the old image
   * @throws IllegalArgumentException if oldFilename or newFilename is null
   */

  protected UpdateBrightnessCommands(String oldFilename, String newFilename) {
    super(oldFilename, newFilename);
  }

  @Override
  public void run(ImageModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model is null.");
    }
    model.brightness(this.value, this.oldFilename, this.newFilename);
  }
}
