import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.image.Image;
import model.image.ImageImpl;
import model.image.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for ImageImpl Class.
 */
public class ImageImplTest {
  Image image1;
  Image image2;
  Image image3;

  @Before
  public void setup() {
    image1 = new ImageImpl(10, 10, 20, "image1");
    image2 = new ImageImpl(20, 10, 10, "image2");
    image3 = new ImageImpl(10, 20, 10, "image3");

  }

  // test illegal constructors
  @Test(expected = IllegalArgumentException.class)
  public void illegelWidth() {
    new ImageImpl(-20, 3, 20, "photo1");
    new ImageImpl(-10, -20, 30, "photo2");
    new ImageImpl(-10, -20, -30, "photo3");
    new ImageImpl(-10, -20, -10, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegelHeight() {
    new ImageImpl(10, -20, 30, "photo");
    new ImageImpl(10, -20, -30, "photo");
    new ImageImpl(10, -20, -10, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegelMaxColor() {
    new ImageImpl(10, 20, -30, "photo");
    new ImageImpl(10, 20, -10, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illgelName() {
    new ImageImpl(10, 20, 30, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ilegelContents() {
    new ImageImpl(10, 20, 30, "nello", null);
  }

  @Test
  public void testGetHeight() {
    setup();
    assertEquals(10, image1.getHeight());
    assertEquals(10, image2.getHeight());
    assertEquals(20, image3.getHeight());
  }

  @Test
  public void testGetWidth() {
    setup();
    assertEquals(10, image1.getWidth());
    assertEquals(20, image2.getWidth());
    assertEquals(10, image3.getWidth());
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalGetPixel() {
    image1.getPixel(-10, 9);
    image1.getPixel(12, 9);
    image1.getPixel(7, -2);
    image1.getPixel(7, 88);
    image1.getPixel(-10, -20);
    image1.getPixel(-10, 14);
    image1.getPixel(12, -20);
    image1.getPixel(12, 14);
  }

  @Test
  public void testGetPixel() {
    Assert.assertEquals(new PixelImpl(0, 0, 0, 20),
            image1.getPixel(1, 1));
    Assert.assertEquals(new PixelImpl(0, 0, 0, 10),
            image2.getPixel(1, 1));
    Assert.assertEquals(new PixelImpl(0, 0, 0, 10),
            image3.getPixel(1, 1));
    image1.updatePixel(1, 1, new PixelImpl(10, 20, 12, 20));
    assertEquals(new PixelImpl(10, 20, 12, 20),
            image1.getPixel(1, 1));
    image2.updatePixel(1, 1, new PixelImpl(10, 8, 10, 10));
    assertEquals(new PixelImpl(10, 8, 10, 10),
            image2.getPixel(1, 1));
    image3.updatePixel(1, 1, new PixelImpl(10, 5, 4, 10));
    assertEquals(new PixelImpl(10, 5, 4, 10),
            image3.getPixel(1, 1));
  }

  @Test
  public void testGetMaxColor() {
    assertEquals(20, image1.getMaxColor());
    assertEquals(10, image2.getMaxColor());
    assertEquals(10, image3.getMaxColor());
  }

  @Test
  public void testGetName() {
    assertEquals("image1", image1.getName());
    assertEquals("image2", image2.getName());
    assertEquals("image3", image3.getName());
  }

  @Test
  public void testUpdatePixel() {
    image1.updatePixel(1, 1, new PixelImpl(10, 20, 12, 20));
    assertEquals(new PixelImpl(10, 20, 12, 20),
            image1.getPixel(1, 1));
    image2.updatePixel(1, 1, new PixelImpl(10, 8, 10, 10));
    assertEquals(new PixelImpl(10, 8, 10, 10),
            image2.getPixel(1, 1));
    image3.updatePixel(1, 1, new PixelImpl(10, 5, 4, 10));
    assertEquals(new PixelImpl(10, 5, 4, 10),
            image3.getPixel(1, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegelUpdatePixelInputs() {
    image1.updatePixel(-10, 9, new PixelImpl(2, 3, 2, 20));
    image1.updatePixel(12, 9, new PixelImpl(2, 3, 2, 20));
    image1.updatePixel(7, -2, new PixelImpl(2, 3, 2, 20));
    image1.updatePixel(7, 88, new PixelImpl(2, 3, 2, 20));
    image1.updatePixel(3, 4, null);
    image1.updatePixel(-10, 9, null);
    image1.updatePixel(12, 9, null);
    image1.updatePixel(7, -2, null);
    image1.updatePixel(7, 88, null);
    image1.updatePixel(-10, -20, null);
    image1.updatePixel(-10, 14, null);
    image1.updatePixel(12, -20, null);
    image1.updatePixel(12, 14, null);
    image1.updatePixel(-10, 9, new PixelImpl(2, 3, 2, 20));
    image1.updatePixel(12, 9, new PixelImpl(2, 3, 2, 20));
    image1.updatePixel(7, -2, new PixelImpl(2, 3, 2, 20));
    image1.updatePixel(7, 88, new PixelImpl(2, 3, 2, 20));
    image1.updatePixel(-10, -20, new PixelImpl(2, 3, 2, 20));
    image1.updatePixel(-10, 14, new PixelImpl(2, 3, 2, 20));
    image1.updatePixel(12, -20, new PixelImpl(2, 3, 2, 20));
    image1.updatePixel(12, 14, new PixelImpl(2, 3, 2, 20));
  }
}