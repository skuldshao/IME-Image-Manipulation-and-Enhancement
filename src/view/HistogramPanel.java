package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.ImageModelState;
import model.image.Image;


/**
 * This panel is drawing the histogram line chart for the current image in the program,
 * the histogram includes red, blue,green and intensity color frequencies from 0 to the
 * max color values.
 */
public class HistogramPanel extends JPanel {
  private ImageModelState modelState;
  // in order to draw the histogram, we must get the current image from
  // model part, however, according to the MVC principle, the model should not interactive with
  // model directly, so we only call the modelstate interface in the model, where the view can get
  // a deep copy of the current image the program is working on without calling all other method
  // in the model interface to change the current model object

  /**
   * Construct a JPanel to draw the image histogram line chart for the current image in the model
   * state.
   *
   * @param modelState the ready-only model where the gui view can read the necessary information
   *                   *                   from the current model part.
   * @throws IllegalArgumentException if the given model state is null.
   */
  public HistogramPanel(ImageModelState modelState) {
    super();
    this.setBackground(Color.WHITE);
    this.setPreferredSize(new Dimension(350, 200));

    // initialize the modelstate
    if (modelState == null) {
      throw new IllegalArgumentException("The given model state cannot be null");
    }
    this.modelState = modelState;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graph2d = (Graphics2D) g;
    // get a deep copy of the current image
    Image currentImage = modelState.getImage();
    if (currentImage == null) {
      // if there's no image, only show a white background
      graph2d.setBackground(Color.WHITE);
    } else {
      graph2d.setBackground(Color.WHITE);
      ImageHistogramDataGenerator histogramDataGenerator =
              new ImageHistogramDataGeneratorImpl(this.modelState);
      // get all frequencies for red/green/blue/intensity from 0 to max color value of
      // the current image
      List<Integer> redColorFrequency = histogramDataGenerator.getRedColorFrequencies();
      List<Integer> greenColorFrequency = histogramDataGenerator.getGreenColorFrequencies();
      List<Integer> blueColorFrequency = histogramDataGenerator.getBlueColorFrequencies();
      List<Integer> intensityColorFrequency = histogramDataGenerator.getIntensityColorFrequencies();

      // draw four lines: red/green/blue/intensity:
      drawOneColorLine(redColorFrequency, Color.red, currentImage, graph2d);
      drawOneColorLine(greenColorFrequency, Color.green, currentImage, graph2d);
      drawOneColorLine(blueColorFrequency, Color.blue, currentImage, graph2d);
      drawOneColorLine(intensityColorFrequency, Color.black, currentImage, graph2d);
    }
  }

  /**
   * Draw a 2d-line chart for given current color frequencies on the given Grahphics2D background.
   * The x-asix represents the color value from 0 to the max value of the given current image.
   * The y-asix represents the frequencies for each color value. The line is drawn using the given
   * color.
   *
   * @param colorFrequency the color requires need to be drawn on
   * @param lineColor      the color to draw the line
   * @param currentImage   the current image the user is working on
   * @param graph2d        the background of this line chart
   */
  private void drawOneColorLine(List<Integer> colorFrequency, Color lineColor,
                                Image currentImage, Graphics2D graph2d) {
    //get Histogram Line points:
    // resize the line points based on their values:
    int redMax = maximumColorValue(colorFrequency);
    int redMin = minimumColorValue(colorFrequency);
    List<Integer> colorFrequencies = rescaleValues(colorFrequency, redMin, redMax);
    colorFrequencies = flipValues(colorFrequencies);

    //draw lines
    int numberOfColors = currentImage.getMaxColor();
    for (int i = 0; i < numberOfColors - 1; i++) {
      // draw current color frequencies:
      graph2d.setColor(lineColor);
      int x1 = i;
      int y1 = colorFrequencies.get(i);
      int x2 = i + 1;
      int y2 = colorFrequencies.get(i + 1);
      graph2d.drawLine(x1 * 2, y1, x2 * 2, y2);
    }
  }

  /**
   * Compute the maximum frequencies in the given color frequencies.
   *
   * @param allColorValues the color frequencies to be computed for the max value
   * @return the maximum frequencies in the given color frequencies.
   */
  private int maximumColorValue(List<Integer> allColorValues) {
    int maximum = Integer.MIN_VALUE;
    for (int i = 0; i < allColorValues.size(); i++) {
      if (allColorValues.get(i) > maximum) {
        maximum = allColorValues.get(i);
      }
    }
    return maximum;
  }

  /**
   * Compute the minimum frequencies in the given color frequencies.
   *
   * @param allColorValues the color frequencies to be computed for the min value
   * @return the  minimum frequencies in the given color frequencies.
   */
  private int minimumColorValue(List<Integer> allColorValues) {
    int minimum = Integer.MAX_VALUE;
    for (int i = 0; i < allColorValues.size(); i++) {
      if (allColorValues.get(i) < minimum) {
        minimum = allColorValues.get(i);
      }
    }
    return minimum;
  }

  /**
   * Rescale the given list so that when using it drawing a line chart on this Jpanel, it can
   * perfectly this panel's size.
   *
   * @param list          the list to be scaled.
   * @param calculatedMin the minimum value in the given list
   * @param calculatedMax the maximum value in the given list
   * @return a rescaled list to fit in the current pane's dimension.
   */
  private List<Integer> rescaleValues(List<Integer> list,
                                      double calculatedMin, double calculatedMax) {
    List<Integer> rescaledValues = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      double calculatedValue = (
              200 * ((double) list.get(i) - calculatedMin) / (calculatedMax - calculatedMin));
      rescaledValues.add((int) calculatedValue);
    }
    return rescaledValues;
  }

  /**
   * Flips all the values in the given list to ensure that the origin of the graph drawn from this
   * list is located at bottomLeft corner.
   *
   * @param list the given list to be flipped for relocating the origin.
   * @return a filpped list that supports the bottomLeft corner as the origin in the graph.
   */
  private List<Integer> flipValues(List<Integer> list) {
    int value;
    for (int i = 0; i < list.size(); i++) {
      value = list.get(i) - 125;
      list.set(i, 125 - value);
    }
    return list;
  }
}
