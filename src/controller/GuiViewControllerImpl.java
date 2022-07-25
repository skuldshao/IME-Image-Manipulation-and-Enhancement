package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import controller.commands.BlurCommands;
import controller.commands.Commands;
import controller.commands.ComponentCommands;
import controller.commands.HorizontalFlipCommands;
import controller.commands.IntensityGreyScaleCommands;
import controller.commands.LoadCommands;
import controller.commands.LumaGreyScaleCommands;
import controller.commands.SaveCommands;
import controller.commands.SepiaCommands;
import controller.commands.SharpenCommands;
import controller.commands.UpdateBrightnessCommands;
import controller.commands.ValueGreyScaleCommands;
import controller.commands.VerticalFlipCommands;
import model.ImageModel;
import model.image.Pixel;
import view.GUIView;

/**
 * This is the controller for the program under GUI mode. This controller works as the actionlister
 * to provide corresponding responses while the user click buttons on the GUi interface. It would
 * call the relevant methods in the model to edit images.The including load/overwrite an image from
 * an ASCII PPM file/JPG/PNG file, create images that visualize individual R,G,B components of an
 * image, create images that visualize the value, intensity or luma of an image,flip an image
 * horizontally or vertically,brighten or darken an image.Save an image to an ASCII PPM/PNG/jpg
 * file.
 */
public class GuiViewControllerImpl implements ImageEditorController, ActionListener {
  private ImageModel model; // the model of this image editor
  //INVARIANT: model isn't null
  private GUIView view; // the GUI view of this image editor
  //INVARIANT: view isn't null

  /**
   * Constructs an {@code GuiViewControllerImpl} object which has an associate
   * model and GUI view.
   *
   * @param model the model associated with this controller
   * @param view  the view associated with this controller
   * @throws IllegalArgumentException if any arguments are null
   */
  public GuiViewControllerImpl(ImageModel model, GUIView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Model and GuiView cannot be null.");
    }
    this.model = model;
    this.view = view;
  }

  @Override
  public void editImage() {
    this.view.setViewLister(this);
  }


  /**
   * return a map/dictionary of command pool for which this program supports to edit the passing-in
   * image.
   *
   * @return a  map/dictionary of command pool
   */
  private Map<String, Supplier<Commands>> getCommands() {
    Map<String, Supplier<Commands>> knownCommands = new HashMap<>();
    knownCommands.put("load", () -> new LoadCommands(view.getLoadFilePath(), view.preferName()));
    knownCommands.put("save", () -> new SaveCommands(view.getSaveFilePath(),
            model.getCurrentImageName()));
    knownCommands.put("red", () -> new ComponentCommands(model.getCurrentImageName(),
            view.preferName(), Pixel.Channel.Red));
    knownCommands.put("green", () -> new ComponentCommands(model.getCurrentImageName(),
            view.preferName(), Pixel.Channel.Green));
    knownCommands.put("blue", () -> new ComponentCommands(model.getCurrentImageName(),
            view.preferName(), Pixel.Channel.Blue));
    knownCommands.put("horizontal", () -> new HorizontalFlipCommands(model.getCurrentImageName(),
            view.preferName()));
    knownCommands.put("vertical", () ->
            new VerticalFlipCommands(model.getCurrentImageName(), view.preferName()));
    knownCommands.put("value", () ->
            new ValueGreyScaleCommands(model.getCurrentImageName(), view.preferName()));
    knownCommands.put("intensity", () ->
            new IntensityGreyScaleCommands(model.getCurrentImageName(), view.preferName()));
    knownCommands.put("luma", () ->
            new LumaGreyScaleCommands(model.getCurrentImageName(), view.preferName()));
    knownCommands.put("blur", () ->
            new BlurCommands(model.getCurrentImageName(), view.preferName()));
    knownCommands.put("sharpen", () ->
            new SharpenCommands(model.getCurrentImageName(), view.preferName()));
    knownCommands.put("sepia", () ->
            new SepiaCommands(model.getCurrentImageName(), view.preferName()));
    knownCommands.put("changeBrightness", () ->
            new UpdateBrightnessCommands(view.getBrightness(),
                    model.getCurrentImageName(), view.preferName()));
    return knownCommands;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String currentCommand = e.getActionCommand();
    Map<String, Supplier<Commands>> commands = getCommands();
    Supplier<Commands> command;
    // get the user requested command from the command pool
    command = commands.getOrDefault(currentCommand, null);
    try {
      // if the user requested command  is not in the command pool
      if (command == null) {
        messageHelper("Invalid Command,please retype a valid command\n");
      } else {
        Commands cmd = command.get();
        cmd.run(this.model);
        this.view.refresh();
      }
      // if the user run the image edit/save command before loading a image;
    } catch (IllegalArgumentException error) {
      messageHelper("The current Image is null, please load a image first.\n");
    }
  }


  /**
   * Help to render a specific message to the provided Appeaable object data destination in view.
   *
   * @param message the message to be transmitted.
   * @throws IllegalStateException if transmission of message the board to the provided data
   *                               destination fails
   */
  private void messageHelper(String message) {
    this.view.renderMessage(message);
  }
}


