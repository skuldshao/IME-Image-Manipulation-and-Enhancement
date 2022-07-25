import org.junit.Test;

import model.filter.Blur;
import model.filter.Filter;
import model.filter.FilterImpl;
import model.filter.Sharpen;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;
import utils.ImageUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is the test class for the codes in the filter package, some tests including
 * get a blur filtering and sharpen filter Since all these function object has only
 * one method edit to perform an operation on a photo. I would abstract some edit in
 * the test class to reduce the duplicated code.
 */
public abstract class AbstractFilterImplTest {
  Image imageWidthEqualToHeight = ImageUtil.readPPM(
          "res/check-width-equal-to-height.ppm", "width-equal-to-height");
  Image imageWidthGreaterThanHeight = ImageUtil.readPPM(
          "res/check-width-greater-than-height.ppm",
          "width-greater-than-height");
  Image imageWidthSmallerThanHeight = ImageUtil.readPPM(
          "res/check-width-less-than-height.ppm", "width-less-than-height");
  String newImageName = "Afterediting";
  Image expectedImageAfterEditingWidthEqualToHeight =
          getExpectedImageWidthEqualToHeight();
  Image expectedImageAfterEditingWidthGreaterThanHeight =
          getExpectedImageWidthGreaterThanHeight();
  Image expectedImageAfterEditingWidthSmallerThanHeight =
          getExpectedImageWidthSmallerThanHeight();
  Image actualImageAfterEditing;
  Filter filter = makeModel();

  /**
   * This method is to produce a Filter  function object to edit a original Image.
   *
   * @return a Filterfunction object to edit aN original Image.
   */
  protected abstract Filter makeModel();

  /**
   * This method is to get an edited image in expectation whose width and height are equal.
   *
   * @return an edited image in expectation whose width and height are equal.
   */
  protected abstract Image getExpectedImageWidthEqualToHeight();

  /**
   * This method is to get an edited image in expectation whose width is greater than its height.
   *
   * @return an edited image in expectation whose width is greater than its height.
   */
  protected abstract Image getExpectedImageWidthGreaterThanHeight();

  /**
   * This method is to get an edited image in expectation whose width is smaller than its height.
   *
   * @return an edited image in expectation whose width is smaller than its height.
   */
  protected abstract Image getExpectedImageWidthSmallerThanHeight();

  /**
   * This factory class to produce a blur-filter-making function object which can produce
   * an blurred image of the given image and get the expected images after editing the
   * original image.
   */
  public static class BlurImage extends AbstractFilterImplTest {

    @Override
    protected Filter makeModel() {
      return new Blur(newImageName);
    }

    @Override
    protected Image getExpectedImageWidthEqualToHeight() {
      Pixel[][] pixels = {{new PixelImpl(109, 77, 15, 255),
              new PixelImpl(77, 109, 62, 255),
              new PixelImpl(15, 46, 78, 255)},
          {new PixelImpl(129, 144, 66, 255),
          new PixelImpl(125, 156, 109, 255),
          new PixelImpl(50, 65, 81, 255)},
          {new PixelImpl(55, 118, 87, 255),
          new PixelImpl(79, 110, 95, 255),
          new PixelImpl(55, 55, 55, 255)}};
      Image output = new ImageImpl(3, 3, 255, "image1", pixels);
      return output;
    }

    @Override
    protected Image getExpectedImageWidthGreaterThanHeight() {
      Pixel[][] pixels2 = {{new PixelImpl(94, 62, 31, 255),
              new PixelImpl(46, 93, 61, 255),
              new PixelImpl(35, 97, 98, 255),
              new PixelImpl(72, 87, 55, 255),},
          {new PixelImpl(118, 102, 87, 255),
          new PixelImpl(80, 127, 111, 255),
          new PixelImpl(60, 138, 139, 255),
          new PixelImpl(76, 107, 91, 255)},
          {new PixelImpl(80, 80, 80, 255),
          new PixelImpl(85, 100, 100, 255),
          new PixelImpl(77, 108, 108, 255),
          new PixelImpl(64, 79, 79, 255)}};
      Image output = new ImageImpl(4, 3, 255, "image2", pixels2);
      return output;
    }

