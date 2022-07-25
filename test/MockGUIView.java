import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.ImageModel;
import model.image.Image;
import view.GUIView;

/**
 * This is class is the mock GUI used for testing purpose-only.This class would work with
 * a appendable object.
 */
public class MockGUIView implements GUIView {
  private Appendable ap;
  private ImageModel model;
  private List<ActionListener> listeners;
  private String loadPath;
  private String savePath;
  private String preferedName;
  private double brigntness;
  private Image image;


  /**
   * Constructs an {@code MockGUIView} object which takes in an appendable for increased
   * flexibility.
   */
  public MockGUIView(Appendable ap, String loadPath, String savePath, String preferedName,
                     double brigntness, ImageModel model) {
    this.listeners = new ArrayList<>();
    this.ap = ap;
    this.loadPath = loadPath;
    this.savePath = savePath;
    this.preferedName = preferedName;
    this.brigntness = brigntness;
    this.model = model;

  }

  @Override
  public void refresh() {
    this.loadPath = "";
    this.savePath = "";
    this.preferedName = "";
    this.brigntness = 0;
    this.image = model.getImage();
  }

  @Override
  public String toString() {
    return "Number of listeners in this window: " + this.listeners.size();
  }

  @Override
  public void setViewLister(ActionListener listener) {
    this.listeners.add(listener);
  }

  @Override
  public String getLoadFilePath() {
    return this.loadPath;
  }

  @Override
  public String getSaveFilePath() {
    return this.savePath;
  }

  @Override
  public String preferName() {
    return this.preferedName;
  }

  @Override
  public double getBrightness() {
    return this.brigntness;
  }

  @Override
  public void renderMessage(String message) {
    try {
      this.ap.append(message);
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  /**
   * This is the test-purpose only method where we can get the current image for the mock GUI
   * so check if the image has been updated.
   *
   * @return the current image for the mock GUI
   */
  public Image getCurrentImageinView() {
    return this.image;
  }
}
