import java.io.IOException;
import java.nio.CharBuffer;

/**
 * This class is for testing purpose only, which would throw a IOE exception.
 */
public class FakeReadable implements Readable {
  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException("Cannot Read");
  }
}
