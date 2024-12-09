package tamagochi;

/**
 * Implementation of the {@link PetModel} interface representing a virtual pet
 * in the Tamagotchi application. Manages the state, attributes, and behavior
 * of the pet, including hunger, hygiene, social, sleep, health levels, mood,
 * and game-over conditions.
 */
public class PetModelImpl implements PetModel {
  private final SurvivalTimer timer;
  private int hungerLevel;
  private int hygieneLevel;
  private int socialLevel;
  private int sleepyLevel;
  private int healthLevel;
  private Mood moodType;
  private PetBehavior behavior;
  private boolean gameOver;

  /**
   * Constructs a new instance of {@code PetModelImpl}, initializing all attributes
   * to their default values and starting the survival timer.
   */
  public PetModelImpl() {
    this.timer = new SurvivalTimer();
    resetGame();
  }

  /**
   * Updates the state of the pet over time by depleting levels,
   * recalculating health, dynamically updating mood and behavior,
   * and checking for game-over conditions.
   */

  @Override
  public void updateState() {
    // Decrease levels over time
    depleteLevels();

    // Update health based on other levels
    calculateHealth();

    // Dynamically determine mood and update behavior
    determineMoodByHealth();

    // Check and handle game-over condition
    checkGameOver();
  }

  /**
   * Depletes the pet's levels over time based on predefined rates.
   * Adjusts hunger, hygiene, social, and sleepy levels.
   */

  private void depleteLevels() {
    // Adjust depletion rates dynamically based on difficulty or elapsed time
    int hungerDepletion = 1;
    hungerLevel = Math.max(0, hungerLevel - hungerDepletion);

    int hygieneDepletion = 1;
    hygieneLevel = Math.max(0, hygieneLevel - hygieneDepletion);

    int socialDepletion = 1;
    socialLevel = Math.max(0, socialLevel - socialDepletion);

    int sleepyIncrease = 1;
    sleepyLevel = Math.max(0, sleepyLevel - sleepyIncrease);
  }

  /**
   * Calculates the pet's health level based on its current attributes.
   * Applies penalties for critically low levels or high sleepiness.
   */
  private void calculateHealth() {
    // Penalize health more significantly for critically low levels
    int penalty = 0;
    if (hungerLevel < 20) {
      penalty += 10;
    }
    if (hygieneLevel < 20) {
      penalty += 10;
    }
    if (socialLevel < 20) {
      penalty += 10;
    }
    if (sleepyLevel < 20) {
      penalty += 10;
    } // High sleepiness penalty

    // Base health calculation with penalties applied
    healthLevel = Math.max(0, (hungerLevel + hygieneLevel
        + socialLevel + sleepyLevel) / 4 - penalty);

    //if health level becomes 0, everything set to 0 for better logic
    if (healthLevel == 0) {
      hungerLevel = 0;
      hygieneLevel = 0;
      socialLevel = 0;
      sleepyLevel = 0;
    }
  }

  /**
   * Checks if the game is over based on the pet's health or critical levels.
   * If the game is over, stops the survival timer.
   */
  private void checkGameOver() {
    // Game-over condition: Any critical level at 0 or health drops to 0
    if (healthLevel == 0 && !gameOver) {
      gameOver = true;
      timer.stop(); // Stop the timer when the game ends
    }
  }

  /**
   * Dynamically determines the pet's mood based on health levels
   * and sets the corresponding behavior.
   * - Happy for health > 70.
   * - Grumpy for health > 30.
   * - Depressive for health <= 30.
   */

  private void determineMoodByHealth() {
    if (healthLevel > 70) {
      moodType = Mood.HAPPY;
      setBehavior(new PlayfulBehaviorImpl()); // Happy behavior
    } else if (healthLevel > 30) {
      moodType = Mood.GRUMPY;
      setBehavior(new GrumpyBehaviorImpl()); // Grumpy behavior
    } else {
      moodType = Mood.DEPRESSIVE;
      setBehavior(new DepressiveBehaviorImpl()); // Depressive behavior
    }
  }

  /**
   * Performs an action on the pet and adjusts its levels based on the current behavior.
   *
   * @param action The {@link PetActions} to perform.
   * @throws IllegalStateException if the game is over or no behavior is set.
   */
  @Override
  public void performAction(PetActions action) {
    if (gameOver) {
      throw new IllegalStateException("The game is over. Reset to play again.");
    }

    if (behavior == null) {
      throw new IllegalStateException("No behavior is set. Unable to perform action.");
    }

    // Delegate the action to the behavior implementation
    behavior.performActionBasedOnBehavior(this, action);
  }