    @Override
    protected Image getExpectedImageWidthSmallerThanHeight() {
      Pixel[][] pixels3 = {{new PixelImpl(109, 77, 15, 255),
              new PixelImpl(77, 109, 62, 255),
              new PixelImpl(15, 46, 78, 255)},
          {new PixelImpl(129, 144, 66, 255),
          new PixelImpl(125, 156, 109, 255),
          new PixelImpl(50, 65, 81, 255)},
          {new PixelImpl(82, 145, 114, 255),
          new PixelImpl(115, 146, 131, 255),
          new PixelImpl(82, 82, 82, 255)},
          {new PixelImpl(59, 90, 90, 255),
          new PixelImpl(89, 104, 104, 255),
          new PixelImpl(74, 74, 74, 255)}};
      Image output = new ImageImpl(3, 4, 255, "image3", pixels3);
      return output;
    }
  }

  /**
   * This factory class to produce a sharpen-filter-making function object which can produce
   * an sharpened image of the given image and get the expected images after editing the
   * original image.
   */
  public static class SharpenImage extends AbstractFilterImplTest {

    @Override
    protected Filter makeModel() {
      return new Sharpen(newImageName);
    }

    @Override
    protected Image getExpectedImageWidthEqualToHeight() {
      Pixel[][] pixels = {{new PixelImpl(255, 131, 0, 255),
              new PixelImpl(163, 255, 69, 255),
              new PixelImpl(0, 38, 255, 255)},
          {new PixelImpl(255, 255, 96, 255),
          new PixelImpl(255, 255, 255, 255),
          new PixelImpl(50, 113, 144, 255)},
          {new PixelImpl(97, 255, 255, 255),
          new PixelImpl(201, 255, 200, 255),
          new PixelImpl(146, 117, 145, 255)}};
      Image output = new ImageImpl(3, 3, 255, "image1", pixels);
      return output;
    }

    @Override
    protected Image getExpectedImageWidthGreaterThanHeight() {
      Pixel[][] pixels2 = {{new PixelImpl(255, 40, 0, 255),
              new PixelImpl(11, 255, 106, 255),
              new PixelImpl(0, 105, 234, 255),
              new PixelImpl(218, 250, 88, 255),},
          {new PixelImpl(255, 255, 240, 255),
          new PixelImpl(170, 233, 255, 255),
          new PixelImpl(117, 255, 255, 255),
          new PixelImpl(194, 226, 255, 255)},
          {new PixelImpl(177, 144, 144, 255),
          new PixelImpl(189, 251, 255, 255),
          new PixelImpl(134, 197, 229, 255),
          new PixelImpl(156, 187, 219, 255)}};
      Image output = new ImageImpl(4, 3, 255, "image2", pixels2);
      return output;
    }

    @Override
    protected Image getExpectedImageWidthSmallerThanHeight() {
      Pixel[][] pixels3 = {{new PixelImpl(255, 131, 0, 255),
              new PixelImpl(163, 255, 69, 255),
              new PixelImpl(0, 38, 255, 255)},
          {new PixelImpl(255, 255, 39, 255),
          new PixelImpl(255, 255, 255, 255),
          new PixelImpl(0, 58, 88, 255)},
          {new PixelImpl(152, 255, 255, 255),
          new PixelImpl(255, 255, 255, 255),
          new PixelImpl(203, 173, 201, 255)},
          {new PixelImpl(107, 170, 201, 255),
          new PixelImpl(212, 255, 255, 255),
          new PixelImpl(158, 128, 158, 255)}};
      Image output = new ImageImpl(3, 4, 255, "image3", pixels3);
      return output;
    }
  }

