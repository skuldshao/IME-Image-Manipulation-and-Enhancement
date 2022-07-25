import org.junit.Test;

import controller.commands.BlurCommands;
import controller.commands.Commands;
import controller.commands.ComponentCommands;
import controller.commands.HorizontalFlipCommands;
import controller.commands.IntensityGreyScaleCommands;
import controller.commands.LumaGreyScaleCommands;
import controller.commands.SepiaCommands;
import controller.commands.SharpenCommands;
import controller.commands.UpdateBrightnessCommands;
import controller.commands.ValueGreyScaleCommands;
import controller.commands.VerticalFlipCommands;
import model.ImageModel;
import model.image.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for command interfaces. This test is also part of GUI controller class's
 * actionLister method. Since we could not test actionlister method directly but need to test
 * the feature methods called in the actionlistener method, we decide to test run method under
 * commands interface for different commands classes(run methods under Commands interface is
 * the only feature method called in the actionlistener method, the other methods in the GUI
 * has been tested in the MockControllerandGUIView test class).
 */
public class CommandsImplTest {
  Commands commands;
  private ImageModel model;


  @Test
  public void testComponentCommands() {
    StringBuilder log = new StringBuilder();
    model = new MockModel(log);
    assertEquals("", log.toString());
    commands = new ComponentCommands(
            "res/check-width-equal-to-height.ppm", "normal",
            Pixel.Channel.Red);
    commands.run(model);
    assertEquals("Component method is running", log.toString());
  }

  @Test
  public void testHorizontalFlipCommands() {
    StringBuilder log = new StringBuilder();
    model = new MockModel(log);
    assertEquals("", log.toString());
    commands = new HorizontalFlipCommands("", "normal");
    commands.run(model);
    assertEquals("horizontalFlip method is running", log.toString());
  }

  @Test
  public void testBlurCommands() {
    StringBuilder log = new StringBuilder();
    model = new MockModel(log);
    assertEquals("", log.toString());
    commands = new BlurCommands("", "normal");
    commands.run(model);
    assertEquals("blur method is running", log.toString());
  }

  @Test
  public void testIntensityGreyScaleCommand() {
    StringBuilder log = new StringBuilder();
    model = new MockModel(log);
    assertEquals("", log.toString());
    commands = new IntensityGreyScaleCommands("", "normal");
    commands.run(model);
    assertEquals("Intensity method is running", log.toString());
  }

  @Test
  public void testLumaGreyScaleCommand() {
    StringBuilder log = new StringBuilder();
    model = new MockModel(log);
    assertEquals("", log.toString());
    commands = new LumaGreyScaleCommands("", "normal");
    commands.run(model);
    assertEquals("lumaGreyImage method is running", log.toString());
  }

  @Test
  public void testSepiaCommands() {
    StringBuilder log = new StringBuilder();
    model = new MockModel(log);
    assertEquals("", log.toString());
    commands = new SepiaCommands("", "normal");
    commands.run(model);
    assertEquals("sepia method is running", log.toString());
  }

  @Test
  public void testSharpenCommands() {
    StringBuilder log = new StringBuilder();
    model = new MockModel(log);
    assertEquals("", log.toString());
    commands = new SharpenCommands("", "normal");
    commands.run(model);
    assertEquals("sharpen method is running", log.toString());
  }

  @Test
  public void testUpdateBrightnessCommands() {
    StringBuilder log = new StringBuilder();
    model = new MockModel(log);
    assertEquals("", log.toString());
    commands = new UpdateBrightnessCommands(10.0, "", "normal");
    commands.run(model);
    assertEquals("brightness method is running", log.toString());
  }

  @Test
  public void testValueGreyScaleCommands() {
    StringBuilder log = new StringBuilder();
    model = new MockModel(log);
    assertEquals("", log.toString());
    commands = new ValueGreyScaleCommands("", "normal");
    commands.run(model);
    assertEquals("valueGreyImage method is running", log.toString());
  }

  @Test
  public void testVertical() {
    StringBuilder log = new StringBuilder();
    model = new MockModel(log);
    assertEquals("", log.toString());
    commands = new VerticalFlipCommands("", "normal");
    commands.run(model);
    assertEquals("verticalFlip method is running", log.toString());
  }
}