import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.image.Pixel;
import model.image.PixelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * This is the test class for PixelImpl class.
 */
public class PixelImplTest {
  Pixel pixel1;
  Pixel pixel2;

  @Before
  public void setup() {
    pixel1 = new PixelImpl(2, 3, 4, 10);
    pixel2 = new PixelImpl(3, 3, 3, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeMaxColor() {
    new PixelImpl(2, 3, 2, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidColor() {
    new PixelImpl(-2, 3, 2, 10);
    new PixelImpl(2, -3, 2, 10);
    new PixelImpl(2, 3, -2, 10);
    new PixelImpl(2, 33, 2, 10);
    new PixelImpl(2, 3, 22, 10);
    new PixelImpl(12, 3, 2, 10);
  }

  @Test
  public void testgetChannel() {
    assertEquals(2, pixel1.getChannel(Pixel.Channel.Red));
    assertEquals(4, pixel1.getChannel(Pixel.Channel.Blue));
    assertEquals(3, pixel1.getChannel(Pixel.Channel.Green));
    assertEquals(3, pixel2.getChannel(Pixel.Channel.Red));
    assertEquals(3, pixel2.getChannel(Pixel.Channel.Blue));
    assertEquals(3, pixel2.getChannel(Pixel.Channel.Green));
  }

  @Test
  public void testSetChannel() {
    assertEquals(2, pixel1.getChannel(Pixel.Channel.Red));
    assertEquals(4, pixel1.getChannel(Pixel.Channel.Blue));
    assertEquals(3, pixel1.getChannel(Pixel.Channel.Green));
    pixel1.setChannel(Pixel.Channel.Red, 3);
    pixel1.setChannel(Pixel.Channel.Green, 2);
    pixel1.setChannel(Pixel.Channel.Blue, 1);
    assertEquals(3, pixel1.getChannel(Pixel.Channel.Red));
    assertEquals(1, pixel1.getChannel(Pixel.Channel.Blue));
    assertEquals(2, pixel1.getChannel(Pixel.Channel.Green));

    assertEquals(3, pixel2.getChannel(Pixel.Channel.Red));
    assertEquals(3, pixel2.getChannel(Pixel.Channel.Blue));
    assertEquals(3, pixel2.getChannel(Pixel.Channel.Green));
    pixel2.setChannel(Pixel.Channel.Red, 4);
    pixel2.setChannel(Pixel.Channel.Green, 1);
    pixel2.setChannel(Pixel.Channel.Blue, 2);
    assertEquals(4, pixel2.getChannel(Pixel.Channel.Red));
    assertEquals(2, pixel2.getChannel(Pixel.Channel.Blue));
    assertEquals(1, pixel2.getChannel(Pixel.Channel.Green));
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegelColorSet() {
    pixel1.setChannel(Pixel.Channel.Red, -2);
    pixel1.setChannel(Pixel.Channel.Red, 12);
    pixel1.setChannel(Pixel.Channel.Green, -2);
    pixel1.setChannel(Pixel.Channel.Green, 12);
    pixel1.setChannel(Pixel.Channel.Blue, -2);
    pixel1.setChannel(Pixel.Channel.Blue, 12);
  }

  @Test
  public void testCEqual() {
    Pixel pixel3 = new PixelImpl(2, 3, 4, 10);
    Pixel pixel4 = new PixelImpl(2, 4, 4, 10);
    Pixel pixel5 = new PixelImpl(2, 3, 5, 10);
    Pixel pixel6 = new PixelImpl(1, 3, 4, 10);
    Pixel pixel7 = new PixelImpl(2, 3, 4, 8);
    List<String> list = new ArrayList<String>();
    assertEquals(pixel1, pixel1);
    assertEquals(pixel3, pixel1);
    assertNotEquals(pixel1, pixel2);
    assertNotEquals(pixel1, pixel4);
    assertNotEquals(pixel1, pixel5);
    assertNotEquals(pixel1, pixel6);
    assertNotEquals(pixel1, pixel7);
    assertNotEquals(pixel1, list);
  }

  @Test
  public void testhashcode() {
    Pixel pixel3 = new PixelImpl(2, 3, 4, 10);
    Pixel pixel4 = new PixelImpl(2, 4, 4, 10);
    Pixel pixel5 = new PixelImpl(2, 3, 5, 10);
    Pixel pixel6 = new PixelImpl(1, 3, 4, 10);
    Pixel pixel7 = new PixelImpl(2, 3, 4, 8);
    List<String> list = new ArrayList<String>();
    assertEquals(pixel1.hashCode(), pixel1.hashCode());
    assertEquals(pixel3.hashCode(), pixel1.hashCode());
    assertNotEquals(pixel1.hashCode(), pixel2.hashCode());
    assertNotEquals(pixel1.hashCode(), pixel4.hashCode());
    assertNotEquals(pixel1.hashCode(), pixel5.hashCode());
    assertNotEquals(pixel1.hashCode(), pixel6.hashCode());
    assertNotEquals(pixel1.hashCode(), pixel7.hashCode());
    assertNotEquals(pixel1.hashCode(), list.hashCode());
  }
}