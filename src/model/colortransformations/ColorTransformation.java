package model.colortransformations;

import model.image.Image;

/**
 * This interface is for the color transformation operation on a given image with a given matrix
 * in its implementation class.
 */
public interface ColorTransformation {
  /**
   * apply the color transformation on given image based on a certain matrix in a implantation
   * class.
   *
   * @param image the given image to be color transformed
   * @return a new Image after color transfromed with the given image
   * @throws IllegalArgumentException if the given image is null
   */
  Image applyColorTransformation(Image image);
}
