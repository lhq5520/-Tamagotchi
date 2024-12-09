package tamagochi;

/**
 * Interface representing the virtual pet in the Tamagotchi application.
 * Provides methods to manage and update the pet's state, perform actions,
 * and retrieve or modify various attributes such as hunger, hygiene, social,
 * sleep, health levels, mood, and behavior.
 */
public interface PetModel {

  /**
   * Updates the state of the pet over time.
   * This method is typically called periodically to simulate the passage of time
   * and adjust the pet's levels accordingly.
   */
  void updateState();

  /**
   * Performs an action on the pet, modifying its attributes based on the
   * current behavior and the type of action.
   *
   * @param action The action to be performed (e.g., feed, shower, socialize, sleep).
   */
  void performAction(PetActions action);

  /**
   * Dynamically sets the behavior of the pet.
   *
   * @param behavior The new {@link PetBehavior} to set for the pet.
   */
  void setBehavior(PetBehavior behavior);

  /**
   * Retrieves the pet's current hunger level.
   *
   * @return The hunger level, ranging from 0 (not hungry) to 100 (extremely hungry).
   */
  int getHungerLevel();

  /**
   * Retrieves the pet's current hygiene level.
   *
   * @return The hygiene level, ranging from 0 (dirty) to 100 (very clean).
   */
  int getHygieneLevel();

  /**
   * Retrieves the pet's current social level.
   *
   * @return The social level, ranging from 0 (isolated) to 100 (very social).
   */
  int getSocialLevel();

  /**
   * Retrieves the pet's current sleep level.
   *
   * @return The sleepy level, ranging from 0 (well-rested) to 100 (very sleepy).
   */
  int getSleepyLevel();

  /**
   * Retrieves the pet's current health level.
   *
   * @return The health level, ranging from 0 (poor health) to 100 (excellent health).
   */
  int getHealthLevel();

  /**
   * Sets the pet's hunger level.
   *
   * @param level The new hunger level, ranging from 0 to 100.
   */
  void setHungerLevel(int level);

  /**
   * Sets the pet's hygiene level.
   *
   * @param level The new hygiene level, ranging from 0 to 100.
   */
  void setHygieneLevel(int level);

  /**
   * Sets the pet's social level.
   *
   * @param level The new social level, ranging from 0 to 100.
   */
  void setSocialLevel(int level);

  /**
   * Sets the pet's sleep level.
   *
   * @param level The new sleepy level, ranging from 0 to 100.
   */
  void setSleepyLevel(int level);

  /**
   * Sets the pet's health level.
   *
   * @param level The new health level, ranging from 0 to 100.
   */
  void setHealthLevel(int level);

  /**
   * Sets the pet's current mood.
   *
   * @param moodType The new {@link Mood} to set for the pet.
   */
  void setMood(Mood moodType);

  /**
   * Retrieves the pet's current mood.
   *
   * @return The current {@link Mood} of the pet.
   */
  Mood getMood();

  /**
   * Retrieves the current behavior of the pet.
   *
   * @return The current {@link PetBehavior} of the pet.
   */
  PetBehavior getBehavior();

  /**
   * Retrieves the total survival time of the pet.
   *
   * @return The survival time in milliseconds since the pet's creation or reset.
   */
  long getSurvivalTime();

  /**
   * Checks whether the game is over for the pet.
   *
   * @return {@code true} if the pet's state indicates the game is over, {@code false} otherwise.
   */
  boolean isGameOver();

  /**
   * Resets the game, restoring the pet to its initial state.
   */
  void resetGame();
}
