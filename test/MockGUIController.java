import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.ImageEditorController;
import controller.commands.Commands;
import controller.commands.LoadCommands;
import model.ImageModel;
import view.GUIView;

/**
 * This is the mock controller for test purpose only.
 */
public class MockGUIController implements ImageEditorController, ActionListener {
  GUIView view;
  ImageModel model;

  /**
   * Constructs a mock GUI controller for testing.
   */
  public MockGUIController(GUIView view, ImageModel model) {
    this.view = view;
    this.model = model;
  }


  @Override
  public void editImage() {
    this.view.setViewLister(this);
    Commands load = new LoadCommands(
            "res/check-width-equal-to-height.ppm", "normal");
    load.run(model);
    this.view.refresh();
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    return;
  }
}
