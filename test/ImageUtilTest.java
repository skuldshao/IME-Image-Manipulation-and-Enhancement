import org.junit.Test;

import model.ImageUtil;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is the test class for ImageUtil class.
 */
public class ImageUtilTest {
  Image imageWidthEqualToHeight;
  Image imageWidthGreaterThanHeight;
  Image imageWidthSmallerThanHeight;

  private void setup() {
    imageWidthEqualToHeight = ImageUtil.readPPM(
            "res/check/check-width-equal-to-height.ppm", "width-equal-to-height");
    imageWidthGreaterThanHeight = ImageUtil.readPPM(
            "res/check/check-width-greater-than-height.ppm",
            "width-greater-than-height");
    imageWidthSmallerThanHeight = ImageUtil.readPPM(
            "res/check/check-width-less-than-height.ppm", "width-less-than-height");
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

  @Test
  public void readPPMTest() {
    setup();
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
    assertEquals(3, imageWidthEqualToHeight.getHeight());
    assertEquals(3, imageWidthEqualToHeight.getWidth());
    assertTrue(equalImages(expect, imageWidthEqualToHeight));
    Pixel[][] pixels2 = {{new PixelImpl(255, 0, 0, 255),
            new PixelImpl(0, 255, 0, 255),
            new PixelImpl(0, 0, 255, 255),
            new PixelImpl(255, 255, 0, 255),},
      {new PixelImpl(255, 255, 255, 255),
            new PixelImpl(0, 0, 0, 255),
            new PixelImpl(0, 255, 255, 255),
            new PixelImpl(75, 75, 75, 255)},
      {new PixelImpl(127, 127, 127, 255),
            new PixelImpl(150, 150, 150, 255),
            new PixelImpl(150, 150, 150, 255),
            new PixelImpl(150, 150, 150, 255)}};
    Image expect2 = new ImageImpl(4, 3, 255, "image2", pixels2);
    assertEquals(3, imageWidthGreaterThanHeight.getHeight());
    assertEquals(4, imageWidthGreaterThanHeight.getWidth());
    assertTrue(equalImages(expect2, imageWidthGreaterThanHeight));

    Pixel[][] pixels3 = {{new PixelImpl(255, 0, 0, 255),
            new PixelImpl(0, 255, 0, 255),
            new PixelImpl(0, 0, 255, 255)},
      {new PixelImpl(255, 255, 0, 255),
            new PixelImpl(255, 255, 255, 255),
            new PixelImpl(0, 0, 0, 255)},
      {new PixelImpl(0, 255, 255, 255),
            new PixelImpl(75, 75, 75, 255),
            new PixelImpl(127, 127, 127, 255)},
      {new PixelImpl(150, 150, 150, 255),
            new PixelImpl(150, 150, 150, 255),
            new PixelImpl(150, 150, 150, 255)}};
    Image expect3 = new ImageImpl(3, 4, 255, "image3", pixels3);
    assertEquals(4, imageWidthSmallerThanHeight.getHeight());
    assertEquals(3, imageWidthSmallerThanHeight.getWidth());
    assertTrue(equalImages(expect3, imageWidthSmallerThanHeight));
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullFileName() {
    ImageUtil.readPPM("res/check/check-width-less-than-height.ppm", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidFilePath() {
    ImageUtil.readPPM("res/check/hello.ppm", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void incorrentPPMFormat() {
    ImageUtil.readPPM("res/bad-input/badinput.ppm",
            null);
  }

  @Test
  public void testClampColor() {
    assertEquals(10, ImageUtil.clampColor(10, 255));
    assertEquals(255, ImageUtil.clampColor(349, 255));
    assertEquals(0, ImageUtil.clampColor(-12, 255));
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullImageWriting() {
    ImageUtil.writePPM("red/check", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidfilePathWriting() {
    Pixel[][] pixels3 = {{new PixelImpl(255, 0, 0, 255),
            new PixelImpl(0, 255, 0, 255),
            new PixelImpl(0, 0, 255, 255)},
      {new PixelImpl(255, 255, 0, 255),
            new PixelImpl(255, 255, 255, 255),
            new PixelImpl(0, 0, 0, 255)},
      {new PixelImpl(0, 255, 255, 255),
            new PixelImpl(75, 75, 75, 255),
            new PixelImpl(127, 127, 127, 255)},
      {new PixelImpl(150, 150, 150, 255),
            new PixelImpl(150, 150, 150, 255),
            new PixelImpl(150, 150, 150, 255)}};
    Image image = new ImageImpl(3, 4, 255, "image3", pixels3);
    ImageUtil.writePPM("res/hello/hello.ppm", image);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nonWriteable() {
    ImageUtil.writePPM("res/readonly.ppm", new ImageImpl(
            10, 10, 255, "hi"));
  }
}