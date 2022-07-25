package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.ImageModelState;
import model.image.Image;
import model.image.Pixel;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This is a simple GUI for the image editing program. This GUI would render the current image
 * the user is editing and updating it while the user click any buttons in the window to do any
 * image manipulations. This window would also show image histogram for the current image as
 * a line chart and update it accordingly when the user finish performing operations.
 */
public class GuiViewWindow extends JFrame implements GUIView {

  // Image Operations Buttons
  private final JButton redComponentButton;
  private final JButton greenComponentButton;
  private final JButton blueComponentButton;
  private final JButton horizontalFlip;
  private final JButton verticalFlip;
  private final JButton changeBrightnessButton;
  private final JTextField incrementforBrightness;
  private final JButton intensityGreyScaleButton;
  private final JButton valueGrayScaleButton;
  private final JButton lumaGrayScaleButton;
  private final JButton blurButton;
  private final JButton sharpenButton;
  private final JButton sepiaButton;

  //save/load buttons:
  private final JButton loadButton;
  private final JButton saveButton;

  //panel for showing the image
  private JPanel imagePanel;
  private JScrollPane imageScroller;

  // The panel to show the histogram of the current image:
  private JPanel histogramPanel;

  private final ImageModelState modelstate;
  // in order to draw the histogram, we must get the current image from
  // model part, however, according to the MVC principle, the model should not interactive with
  // model directly, so we only call the modelstate interface in the model, where the view can get
  // a deep copy of the current image the program is working on without calling all other method
  // in the model interface to change the current model object

  //INVARIANT: This model state cannot be null.

  /**
   * Constructs a GuiViewWindow object for this program so that the user can see the current image
   * and do interaction with the window.
   *
   * @param modelState the ready-only model where the gui view can read the necessary information
   *                   from the current model part.
   * @throws IllegalArgumentException if the given model state is null;
   */
  public GuiViewWindow(ImageModelState modelState) {
    super("Image Processor");
    if (modelState == null) {
      throw new IllegalArgumentException("The given modelstate cannot be null");
    }
    this.modelstate = modelState;
    setSize(new Dimension(1000, 800));
    setLayout(new BorderLayout());
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    // set up the histogram panel
    this.histogramPanel = new JPanel();
    histogramPanel.setPreferredSize(new Dimension(450, 550));
    this.placeHistogram();
    this.add(histogramPanel, BorderLayout.EAST);

    // set the image panel
    this.imagePanel = new JPanel();
    imagePanel.setPreferredSize(new Dimension(550, 550));
    imageScroller = new JScrollPane();
    this.placeImage();
    this.add(imagePanel, BorderLayout.CENTER);

    // set the image editing buttons and add listeners to all the buttons:
    redComponentButton = new JButton("Red Component");
    greenComponentButton = new JButton("Green Component");
    blueComponentButton = new JButton("Blue Component");
    horizontalFlip = new JButton("horizontal Flip");
    verticalFlip = new JButton("vertical Flip");
    changeBrightnessButton = new JButton("Change Brightness");
    incrementforBrightness = new JTextField(3);
    intensityGreyScaleButton = new JButton("Intensity GreyScale");
    valueGrayScaleButton = new JButton("Value GrayScale");
    lumaGrayScaleButton = new JButton("Luma GrayScale");
    blurButton = new JButton("Blur");
    sharpenButton = new JButton("Sharpen");
    sepiaButton = new JButton("Sepia");
    JPanel imageEditingOptionsPanel = new JPanel();
    this.initializeImageEditingOptionsPanel(imageEditingOptionsPanel);

    //Load and Save Panel -- Load,Save and add listeners to all the buttons:
    JPanel loadSavePanel = new JPanel();
    loadButton = new JButton("Load");
    saveButton = new JButton("Save");
    this.initializeLoadandSave(loadSavePanel);

    // Split Pane for Image Operations Panel and Load/Save Panel
    JSplitPane splitOperAndLoadAndSave = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
            imageEditingOptionsPanel, loadSavePanel);
    add(splitOperAndLoadAndSave, BorderLayout.WEST);

    pack();
    this.setVisible(true);

    try {
      // Set cross-platform Java L&F (also called "Metal")
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (UnsupportedLookAndFeelException e) {
      // handle exception
    } catch (ClassNotFoundException e) {
      // handle exception
    } catch (InstantiationException e) {
      // handle exception
    } catch (IllegalAccessException e) {
      // handle exception
    }
  }

  /**
   * This method is used for initializing the histogram panel to draw the image histogram line chart
   * for the current image and make updates when necessary.
   */
  private void placeHistogram() {
    this.histogramPanel.setBackground(new Color(240, 120, 150));
    this.histogramPanel.setBorder(BorderFactory.createTitledBorder("Image Histogram"));
    JPanel histogram = new HistogramPanel(this.modelstate);
    histogram.setPreferredSize(new Dimension(350, 250));
    histogramPanel.add(histogram);
    //JScrollPane pane = new JScrollPane();
    //pane.setPreferredSize(new Dimension(400, 400));
    // pane.add(histogram);
    //histogramPanel.add(pane);

  }

  /**
   * This method is used for initializing the image panel so that the user can see the current image
   * they're working on and if they make operations for the current image, the updates for image
   * would be shown immediately in the panel.
   */
  private void placeImage() {
    this.imagePanel.setBackground(new Color(250, 140, 200));
    this.imagePanel.setBorder(BorderFactory.createTitledBorder("Current Image"));
    Image image = modelstate.getImage();
    if (image != null) {
      BufferedImage currentImage = imageToBufferedImage(image);
      JLabel imageLabel = new JLabel();
      imageLabel.setIcon(new ImageIcon(currentImage));
      imageScroller = new JScrollPane(imageLabel);
      imageScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      imageScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

    } else {
      imageScroller = new JScrollPane();
    }
    // set the dimension for scroller the same of the panel's
    imageScroller.setPreferredSize(new Dimension(400, 400));
    imagePanel.add(imageScroller);
  }

