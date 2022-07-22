package model;

/**
 * This is the model for the image editor program. It includes the actions that it can perform
 * all the operations the user can apply on a given image, like getting the red/green/blue
 * component-image getting the value/intensity/luma grey image, flipping a image horizontally/
 * vertically, darkening/brightening an image and exporting/importing images.
 */
public interface ImageModel {
  /**
   * make a visualize-red-component operation on the image with a given oldImageName and name
   * the updated the image as the given newImagename.
   *
   * @param oldImageName the old image name used for doing visualize-red-component operation.
   * @param newImagename the name of image after doing operation
   * @throws IllegalArgumentException if the given oldeImageName or newImagename is null
   */
  void redComponent(String oldImageName, String newImagename);

  /**
   * make a visualize-green-component operation on the image with a given oldImageName and name
   * the updated the image as the given newImagename.
   *
   * @param oldImageName the old image name used for doing visualize-green-component operation.
   * @param newImagename the name of image after doing operation
   * @throws IllegalArgumentException if the given oldeImageName or newImagename is null
   */
  void greenComponent(String oldImageName, String newImagename);

  /**
   * make a visualize-blue-component operation on the image with a given oldImageName and name
   * the updated the image as the given newImagename.
   *
   * @param oldImageName the old image name used for doing visualize-blue-component operation.
   * @param newImagename the name of image after doing operation
   * @throws IllegalArgumentException if the given oldeImageName or newImagename is null
   */
  void blueComponent(String oldImageName, String newImagename);

  /**
   * make a get-value-grayscale-image operation on the image with a given oldImageName and name
   * the updated the image as the given newImagename.
   *
   * @param oldImageName the old image name used for doing get-value-grayscale-image  operation.
   * @param newImagename the name of image after doing operation
   * @throws IllegalArgumentException if the given oldeImageName or newImagename is null
   */
  void valueGreyImage(String oldImageName, String newImagename);

  /**
   * make a get-intensity-grayscale-image operation on the image with a given oldImageName and
   * name the updated the image as the given newImagename.
   *
   * @param oldImageName the old image name used for doing get-intensity-grayscale-image operation.
   * @param newImagename the name of image after doing operation
   * @throws IllegalArgumentException if the given oldeImageName or newImagename is null
   */
  void intensityGreyImage(String oldImageName, String newImagename);

  /**
   * make a get-luma-grayscale-image operation on the image with a given oldImageName and name
   * the updated the image as the given newImagename.
   *
   * @param oldImageName the old image name used for doing get-luma-grayscale-image operation.
   * @param newImagename the name of image after doing operation
   * @throws IllegalArgumentException if the given oldeImageName or newImagename is null
   */
  void lumaGreyImage(String oldImageName, String newImagename);

  /**
   * make a horizontal-flip operation on the image with a given oldImageName and name
   * the updated the image as the given newImagename.
   *
   * @param oldImageName the old image name used for doing horizontal-flip operation.
   * @param newImagename the name of image after doing operation
   * @throws IllegalArgumentException if the given oldeImageName or newImagename is null
   */
  void horizontalFlip(String oldImageName, String newImagename);

  /**
   * make a vertical-flip operation on the image with a given oldImageName and name
   * the updated the image as the given newImagename.
   *
   * @param oldImageName the old image name used for doing vertical-flip operation.
   * @param newImagename the name of image after doing operation
   * @throws IllegalArgumentException if the given oldeImageName or newImagename is null
   */
  void verticalFlip(String oldImageName, String newImagename);

  /**
   * make a update-brightness operation with a given oldImageName and name
   * the updated the image as the given newImagename.
   *
   * @param oldImageName the old image name used for doing vertical-flip operation.
   * @param newImagename the name of image after doing operation
   * @throws IllegalArgumentException if the given oldeImageName or newImagename is null
   **/
  void brightness(double value, String oldImageName, String newImagename);

  /**
   * make a load-new-image/overwrite-a-exitsing-image operation.If there's a image with oldImageName
   * given existing in the program, then this method would overwrite this image by importing the
   * image from the given filepath. Otherwise, this method would load a new image from the given
   * filepath and name it as the given oldImagename.
   *
   * @param filepath     the file path to import image in a folder
   * @param oldImageName the filename of a image to be overwritten or loaded and this name would
   *                     be the name of a image referred in the program.
   * @throws IllegalArgumentException if the given filepath and oldImagename is null
   * @throws IllegalArgumentException if the given filepath is invalid
   */
  void load(String filepath, String oldImageName);

  /**
   * make a export-image operation on a image with the given name and export it to the given
   * filepath.
   *
   * @param filepath the filepath to export a image
   * @param name     the name of image to be exported
   * @throws IllegalArgumentException if the filepath or name is null
   * @throws IllegalArgumentException if the filepath is invalid
   */
  void save(String filepath, String name);
}
