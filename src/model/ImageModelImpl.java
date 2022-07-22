package model;

import java.util.ArrayList;
import java.util.List;

import model.image.Image;
import model.image.Pixel;
import model.manipulation.ImageManipulationModel;
import model.manipulation.changebrigntness.ChangeBrightness;
import model.manipulation.flip.FlipImage;
import model.manipulation.greyscale.IntensityGreyScaleImage;
import model.manipulation.greyscale.LumaGreyScaleImage;
import model.manipulation.greyscale.ValueGreyScaleImage;
import model.manipulation.greyscale.component.BlueComponent;
import model.manipulation.greyscale.component.GreenComponent;
import model.manipulation.greyscale.component.RedComponent;

/**
 * This class represents a simple inplementation of the model of this program.It includes the
 * actions that it can perform all the operations the user can apply on a given image,
 * like getting the red/green/blue component-image getting the value/intensity/luma grey image,
 * flipping a image horizontally/vertically, darkening/brightening an image and exporting/importing
 * images.
 */
public class ImageModelImpl implements ImageModel {
  private Image image; // the current image the program is working on
  private List<Image> allImages; // all the images the user loaded into the program

  /**
   * Constuts a ImageModelImpl object for this program.
   */
  public ImageModelImpl() {
    this.allImages = new ArrayList<>();
  }

  @Override
  public void redComponent(String oldImageName, String newImagename) {
    updateImage(oldImageName, newImagename, new RedComponent(Pixel.Channel.Red, newImagename));
  }

  @Override
  public void greenComponent(String oldImageName, String newImagename) {
    updateImage(oldImageName, newImagename, new GreenComponent(Pixel.Channel.Green, newImagename));
  }

  @Override
  public void blueComponent(String oldImageName, String newImagename) {
    updateImage(oldImageName, newImagename, new BlueComponent(Pixel.Channel.Blue, newImagename));
  }

  @Override
  public void valueGreyImage(String oldImageName, String newImagename) {
    updateImage(oldImageName, newImagename, new ValueGreyScaleImage(newImagename));
  }

  @Override
  public void intensityGreyImage(String oldImageName, String newImagename) {
    updateImage(oldImageName, newImagename, new IntensityGreyScaleImage(newImagename));
  }

  @Override
  public void lumaGreyImage(String oldImageName, String newImagename) {
    updateImage(oldImageName, newImagename, new LumaGreyScaleImage(newImagename));
  }

  @Override
  public void horizontalFlip(String oldImageName, String newImagename) {
    updateImage(oldImageName, newImagename, new FlipImage(ImageManipulationModel.Flip.Horizontal,
            newImagename));
  }

  @Override
  public void verticalFlip(String oldImageName, String newImagename) {
    updateImage(oldImageName, newImagename, new FlipImage(ImageManipulationModel.Flip.Vertical,
            newImagename));
  }

  @Override
  public void brightness(double value, String oldImageName, String newImagename) {
    updateImage(oldImageName, newImagename, new ChangeBrightness(value,
            newImagename));
  }


  @Override
  public void load(String filepath, String imageName) {
    // check if the given image is null
    checkName(imageName);
    if (this.image != null) {
      // check if the user would like to overwrite the current image
      if (imageName.equals(this.image.getName())) {
        this.image = ImageUtil.readPPM(filepath, imageName);
      } else {
        // check if the user would like to overwrite any of previous loaded image
        boolean isLoaded = false;
        for (Image image : allImages) {
          if (image.getName().equals(imageName)) {
            image = ImageUtil.readPPM(filepath, imageName);
            isLoaded = true;
            break;
          }
        }
        // if the image the user would like to overwrite doesn't exist, then the progran would
        // import the image from the given filepath automatically and name it as the given
        // imagename
        if (!isLoaded) {
          allImages.add(this.image);
          this.image = ImageUtil.readPPM(filepath, imageName);
        }
      }
    } else {
      // load a new image into the program
      this.image = ImageUtil.readPPM(filepath, imageName);

    }
  }

  @Override
  public void save(String filepath, String oldImageName) {
    // check if the current image is empty/null
    checkImage();
    // check if the given image name is null
    checkName(oldImageName);
    // check if the user would like to export the current image
    if (oldImageName.equals(this.image.getName())) {
      ImageUtil.writePPM(filepath, this.image);
    } else {
      // check if the user would like to export any previous loaded image
      Image oldImage;
      boolean isLoaded = false;
      for (Image image : allImages) {
        if (image.getName().equals(oldImageName)) {
          oldImage = image;
          isLoaded = true;
          ImageUtil.writePPM(filepath, oldImage);
          break;
        }
      }
      // if the image the user would like to export is never loaded:
      if (!isLoaded) {
        throw new IllegalArgumentException("Cannot save this file because it's never loaded");
      }
    }
  }

  /**
   * update a image with the given olderimagename by using the given ImageManipulationModel function
   * object, then store the updated image with the new imagename into the program, the original
   * image would be moved into the image archive/history archive of this program.
   *
   * @param oldImageName the name of original image to be edited
   * @param newImagename the name of new image after editing
   * @param manipulation the function object to edit the original image
   * @throws IllegalArgumentException if the current image is null
   * @throws IllegalArgumentException if the old/new image file is null
   * @throws IllegalArgumentException if the user tried to update any image with the
   *                                  ldimagename but the old image has never been loaded
   */
  private void updateImage(String oldImageName, String newImagename,
                           ImageManipulationModel manipulation) {
    checkImage();
    checkName(oldImageName);
    checkName(newImagename);
    allImages.add(this.image);
    Image oldImage;
    boolean isLoaded = false;
    for (Image image : allImages) {
      if (image.getName().equals(oldImageName)) {
        oldImage = image;
        isLoaded = true;
        this.image = manipulation.edit(oldImage);
        break;
      }
    }
    if (!isLoaded) {
      throw new IllegalArgumentException("Cannot edit this file because it's never loaded");
    }
  }

  /**
   * Check if the current iamge is null.if so, throw a IllegellArgumentException.
   *
   * @throws IllegalArgumentException if the current image is null
   */
  private void checkImage() {
    if (this.image == null) {
      throw new IllegalArgumentException("The current image is null");
    }
  }

  /**
   * Check if the given image is null, if so, throw a IllegellArgumentException.
   *
   * @param name the name to be checked
   * @throws IllegalArgumentException if the given name is null
   */
  private void checkName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("The name cannot be null");
    }
  }
}
