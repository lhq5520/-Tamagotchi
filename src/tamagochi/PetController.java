package tamagochi;

/**
 * Interface for the Tamagotchi application's controller.
 * Manages interactions between the model and view,
 * handles user input, and updates the application state.
 */
public interface PetController {
  /**
   * Starts the Tamagotchi game, resetting the model state,
   * initializing the view, and beginning periodic updates.
   */
  void startGame();
}
