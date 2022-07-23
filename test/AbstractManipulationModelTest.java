import org.junit.Test;

import model.ImageUtil;
import model.image.Image;
import model.image.Pixel;
import model.manipulation.ImageManipulationModel;
import model.manipulation.changebrigntness.ChangeBrightness;
import model.manipulation.flip.FlipImage;
import model.manipulation.greyscale.IntensityGreyScaleImage;
import model.manipulation.greyscale.LumaGreyScaleImage;
import model.manipulation.greyscale.ValueGreyScaleImage;
import model.manipulation.greyscale.component.BlueComponent;
import model.manipulation.greyscale.component.GreenComponent;
import model.manipulation.greyscale.component.RedComponent;

import static org.junit.Assert.assertEquals;

/**
 * This class is the abstract class for all the image manipulation function object in the
 * manipulation package.Since all these function object has only one method edit to perform
 * an operation on a photo. I would abstract some edit in the test class to reduce.
 * the duplicated code.
 */
public abstract class AbstractManipulationModelTest {
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
  double increaseValue = 30;
  double decreaseValue = -40;
  ImageManipulationModel manipulationModel = makeModel();

  /**
   * This method is to produce a ImageManipulationModel function object to edit a original Image.
   *
   * @return a ImageManipulationModel function object to edit a original Image.
   */
  protected abstract ImageManipulationModel makeModel();

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
   * This factory class to produce a changeBrightness function object which can increase the
   * brightness of a image and get the expected images after editing the original image.
   */
  public static class IncreaseBrigntness extends AbstractManipulationModelTest {
    @Override
    protected ImageManipulationModel makeModel() {
      return new ChangeBrightness(increaseValue, this.newImageName);
    }

    @Override
    protected Image getExpectedImageWidthEqualToHeight() {
      return ImageUtil.readPPM(
              "res/check-width-equal-to-height-brighten-by-30.ppm",
              "brightening1");
    }

    @Override
    protected Image getExpectedImageWidthGreaterThanHeight() {
      return ImageUtil.readPPM(
              "res/check-width-greater-than-width-brighten-by-30.ppm",
              "brightening2");
    }

    @Override
    protected Image getExpectedImageWidthSmallerThanHeight() {
      return ImageUtil.readPPM(
              "res/check-brightening-by-30-width-less-than-height.ppm",
              "brightening3");
    }
  }

  /**
   * This factory class to produce a changeBrightness function object which can decrease the
   * brightness of a image and get the expected images after editing the original image.
   */
  public static class DecreaseBrigntness extends AbstractManipulationModelTest {
    @Override
    protected ImageManipulationModel makeModel() {
      return new ChangeBrightness(decreaseValue, this.newImageName);
    }

    @Override
    protected Image getExpectedImageWidthEqualToHeight() {
      return ImageUtil.readPPM(
              "res/check-width-equal-to-height-darken-by-40.ppm",
              "darkening1");
    }

    @Override
    protected Image getExpectedImageWidthGreaterThanHeight() {
      return ImageUtil.readPPM(
              "res/check-width-greater-than-width-darken-by-40.ppm",
              "darkening2");
    }

    @Override
    protected Image getExpectedImageWidthSmallerThanHeight() {
      return ImageUtil.readPPM(
              "res/check-darkening-by-40-width-less-than-height.ppm",
              "darkening3");
    }
  }

  /**
   * This factory class to produce a filpImage function object which can flip an image
   * Horizontally  and get the expected images after editing the original image.
   */

  public static class FlipHorizontally extends AbstractManipulationModelTest {

    @Override
    protected ImageManipulationModel makeModel() {
      return new FlipImage(ImageManipulationModel.Flip.Horizontal, this.newImageName);
    }

    @Override
    protected Image getExpectedImageWidthEqualToHeight() {
      return ImageUtil.readPPM(
              "res/check-width-equal-to-height-Flip-horizontally.ppm",
              "flip1");
    }

    @Override
    protected Image getExpectedImageWidthGreaterThanHeight() {
      return ImageUtil.readPPM(
              "res/check-width-greater-than-height-Flip-horizontally.ppm",
              "flip2");
    }

