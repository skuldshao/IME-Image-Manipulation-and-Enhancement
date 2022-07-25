package model.filter;

import model.image.Image;

/**
 * This interface can filter an image with the given filter matrix in the implementation class.
 */
public interface Filter {

  /**
   * Filter the given image based on a certain matrix.
   * @param image the given image to be filtered
   * @return a new Image after filtered with the given image
   * @throws IllegalArgumentException if the given image is null
   */
  Image applyFilter(Image image);
}
