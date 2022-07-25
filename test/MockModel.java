import java.util.List;

import model.ImageModel;
import model.ImageModelImpl;
import model.image.Image;
import model.image.Pixel;

/**
 * This is a mock model of the program for test purpose-onlu.
 */
public class MockModel implements ImageModel {
  private StringBuilder log;
  private ImageModel model;

  /**
   * Construct a mock model for testing.
   *
   * @param log the log records the current method is called.
   */
  public MockModel(StringBuilder log) {
    this.log = log;
    this.model = new ImageModelImpl();
  }

  @Override
  public void component(String oldImageName, String newImagename, Pixel.Channel channel) {
    log.append("Component method is running");
  }

  @Override
  public void valueGreyImage(String oldImageName, String newImagename) {
    log.append("valueGreyImage method is running");
  }

  @Override
  public void intensityGreyImage(String oldImageName, String newImagename) {
    log.append("Intensity method is running");
  }

  @Override
  public void lumaGreyImage(String oldImageName, String newImagename) {
    log.append("lumaGreyImage method is running");
  }

  @Override
  public void horizontalFlip(String oldImageName, String newImagename) {
    log.append("horizontalFlip method is running");
  }

  @Override
  public void verticalFlip(String oldImageName, String newImagename) {
    log.append("verticalFlip method is running");
  }

  @Override
  public void brightness(double value, String oldImageName, String newImagename) {
    log.append("brightness method is running");
  }

  @Override
  public void blur(String oldImageName, String newImagename) {
    log.append("blur method is running");
  }

  @Override
  public void sharpen(String oldImageName, String newImagename) {
    log.append("sharpen method is running");
  }

  @Override
  public void sepia(String oldImageName, String newImagename) {
    log.append("sepia method is running");
  }

  @Override
  public void updateCurrentImage(Image image) {
    log.append("updateCurrentImage method is running");
  }

  @Override
  public void updateAllLoadedImage(List<Image> images) {
    log.append("updateAllLoadedImage method is running");
  }

  @Override
  public Image getImage() {
    return model.getImage();
  }

  @Override
  public List<Image> getAllLoadedImage() {
    return model.getAllLoadedImage();
  }

  @Override
  public String getCurrentImageName() {
    return model.getCurrentImageName();
  }
}
