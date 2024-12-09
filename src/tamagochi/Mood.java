package tamagochi;

/**
 * Enum representing the possible moods of a pet in the Tamagotchi application.
 * The mood of a pet determines its behavior and how it reacts to different actions.
 */
public enum Mood {
  /**
   * The pet is in a happy mood. In this state, actions like feeding, showering,
   * socializing, or sleeping are generally more effective and positively impact the pet's levels.
   */
  HAPPY,

  /**
   * The pet is in a depressive mood. In this state, actions have a less positive effect
   * on the pet's levels, reflecting its low energy or motivation.
   */
  DEPRESSIVE,

  /**
   * The pet is in a grumpy mood. In this state, actions result in dramatic changes
   * to the pet's levels, often reflecting heightened sensitivity or strong reactions.
   */
  GRUMPY;
}
