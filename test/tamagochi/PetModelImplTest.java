package tamagochi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;



/**
 * Unit tests for the {@link PetModelImpl} class, which represents the virtual pet
 * model in the Tamagotchi application. These tests validate the pet's behavior,
 * attribute management, state transitions, and game logic.
 */
public class PetModelImplTest {
  private PetModelImpl pet;

  /**
   * Sets up a fresh instance of {@link PetModelImpl} before each test.
   */
  @Before
  public void setUp() {
    pet = new PetModelImpl();
  }

  /**
   * Tests the initial state of the pet to ensure default attribute values
   * and initial behavior are correct.
   */
  @Test
  public void testInitialState() {
    assertEquals("Initial hunger level should be 50", 50, pet.getHungerLevel());
    assertEquals("Initial hygiene level should be 50", 50, pet.getHygieneLevel());
    assertEquals("Initial social level should be 50", 50, pet.getSocialLevel());
    assertEquals("Initial sleepy level should be 50", 50, pet.getSleepyLevel());
    assertEquals("Initial health level should be 100", 100, pet.getHealthLevel());
    assertEquals("Initial mood should be HAPPY", Mood.HAPPY, pet.getMood());
    assertTrue("Initial behavior should be PlayfulBehaviorImpl",
        pet.getBehavior() instanceof PlayfulBehaviorImpl);
    assertFalse("Game should not be over initially", pet.isGameOver());
  }

  /**
   * Tests the effects of performing actions while the pet is in a happy mood.
   * Verifies correct adjustments to levels and behavior consistency.
   */
  @Test
  public void testPerformActionsInHappyMood() {
    pet.performAction(PetActions.FEED);
    assertEquals("Hunger level after feeding should decrease to 20", 20, pet.getHungerLevel());
    assertEquals("Mood should remain HAPPY after feeding", Mood.HAPPY, pet.getMood());

    pet.performAction(PetActions.SHOWER);
    assertEquals("Hygiene level after showering should increase to 75", 75, pet.getHygieneLevel());

    pet.performAction(PetActions.SOCIALIZE);
    assertEquals("Social level after socializing should increase to 70", 70, pet.getSocialLevel());

    pet.performAction(PetActions.SLEEP);
    assertEquals("Sleepy level after sleeping should decrease to 15", 15, pet.getSleepyLevel());
  }

  /**
   * Tests mood transition from happy to grumpy based on health level.
   */
  @Test
  public void testMoodTransitionToGrumpy() {
    pet.setHealthLevel(40);
    pet.updateState();

    assertEquals(Mood.GRUMPY, pet.getMood());
    assertTrue(pet.getBehavior() instanceof GrumpyBehaviorImpl);

    pet.performAction(PetActions.FEED);
    assertEquals(40, pet.getHungerLevel());

    pet.performAction(PetActions.SHOWER);
    assertEquals(62, pet.getHygieneLevel());
  }

  /**
   * Tests mood transition from grumpy to depressive based on critical health levels.
   */
  @Test
  public void testMoodTransitionToDepressive() {
    pet.setHealthLevel(20);
    pet.setSleepyLevel(20);
    pet.setHungerLevel(20);
    pet.setSocialLevel(20);
    pet.updateState();
    assertEquals(Mood.DEPRESSIVE, pet.getMood());
    assertTrue(pet.getBehavior() instanceof DepressiveBehaviorImpl);

    pet.performAction(PetActions.FEED);
    assertEquals(15, pet.getHungerLevel());

    pet.performAction(PetActions.SLEEP);
    assertEquals(9, pet.getSleepyLevel());
  }

  /**
   * Tests that the pet's levels are correctly depleted over time
   * when {@code updateState()} is called.
   */
  @Test
  public void testUpdateStateDecreasesLevels() {
    pet.updateState();

    assertEquals("Hunger level should increase by 5", 55, pet.getHungerLevel());
    assertEquals("Hygiene level should decrease by 3", 47, pet.getHygieneLevel());
    assertEquals("Social level should decrease by 2", 48, pet.getSocialLevel());
    assertEquals("Sleepy level should increase by 4", 54, pet.getSleepyLevel());
    assertTrue("Health level should be recalculated", pet.getHealthLevel() > 0);
    assertNotNull("Mood should be updated", pet.getMood());
  }

