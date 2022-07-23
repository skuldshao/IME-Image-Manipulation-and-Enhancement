import java.io.IOException;

/**
 * This class is for testing purpose only, which would throw a IOE exception.
 */
public class FakeAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Cannot write");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Cannot write");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Cannot write");
  }
}
