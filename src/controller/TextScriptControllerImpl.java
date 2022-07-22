package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.BlueComponentCommands;
import controller.commands.Commands;
import controller.commands.DecreaseBrightnessCommands;
import controller.commands.GreenComponentCommands;
import controller.commands.HorizontalFlipCommands;
import controller.commands.IncreaseBrightnessCommands;
import controller.commands.IntensityGreyScaleCommands;
import controller.commands.LoadCommands;
import controller.commands.LumaGreyScaleCommands;
import controller.commands.RedComponentCommands;
import controller.commands.SaveCommands;
import controller.commands.ValueGreyScaleCommands;
import controller.commands.VerticalFlipCommands;
import model.ImageModel;
import view.ImageTextScriptView;
import view.TextScriptView;

/**
 * This class represents implementation of a simple controller for Image Editor under the
 * text-script based mode While the user initializes this controller, they could edit image
 * by passing the commands in the menu shown. The commands can either be inputted in an interactive
 * way (the user types the script manually) or a file-based way (the user specifies a file that
 * contains the script).The including load/overwrite an image from an ASCII PPM file, create images
 * that visualize individual R,G,B components of an image, create images that visualize the value,
 * intensity or luma of an image,flip an image horizontally or vertically,brighten or darken an
 * image.Save an image to an ASCII PPM file.
 */
public class TextScriptControllerImpl implements TextScriptController {
  private Readable read; // the inputs of the user
  //INVARIANT: readable isn't null
  private ImageModel model; // the model of this image editor
  //INVARIANT: model isn't null
  private TextScriptView view; // the view of this image editor
  //INVARIANT: view isn't null
  //INVARIANT: appendable isn't null

  /**
   * Construct a TextScriptControllerImpl object that would use the given model to process the image
   * and the view to render the interactive message from the program.
   *
   * @param model the model to process image
   * @param read  the user inputs
   * @param ap    the appended object for the view to render the message
   * @throws IllegalArgumentException if the given model,readable object or appended object is null
   */
  public TextScriptControllerImpl(ImageModel model, Readable read, Appendable ap) {
    if (model == null || ap == null || read == null) {
      throw new IllegalArgumentException("The model, view or the readable object cannot be null.");
    }
    this.read = read;
    this.model = model;
    this.view = new ImageTextScriptView(ap);
  }

  /**
   * Constructs a TextScriptControllerImpl object with the given model to process the image
   * and the view to render the interactive message from the program, as well as a file
   * with a given script.
   *
   * @param model the IImage model that the user will use to process images with
   * @param file  represents the file to be read from
   * @param ap    the appended object for the view to render the message
   * @throws IllegalArgumentException if the given model,file or appended object is null
   *                                  or if the file is not found
   */
  public TextScriptControllerImpl(ImageModel model, File file, Appendable ap)
          throws IllegalArgumentException {
    if (model == null || file == null || ap == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }

    this.model = model;
    this.view = new ImageTextScriptView(ap);
    try {
      this.read = new FileReader(file);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found!");
    }
  }

  @Override
  public void editImage() throws IllegalStateException {
    Scanner scan = new Scanner(this.read);
    // initialize all the commands
    Map<String, Function<Scanner, Commands>> commands = getCommands();
    Function<Scanner, Commands> command;
    boolean hasQuit = false;

    // render the menu
    messageHelper("Welcome to the Text-Script Based Image Editor\n");
    menu();

    // check if the program has been quitted
    while (!hasQuit) {
      //check if the user put the next input
      while (scan.hasNext()) {
        String next = scan.next();

        // check if the current command is to quit the program
        if (next.equalsIgnoreCase("q") || next.equalsIgnoreCase("quit")) {
          hasQuit = true;
          messageHelper("Thank you for using this editor.\n");
          return;
        }

        // get the user requested command from the command pool
        command = commands.getOrDefault(next, null);
        try {
          // if the user requested command  is not in the command pool
          if (command == null) {
            messageHelper("Invalid Command,please retype a valid command\n");
          } else {
            Commands cmd = command.apply(scan);
            cmd.run(this.model);
            messageHelper(next + " Command has been executed successfully.\n");
          }
        } catch (IllegalArgumentException e) {
          messageHelper("The Command you put is invalid. please retype a valid command\n");
        }
      }
      // check if the program is continuing running but there is no more input from the user-
      // run out of inputs issue
      if (!scan.hasNext()) {
        throw new IllegalStateException("No more input to continue the program\n");
      }
    }
  }

