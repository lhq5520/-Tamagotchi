package tamagochi;

/**
 * The `RunModel` class is the entry point for running the Tamagochi game model.
 * It simulates a series of predefined actions on the pet model and displays the pet's state.
 */
public class RunModel {
  /**
   * The main method to start the Tamagochi game model.
   * It initializes the pet model, simulates a series of predefined actions,
   * and displays the pet's state.
   *
   * @param args Command-line arguments (not used).
   */
  public static void main(String[] args) {
    // Create a pet model instance
    PetModelImpl pet = new PetModelImpl();

    System.out.println("=== STARTING THE GAME ===");
    displayAllFields(pet, "Game Started");

    // Simulate a series of predefined actions
    try {
      System.out.println("\n=== ACTION SEQUENCE ===");

      pet.performAction(PetActions.FEED);
      displayAllFields(pet, "FEED");

      pet.performAction(PetActions.SHOWER);
      displayAllFields(pet, "SHOWER");

      pet.performAction(PetActions.SOCIALIZE);
      displayAllFields(pet, "SOCIALIZE");

      pet.performAction(PetActions.SLEEP);
      displayAllFields(pet, "SLEEP");

      // Simulate a health drop to change the mood
      System.out.println("\n=== SIMULATING HEALTH DROP ===");
      pet.setHealthLevel(20); // Drop health
      pet.updateState(); // Update the pet's state
      displayAllFields(pet, "Health Drop");

      // Perform an action in a depressive mood
      pet.performAction(PetActions.FEED);
      displayAllFields(pet, "FEED (Depressive)");

      // End the game
      System.out.println("\n=== ENDING THE GAME ===");
      pet.setHealthLevel(0);
      pet.updateState();
      displayAllFields(pet, "End Game");
      System.out.println("Game Over: " + pet.isGameOver());
      System.out.println("Total Survival Time: " + pet.getSurvivalTime() + " seconds");

    } catch (IllegalStateException e) {
      System.err.println("Error: " + e.getMessage());
    }

    // Reset the game
    System.out.println("\n=== RESETTING THE GAME ===");
    pet.resetGame();
    displayAllFields(pet, "Game Reset");
  }

  /**
   * Helper method to display all fields of the pet model along with the action performed.
   * Prints the pet's hunger, hygiene, social, sleepy, health levels, mood, behavior,
   * game-over status, and survival time.
   *
   * @param pet    The {@link PetModelImpl} instance representing the pet.
   * @param action The action that was just performed.
   */
  private static void displayAllFields(PetModelImpl pet, String action) {
    System.out.println("\n--- Pet State After Action: " + action + " ---");
    System.out.println("Hunger Level: " + pet.getHungerLevel());
    System.out.println("Hygiene Level: " + pet.getHygieneLevel());
    System.out.println("Social Level: " + pet.getSocialLevel());
    System.out.println("Sleepy Level: " + pet.getSleepyLevel());
    System.out.println("Health Level: " + pet.getHealthLevel());
    System.out.println("Mood: " + pet.getMood());
    System.out.println("Behavior: "
        + (pet.getBehavior() != null ? pet.getBehavior().getClass().getSimpleName() : "None"));
    System.out.println("Game Over: " + pet.isGameOver());
    System.out.println("Survival Time: " + pet.getSurvivalTime() + " seconds");
    System.out.println("------------------");
  }
}
