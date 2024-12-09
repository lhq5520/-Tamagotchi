package tamagochi;

/**
 * Enum representing the possible actions that can be performed on a pet
 * in the Tamagotchi application. Each action affects the pet's levels
 * (hunger, hygiene, social, or sleepy) based on its current mood.
 */
public enum PetActions {
  /**
   * Represents the action of showering the pet.
   * This action increases the pet's hygiene level.
   */
  SHOWER,

  /**
   * Represents the action of feeding the pet.
   * This action decreases the pet's hunger level.
   */
  FEED,

  /**
   * Represents the action of socializing with the pet.
   * This action increases the pet's social level.
   */
  SOCIALIZE,

  /**
   * Represents the action of letting the pet sleep.
   * This action decreases the pet's sleepy level.
   */
  SLEEP;
}
