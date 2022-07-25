package model.filter;

/**
 * This class represent a sharpen filter operation on a given image.
 */
public class Sharpen extends FilterImpl {
  // INVARIANT: name cannot be null

  /**
   * Construct a sharpen filter object with given default blur filter matrix to filter an image and
   * a given filename to name the new image after filtering.
   *
   * @param name the name of a new image after imaging
   * @throws IllegalArgumentException if the given name is null
   */
  public Sharpen(String name) {
    super(new double[][]{
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}}, name);
  }
}
