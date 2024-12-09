package tamagochi;

/**
 * The `RunView` class is responsible for creating and updating the graphical
 * user interface (GUI) for the Tamagochi game.
 */
public class RunView {
  /**
   * The main method to start the Tamagochi game view.
   *
   * @param args Command-line arguments (not used).
   */
  public static void main(String[] args) {
    // Create the PetView
    PetViewImpl petView = new PetViewImpl();

    // Update the GUI
    petView.updateBars(50, 70, 40, 20, 90); // Set progress bars
    petView.updateMood(Mood.HAPPY); // Set initial mood
    petView.updateGameOver(true); // Game is not over

    // Set the image
    //petView.setPetImage("/img/Character.png");
  }
}
