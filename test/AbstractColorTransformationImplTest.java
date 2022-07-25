import org.junit.Test;

import model.colortransformations.ColorTransformation;
import model.colortransformations.ColorTransformationImpl;
import model.colortransformations.LumaGreyScale;
import model.colortransformations.Sepia;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;
import utils.ImageUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is the test class for the codes in the color transformations package, some tests including
 * get a luma grayscale color transformation and sepia color transformation
 * Since all these function object has only one method edit to perform
 * an operation on a photo. I would abstract some edit in the test class to reduce.
 * the duplicated code.
 */
public abstract class AbstractColorTransformationImplTest {
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
  ColorTransformation colorTransformation = makeModel();

  /**
   * This method is to produce a ColorTransformation  function object to edit a original Image.
   *
   * @return a ColorTransformation function object to edit aN original Image.
   */
  protected abstract ColorTransformation makeModel();

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
   * This factory class to produce a greyscale-image-making function object which can produce
   * an luma grey scale image of the given image
   * and get the expected images after editing the original image.
   */
  public static class LumaGrayScale extends AbstractColorTransformationImplTest {

    @Override
    protected ColorTransformation makeModel() {
      return new LumaGreyScale("luma");
    }

    @Override
    protected Image getExpectedImageWidthEqualToHeight() {
      Pixel[][] pixels = {{new PixelImpl(54, 54, 54, 255),
              new PixelImpl(193, 193, 193, 255),
              new PixelImpl(18, 18, 18, 255)},
          {new PixelImpl(247, 247, 247, 255),
          new PixelImpl(255, 255, 255, 255),
          new PixelImpl(0, 0, 0, 255)},
          {new PixelImpl(211, 211, 211, 255),
          new PixelImpl(78, 78, 78, 255),
          new PixelImpl(132, 132, 132, 255)}};
      Image output = new ImageImpl(3, 3, 255, "image1", pixels);
      return output;
    }

    @Override
    protected Image getExpectedImageWidthGreaterThanHeight() {
      Pixel[][] pixels2 = {{new PixelImpl(54, 54, 54, 255),
              new PixelImpl(193, 193, 193, 255),
              new PixelImpl(18, 18, 18, 255),
              new PixelImpl(247, 247, 247, 255),},
          {new PixelImpl(255, 255, 255, 255),
          new PixelImpl(0, 0, 0, 255),
          new PixelImpl(211, 211, 211, 255),
          new PixelImpl(78, 78, 78, 255)},
          {new PixelImpl(132, 132, 132, 255),
          new PixelImpl(156, 156, 156, 255),
          new PixelImpl(156, 156, 156, 255),
          new PixelImpl(156, 156, 156, 255)}};
      Image output = new ImageImpl(4, 3, 255, "image2", pixels2);
      return output;
    }

    @Override
    protected Image getExpectedImageWidthSmallerThanHeight() {
      Pixel[][] pixels3 = {{new PixelImpl(54, 54, 54, 255),
              new PixelImpl(193, 193, 193, 255),
              new PixelImpl(18, 18, 18, 255)},
          {new PixelImpl(247, 247, 247, 255),
          new PixelImpl(255, 255, 255, 255),
          new PixelImpl(0, 0, 0, 255)},
          {new PixelImpl(211, 211, 211, 255),
          new PixelImpl(78, 78, 78, 255),
          new PixelImpl(132, 132, 132, 255)},
          {new PixelImpl(156, 156, 156, 255),
          new PixelImpl(156, 156, 156, 255),
          new PixelImpl(156, 156, 156, 255)}};
      Image output = new ImageImpl(3, 4, 255, "image3", pixels3);
      return output;
    }
  }

  /**
   * This factory class to produce a sepia toned-making function object which can produce
   * an sepia-toned image of the given image
   * and get the expected images after editing the original image.
   */
  public static class SepiaImage extends AbstractColorTransformationImplTest {

    @Override
    protected ColorTransformation makeModel() {
      return new Sepia("sepia");
    }

    @Override
    protected Image getExpectedImageWidthEqualToHeight() {
      Pixel[][] pixels = {{new PixelImpl(100, 89, 69, 255),
              new PixelImpl(196, 175, 136, 255),
              new PixelImpl(48, 43, 33, 255)},
          {new PixelImpl(255, 255, 206, 255),
          new PixelImpl(255, 255, 239, 255),
          new PixelImpl(0, 0, 0, 255)},
          {new PixelImpl(244, 218, 170, 255),
           new PixelImpl(101, 90, 70, 255),
          new PixelImpl(172, 153, 119, 255)}};
      Image output = new ImageImpl(3, 3, 255, "image1", pixels);
      return output;
    }

