package tamagochi;

import java.awt.event.ActionListener;

/**
 * Implementation of the {@link PetView} interface for the Tamagotchi application.
 * Manages the graphical user interface (GUI) components and updates
 * the view based on the pet's state.
 */
public interface PetView {
  /**
   * Updates the progress bars based on the pet's current status.
   *
   * @param hunger  The hunger level of the pet.
   * @param hygiene The hygiene level of the pet.
   * @param social  The social level of the pet.
   * @param sleepy  The sleepiness level of the pet.
   * @param health  The health level of the pet.
   */
  void updateBars(int hunger, int hygiene, int social, int sleepy, int health);

  /**
   * Updates the pet's mood displayed in the view.
   *
   * @param mood The current mood of the pet.
   */
  void updateMood(Mood mood);

  /**
   * Updates the game-over label based on the game state.
   *
   * @param isGameOver True if the game is over, false otherwise.
   */
  void updateGameOver(boolean isGameOver);

  /**
   * Updates the displayed pet image.
   *
   * @param imagePath The path to the image file representing the pet's current state.
   */
  void setPetImage(String imagePath);

  /**
   * Updates the Survival Time.
   *
   * @param time The String of Survival Timer in the model.
   */
  void updateSurvivalTime(String time);

  /**
   * Adds a listener to the "Feed" button.
   *
   * @param listener The ActionListener to handle the "Feed" button click.
   */
  void addFeedListener(ActionListener listener);

  /**
   * Adds a listener to the "Clean" button.
   *
   * @param listener The ActionListener to handle the "Clean" button click.
   */
  void addCleanListener(ActionListener listener);

  /**
   * Adds a listener to the "Play" button.
   *
   * @param listener The ActionListener to handle the "Play" button click.
   */
  void addPlayListener(ActionListener listener);

  /**
   * Adds a listener to the "Sleep" button.
   *
   * @param listener The ActionListener to handle the "Sleep" button click.
   */
  void addSleepListener(ActionListener listener);

  /**
   * Adds a listener to the "Sleep" button.
   *
   * @param listener The ActionListener to handle the "Reset" button click.
   */
  void addResetListener(ActionListener listener);
}
