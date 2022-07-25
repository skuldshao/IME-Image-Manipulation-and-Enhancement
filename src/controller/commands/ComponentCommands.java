package controller.commands;

import model.ImageModel;
import model.image.Pixel;

/**
 * A class represents the command to call the get component method in model
 * in drawing red/green/blue/component image.
 */
public class ComponentCommands extends CommandsImpl {
  private Pixel.Channel channel;
  //INVARIANT: oldFilename isn't null
  //INVARIANT: newfilename isn't null

  /**
   * Constructs the command object to call draw a red/blue/green component image method.
   *
   * @param oldFilename the name of original image to be used for creating a component image
   * @param newFilename the name of a new component image created from the old image
   * @param channel     the component grey-scale the user would like to get
   * @throws IllegalArgumentException if oldFilename or newFilename is null
   */
  public ComponentCommands(String oldFilename, String newFilename, Pixel.Channel channel) {
    super(oldFilename, newFilename);
    this.channel = channel;
  }

  @Override
  public void run(ImageModel model) {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null.");
    }
    model.component(oldFilename, newFilename, channel);
  }
}
