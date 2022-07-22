package controller.commands;

/**
 * This class represent a command to darken an image.
 */
public class DecreaseBrightnessCommands extends UpdateBrightnessCommands {
  //INVARIANT: oldFilename isn't null
  //INVARIANT: newfilename isn't null
  //INVARIANT: the value isn't  positive

  /**
   * Constructs a IncreaseBrightnessCommands to create a darker image of an original image with
   * the given value and the given newFilename as its name, and the original image has  oldfilename
   * as its name.
   *
   * @param value       the brightness increased on a image
   * @param oldFilename the name of original image to be used for creating a dorker image
   * @param newFilename the name of a new dorker  image created from the old image
   * @throws IllegalArgumentException if the value is negative or the value ,
   *                                  oldfilename or newfilename is null
   **/
  public DecreaseBrightnessCommands(double value, String oldFilename, String newFilename) {
    super(oldFilename, newFilename);
    if (value > 0) {
      throw new IllegalArgumentException("The value to brighten the image must be negative or " +
              "zero .");
    }
    this.value = value;
  }
}