  /**
   * Gets the current hunger level of the pet.
   *
   * @return The hunger level, ranging from 0 (not hungry) to 100 (very hungry).
   */
  @Override
  public int getHungerLevel() {
    return hungerLevel;
  }

  /**
   * Sets the pet's hunger level.
   *
   * @param level The new hunger level, ranging from 0 to 100.
   */
  @Override
  public void setHungerLevel(int level) {
    this.hungerLevel = Math.max(0, Math.min(100, level));
  }

  /**
   * Gets the current hygiene level of the pet.
   *
   * @return The hygiene level, ranging from 0 (very dirty) to 100 (very clean).
   */
  @Override
  public int getHygieneLevel() {
    return hygieneLevel;
  }

  /**
   * Sets the pet's hygiene level.
   *
   * @param level The new hygiene level, ranging from 0 to 100.
   */
  @Override
  public void setHygieneLevel(int level) {
    this.hygieneLevel = Math.max(0, Math.min(100, level));
  }

  /**
   * Gets the current social level of the pet.
   *
   * @return The social level, ranging from 0 (very isolated) to 100 (very social).
   */
  @Override
  public int getSocialLevel() {
    return socialLevel;
  }

  /**
   * Sets the pet's social level.
   *
   * @param level The new social level, ranging from 0 to 100.
   */
  @Override
  public void setSocialLevel(int level) {
    this.socialLevel = Math.max(0, Math.min(100, level));
  }

  /**
   * Gets the current sleepiness level of the pet.
   *
   * @return The sleepy level, ranging from 0 (well-rested) to 100 (very sleepy).
   */
  @Override
  public int getSleepyLevel() {
    return sleepyLevel;
  }

  /**
   * Sets the pet's sleep level.
   *
   * @param level The new sleepy level, ranging from 0 to 100.
   */
  @Override
  public void setSleepyLevel(int level) {
    this.sleepyLevel = Math.max(0, Math.min(100, level));
  }

  /**
   * Gets the current health level of the pet.
   *
   * @return The health level, ranging from 0 (very unhealthy) to 100 (perfect health).
   */
  @Override
  public int getHealthLevel() {
    return healthLevel;
  }

  /**
   * Sets the pet's health level.
   *
   * @param level The new health level, ranging from 0 to 100.
   */
  @Override
  public void setHealthLevel(int level) {
    this.healthLevel = Math.max(0, Math.min(100, level));
  }

  /**
   * Gets the total survival time of the pet since the game started or was last reset.
   *
   * @return The survival time in seconds.
   */
  public long getSurvivalTime() {
    return timer.getSurvivalTime(); // Return survival time in seconds
  }

  /**
   * Retrieves the current behavior of the pet.
   *
   * @return The current {@link PetBehavior} of the pet.
   */
  @Override
  public PetBehavior getBehavior() {
    return behavior;
  }

  /**
   * Sets the behavior of the pet.
   *
   * @param behavior The {@link PetBehavior} to associate with the pet.
   */
  @Override
  public void setBehavior(PetBehavior behavior) {
    this.behavior = behavior;
  }

  /**
   * Gets the current mood of the pet.
   *
   * @return The current {@link Mood} of the pet.
   */
  @Override
  public Mood getMood() {
    return moodType;
  }

  /**
   * Sets the pet's current mood.
   *
   * @param moodType The new {@link Mood} to set for the pet.
   */
  @Override
  public void setMood(Mood moodType) {
    this.moodType = moodType;
  }

  /**
   * Checks if the game is over for the pet.
   *
   * @return {@code true} if the game is over, {@code false} otherwise.
   */
  @Override
  public boolean isGameOver() {
    return gameOver;
  }

  /**
   * Resets the game, restoring the pet to its initial state with default values.
   * Restarts the survival timer and determines the initial mood and behavior of the pet.
   */
  @Override
  public void resetGame() {
    this.hungerLevel = 100;
    this.hygieneLevel = 100;
    this.socialLevel = 100;
    this.sleepyLevel = 100;
    this.healthLevel = 100;
    this.gameOver = false;

    timer.reset();  // Reset the timer for a new game
    timer.start();  // Start counting survival time

    determineMoodByHealth(); // Automatically sets mood and behavior
  }
}
