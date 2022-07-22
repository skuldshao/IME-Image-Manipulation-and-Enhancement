package controller.commands;

import model.ImageModel;

/**
 * A class represents a command to get the intensity grey scale image of the loaded image.
 */
public class IntensityGreyScaleCommands extends CommandsImpl {
  //INVARIANT: oldFilename isn't null
  //INVARIANT: newfilename isn't null

  /**
   * Constructs the command to create a intensity grey scale image with the given newFilename as
   * its name of the original image with oldfilename as its name.
   *
   * @param oldFilename the name of original image to be used for creating a intensity grey scale
   *                    image
   * @param newFilename the name of a newintensity grey scale image created from the old image
   * @throws IllegalArgumentException if oldFilename or newFilename is null
   */
  public IntensityGreyScaleCommands(String oldFilename, String newFilename) {
    super(oldFilename, newFilename);
  }

  @Override
  public void run(ImageModel model) {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null.");
    }
    model.intensityGreyImage(this.oldFilename, this.newFilename);
  }
}
