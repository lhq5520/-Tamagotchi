package tamagochi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.Border;


/**
 * Implementation of the PetView interface using Swing for the Tamagochi game.
 * This class provides the graphical user interface for interacting with the pet.
 */
public class PetViewImpl extends JFrame implements PetView {
  private JLabel petImageLabel;

  private JLabel moodLabel;
  private JLabel gameOverLabel;

  private JProgressBar hungerBar;
  private JProgressBar hygieneBar;
  private JProgressBar socialBar;
  private JProgressBar sleepyBar;
  private JProgressBar healthBar;

  private JButton feedButton;
  private JButton cleanButton;
  private JButton playButton;
  private JButton sleepButton;
  private JButton resetButton;


  /**
   * Constructs a new PetViewImpl and initializes the GUI components.
   */
  public PetViewImpl() {
    setTitle("Tamagochi");
    setSize(500, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // Initialize GUI components

    add(northLayout(), BorderLayout.NORTH);
    add(southLayout(), BorderLayout.SOUTH);
    add(centerLayout(), BorderLayout.CENTER);
    setVisible(true);
  }

  /**
   * Creates the layout for the south container, including function buttons and status panel.
   *
   * @return The JPanel containing the south layout.
   */
  private JPanel southLayout() {
    JPanel southContainer = new JPanel();
    southContainer.setLayout(new BorderLayout());

    // Add function button to the top of the container
    southContainer.add(createButtonPanel(), BorderLayout.CENTER);

    // Add status to the bottom of the container
    southContainer.add(createStatusPanel(), BorderLayout.SOUTH);

    //southContainer.setBorder(BorderFactory.createLineBorder(Color.BLUE)); // Debug border
    return southContainer;
  }

  /**
   * Creates the layout for the north container, including the status text panel.
   *
   * @return The JPanel containing the north layout.
   */
  private JPanel northLayout() {
    JPanel northContainer = new JPanel();
    northContainer.setLayout(new BorderLayout());

    // Add message center to the top of the container
    northContainer.add(createStatusTextPanel(), BorderLayout.CENTER);

    //northContainer.setBorder(BorderFactory.createLineBorder(Color.GREEN)); // Debug border
    return northContainer;
  }

  /**
   * Creates the layout for the center container, including the pet image panel.
   *
   * @return The JPanel containing the center layout.
   */
  private JPanel centerLayout() {
    JPanel northContainer = new JPanel();
    northContainer.setLayout(new BorderLayout());

    // Add pet image to the top of the container
    northContainer.add(createPetPanel(), BorderLayout.CENTER);

    //northContainer.setBorder(BorderFactory.createLineBorder(Color.MAGENTA)); // Debug border
    return northContainer;
  }

  /**
   * Creates the panel for displaying the pet image.
   *
   * @return The JPanel containing the pet image.
   */
  private JPanel createPetPanel() {
    JPanel petPanel = new JPanel();
    petPanel.setBackground(new Color(255, 204, 153));

    ImageIcon originalIcon = new ImageIcon("/img/default.gif");
    Image originalImage = originalIcon.getImage();

    // Add a decorative border
    Border outerBorder = BorderFactory.createLineBorder(Color.BLACK, 5); // Thick black border
    Border innerBorder =
        BorderFactory.createEmptyBorder(10, 10, 10, 10); // Padding inside the border
    petPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

    // Calculate scaled dimensions
    int originalWidth = originalIcon.getIconWidth();
    int originalHeight = originalIcon.getIconHeight();
    int newWidth = 200; // Desired width
    int newHeight = (originalHeight * newWidth) / originalWidth; // Maintain aspect ratio

    Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    petImageLabel = new JLabel(new ImageIcon(scaledImage));

    petPanel.add(petImageLabel);
    return petPanel;
  }

  /**
   * Creates the panel for displaying the status bars.
   *
   * @return The JPanel containing the status bars.
   */
  private JPanel createStatusPanel() {
    JPanel statusPanel = new JPanel();
    statusPanel.setLayout(new GridLayout(5, 1));
    hungerBar = createStatusBar("Satiety");
    hygieneBar = createStatusBar("Hygiene");
    socialBar = createStatusBar("Social");
    sleepyBar = createStatusBar("Energy");
    healthBar = createStatusBar("Health");
    statusPanel.add(hungerBar);
    statusPanel.add(hygieneBar);
    statusPanel.add(socialBar);
    statusPanel.add(sleepyBar);
    statusPanel.add(healthBar);

    return statusPanel;
  }

  /**
   * Creates a styled JProgressBar with a label.
   *
   * @param label The label for the progress bar.
   * @return The styled JProgressBar.
   */
  private JProgressBar createStatusBar(String label) {
    JProgressBar bar = new JProgressBar(0, 100);
    bar.setStringPainted(false);
    //bar.setBorder(BorderFactory.createTitledBorder(label));

    bar.setFont(new Font("Arial", Font.BOLD, 12));
    bar.setPreferredSize(new Dimension(400, 35));

    bar.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createTitledBorder(label), // Outer titled border
        BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY, 2), // Middle line border
            BorderFactory.createEmptyBorder(2, 2, 2, 2) // Inner padding
        )
    ));

    // Gradient colors
    bar.addChangeListener(e -> {
      int value = bar.getValue();
      if (value >= 50) {
        bar.setForeground(new Color(50, 200, 50));
        bar.setBackground(new Color(200, 255, 200));
      } else if (value >= 20) {
        bar.setForeground(new Color(255, 200, 50));
        bar.setBackground(new Color(255, 250, 200));
      } else {
        bar.setForeground(new Color(200, 50, 50));
        bar.setBackground(new Color(255, 200, 200));
      }
    });

    // Tooltip
    bar.setToolTipText(label);

    return bar;
  }


  /**
   * Creates the panel for displaying the function buttons.
   *
   * @return The JPanel containing the function buttons.
   */
  private JPanel createButtonPanel() {
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(255, 230, 204));
    feedButton = createStyledButton("Feed", new Color(0, 255, 3, 223));
    cleanButton = createStyledButton("Clean", new Color(102, 204, 255));
    playButton = createStyledButton("Play", new Color(253, 3, 167));
    sleepButton = createStyledButton("Sleep", new Color(204, 153, 255));
    resetButton = createStyledButton("Respawn", new Color(255, 0, 0));
    buttonPanel.add(feedButton);
    buttonPanel.add(cleanButton);
    buttonPanel.add(playButton);
    buttonPanel.add(sleepButton);
    buttonPanel.add(resetButton);

    return buttonPanel;
  }

  /**
   * Creates a styled JButton with specified text and background color.
   *
   * @param text    The text for the button.
   * @param bgColor The background color for the button.
   * @return The styled JButton.
   */
  private JButton createStyledButton(String text, Color bgColor) {
    JButton button = new JButton(text);
    button.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); // Playful font
    button.setBackground(bgColor); // Set background color
    button.setForeground(Color.WHITE); // White text
    button.setFocusPainted(false);
    button.setBorder(BorderFactory.createEtchedBorder());
    return button;
  }

  /**
   * Creates the panel for displaying the status text (mood and game over labels).
   *
   * @return The JPanel containing the status text.
   */
  private JPanel createStatusTextPanel() {
    JPanel statusTextPanel = new JPanel();
    statusTextPanel.setLayout(new GridLayout(2, 1));
    statusTextPanel.setBackground(new Color(255, 230, 204));
    moodLabel = new JLabel("mood label initialized", SwingConstants.CENTER);
    gameOverLabel = new JLabel("", SwingConstants.CENTER);
    statusTextPanel.add(moodLabel);
    statusTextPanel.add(gameOverLabel);

    return statusTextPanel;
  }

  /**
   * Updates the progress bars based on the pet's current status.
   *
   * @param hunger  The hunger level of the pet.
   * @param hygiene The hygiene level of the pet.
   * @param social  The social level of the pet.
   * @param sleepy  The sleepiness level of the pet.
   * @param health  The health level of the pet.
   */
  @Override
  public void updateBars(int hunger, int hygiene, int social, int sleepy, int health) {
    hungerBar.setValue(hunger);
    hygieneBar.setValue(hygiene);
    socialBar.setValue(social);
    sleepyBar.setValue(sleepy);
    healthBar.setValue(health);
  }

  /**
   * Updates the pet's mood displayed in the view.
   *
   * @param mood The current mood of the pet.
   */
  @Override
  public void updateMood(Mood mood) {
    switch (mood) {
      case HAPPY:
        moodLabel.setText("Mood: HAPPY - I'm on cloud nine, life is amazing!");
        moodLabel.setForeground(new Color(34, 139, 34)); // Fun green color
        moodLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        break;

      case GRUMPY:
        moodLabel.setText("Mood: GRUMPY - Everything is annoying today. GRRR!");
        moodLabel.setForeground(new Color(255, 165, 0)); // Orange color
        moodLabel.setFont(new Font("Impact", Font.BOLD, 18));
        break;

      case DEPRESSIVE:
        moodLabel.setText("Mood: DEPRESSIVE - Feeling blue... Can I get a hug?");
        moodLabel.setForeground(new Color(70, 70, 70)); // Gray color
        moodLabel.setFont(new Font("Serif", Font.ITALIC, 18));
        break;

      default:
        moodLabel.setText("Mood: UNKNOWN - I can't figure out my feelings...");
        moodLabel.setForeground(Color.BLACK); // Default black color
        moodLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        break;
    }
  }

  /**
   * Updates the game-over label based on the game state.
   *
   * @param isGameOver True if the game is over, false otherwise.
   */
  @Override
  public void updateGameOver(boolean isGameOver) {
    if (isGameOver) {
      gameOverLabel.setText("Your Pet Has Passed Away. Click 'Respawn' to start again");
      gameOverLabel.setForeground(new Color(255, 0, 0));
      gameOverLabel.setFont(new Font("Impact", Font.BOLD, 20));
      moodLabel.setVisible(false);
    }
    if (!isGameOver) {
      moodLabel.setVisible(true);
      gameOverLabel.setForeground(new Color(21, 21, 21));
      gameOverLabel.setFont(new Font("Arial", Font.PLAIN, 10));
    }
  }

  /**
   * Updates the displayed survival time.
   *
   * @param text The string representing the survival time.
   */
  @Override
  public void updateSurvivalTime(String text) {
    gameOverLabel.setText("Your Pet Has Lived: " + text);
  }

  /**
   * Updates the displayed pet image.
   *
   * @param imagePath The path to the image file representing the pet's current state.
   */
  @Override
  public void setPetImage(String imagePath) {
    URL resource = getClass().getResource(imagePath);
    if (resource == null) {
      System.err.println("Resource not found: " + imagePath);
      petImageLabel.setIcon(null);
      return;
    }

    petImageLabel.setIcon(new ImageIcon(resource));
  }

  /**
   * Adds a listener to the "Feed" button.
   *
   * @param listener The ActionListener to handle the "Feed" button click.
   */
  @Override
  public void addFeedListener(ActionListener listener) {
    feedButton.addActionListener(listener);
  }

  /**
   * Adds a listener to the "Clean" button.
   *
   * @param listener The ActionListener to handle the "Clean" button click.
   */
  @Override
  public void addCleanListener(ActionListener listener) {
    cleanButton.addActionListener(listener);
  }

  /**
   * Adds a listener to the "Play" button.
   *
   * @param listener The ActionListener to handle the "Play" button click.
   */
  @Override
  public void addPlayListener(ActionListener listener) {
    playButton.addActionListener(listener);
  }

  /**
   * Adds a listener to the "Sleep" button.
   *
   * @param listener The ActionListener to handle the "Sleep" button click.
   */
  @Override
  public void addSleepListener(ActionListener listener) {
    sleepButton.addActionListener(listener);
  }

  /**
   * Adds a listener to the "Reset" button.
   *
   * @param listener The ActionListener to handle the "Reset" button click.
   */
  @Override
  public void addResetListener(ActionListener listener) {
    resetButton.addActionListener(listener);
  }
}

