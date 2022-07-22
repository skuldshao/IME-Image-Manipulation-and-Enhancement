package controller.commands;

import model.ImageModel;

/**
 * This is a abstract class representing some shared fields or methods for most the implementation
 * of Commands interface.For example, most of the editing image manipulation requires the old
 * filename and new filename for image before and after precessing. Both of these name
 * cannot be null.
 */
public abstract class CommandsImpl implements Commands {
  protected String oldFilename;
  //INVARIANT: oldFilename isn't null
  protected String newFilename;
  //INVARIANT: newfilename isn't null

  protected CommandsImpl(String oldFilename, String newFilename) {
    if (oldFilename == null || newFilename == null) {
      throw new IllegalArgumentException("The given filenames cannot be null");
    }
    this.newFilename = newFilename;
    this.oldFilename = oldFilename;
  }

  public abstract void run(ImageModel model);
}