    @Override
    protected Image getExpectedImageWidthSmallerThanHeight() {
      return ImageUtil.readPPM(
              "res/check-width-smaller-than-height-Flip-horizontally.ppm",
              "flip3");
    }
  }

  /**
   * This factory class to produce a filpImage function object which can flip an image
   * vertically and get the expected images after editing the original image.
   */
  public static class FlipVertically extends AbstractManipulationModelTest {

    @Override
    protected ImageManipulationModel makeModel() {
      return new FlipImage(ImageManipulationModel.Flip.Vertical, this.newImageName);
    }

    @Override
    protected Image getExpectedImageWidthEqualToHeight() {
      return ImageUtil.readPPM(
              "res/check-width-equal-to-height-Flip-Vertically.ppm",
              "flip4");
    }

    @Override
    protected Image getExpectedImageWidthGreaterThanHeight() {
      return ImageUtil.readPPM(
              "res/check-width-greater-than-height-Flip-Vertically.ppm",
              "flip5");
    }

    @Override
    protected Image getExpectedImageWidthSmallerThanHeight() {
      return ImageUtil.readPPM(
              "res/check-width-smaller-than-height-Flip-Vertically.ppm",
              "flip6");
    }
  }

  /**
   * This factory class to produce a greyscale-image-making function object which can produce
   * an intensity grey scale image of the given image
   * and get the expected images after editing the original image.
   */
  public static class IntensityGreyScale extends AbstractManipulationModelTest {

    @Override
    protected ImageManipulationModel makeModel() {
      return new IntensityGreyScaleImage(this.newImageName);
    }

    @Override
    protected Image getExpectedImageWidthEqualToHeight() {
      return ImageUtil.readPPM("res/check-width-equal-to-height-Intensity.ppm",
              "intensity1");
    }

    @Override
    protected Image getExpectedImageWidthGreaterThanHeight() {
      return ImageUtil.readPPM(
              "res/check-width-greater-than-height-Intensity.ppm",
              "intensity2");
    }

    @Override
    protected Image getExpectedImageWidthSmallerThanHeight() {
      return ImageUtil.readPPM(
              "res/check-width-smaller-than-height-Intensity.ppm",
              "intensity3");
    }
  }

  /**
   * This factory class to produce a greyscale-image-making function object which can produce
   * an luma grey scale image of the given image
   * and get the expected images after editing the original image.
   */
  public static class LumaGreyScale extends AbstractManipulationModelTest {

    @Override
    protected ImageManipulationModel makeModel() {
      return new LumaGreyScaleImage(this.newImageName);
    }

    @Override
    protected Image getExpectedImageWidthEqualToHeight() {
      return ImageUtil.readPPM(
              "res/check-width-equal-to-height-luma.ppm", "luma1");
    }

    @Override
    protected Image getExpectedImageWidthGreaterThanHeight() {
      return ImageUtil.readPPM(
              "res/check-width-greater-than-height-luma.ppm", "luma2");
    }

    @Override
    protected Image getExpectedImageWidthSmallerThanHeight() {
      return ImageUtil.readPPM(
              "res/check-width-smaller-than-height-luma.ppm", "luma3");
    }
  }

  /**
   * This factory class to produce a greyscale-image-making function object which can produce
   * an value grey scale image of the given image
   * and get the expected images after editing the original image.
   */
  public static class ValueGreyScale extends AbstractManipulationModelTest {

    @Override
    protected ImageManipulationModel makeModel() {
      return new ValueGreyScaleImage(this.newImageName);
    }

    @Override
    protected Image getExpectedImageWidthEqualToHeight() {
      return ImageUtil.readPPM(
              "res/check-width-equal-to-height-value.ppm", "value1");
    }

    @Override
    protected Image getExpectedImageWidthGreaterThanHeight() {
      return ImageUtil.readPPM(
              "res/check-width-greater-than-height-value.ppm",
              "value2");
    }

    @Override
    protected Image getExpectedImageWidthSmallerThanHeight() {
      return ImageUtil.readPPM(
              "res/check-width-smaller-than-height-value.ppm",
              "value3");
    }
  }

