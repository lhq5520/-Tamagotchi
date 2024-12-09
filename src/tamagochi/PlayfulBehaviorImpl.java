package tamagochi;

/**
 * Implementation of the {@link PetBehavior} interface for playful behavior.
 * This class defines how a pet behaves when it is in a happy and playful state,
 * with actions having highly positive effects on the pet's levels.
 */
public class PlayfulBehaviorImpl implements PetBehavior {

  /**
   * Performs an action on the pet and adjusts its levels according to playful behavior.
   * Playful behavior amplifies the positive effects of actions like feeding, showering,
   * socializing, and sleeping, reflecting the pet's happy and energetic state.
   *
   * @param pet    the {@link PetModel} instance representing the pet
   * @param action the {@link PetActions} to be performed on the pet
   * @throws IllegalArgumentException if an unknown action is provided
   */
  @Override
  public void performActionBasedOnBehavior(PetModel pet, PetActions action) {
    // Playful (Happy) Behavior
    switch (action) {
      case FEED:
        // Feeding increases hunger level significantly by 30, capped at 100.
        pet.setHungerLevel(Math.min(100, pet.getHungerLevel() + 30));
        break;
      case SHOWER:
        // Showering greatly increases hygiene level by 25, capped at 100.
        pet.setHygieneLevel(Math.min(100, pet.getHygieneLevel() + 25));
        break;
      case SOCIALIZE:
        // Socializing significantly increases social level by 20, capped at 100.
        pet.setSocialLevel(Math.min(100, pet.getSocialLevel() + 20));
        break;
      case SLEEP:
        // Sleeping significantly increases sleepy level by 35, capped at 100.
        pet.setSleepyLevel(Math.min(100, pet.getSleepyLevel() + 35));
        break;
      default:
        // Throws an exception for unknown actions.
        throw new IllegalArgumentException("Unknown action: " + action);
    }
  }
}