  /**
   * Converts the current image in the program to a buffered image so that it can be showen in
   * front of the user.
   *
   * @param image the current image the user is editing.
   * @return current image in the program to a buffered image
   */
  private BufferedImage imageToBufferedImage(Image image) {
    BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Pixel currentPixel = image.getPixel(j, i);
        int red = currentPixel.getChannel(Pixel.Channel.Red);
        int green = currentPixel.getChannel(Pixel.Channel.Green);
        int blue = currentPixel.getChannel(Pixel.Channel.Blue);
        int rgb = (red << 16 | green << 8 | blue);
        bufferedImage.setRGB(j, i, rgb);
      }
    }
    return bufferedImage;
  }

  /**
   * Initialize the all buttons on the ImageEditingOptionsPanel.
   *
   * @param panel the ImageEditingOptionsPanel to be initialized
   */
  private void initializeImageEditingOptionsPanel(JPanel panel) {
    panel.setBorder(BorderFactory.createTitledBorder("Image Editing Operations"));
    panel.setBackground(new Color(150, 140, 200));
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    // Components:
    redComponentButton.setActionCommand("red");
    redComponentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(redComponentButton);
    greenComponentButton.setActionCommand("green");
    greenComponentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(greenComponentButton);
    blueComponentButton.setActionCommand("blue");
    blueComponentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(blueComponentButton);

    // flip:
    horizontalFlip.setActionCommand("horizontal");
    horizontalFlip.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(horizontalFlip);

    verticalFlip.setActionCommand("vertical");
    verticalFlip.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(verticalFlip);

    //Grayscale
    valueGrayScaleButton.setActionCommand("value");
    valueGrayScaleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(valueGrayScaleButton);

    intensityGreyScaleButton.setActionCommand("intensity");
    intensityGreyScaleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(intensityGreyScaleButton);

    lumaGrayScaleButton.setActionCommand("luma");
    lumaGrayScaleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(lumaGrayScaleButton);

    //Blur
    blurButton.setActionCommand("blur");
    blurButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(blurButton);

    //Sharpen
    sharpenButton.setActionCommand("sharpen");
    sharpenButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(sharpenButton);

    //Sepia
    sepiaButton.setActionCommand("sepia");
    sepiaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(sepiaButton);

    //changeBrightness
    JPanel changeBrightnessPanel = new JPanel();
    changeBrightnessPanel.setLayout(new FlowLayout());
    changeBrightnessPanel.setBackground(new Color(150, 140, 200));
    changeBrightnessPanel.add(incrementforBrightness);
    changeBrightnessButton.setActionCommand("changeBrightness");
    changeBrightnessPanel.add(changeBrightnessButton);
    panel.add(changeBrightnessPanel);
  }

  /**
   * Initialize the all buttons on the LoadandSavePanel.
   *
   * @param panel the LoadandSavePanel to be initialized
   */
  private void initializeLoadandSave(JPanel panel) {
    panel.setBorder(BorderFactory.createTitledBorder("Load/Save"));
    panel.setBackground(new Color(170, 130, 200));
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    loadButton.setActionCommand("load");
    loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(loadButton);
    saveButton.setActionCommand("save");
    saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(saveButton);
  }

  @Override
  public void refresh() {
    this.remove(imagePanel);
    imagePanel = new JPanel();
    this.placeImage();
    this.add(imagePanel, BorderLayout.CENTER);
    this.repaint();
  }

  @Override
  public void setViewLister(ActionListener listener) {
    loadButton.addActionListener(listener);
    saveButton.addActionListener(listener);
    redComponentButton.addActionListener(listener);
    greenComponentButton.addActionListener(listener);
    blueComponentButton.addActionListener(listener);
    horizontalFlip.addActionListener(listener);
    verticalFlip.addActionListener(listener);
    valueGrayScaleButton.addActionListener(listener);
    intensityGreyScaleButton.addActionListener(listener);
    lumaGrayScaleButton.addActionListener(listener);
    blurButton.addActionListener(listener);
    sharpenButton.addActionListener(listener);
    sepiaButton.addActionListener(listener);
    changeBrightnessButton.addActionListener(listener);
  }

  @Override
  public void renderMessage(String message) {
    if (message == null) {
      throw new IllegalArgumentException("Message cannot be null!");
    }
    JOptionPane.showMessageDialog(this, message);
  }

  @Override
  public String getLoadFilePath() {
    JFileChooser filechooser = new JFileChooser(".");
    FileNameExtensionFilter filter;
    filter = new FileNameExtensionFilter("JPG,PNG,BMP,PPM",
            "jpg", "png", "bmp", "ppm");
    filechooser.setFileFilter(filter);
    int retvalue = filechooser.showOpenDialog(GuiViewWindow.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = filechooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    return null;
  }

  @Override
  public String getSaveFilePath() {
    JFileChooser filechooser = new JFileChooser(".");
    int retvalue = filechooser.showOpenDialog(GuiViewWindow.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = filechooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    return null;
  }


  @Override
  public String preferName() {
    String imageName = JOptionPane.showInputDialog("" +
            "How would you like refer this image in the program?" +
            "(Please give the loaded image a name).");
    return imageName;
  }

  @Override
  public double getBrightness() {
    double amount = Double.parseDouble(this.incrementforBrightness.getText());
    return amount;
  }
}

