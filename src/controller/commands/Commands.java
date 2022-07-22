package controller.commands;

import model.ImageModel;

/**
 * An interface allows the model to perform a command. These commands would inform the controller
 * to tell the model what to do.
 */
public interface Commands {
  /**
   * Uses the given model to perform the class' command as a method to talk with the model.
   *
   * @param model the model of the image-processing
   * @throws IllegalArgumentException if the given model is null or if any parameters produce
   *                                  invalid results like a invalid filepath
   */
  void run(ImageModel model);
}
