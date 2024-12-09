package tamagochi;

/**
 * A utility class that tracks the survival time of the virtual pet.
 * The timer can be started, stopped, and reset to track the pet's
 * survival duration during the game.
 */
public class SurvivalTimer {
  private long startTime;
  private long endTime;
  private boolean isRunning;

  /**
   * Constructs a new {@code SurvivalTimer} with initial values.
   * The timer is not running when initialized.
   */
  public SurvivalTimer() {
    this.startTime = 0;
    this.endTime = 0;
    this.isRunning = false;
  }

  /**
   * Starts the survival timer. If the timer is already running, calling this
   * method has no effect.
   */
  public void start() {
    if (!isRunning) {
      this.startTime = System.currentTimeMillis();
      this.isRunning = true;
    }
  }

  /**
   * Stops the survival timer. If the timer is not running, calling this
   * method has no effect.
   */
  public void stop() {
    if (isRunning) {
      this.endTime = System.currentTimeMillis();
      this.isRunning = false;
    }
  }

  /**
   * Retrieves the total survival time in seconds.
   * If the timer is running, the survival time is calculated from the start time
   * to the current time. If the timer is stopped, the survival time is calculated
   * from the start time to the end time.
   *
   * @return The survival time in seconds.
   */
  public long getSurvivalTime() {
    long currentTime = isRunning ? System.currentTimeMillis() : endTime;
    return (currentTime - startTime) / 1000; // Convert milliseconds to seconds
  }

  /**
   * Resets the timer to its initial state, clearing the start and end times
   * and stopping the timer.
   */
  public void reset() {
    this.startTime = 0;
    this.endTime = 0;
    this.isRunning = false;
  }
}
