package view;

import java.io.IOException;

/**
 * A class represents the view of image editor under the text/script mode
 * shown to the user. This class is in charge of visually representing and rendering
 * the program interactive(response) message as text after the user interact with
 * this program(show the state/progress of this program).
 */
public class ImageTextScriptView implements TextScriptView {
  private final Appendable ap;
  //INVARIANT: appendable isn't null
  //INVARIANT: model isn't null

  /**
   * Constructs a {@code ImageTextScriptView} object of image editor under the text/script mode
   * shown to the user. It will transmit the state of program to a given destination {@code ap}
   * object to render.
   *
   * @param ap represents a given destination for the message to be rendered
   * @throws IllegalArgumentException while the given  {@code ap} object is null
   */
  public ImageTextScriptView(Appendable ap) {
    if (ap == null) {
      throw new IllegalArgumentException("Appendable object provided!");
    }
    this.ap = ap;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.ap.append(message);
    } catch (IOException e) {
      throw new IOException("Transmission for rendering the message failed");
    }
  }
}
