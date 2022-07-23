import org.junit.Test;
import org.junit.Before;

import java.io.File;
import java.io.StringReader;

import controller.TextScriptController;
import controller.TextScriptControllerImpl;
import model.ImageModel;
import model.ImageModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This the test class for the controller.
 */
public class TextScriptControllerImplTest {

  private Appendable a1;
  private ImageModel model;


  @Before
  public void setup() {
    a1 = new StringBuilder();
    model = new ImageModelImpl();
    model.load("res/check-width-equal-to-height.ppm", "normal");
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalNullModel() {
    Readable r1 = new StringReader("asdf quit");
    TextScriptController controller = new TextScriptControllerImpl(null, r1, a1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalNullReader() {
    Readable r1 = null;
    TextScriptController controller = new TextScriptControllerImpl(model, r1, a1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalNullAppendable() {
    Readable r1 = new StringReader("asdf quit");
    Appendable a2 = null;
    TextScriptController controller = new TextScriptControllerImpl(model, r1, a2);
  }


  @Test(expected = IllegalArgumentException.class)
  public void fileControllerNoFile() {
    File file = new File("res/testCommandFileasdf.txt");
    TextScriptController controller = new TextScriptControllerImpl(model, file, a1);
    controller.editImage();
  }

  @Test(expected = IllegalArgumentException.class)
  public void fileControllerNullFile() {
    File file = null;
    TextScriptController controller = new TextScriptControllerImpl(model, file, a1);
    controller.editImage();
  }

  @Test(expected = IllegalArgumentException.class)
  public void fileControllerNullModel() {
    File file = new File("res/testCommandFile.txt");
    TextScriptController controller = new TextScriptControllerImpl(null, file, a1);
    controller.editImage();
  }

  @Test(expected = IllegalArgumentException.class)
  public void fileControllerNullAppendable() {
    File file = new File("res/testCommandFile.txt");
    Appendable a2 = null;
    TextScriptController controller = new TextScriptControllerImpl(model, file, a2);
    controller.editImage();
  }

  @Test
  public void fileController() {
    File file = new File("res/testCommandFile.txt");
    TextScriptController controller = new TextScriptControllerImpl(model, file, a1);
    controller.editImage();
    assertEquals("Welcome to the Text-Script Based Image Editor\n" +
            "Please type one of following command to edit images\n" +
            "To overwrite the loaded image/load a new image: " +
            "'Load image-path image-name' \n" +
            "To get the red-component image of the loaded image:" +
            " 'RedComponent image-name dest-image-name' \n" +
            "To get the green-component image of the loaded image:" +
            " 'GreenComponent image-name dest-image-name' \n" +
            "To get the blue-component image of the loaded image:" +
            " 'BlueComponent image-name dest-image-name' \n" +
            "To get the value grayscale image of the loaded image:" +
            "'ValueGreyScaleImage image-name dest-image-name' \n" +
            "To get the intensity grayscale image of the loaded image:" +
            "'IntensityGreyScaleImage image-name dest-image-name' \n" +
            "To get the luma grayscale image of the loaded image:" +
            "'LumaGreyScaleImage image-name dest-image-name' \n" +
            "To get the flipped horizontally image of the loaded image:" +
            " 'Flip-horizontally image-name dest-image-name' \n" +
            "To get the flipped vertically image of the loaded image:" +
            " 'Flip-vertically image-name dest-image-name' \n" +
            "To brighten the loaded image: 'Brighten increment image-name dest-image-name' \n" +
            "To darken the loaded image: 'Darken increment image-name dest-image-name' \n" +
            "To save the loaded image: 'Save image-path image-name' \n" +
            "Type quit or q to exit this program \n" +
            "Brighten Command has been executed successfully.\n" +
            "Thank you for using this editor.\n", a1.toString());
  }

  //illegal commands
  @Test
  public void testIllegalCommand() {
    Readable r1 = new StringReader("asdf quit");
    TextScriptController controller = new TextScriptControllerImpl(model, r1, a1);
    controller.editImage();
    assertEquals("Welcome to the Text-Script Based Image Editor\n" +
            "Please type one of following command to edit images\n" +
            "To overwrite the loaded image/load a new image: " +
            "'Load image-path image-name' \n" +
            "To get the red-component image of the loaded image:" +
            " 'RedComponent image-name dest-image-name' \n" +
            "To get the green-component image of the loaded image:" +
            " 'GreenComponent image-name dest-image-name' \n" +
            "To get the blue-component image of the loaded image:" +
            " 'BlueComponent image-name dest-image-name' \n" +
            "To get the value grayscale image of the loaded image:" +
            "'ValueGreyScaleImage image-name dest-image-name' \n" +
            "To get the intensity grayscale image of the loaded image:" +
            "'IntensityGreyScaleImage image-name dest-image-name' \n" +
            "To get the luma grayscale image of the loaded image:" +
            "'LumaGreyScaleImage image-name dest-image-name' \n" +
            "To get the flipped horizontally image of the loaded image:" +
            " 'Flip-horizontally image-name dest-image-name' \n" +
            "To get the flipped vertically image of the loaded image:" +
            " 'Flip-vertically image-name dest-image-name' \n" +
            "To brighten the loaded image: 'Brighten increment image-name dest-image-name' \n" +
            "To darken the loaded image: 'Darken increment image-name dest-image-name' \n" +
            "To save the loaded image: 'Save image-path image-name' \n" +
            "Type quit or q to exit this program \n" +
            "Invalid Command,please retype a valid command\n" +
            "Thank you for using this editor.\n", a1.toString());
  }

  @Test
  public void testIllegalCommandAfterSuccess() {
    Readable r1 = new StringReader("Brighten 40 normal brightened asdf quit");
    TextScriptController controller = new TextScriptControllerImpl(model, r1, a1);
    controller.editImage();
    assertEquals("Welcome to the Text-Script Based Image Editor\n" +
            "Please type one of following command to edit images\n" +
            "To overwrite the loaded image/load a new image: " +
            "'Load image-path image-name' \n" +
            "To get the red-component image of the loaded image:" +
            " 'RedComponent image-name dest-image-name' \n" +
            "To get the green-component image of the loaded image:" +
            " 'GreenComponent image-name dest-image-name' \n" +
            "To get the blue-component image of the loaded image:" +
            " 'BlueComponent image-name dest-image-name' \n" +
            "To get the value grayscale image of the loaded image:" +
            "'ValueGreyScaleImage image-name dest-image-name' \n" +
            "To get the intensity grayscale image of the loaded image:" +
            "'IntensityGreyScaleImage image-name dest-image-name' \n" +
            "To get the luma grayscale image of the loaded image:" +
            "'LumaGreyScaleImage image-name dest-image-name' \n" +
            "To get the flipped horizontally image of the loaded image:" +
            " 'Flip-horizontally image-name dest-image-name' \n" +
            "To get the flipped vertically image of the loaded image:" +
            " 'Flip-vertically image-name dest-image-name' \n" +
            "To brighten the loaded image: 'Brighten increment image-name dest-image-name' \n" +
            "To darken the loaded image: 'Darken increment image-name dest-image-name' \n" +
            "To save the loaded image: 'Save image-path image-name' \n" +
            "Type quit or q to exit this program \n" +
            "Brighten Command has been executed successfully.\n" +
            "Invalid Command,please retype a valid command\n" +
            "Thank you for using this editor.\n", a1.toString());
  }

  @Test
  public void testValidBrightenImage() {
    Readable r1 = new StringReader("Brighten 40 normal brighter quit");
    TextScriptController controller = new TextScriptControllerImpl(model, r1, a1);
    controller.editImage();
    assertEquals("Welcome to the Text-Script Based Image Editor\n" +
            "Please type one of following command to edit images\n" +
            "To overwrite the loaded image/load a new image: " +
            "'Load image-path image-name' \n" +
            "To get the red-component image of the loaded image:" +
            " 'RedComponent image-name dest-image-name' \n" +
            "To get the green-component image of the loaded image:" +
            " 'GreenComponent image-name dest-image-name' \n" +
            "To get the blue-component image of the loaded image:" +
            " 'BlueComponent image-name dest-image-name' \n" +
            "To get the value grayscale image of the loaded image:" +
            "'ValueGreyScaleImage image-name dest-image-name' \n" +
            "To get the intensity grayscale image of the loaded image:" +
            "'IntensityGreyScaleImage image-name dest-image-name' \n" +
            "To get the luma grayscale image of the loaded image:" +
            "'LumaGreyScaleImage image-name dest-image-name' \n" +
            "To get the flipped horizontally image of the loaded image:" +
            " 'Flip-horizontally image-name dest-image-name' \n" +
            "To get the flipped vertically image of the loaded image:" +
            " 'Flip-vertically image-name dest-image-name' \n" +
            "To brighten the loaded image: 'Brighten increment image-name dest-image-name' \n" +
            "To darken the loaded image: 'Darken increment image-name dest-image-name' \n" +
            "To save the loaded image: 'Save image-path image-name' \n" +
            "Type quit or q to exit this program \n" +
            "Brighten Command has been executed successfully.\n" +
            "Thank you for using this editor.\n", a1.toString());

  }

  @Test
  public void testValidRedImage() {
    Readable r1 = new StringReader("RedComponent normal brighter quit");
    TextScriptController controller = new TextScriptControllerImpl(model, r1, a1);
    controller.editImage();
    assertEquals("Welcome to the Text-Script Based Image Editor\n" +
            "Please type one of following command to edit images\n" +
            "To overwrite the loaded image/load a new image: " +
            "'Load image-path image-name' \n" +
            "To get the red-component image of the loaded image:" +
            " 'RedComponent image-name dest-image-name' \n" +
            "To get the green-component image of the loaded image:" +
            " 'GreenComponent image-name dest-image-name' \n" +
            "To get the blue-component image of the loaded image:" +
            " 'BlueComponent image-name dest-image-name' \n" +
            "To get the value grayscale image of the loaded image:" +
            "'ValueGreyScaleImage image-name dest-image-name' \n" +
            "To get the intensity grayscale image of the loaded image:" +
            "'IntensityGreyScaleImage image-name dest-image-name' \n" +
            "To get the luma grayscale image of the loaded image:" +
            "'LumaGreyScaleImage image-name dest-image-name' \n" +
            "To get the flipped horizontally image of the loaded image:" +
            " 'Flip-horizontally image-name dest-image-name' \n" +
            "To get the flipped vertically image of the loaded image:" +
            " 'Flip-vertically image-name dest-image-name' \n" +
            "To brighten the loaded image: 'Brighten increment image-name dest-image-name' \n" +
            "To darken the loaded image: 'Darken increment image-name dest-image-name' \n" +
            "To save the loaded image: 'Save image-path image-name' \n" +
            "Type quit or q to exit this program \n" +
            "RedComponent Command has been executed successfully.\n" +
            "Thank you for using this editor.\n", a1.toString());

  }

  @Test
  public void testValidBlueImage() {
    Readable r1 = new StringReader("BlueComponent normal brighter quit");
    TextScriptController controller = new TextScriptControllerImpl(model, r1, a1);
    controller.editImage();
    assertEquals("Welcome to the Text-Script Based Image Editor\n" +
            "Please type one of following command to edit images\n" +
            "To overwrite the loaded image/load a new image: " +
            "'Load image-path image-name' \n" +
            "To get the red-component image of the loaded image:" +
            " 'RedComponent image-name dest-image-name' \n" +
            "To get the green-component image of the loaded image:" +
            " 'GreenComponent image-name dest-image-name' \n" +
            "To get the blue-component image of the loaded image:" +
            " 'BlueComponent image-name dest-image-name' \n" +
            "To get the value grayscale image of the loaded image:" +
            "'ValueGreyScaleImage image-name dest-image-name' \n" +
            "To get the intensity grayscale image of the loaded image:" +
            "'IntensityGreyScaleImage image-name dest-image-name' \n" +
            "To get the luma grayscale image of the loaded image:" +
            "'LumaGreyScaleImage image-name dest-image-name' \n" +
            "To get the flipped horizontally image of the loaded image:" +
            " 'Flip-horizontally image-name dest-image-name' \n" +
            "To get the flipped vertically image of the loaded image:" +
            " 'Flip-vertically image-name dest-image-name' \n" +
            "To brighten the loaded image: 'Brighten increment image-name dest-image-name' \n" +
            "To darken the loaded image: 'Darken increment image-name dest-image-name' \n" +
            "To save the loaded image: 'Save image-path image-name' \n" +
            "Type quit or q to exit this program \n" +
            "BlueComponent Command has been executed successfully.\n" +
            "Thank you for using this editor.\n", a1.toString());

  }

  @Test
  public void testValidGreenImage() {
    Readable r1 = new StringReader("GreenComponent normal brighter quit");
    TextScriptController controller = new TextScriptControllerImpl(model, r1, a1);
    controller.editImage();
    assertEquals("Welcome to the Text-Script Based Image Editor\n" +
            "Please type one of following command to edit images\n" +
            "To overwrite the loaded image/load a new image: " +
            "'Load image-path image-name' \n" +
            "To get the red-component image of the loaded image:" +
            " 'RedComponent image-name dest-image-name' \n" +
            "To get the green-component image of the loaded image:" +
            " 'GreenComponent image-name dest-image-name' \n" +
            "To get the blue-component image of the loaded image:" +
            " 'BlueComponent image-name dest-image-name' \n" +
            "To get the value grayscale image of the loaded image:" +
            "'ValueGreyScaleImage image-name dest-image-name' \n" +
            "To get the intensity grayscale image of the loaded image:" +
            "'IntensityGreyScaleImage image-name dest-image-name' \n" +
            "To get the luma grayscale image of the loaded image:" +
            "'LumaGreyScaleImage image-name dest-image-name' \n" +
            "To get the flipped horizontally image of the loaded image:" +
            " 'Flip-horizontally image-name dest-image-name' \n" +
            "To get the flipped vertically image of the loaded image:" +
            " 'Flip-vertically image-name dest-image-name' \n" +
            "To brighten the loaded image: 'Brighten increment image-name dest-image-name' \n" +
            "To darken the loaded image: 'Darken increment image-name dest-image-name' \n" +
            "To save the loaded image: 'Save image-path image-name' \n" +
            "Type quit or q to exit this program \n" +
            "GreenComponent Command has been executed successfully.\n" +
            "Thank you for using this editor.\n", a1.toString());

  }

  @Test
  public void testValidLumaImage() {
    Readable r1 = new StringReader("LumaGreyScaleImage normal brighter quit");
    TextScriptController controller = new TextScriptControllerImpl(model, r1, a1);
    controller.editImage();
    assertEquals("Welcome to the Text-Script Based Image Editor\n" +
            "Please type one of following command to edit images\n" +
            "To overwrite the loaded image/load a new image: " +
            "'Load image-path image-name' \n" +
            "To get the red-component image of the loaded image:" +
            " 'RedComponent image-name dest-image-name' \n" +
            "To get the green-component image of the loaded image:" +
            " 'GreenComponent image-name dest-image-name' \n" +
            "To get the blue-component image of the loaded image:" +
            " 'BlueComponent image-name dest-image-name' \n" +
            "To get the value grayscale image of the loaded image:" +
            "'ValueGreyScaleImage image-name dest-image-name' \n" +
            "To get the intensity grayscale image of the loaded image:" +
            "'IntensityGreyScaleImage image-name dest-image-name' \n" +
            "To get the luma grayscale image of the loaded image:" +
            "'LumaGreyScaleImage image-name dest-image-name' \n" +
            "To get the flipped horizontally image of the loaded image:" +
            " 'Flip-horizontally image-name dest-image-name' \n" +
            "To get the flipped vertically image of the loaded image:" +
            " 'Flip-vertically image-name dest-image-name' \n" +
            "To brighten the loaded image: 'Brighten increment image-name dest-image-name' \n" +
            "To darken the loaded image: 'Darken increment image-name dest-image-name' \n" +
            "To save the loaded image: 'Save image-path image-name' \n" +
            "Type quit or q to exit this program \n" +
            "LumaGreyScaleImage Command has been executed successfully.\n" +
            "Thank you for using this editor.\n", a1.toString());

  }

  @Test
  public void testValidIntensityImage() {
    Readable r1 = new StringReader("IntensityGreyScaleImage normal brighter quit");
    TextScriptController controller = new TextScriptControllerImpl(model, r1, a1);
    controller.editImage();
    assertEquals("Welcome to the Text-Script Based Image Editor\n" +
            "Please type one of following command to edit images\n" +
            "To overwrite the loaded image/load a new image: " +
            "'Load image-path image-name' \n" +
            "To get the red-component image of the loaded image:" +
            " 'RedComponent image-name dest-image-name' \n" +
            "To get the green-component image of the loaded image:" +
            " 'GreenComponent image-name dest-image-name' \n" +
            "To get the blue-component image of the loaded image:" +
            " 'BlueComponent image-name dest-image-name' \n" +
            "To get the value grayscale image of the loaded image:" +
            "'ValueGreyScaleImage image-name dest-image-name' \n" +
            "To get the intensity grayscale image of the loaded image:" +
            "'IntensityGreyScaleImage image-name dest-image-name' \n" +
            "To get the luma grayscale image of the loaded image:" +
            "'LumaGreyScaleImage image-name dest-image-name' \n" +
            "To get the flipped horizontally image of the loaded image:" +
            " 'Flip-horizontally image-name dest-image-name' \n" +
            "To get the flipped vertically image of the loaded image:" +
            " 'Flip-vertically image-name dest-image-name' \n" +
            "To brighten the loaded image: 'Brighten increment image-name dest-image-name' \n" +
            "To darken the loaded image: 'Darken increment image-name dest-image-name' \n" +
            "To save the loaded image: 'Save image-path image-name' \n" +
            "Type quit or q to exit this program \n" +
            "IntensityGreyScaleImage Command has been executed successfully.\n" +
            "Thank you for using this editor.\n", a1.toString());

  }

  @Test
  public void testValidValueImage() {
    Readable r1 = new StringReader("ValueGreyScaleImage normal brighter quit");
    TextScriptController controller = new TextScriptControllerImpl(model, r1, a1);
    controller.editImage();
    assertEquals("Welcome to the Text-Script Based Image Editor\n" +
            "Please type one of following command to edit images\n" +
            "To overwrite the loaded image/load a new image: " +
            "'Load image-path image-name' \n" +
            "To get the red-component image of the loaded image:" +
            " 'RedComponent image-name dest-image-name' \n" +
            "To get the green-component image of the loaded image:" +
            " 'GreenComponent image-name dest-image-name' \n" +
            "To get the blue-component image of the loaded image:" +
            " 'BlueComponent image-name dest-image-name' \n" +
            "To get the value grayscale image of the loaded image:" +
            "'ValueGreyScaleImage image-name dest-image-name' \n" +
            "To get the intensity grayscale image of the loaded image:" +
            "'IntensityGreyScaleImage image-name dest-image-name' \n" +
            "To get the luma grayscale image of the loaded image:" +
            "'LumaGreyScaleImage image-name dest-image-name' \n" +
            "To get the flipped horizontally image of the loaded image:" +
            " 'Flip-horizontally image-name dest-image-name' \n" +
            "To get the flipped vertically image of the loaded image:" +
            " 'Flip-vertically image-name dest-image-name' \n" +
            "To brighten the loaded image: 'Brighten increment image-name dest-image-name' \n" +
            "To darken the loaded image: 'Darken increment image-name dest-image-name' \n" +
            "To save the loaded image: 'Save image-path image-name' \n" +
            "Type quit or q to exit this program \n" +
            "ValueGreyScaleImage Command has been executed successfully.\n" +
            "Thank you for using this editor.\n", a1.toString());

  }

  @Test
  public void testValidHorizontalImage() {
    Readable r1 = new StringReader("Flip-horizontally normal brighter quit");
    TextScriptController controller = new TextScriptControllerImpl(model, r1, a1);
    controller.editImage();
    assertEquals("Welcome to the Text-Script Based Image Editor\n" +
            "Please type one of following command to edit images\n" +
            "To overwrite the loaded image/load a new image: " +
            "'Load image-path image-name' \n" +
            "To get the red-component image of the loaded image:" +
            " 'RedComponent image-name dest-image-name' \n" +
            "To get the green-component image of the loaded image:" +
            " 'GreenComponent image-name dest-image-name' \n" +
            "To get the blue-component image of the loaded image:" +
            " 'BlueComponent image-name dest-image-name' \n" +
            "To get the value grayscale image of the loaded image:" +
            "'ValueGreyScaleImage image-name dest-image-name' \n" +
            "To get the intensity grayscale image of the loaded image:" +
            "'IntensityGreyScaleImage image-name dest-image-name' \n" +
            "To get the luma grayscale image of the loaded image:" +
            "'LumaGreyScaleImage image-name dest-image-name' \n" +
            "To get the flipped horizontally image of the loaded image:" +
            " 'Flip-horizontally image-name dest-image-name' \n" +
            "To get the flipped vertically image of the loaded image:" +
            " 'Flip-vertically image-name dest-image-name' \n" +
            "To brighten the loaded image: 'Brighten increment image-name dest-image-name' \n" +
            "To darken the loaded image: 'Darken increment image-name dest-image-name' \n" +
            "To save the loaded image: 'Save image-path image-name' \n" +
            "Type quit or q to exit this program \n" +
            "Flip-horizontally Command has been executed successfully.\n" +
            "Thank you for using this editor.\n", a1.toString());

  }

  @Test
  public void testValidVerticalImage() {
    Readable r1 = new StringReader("Flip-vertically normal brighter quit");
    TextScriptController controller = new TextScriptControllerImpl(model, r1, a1);
    controller.editImage();
    assertEquals("Welcome to the Text-Script Based Image Editor\n" +
            "Please type one of following command to edit images\n" +
            "To overwrite the loaded image/load a new image: " +
            "'Load image-path image-name' \n" +
            "To get the red-component image of the loaded image:" +
            " 'RedComponent image-name dest-image-name' \n" +
            "To get the green-component image of the loaded image:" +
            " 'GreenComponent image-name dest-image-name' \n" +
            "To get the blue-component image of the loaded image:" +
            " 'BlueComponent image-name dest-image-name' \n" +
            "To get the value grayscale image of the loaded image:" +
            "'ValueGreyScaleImage image-name dest-image-name' \n" +
            "To get the intensity grayscale image of the loaded image:" +
            "'IntensityGreyScaleImage image-name dest-image-name' \n" +
            "To get the luma grayscale image of the loaded image:" +
            "'LumaGreyScaleImage image-name dest-image-name' \n" +
            "To get the flipped horizontally image of the loaded image:" +
            " 'Flip-horizontally image-name dest-image-name' \n" +
            "To get the flipped vertically image of the loaded image:" +
            " 'Flip-vertically image-name dest-image-name' \n" +
            "To brighten the loaded image: 'Brighten increment image-name dest-image-name' \n" +
            "To darken the loaded image: 'Darken increment image-name dest-image-name' \n" +
            "To save the loaded image: 'Save image-path image-name' \n" +
            "Type quit or q to exit this program \n" +
            "Flip-vertically Command has been executed successfully.\n" +
            "Thank you for using this editor.\n", a1.toString());

  }


}