  /**
   * Tests the calculation of health based on various levels.
   */
  @Test
  public void testHealthCalculatedCorrectly() {
    pet.setHungerLevel(20);
    pet.setHygieneLevel(30);
    pet.setSocialLevel(40);
    pet.setSleepyLevel(50);
    pet.updateState();

    assertEquals(34, pet.getHealthLevel()); // Average of all levels
  }

  /**
   * Tests that the game ends when critical levels reach zero and health drops to zero.
   */
  @Test
  public void testGameOverCondition() {
    pet.setHungerLevel(0);
    pet.setHygieneLevel(0);
    pet.setSocialLevel(0);
    pet.setSleepyLevel(0);
    pet.updateState();

    assertEquals("Health level should be 0 when critical levels are zero", 0, pet.getHealthLevel());
    assertTrue("Game should be over when all critical levels are zero", pet.isGameOver());
  }

  /**
   * Tests that performing an action after the game is over throws an {@link IllegalStateException}.
   */
  @Test(expected = IllegalStateException.class)
  public void testPerformActionAfterGameOver() {
    pet.setHungerLevel(0);
    pet.setHygieneLevel(0);
    pet.setSocialLevel(0);
    pet.setSleepyLevel(0);
    pet.updateState();

    assertTrue("Game should be over", pet.isGameOver());

    try {
      pet.performAction(PetActions.FEED);
      fail("Expected IllegalStateException when performing action after game over");
    } catch (IllegalStateException e) {
      assertEquals("The game is over. Reset to play again.", e.getMessage());
      throw e;
    }
  }

  /**
   * Tests that resetting the game restores all attributes to their initial values.
   */
  @Test
  public void testResetGameResetsAllAttributes() {
    pet.setHealthLevel(0);
    pet.updateState();
    pet.resetGame();

    assertEquals(50, pet.getHungerLevel());
    assertEquals(50, pet.getHygieneLevel());
    assertEquals(50, pet.getSocialLevel());
    assertEquals(50, pet.getSleepyLevel());
    assertEquals(100, pet.getHealthLevel());
    assertEquals(Mood.HAPPY, pet.getMood());
    assertFalse(pet.isGameOver());
  }

  /**
   * Tests the survival timer to ensure it tracks the survival duration correctly.
   *
   * @throws InterruptedException if the test thread is interrupted during sleep
   */
  @Test
  public void testSurvivalTimer() throws InterruptedException {
    pet.resetGame();
    Thread.sleep(1000); // Wait for 1 second
    pet.updateState();

    long survivalTime = pet.getSurvivalTime();
    assertTrue("Survival time should be at least 1 second", survivalTime >= 1);
    assertFalse("Game should not be over during survival", pet.isGameOver());
  }

  /**
   * Tests that the pet's behavior switches appropriately based on its mood.
   */
  @Test
  public void testBehaviorSwitchBasedOnMood() {
    // Case 1: GrumpyBehaviorImpl
    pet.resetGame(); // Ensure clean initial state
    pet.setHealthLevel(40); // Mid-range health
    pet.updateState();
    assertTrue("Expected GrumpyBehaviorImpl for health level 40",
        pet.getBehavior() instanceof GrumpyBehaviorImpl);

    // Case 2: DepressiveBehaviorImpl
    pet.resetGame(); // Reset the game for a fresh state
    pet.setHealthLevel(10);
    pet.setHungerLevel(10);
    pet.setHygieneLevel(10);
    pet.setSocialLevel(10);
    pet.setSleepyLevel(10);
    pet.updateState();
    assertTrue("Expected DepressiveBehaviorImpl for low health and levels",
        pet.getBehavior() instanceof DepressiveBehaviorImpl);

    // Case 3: PlayfulBehaviorImpl
    pet.resetGame(); // Reset the game for a fresh state
    pet.setHealthLevel(100);
    pet.setHungerLevel(90);
    pet.setHygieneLevel(100);
    pet.setSocialLevel(100);
    pet.setSleepyLevel(10); // Ensure low sleepy level for playful behavior
    pet.updateState();
    assertTrue("Expected PlayfulBehaviorImpl for high health and good levels",
        pet.getBehavior() instanceof PlayfulBehaviorImpl);
  }

