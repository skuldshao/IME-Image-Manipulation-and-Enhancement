package model.colortransformations;

/**
 * This class represents a luma grey scale color transformation on a given image,where all the
 * pixel red,green,blue value would be set as  the weighted sum 0.2126𝑟+0.7152𝑔+0.0722𝑏.
 */
public class LumaGreyScale extends ColorTransformationImpl {
  // INVARIANT: name cannot be null

  /**
   * Construct a LumaGreyScale object to do a luma grey scale color transform on an image
   * and name the name image after transforming with the given name.
   *
   * @param name the name of a new image after imaging
   * @throws IllegalArgumentException if the given and name is null
   */
  public LumaGreyScale(String name) {
    super(new double[][]{
            {0.2126, 0.7552, 0.0722},
            {0.2126, 0.7552, 0.0722},
            {0.2126, 0.7552, 0.0722}}, name);
  }
}
