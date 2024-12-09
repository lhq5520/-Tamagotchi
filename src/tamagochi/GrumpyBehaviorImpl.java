package tamagochi;

/**
 * Implementation of the {@link PetBehavior} interface for grumpy behavior.
 * This class defines how a pet behaves when it exhibits grumpy tendencies,
 * modifying its levels (hunger, hygiene, social, and sleepy) based on actions performed.
 */
public class GrumpyBehaviorImpl implements PetBehavior {

  /**
   * Performs an action on the pet and adjusts its levels according to grumpy behavior.
   * Grumpy behavior results in more dramatic changes to the pet's levels compared
   * to other behaviors when interacting with actions like feeding, showering,
   * socializing, or sleeping.
   *
   * @param pet    the {@link PetModel} instance representing the pet
   * @param action the {@link PetActions} to be performed on the pet
   * @throws IllegalArgumentException if an unknown action is provided
   */
  @Override
  public void performActionBasedOnBehavior(PetModel pet, PetActions action) {
    // Grumpy Behavior
    switch (action) {
      case FEED:
        // Feeding increases hunger level by 15, capped at 100.
        pet.setHungerLevel(Math.min(100, pet.getHungerLevel() + 15));
        break;
      case SHOWER:
        // Showering increases hygiene level by 15, capped at 100.
        pet.setHygieneLevel(Math.min(100, pet.getHygieneLevel() + 15));
        break;
      case SOCIALIZE:
        // Socializing increases social level by 30, capped at 100.
        pet.setSocialLevel(Math.min(100, pet.getSocialLevel() + 30));
        break;
      case SLEEP:
        // Sleeping increases sleepy level by 20, capped at 100.
        pet.setSleepyLevel(Math.min(100, pet.getSleepyLevel() + 20));
        break;
      default:
        // Throws an exception for unknown actions.
        throw new IllegalArgumentException("Unknown action: " + action);
    }
  }
}