  @Test
  public void doubleBlur() {
    Image blurOne = new Blur("double-blur").applyFilter(imageWidthEqualToHeight);
    Image blurTwo = new Blur("double-blur").applyFilter(blurOne);
    Pixel[][] pixels = {{new PixelImpl(59, 59, 24, 255),
            new PixelImpl(59, 73, 47, 255),
            new PixelImpl(25, 41, 42, 255)},
        {new PixelImpl(74, 90, 48, 255),
          new PixelImpl(83, 107, 75, 255),
          new PixelImpl(42, 58, 56, 255)},
        {new PixelImpl(45, 69, 46, 255),
          new PixelImpl(57, 79, 61, 255),
          new PixelImpl(35, 43, 40, 255)}};
    Image expect = new ImageImpl(3, 3, 255, "image1", pixels);
    assertTrue(equalImages(expect,blurTwo));

  }

  @Test
  public void doubleSharpen() {
    Image sharpenOne = new Sharpen("double-sharpen").applyFilter(imageWidthEqualToHeight);
    Image sharpenTwo = new Sharpen("double-sharpen").applyFilter(sharpenOne);
    Pixel[][] pixels = {{new PixelImpl(255, 221, 0, 255),
            new PixelImpl(255, 255, 180, 255),
            new PixelImpl(0, 68, 255, 255)},
        {new PixelImpl(255, 255, 220, 255),
          new PixelImpl(255, 255, 255, 255),
          new PixelImpl(166, 255, 255, 255)},
        {new PixelImpl(195, 255, 255, 255),
          new PixelImpl(255, 255, 255, 255),
          new PixelImpl(178, 158, 211, 255)}};
    Image expect = new ImageImpl(3, 3, 255, "image1", pixels);
    assertTrue(equalImages(expect,sharpenTwo));

  }

  @Test(expected = IllegalArgumentException.class)
  public void nullNameConstruct() {
    new Blur(null);
    new Sharpen(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullMatrix() {
    new FilterImpl(null, "hello");
    new FilterImpl(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nonOddDemensionmatrix() {
    new FilterImpl(new double[][]{{1, 1}, {1, 1}}, "hello");
    new FilterImpl(new double[][]{{1, 1}, {1, 1}, {1, 1}}, "hi");
    new FilterImpl(new double[][]{{1, 1, 1}, {1, 1, 1}}, "hey");
    new FilterImpl(new double[][]{{}}, "hey");
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullImageinputforEdit() {
    filter.applyFilter(null);
  }

  @Test
  public void testEditimageWidthEqualToHeight() {
    actualImageAfterEditing = filter.applyFilter(imageWidthEqualToHeight);
    for (int row = 0; row < expectedImageAfterEditingWidthEqualToHeight.getHeight(); row++) {
      for (int col = 0; col < expectedImageAfterEditingWidthEqualToHeight.getWidth(); col++) {
        Pixel expectedPixel = expectedImageAfterEditingWidthEqualToHeight.getPixel(col, row);
        Pixel actualPixel = actualImageAfterEditing.getPixel(col, row);
        assertEquals(expectedPixel, actualPixel);
      }
    }
  }

  @Test
  public void testEditWidthGreaterThanHeight() {
    actualImageAfterEditing = filter.applyFilter(imageWidthGreaterThanHeight);
    for (int row = 0; row < expectedImageAfterEditingWidthGreaterThanHeight.getHeight(); row++) {
      for (int col = 0; col < expectedImageAfterEditingWidthGreaterThanHeight.getWidth(); col++) {
        Pixel expectedPixel = expectedImageAfterEditingWidthGreaterThanHeight.getPixel(col, row);
        Pixel actualPixel = actualImageAfterEditing.getPixel(col, row);
        assertEquals(expectedPixel, actualPixel);
      }
    }
  }

  @Test
  public void testEditWidthSmallerThanHeight() {
    actualImageAfterEditing = filter.applyFilter(imageWidthSmallerThanHeight);
    for (int row = 0; row < expectedImageAfterEditingWidthSmallerThanHeight.getHeight(); row++) {
      for (int col = 0; col < expectedImageAfterEditingWidthSmallerThanHeight.getWidth(); col++) {
        Pixel expectedPixel = expectedImageAfterEditingWidthSmallerThanHeight.getPixel(col, row);
        Pixel actualPixel = actualImageAfterEditing.getPixel(col, row);
        assertEquals(expectedPixel, actualPixel);
      }
    }
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