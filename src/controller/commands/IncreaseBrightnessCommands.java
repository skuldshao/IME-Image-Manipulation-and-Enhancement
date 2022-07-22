package controller.commands;

/**
 * This class represent a command to brightne an image.
 */
public class IncreaseBrightnessCommands extends UpdateBrightnessCommands {
  //INVARIANT: oldFilename isn't null
  //INVARIANT: newfilename isn't null
  //INVARIANT: the value isn't  negative

  /**
   * Constructs a IncreaseBrightnessCommands to create a brighter image of an original image with
   * the given value and the given newFilename as its name, and the original image has  oldfilename
   * as its name.
   *
   * @param value       the brightness increased on a image
   * @param oldFilename the name of original image to be used for creating a brigher image
   * @param newFilename the name of a new brigher  image created from the old image
   * @throws IllegalArgumentException if the value is negative or the value ,
   *                                  oldfilename or newfilename is null
   */
  public IncreaseBrightnessCommands(double value, String oldFilename, String newFilename) {
    super(oldFilename, newFilename);
    if (value < 0) {
      throw new IllegalArgumentException("The value to brighten the image must be positiveor " +
              "zero .");
    }
    this.value = value;
  }
}
