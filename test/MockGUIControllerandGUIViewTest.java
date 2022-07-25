import org.junit.Before;
import org.junit.Test;

import controller.ImageEditorController;
import controller.commands.Commands;
import controller.commands.LoadCommands;
import model.ImageModel;
import model.ImageModelImpl;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is the test class for parts methods called in the actionlistener method in the GUI
 * controller,since we cannot test the actionmethod directly. We use a mock GUI view
 * to stimulate this process.Another part of testing for actionlister method is in the commandsImpl
 * test class.
 */
public class MockGUIControllerandGUIViewTest {
  MockGUIView view;
  Appendable ap;
  MockGUIController controller;
  ImageModel model;

  @Before
  public void setUp() {
    this.ap = new StringBuilder();
    this.model = new ImageModelImpl();
    this.view = new MockGUIView(ap,
            "res/check-width-equal-to-height.ppm",
            "res/check-width-equal-to-height.png",
            "check", 30.0, this.model);
    StringBuilder log = new StringBuilder();
    this.controller = new MockGUIController(view, new MockModel(log));
  }

  @Test
  public void testToStringAddViewEventListenerMockView() {
    assertEquals("Number of listeners in this window: 0", this.view.toString());
    this.view.setViewLister(controller);
    assertEquals("Number of listeners in this window: 1", this.view.toString());
  }

  @Test
  public void testRenderMessageMockView() {
    assertEquals("", this.ap.toString());
    this.view.renderMessage("hello world");
    assertEquals("hello world", this.ap.toString());
  }

  @Test
  public void testGetLoadPathMockView() {
    assertEquals("res/check-width-equal-to-height.ppm", view.getLoadFilePath());
  }

  @Test
  public void testGetSavePathMockView() {
    assertEquals("res/check-width-equal-to-height.png", view.getSaveFilePath());
  }

  @Test
  public void testGetPerferredNameMockView() {
    assertEquals("check", this.view.preferName());
  }

  @Test
  public void testGetBrightness() {
    assertEquals(30, this.view.getBrightness(), 0.01);
  }

  @Test
  public void testRefresh() {
    // the origianl gui view states
    assertEquals("res/check-width-equal-to-height.ppm", view.getLoadFilePath());
    assertEquals("res/check-width-equal-to-height.png", view.getSaveFilePath());
    assertEquals("check", this.view.preferName());
    assertEquals(30, this.view.getBrightness(), 0.01);
    assertEquals(null, this.view.getCurrentImageinView());
    ImageEditorController mockGUIController = new MockGUIController(this.view, this.model);
    // run refresh to update the current image in the image
    mockGUIController.editImage();
    //check if the current gui state has been updated
    assertEquals("", view.getLoadFilePath());
    assertEquals("", view.getSaveFilePath());
    assertEquals("", this.view.preferName());
    assertEquals(0, this.view.getBrightness(), 0.01);
    // check if the gui view can get the correct current image after updating
    Pixel[][] pixels = {{new PixelImpl(255, 0, 0, 255),
            new PixelImpl(0, 255, 0, 255),
            new PixelImpl(0, 0, 255, 255)},
        {new PixelImpl(255, 255, 0, 255),
        new PixelImpl(255, 255, 255, 255),
        new PixelImpl(0, 0, 0, 255)},
        {new PixelImpl(0, 255, 255, 255),
        new PixelImpl(75, 75, 75, 255),
        new PixelImpl(127, 127, 127, 255)}};
    Image expect = new ImageImpl(3, 3, 255, "image1", pixels);
    assertTrue(equalImages(expect, this.view.getCurrentImageinView()));
  }

  @Test
  public void testGetCurrentImage() {
    assertEquals(null, this.model.getImage());
    Commands load = new LoadCommands(
            "res/check-width-equal-to-height.ppm", "normal");
    load.run(model);
    Pixel[][] pixels = {{new PixelImpl(255, 0, 0, 255),
            new PixelImpl(0, 255, 0, 255),
            new PixelImpl(0, 0, 255, 255)},
        {new PixelImpl(255, 255, 0, 255),
        new PixelImpl(255, 255, 255, 255),
        new PixelImpl(0, 0, 0, 255)},
        {new PixelImpl(0, 255, 255, 255),
        new PixelImpl(75, 75, 75, 255),
        new PixelImpl(127, 127, 127, 255)}};
    Image expect = new ImageImpl(3, 3, 255, "image1", pixels);
    assertTrue(equalImages(expect, this.model.getImage()));
  }

  private boolean equalImages(Image expected, Image actual) {
    if (expected.getWidth() != actual.getWidth() || expected.getHeight() != actual.getHeight()) {
      return false;
    }
    for (int yy = 0; yy < expected.getHeight(); yy++) {
      for (int xx = 0; xx < expected.getWidth(); xx++) {
        if (expected.getPixel(xx, yy).getChannel(Pixel.Channel.Red)
                != actual.getPixel(xx, yy).getChannel(Pixel.Channel.Red)) {
          return false;
        } else if (expected.getPixel(xx, yy).getChannel(Pixel.Channel.Green)
                != actual.getPixel(xx, yy).getChannel(Pixel.Channel.Green)) {
          return false;
        } else if (expected.getPixel(xx, yy).getChannel(Pixel.Channel.Blue)
                != actual.getPixel(xx, yy).getChannel(Pixel.Channel.Blue)) {
          return false;
        }
      }
    }
    return true;
  }
}