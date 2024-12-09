package tamagochi;

/**
 * Interface representing the behavior of a pet in the Tamagotchi application.
 * Defines how a pet's levels are adjusted based on its current behavior
 * and the action performed.
 */
public interface PetBehavior {

  /**
   * Performs an action on the pet and adjusts its levels based on the specific behavior.
   * The implementation of this method defines how each behavior (e.g., Happy, Depressive, Grumpy)
   * affects the pet's response to various actions.
   *
   * @param pet    the {@link PetModel} instance representing the pet
   * @param action the {@link PetActions} to be performed on the pet
   * @throws IllegalArgumentException if an invalid action is provided
   */
  void performActionBasedOnBehavior(PetModel pet, PetActions action);
}
