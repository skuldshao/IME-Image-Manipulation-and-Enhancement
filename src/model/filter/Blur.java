package model.filter;

/**
 * This class represent a Blur filter operation on a given image.
 */
public class Blur extends FilterImpl {
  // INVARIANT: name cannot be null

  /**
   * Construct a Blur filter object with given default blur filter matrix to filter an image and
   * a given filename to name the new image after filtering.
   *
   * @param name the name of a new image after imaging
   * @throws IllegalArgumentException if the given name is null
   */
  public Blur(String name) {
    super(new double[][]{{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125}, {0.0625, 0.125, 0.0625}},
            name);
  }
}
