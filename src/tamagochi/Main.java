package tamagochi;

/**
 * Entry point for the Tamagotchi game application.
 * Initializes the model, view, and controller, and starts the game.
 */
public class Main {
  /**
   * The main method to start the Tamagotchi game application.
   *
   * @param args Command-line arguments (not used).
   */
  public static void main(String[] args) {
    // Create the model, view, and controller
    PetModel model = new PetModelImpl();
    PetView view = new PetViewImpl();
    PetController controller = new PetControllerImpl(model, view);

    // Start the game
    controller.startGame();
  }
}