  /**
   * return a map/dictionary of command pool for which this program supports to edit the passing-in
   * image.
   *
   * @return a  map/dictionary of command pool
   */
  private Map<String, Function<Scanner, Commands>> getCommands() {
    Map<String, Function<Scanner, Commands>> knownCommands = new HashMap<>();
    knownCommands.put("RedComponent", (Scanner s) -> new RedComponentCommands(
            s.next(), s.next()));
    knownCommands.put("GreenComponent", (Scanner s) -> new GreenComponentCommands(
            s.next(), s.next()));
    knownCommands.put("BlueComponent", (Scanner s) -> new BlueComponentCommands(
            s.next(), s.next()));
    knownCommands.put("IntensityGreyScaleImage", (Scanner s) -> new IntensityGreyScaleCommands(
            s.next(), s.next()));
    knownCommands.put("ValueGreyScaleImage", (Scanner s) -> new ValueGreyScaleCommands(
            s.next(), s.next()));
    knownCommands.put("LumaGreyScaleImage", (Scanner s) -> new LumaGreyScaleCommands(
            s.next(), s.next()));
    knownCommands.put("Flip-horizontally", (Scanner s) -> new HorizontalFlipCommands(
            s.next(), s.next()));
    knownCommands.put("Flip-vertically", (Scanner s) -> new VerticalFlipCommands(
            s.next(), s.next()));
    knownCommands.put("Brighten", (Scanner s) -> new IncreaseBrightnessCommands(
            s.nextDouble(), s.next(), s.next()));
    knownCommands.put("Darken", (Scanner s) -> new DecreaseBrightnessCommands(
            s.nextDouble(), s.next(), s.next()));
    knownCommands.put("Load", (Scanner s) -> new LoadCommands(
            s.next(), s.next()));
    knownCommands.put("Save", (Scanner s) -> new SaveCommands(
            s.next(), s.next()));
    return knownCommands;
  }

  /**
   * produce a menu message to the user about all the commands this program supports to edit the
   * passing in image.
   *
   * @throws IllegalStateException if transmission of message the board to the provided data
   *                               destination fails
   */
  private void menu() {
    messageHelper("Please type one of following command to edit images\n" +
            "To overwrite the loaded image/load a new image: " +
            "\'Load image-path image-name\' \n" +
            "To get the red-component image of the loaded image:" +
            " \'RedComponent image-name dest-image-name\' \n" +
            "To get the green-component image of the loaded image: " +
            "\'GreenComponent image-name dest-image-name\' \n" +
            "To get the blue-component image of the loaded image: " +
            "\'BlueComponent image-name dest-image-name\' \n" +
            "To get the value grayscale image of the loaded image:" +
            "\'ValueGreyScaleImage image-name dest-image-name\' \n" +
            "To get the intensity grayscale image of the loaded " +
            "image:\'IntensityGreyScaleImage image-name dest-image-name\' \n" +
            "To get the luma grayscale image of the loaded image:" +
            "\'LumaGreyScaleImage image-name dest-image-name\' \n" +
            "To get the flipped horizontally image of the loaded image: " +
            "\'Flip-horizontally image-name dest-image-name\' \n" +
            "To get the flipped vertically image of the loaded image:" +
            " \'Flip-vertically image-name dest-image-name\' \n" +
            "To brighten the loaded image: " +
            "\'Brighten increment image-name dest-image-name\' \n" +
            "To darken the loaded image:" +
            " \'Darken increment image-name dest-image-name\' \n" +
            "To save the loaded image: " +
            "\'Save image-path image-name\' \n" +
            "Type quit or q to exit this program \n");
  }

  /**
   * Help to render a specific message to the provided Appeaable object data destination in view.
   *
   * @param message the message to be transmitted.
   * @throws IllegalStateException if transmission of message the board to the provided data
   *                               destination fails
   */
  private void messageHelper(String message) {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Cannot read the inputs from the readable object or fail "
              + "to produce the Appendable view object!");
    }
  }
}
