package tamagochi;

/**
 * Implementation of the {@link PetBehavior} interface for depressive behavior.
 * This class defines how a pet behaves when it has depressive tendencies,
 * modifying its levels (hunger, hygiene, social, and sleepy) based on actions performed.
 */
public class DepressiveBehaviorImpl implements PetBehavior {

  /**
   * Performs an action on the pet and adjusts its levels according to depressive behavior.
   * Depressive behavior affects the pet less positively compared to normal behavior
   * when interacting with actions like feeding, showering, socializing, or sleeping.
   *
   * @param pet    the {@link PetModel} instance representing the pet
   * @param action the {@link PetActions} to be performed on the pet
   * @throws IllegalArgumentException if an unknown action is provided
   */
  @Override
  public void performActionBasedOnBehavior(PetModel pet, PetActions action) {
    // Depressive Behavior
    switch (action) {
      case FEED:
        // Feeding increases hunger level by 10, capped at 100.
        pet.setHungerLevel(Math.min(100, pet.getHungerLevel() + 10));
        break;
      case SHOWER:
        // Showering increases hygiene level by 10, capped at 100.
        pet.setHygieneLevel(Math.min(100, pet.getHygieneLevel() + 10));
        break;
      case SOCIALIZE:
        // Socializing increases social level by 5, capped at 100.
        pet.setSocialLevel(Math.min(100, pet.getSocialLevel() + 5));
        break;
      case SLEEP:
        // Sleeping increases sleepy level by 15, capped at 100.
        pet.setSleepyLevel(Math.min(100, pet.getSleepyLevel() + 15));
        break;
      default:
        // Throws an exception for unknown actions.
        throw new IllegalArgumentException("Unknown action: " + action);
    }
  }
}
