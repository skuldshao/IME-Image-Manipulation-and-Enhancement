package model.colortransformations;

/**
 * This class represents a operation that convert a normal color image into a sepia-toned image.
 */
public class Sepia extends ColorTransformationImpl {
  // INVARIANT: name cannot be null

  /**
   * Construct a Sepia object to do a sepia-toned color transform on an image
   * and name the name image after transforming with the given name.
   *
   * @param name the name of a new image after imaging
   * @throws IllegalArgumentException if the name is null
   */
  public Sepia(String name) {
    super(new double[][]{
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}}, name);
  }
}