  /**
   * This factory class to produce a greyscale-image-making function object which can produce
   * an red-component grey scale image of the given image
   * and get the expected images after editing the original image.
   */
  public static class RedComponentImage extends AbstractManipulationModelTest {

    @Override
    protected ImageManipulationModel makeModel() {
      return new RedComponent(Pixel.Channel.Red, this.newImageName);
    }

    @Override
    protected Image getExpectedImageWidthEqualToHeight() {
      return ImageUtil.readPPM(
              "res/check-width-equal-to-height-red.ppm", "red1");
    }

    @Override
    protected Image getExpectedImageWidthGreaterThanHeight() {
      return ImageUtil.readPPM(
              "res/check-width-greater-than-height-red.ppm", "red2");
    }

    @Override
    protected Image getExpectedImageWidthSmallerThanHeight() {
      return ImageUtil.readPPM(
              "res/check-width-smaller-than-height-red.ppm", "red3");
    }
  }

  /**
   * This factory class to produce a greyscale-image-making function object which can produce
   * an green-component grey scale image of the given image
   * and get the expected images after editing the original image.
   */
  public static class GreenComponentImage extends AbstractManipulationModelTest {

    @Override
    protected ImageManipulationModel makeModel() {
      return new GreenComponent(Pixel.Channel.Green, this.newImageName);
    }

    @Override
    protected Image getExpectedImageWidthEqualToHeight() {
      return ImageUtil.readPPM(
              "res/check-width-equal-to-height-green.ppm",
              "green1");
    }

    @Override
    protected Image getExpectedImageWidthGreaterThanHeight() {
      return ImageUtil.readPPM(
              "res/check-width-greater-than-height-green.ppm",
              "green2");
    }

    @Override
    protected Image getExpectedImageWidthSmallerThanHeight() {
      return ImageUtil.readPPM(
              "res/check-width-smaller-than-height-green.ppm",
              "green3");
    }
  }

  /**
   * This factory class to produce a greyscale-image-making function object which can produce
   * an blue-component grey scale image of the given image
   * and get the expected images after editing the original image.
   */
  public static class BlueComponentImage extends AbstractManipulationModelTest {

    @Override
    protected ImageManipulationModel makeModel() {
      return new BlueComponent(Pixel.Channel.Blue, newImageName);
    }

    @Override
    protected Image getExpectedImageWidthEqualToHeight() {
      return ImageUtil.readPPM(
              "res/check-width-equal-to-height-blue.ppm",
              "blue1");
    }

    @Override
    protected Image getExpectedImageWidthGreaterThanHeight() {
      return ImageUtil.readPPM(
              "res/check-width-greater-than-height-blue.ppm",
              "blue2");
    }

    @Override
    protected Image getExpectedImageWidthSmallerThanHeight() {
      return ImageUtil.readPPM(
              "res/check-width-smaller-than-height-blue.ppm",
              "blue3");
    }
  }


  @Test(expected = IllegalArgumentException.class)
  public void nullNameConstructorChangeBrightness() {
    new ChangeBrightness(20, null);
    new ChangeBrightness(-20, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullImageinputforEdit() {
    manipulationModel.edit(null);
  }

  @Test
  public void testEditimageWidthEqualToHeight() {
    actualImageAfterEditing = manipulationModel.edit(imageWidthEqualToHeight);
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
    actualImageAfterEditing = manipulationModel.edit(imageWidthGreaterThanHeight);
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
    actualImageAfterEditing = manipulationModel.edit(imageWidthSmallerThanHeight);
    for (int row = 0; row < expectedImageAfterEditingWidthSmallerThanHeight.getHeight(); row++) {
      for (int col = 0; col < expectedImageAfterEditingWidthSmallerThanHeight.getWidth(); col++) {
        Pixel expectedPixel = expectedImageAfterEditingWidthSmallerThanHeight.getPixel(col, row);
        Pixel actualPixel = actualImageAfterEditing.getPixel(col, row);
        assertEquals(expectedPixel, actualPixel);
      }
    }
  }
}