  /**
   * Tests the getter and setter for hunger level.
   * Verifies values are clamped between 0 and 100.
   */
  @Test
  public void testSetAndGetHungerLevel() {
    pet.setHungerLevel(30);
    assertEquals(30, pet.getHungerLevel());

    pet.setHungerLevel(-10); // Below range
    assertEquals(0, pet.getHungerLevel());

    pet.setHungerLevel(150); // Above range
    assertEquals(100, pet.getHungerLevel());
  }

  /**
   * Tests the getter and setter for hygiene level.
   * Verifies values are clamped between 0 and 100.
   */
  @Test
  public void testSetAndGetHygieneLevel() {
    pet.setHygieneLevel(70);
    assertEquals(70, pet.getHygieneLevel());

    pet.setHygieneLevel(-5); // Below range
    assertEquals(0, pet.getHygieneLevel());

    pet.setHygieneLevel(200); // Above range
    assertEquals(100, pet.getHygieneLevel());
  }

  /**
   * Tests the getter and setter for social level.
   * Verifies values are clamped between 0 and 100.
   */
  @Test
  public void testSetAndGetSocialLevel() {
    pet.setSocialLevel(50);
    assertEquals(50, pet.getSocialLevel());

    pet.setSocialLevel(-20); // Below range
    assertEquals(0, pet.getSocialLevel());

    pet.setSocialLevel(120); // Above range
    assertEquals(100, pet.getSocialLevel());
  }

  /**
   * Tests the getter and setter for sleepiness level.
   * Verifies values are clamped between 0 and 100.
   */
  @Test
  public void testSetAndGetSleepyLevel() {
    pet.setSleepyLevel(40);
    assertEquals(40, pet.getSleepyLevel());

    pet.setSleepyLevel(-1); // Below range
    assertEquals(0, pet.getSleepyLevel());

    pet.setSleepyLevel(110); // Above range
    assertEquals(100, pet.getSleepyLevel());
  }

  /**
   * Tests the getter and setter for health level.
   * Verifies values are clamped between 0 and 100.
   */
  @Test
  public void testSetAndGetHealthLevel() {
    pet.setHealthLevel(90);
    assertEquals(90, pet.getHealthLevel());

    pet.setHealthLevel(-30); // Below range
    assertEquals(0, pet.getHealthLevel());

    pet.setHealthLevel(130); // Above range
    assertEquals(100, pet.getHealthLevel());
  }

  /**
   * Tests the getter and setter for mood.
   * Ensures the correct mood is returned after setting it.
   */
  @Test
  public void testSetAndGetMood() {
    pet.setMood(Mood.GRUMPY);
    assertEquals(Mood.GRUMPY, pet.getMood());

    pet.setMood(Mood.HAPPY);
    assertEquals(Mood.HAPPY, pet.getMood());

    pet.setMood(Mood.DEPRESSIVE);
    assertEquals(Mood.DEPRESSIVE, pet.getMood());
  }

  /**
   * Tests the getter and setter for behavior.
   * Verifies that the correct behavior is returned after setting it.
   */
  @Test
  public void testSetAndGetBehavior() {
    PetBehavior playfulBehavior = new PlayfulBehaviorImpl();
    pet.setBehavior(playfulBehavior);
    assertEquals(playfulBehavior, pet.getBehavior());

    PetBehavior grumpyBehavior = new GrumpyBehaviorImpl();
    pet.setBehavior(grumpyBehavior);
    assertEquals(grumpyBehavior, pet.getBehavior());
  }

  /**
   * Tests the getSurvivalTime method.
   * Simulates elapsed time and ensures the survival time increases.
   */
  @Test
  public void testGetSurvivalTime() {
    pet.resetGame();
    long initialTime = pet.getSurvivalTime();

    // Simulate elapsed time
    try {
      Thread.sleep(2000); // 2 seconds
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    long elapsedTime = pet.getSurvivalTime();
    assertTrue(elapsedTime > initialTime); // Time should have increased
  }
}
