package view;

import java.util.List;

/**
 * This interface would compute the frequencies for different channels from the 0 to
 * the max color value in the current image by call different methods. Each method would return
 * a list of integers. The order of the number in the list is value the current element(frequency)
 * corresponds to. For example, the first element in the list is 1944, it means the color value 0
 * has a frequencies of  1944 for a certain channel.
 */
public interface ImageHistogramDataGenerator {

  /**
   * Compute the frequencies from 0 to the max color value for the current image's red channels.
   * The order of the number in the list is value the current element(frequency) corresponds to.
   * For example, the first element in the list is 1944, it means the color value 0
   * has a frequencies of  1944 for the red channel.
   *
   * @return the frequencies from 0 to the max color value for the current image's red channels.
   */
  List<Integer> getRedColorFrequencies();

  /**
   * Compute the frequencies from 0 to the max color value for the current image's green channels.
   * The order of the number in the list is value the current element(frequency) corresponds to.
   * For example, the first element in the list is 1944, it means the color value 0
   * has a frequencies of  1944 for the green channel.
   *
   * @return the frequencies from 0 to the max color value for the current image's green channels.
   */
  List<Integer> getGreenColorFrequencies();

  /**
   * Compute the frequencies from 0 to the max color value for the current image's blue channels.
   * The order of the number in the list is value the current element(frequency) corresponds to.
   * For example, the first element in the list is 1944, it means the color value 0
   * has a frequencies of  1944 for the blue channel.
   *
   * @return the frequencies from 0 to the max color value for the current image's blue channels.
   */
  List<Integer> getBlueColorFrequencies();

  /**
   * Compute the frequencies from 0 to the max color value for the current image's intensity
   * grayscale color.
   * The order of the number in the list is value the current element(frequency) corresponds to.
   * For example, the first element in the list is 1944, it means the color value 0
   * has a frequencies of  1944 for the intensity grayscale color.
   *
   * @return the frequencies from 0 to the max color value for the current image's intensity
   *         grayscale color.
   */
  List<Integer> getIntensityColorFrequencies();
}