    @Override
    protected Image getExpectedImageWidthGreaterThanHeight() {
      Pixel[][] pixels2 = {{new PixelImpl(100, 89, 69, 255),
              new PixelImpl(196, 175, 136, 255),
              new PixelImpl(48, 43, 33, 255),
              new PixelImpl(255, 255, 206, 255),},
          {new PixelImpl(255, 255, 239, 255),
          new PixelImpl(0, 0, 0, 255),
          new PixelImpl(244, 218, 170, 255),
          new PixelImpl(101, 90, 70, 255)},
          {new PixelImpl(172, 153, 119, 255),
          new PixelImpl(203, 180, 141, 255),
          new PixelImpl(203, 180, 141, 255),
          new PixelImpl(203, 180, 141, 255)}};
      Image output = new ImageImpl(4, 3, 255, "image2", pixels2);
      return output;
    }

    @Override
    protected Image getExpectedImageWidthSmallerThanHeight() {
      Pixel[][] pixels3 = {{new PixelImpl(100, 89, 69, 255),
              new PixelImpl(196, 175, 136, 255),
              new PixelImpl(48, 43, 33, 255)},
          {new PixelImpl(255, 255, 206, 255),
          new PixelImpl(255, 255, 239, 255),
          new PixelImpl(0, 0, 0, 255)},
          {new PixelImpl(244, 218, 170, 255),
          new PixelImpl(101, 90, 70, 255),
          new PixelImpl(172, 153, 119, 255)},
          {new PixelImpl(203, 180, 141, 255),
          new PixelImpl(203, 180, 141, 255),
          new PixelImpl(203, 180, 141, 255)}};
      Image output = new ImageImpl(3, 4, 255, "image3", pixels3);
      return output;
    }
  }

  @Test
  public void doubleGreyScale() {
    Image greyScaleOne = new LumaGreyScale("double-luma").applyColorTransformation(
            imageWidthEqualToHeight);
    Image greyScaleTwo = new LumaGreyScale("double-luma").applyColorTransformation(
            greyScaleOne);
    Pixel[][] pixels = {{new PixelImpl(56, 56, 56, 255),
            new PixelImpl(201, 201, 201, 255),
            new PixelImpl(19, 19, 19, 255)},
        {new PixelImpl(255, 255, 255, 255),
          new PixelImpl(255, 255, 255, 255),
          new PixelImpl(0, 0, 0, 255)},
        {new PixelImpl(219, 219, 219, 255),
          new PixelImpl(81, 81, 81, 255),
          new PixelImpl(137, 137, 137, 255)}};
    Image expect = new ImageImpl(3, 3, 255, "image1", pixels);
    assertTrue(equalImages(expect,greyScaleTwo));

  }

  @Test
  public void doubleSepia() {
    Image sepiaOne = new Sepia("double-sepia").applyColorTransformation(
            imageWidthEqualToHeight);
    Image sepiaTwo = new Sepia("double-sepia1").applyColorTransformation(sepiaOne);
    Pixel[][] pixels = {{new PixelImpl(121, 108, 84, 255),
            new PixelImpl(237, 211, 165, 255),
            new PixelImpl(58, 52, 40, 255)},
        {new PixelImpl(255, 255, 233, 255),
          new PixelImpl(255, 255, 237, 255),
          new PixelImpl(0, 0, 0, 255)},
        {new PixelImpl(255, 255, 205, 255),
          new PixelImpl(122, 109, 85, 255),
          new PixelImpl(208, 185, 144, 255)}};
    Image expect = new ImageImpl(3, 3, 255, "image1", pixels);
    assertTrue(equalImages(expect,sepiaTwo));

  }

  @Test(expected = IllegalArgumentException.class)
  public void nullNameConstruct() {
    new LumaGreyScale(null);
    new Sepia(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullMatrix() {
    new ColorTransformationImpl(null, "hello");
    new ColorTransformationImpl(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nonthreetimesthreematrix() {
    new ColorTransformationImpl(new double[][]{{1, 1}, {1, 1}}, "hello");
    new ColorTransformationImpl(new double[][]{{1, 1}, {1, 1}, {1, 1}}, "hi");
    new ColorTransformationImpl(new double[][]{{1, 1, 1}}, "hey");
    new ColorTransformationImpl(new double[][]{{}}, "hey");
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullImageinputforEdit() {
    colorTransformation.applyColorTransformation(null);
  }

  @Test
  public void testEditimageWidthEqualToHeight() {
    actualImageAfterEditing = colorTransformation.applyColorTransformation(imageWidthEqualToHeight);
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
    actualImageAfterEditing = colorTransformation.applyColorTransformation(
            imageWidthGreaterThanHeight);
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
    actualImageAfterEditing = colorTransformation.applyColorTransformation(
            imageWidthSmallerThanHeight);
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