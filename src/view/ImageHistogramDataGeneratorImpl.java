package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.ImageModelState;
import model.image.Image;
import model.image.Pixel;

/**
 * This class is the simple implementation of histogram data generator interface. It would compute
 * the frequencies for different channels from the 0 to
 * the max color value in the current image by call different methods. Each method would return
 * a list of integers. The order of the number in the list is value the current element(frequency)
 * corresponds to.
 */
public class ImageHistogramDataGeneratorImpl implements ImageHistogramDataGenerator {
  private ImageModelState modelState;
  // a list to store all the red color value for the current image in the model
  private List<Integer> allRedColor;
  // a list to store all the green color value for the current image in the model
  private List<Integer> allGreenColor;
  // a list to store all the blue color value for the current image in the model
  private List<Integer> allBlueColor;
  // a list to store all the intensity color value for the current image in the model
  private List<Integer> allIntensityGreyScaleColor;

  //INVARIANT: This model state cannot be null.

  /**
   * Construct a ImageHistogramDataGeneratorImpl to compute the frequencies for different channels
   * from the 0 to the max color value in the current image in the model state.
   *
   * @param modelState the ready-only model where the gui view can read the necessary information
   *                   *                   from the current model part.
   * @throws IllegalArgumentException if the given model state is null.
   */
  public ImageHistogramDataGeneratorImpl(ImageModelState modelState) {
    if (modelState == null) {
      throw new IllegalArgumentException("The model cannot be null");
    }
    this.modelState = modelState;
  }

  @Override
  public List<Integer> getRedColorFrequencies() {
    this.getAllColor();
    return this.getOneColorFrequency(this.allRedColor);
  }

  @Override
  public List<Integer> getGreenColorFrequencies() {
    this.getAllColor();
    return this.getOneColorFrequency(this.allGreenColor);
  }

  @Override
  public List<Integer> getBlueColorFrequencies() {
    this.getAllColor();
    return this.getOneColorFrequency(this.allBlueColor);
  }

  @Override
  public List<Integer> getIntensityColorFrequencies() {
    this.getAllColor();
    return this.getOneColorFrequency(this.allIntensityGreyScaleColor);
  }

  /**
   * Compute the frequencies from 0 to the max color value for a current image's channel.
   * The order of the number in the list is value the current element(frequency) corresponds to.
   * For example, the first element in the list is 1944, it means the color value 0
   * has a frequencies of  1944 for the certain channel.
   *
   * @param allColorValues the list of values for a channel from all the pixels in the current
   *                       image.
   * @return the frequencies from 0 to the max color value for a current image's channel.
   */
  private List<Integer> getOneColorFrequency(List<Integer> allColorValues) {
    List<Integer> colorFrequency = new ArrayList<>();
    Image currentImage = modelState.getImage();
    if (currentImage != null) {
      int maxColor = currentImage.getMaxColor();
      for (int value = 0; value <= maxColor; value++) {
        // count the frequency of the current color value:
        int currentFrequency = Collections.frequency(allColorValues, value);
        colorFrequency.add(currentFrequency);
      }
    }
    return colorFrequency;
  }

  /**
   * This method would grab the values for red/blue/green channels and computes the intensity scale
   * pixel by pixel. All these data would be stared in the corresponding fields.
   */
  private void getAllColor() {
    Image currentImage = modelState.getImage();
    this.allRedColor = new ArrayList<>();
    this.allGreenColor = new ArrayList<>();
    this.allBlueColor = new ArrayList<>();
    this.allIntensityGreyScaleColor = new ArrayList<>();
    if (currentImage != null) {
      int imageHeight = currentImage.getHeight();
      int imageWidth = currentImage.getWidth();
      for (int row = 0; row < imageHeight; row++) {
        for (int col = 0; col < imageWidth; col++) {
          Pixel pixel = currentImage.getPixel(col, row);
          // get the current red color in the current pixel:
          int currentRed = pixel.getChannel(Pixel.Channel.Red);
          // get the green red color in the current pixel:
          int currentGreen = pixel.getChannel(Pixel.Channel.Green);
          // get the green red color in the current pixel:
          int currentBlue = pixel.getChannel(Pixel.Channel.Blue);
          // compute the intensity gray color value for the current pixel
          int currentIntensityColor = Math.round((currentRed + currentGreen + currentBlue) / 3);
          // add the current red,blue,green,intensity colors to the corresponding lists
          allRedColor.add(currentRed);
          allGreenColor.add(currentGreen);
          allBlueColor.add(currentBlue);
          allIntensityGreyScaleColor.add(currentIntensityColor);
        }
      }
    }
  }
}
