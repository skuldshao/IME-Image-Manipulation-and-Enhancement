import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import view.ImageTextScriptView;
import view.TextScriptView;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for ImageTextScriptView class to ensure that the views' behavior
 * works as expected and properly deals with displaying text.
 */
public class ImageTextScriptViewTest {
  // test RenderMessage method
  @Test
  public void testRenderMessage() throws IOException {

    // test user-defined appendable object
    StringBuilder out = new StringBuilder();
    TextScriptView view = new ImageTextScriptView(out);
    view.renderMessage("Hello!");
    assertEquals("Hello!", out.toString());

    OutputStream result = new ByteArrayOutputStream();
    TextScriptView view2 = new ImageTextScriptView(new PrintStream(result));
    view2.renderMessage("Wow");
    assertEquals("Wow", result.toString());

    StringBuilder out2 = new StringBuilder();
    TextScriptView view3 = new ImageTextScriptView(out2);
    view3.renderMessage(null);
    assertEquals("null", out2.toString());

  }

  // test Illegel RenderMessage:
  @Test(expected = IOException.class)
  public void CannotAppendMessage() throws IOException {
    FakeAppendable fakeAppendable = new FakeAppendable();
    TextScriptView fakeView = new ImageTextScriptView(fakeAppendable);
    fakeView.renderMessage("Good Morning");

  }

  // test illegel Contructor

  @Test(expected = IllegalArgumentException.class)
  public void nullAppendable() {
    new ImageTextScriptView(null);
  }

}
