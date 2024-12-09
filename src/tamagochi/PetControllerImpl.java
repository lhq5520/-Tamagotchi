package tamagochi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Implementation of the {@link PetController} interface for the Tamagotchi application.
 * Manages interactions between the model and view,
 * handles user input, and updates the application state.
 */

public class PetControllerImpl implements PetController {
  private final PetModel model;
  private final PetView view;
  private final Timer updateTimer;

  /**
   * Constructs a new {@code PetControllerImpl} with the specified model and view.
   *
   * @param model The {@link PetModel} representing the pet's state and behavior.
   * @param view  The {@link PetView} representing the GUI for user interaction.
   */
  public PetControllerImpl(PetModel model, PetView view) {
    this.model = model;
    this.view = view;

    // Set up listeners for user interactions
    setupListeners();

    // Initialize periodic updates for the model state
    updateTimer = new Timer(1000, e -> updateModelState());
    updateTimer.start();

    // Initial UI update
    updateView();
  }

  /**
   * Sets up action listeners for the view's buttons to perform actions on the pet.
   */
  private void setupListeners() {
    view.addFeedListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        model.performAction(PetActions.FEED);
        view.setPetImage("/img/feed.gif");

        // Pause the game timer
        updateTimer.stop();

        // Delay the update to allow the animation to play
        Timer transitionTimer = new Timer(1500, new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            updateView();
            updateTimer.start(); // Resume the timer
          }
        });

        transitionTimer.setRepeats(false);
        transitionTimer.start();
      }
    });

    view.addCleanListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        model.performAction(PetActions.SHOWER);
        view.setPetImage("/img/shower.gif");

        // Pause the game timer
        updateTimer.stop();

        // Delay the update to allow the animation to play
        Timer transitionTimer = new Timer(2000, new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            updateView();
            updateTimer.start(); // Resume the timer
          }
        });

        transitionTimer.setRepeats(false);
        transitionTimer.start();
      }
    });

    view.addPlayListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        model.performAction(PetActions.SOCIALIZE);
        view.setPetImage("/img/play.gif");

        // Pause the game timer
        updateTimer.stop();

        // Delay the update to allow the animation to play
        Timer transitionTimer = new Timer(2000, new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            updateView();
            updateTimer.start(); // Resume the timer
          }
        });

        transitionTimer.setRepeats(false);
        transitionTimer.start();
      }
    });

    view.addSleepListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        model.performAction(PetActions.SLEEP);
        view.setPetImage("/img/sleep.gif");

        // Pause the game timer
        updateTimer.stop();

        // Delay the update to allow the animation to play
        Timer transitionTimer = new Timer(2000, new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            updateView();
            updateTimer.start(); // Resume the timer
          }
        });

        transitionTimer.setRepeats(false);
        transitionTimer.start();
      }
    });

    view.addResetListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        updateTimer.stop();
        updateTimer.restart();
        model.resetGame();
        view.setPetImage("/img/reborn.gif");
        // Pause the game timer
        updateTimer.stop();

        // Delay the update to allow the animation to play
        Timer transitionTimer = new Timer(2000, new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            updateView();
            updateTimer.start(); // Resume the timer
          }
        });

        transitionTimer.setRepeats(false);
        transitionTimer.start();
      }
    });

  }

  /**
   * Periodically updates the model state and refreshes the view.
   */
  private void updateModelState() {
    model.updateState();
    updateView();
  }

  /**
   * Updates the view based on the current state of the model.
   */
  private void updateView() {
    view.updateBars(
        model.getHungerLevel(),
        model.getHygieneLevel(),
        model.getSocialLevel(),
        model.getSleepyLevel(),
        model.getHealthLevel()
    );

    view.updateMood(model.getMood());
    view.updateGameOver(model.isGameOver());

    // Determine the appropriate image based on the model's state
    String imagePath;
    if (model.getHealthLevel() <= 20) {
      imagePath = "/img/dead.gif";
    } else if (model.getMood().equals(Mood.HAPPY)) {
      imagePath = "/img/happy.gif";
    } else if (model.getMood().equals(Mood.GRUMPY)) {
      imagePath = "/img/grumpy.gif";
    } else if (model.getMood().equals(Mood.DEPRESSIVE)) {
      imagePath = "/img/depressive.gif";
    } else {
      imagePath = "/img/default.gif";
    }

    // Update the view with the selected image
    view.setPetImage(imagePath);

    // Stop the timer if the game is over
    if (model.isGameOver()) {
      updateTimer.stop();
      view.updateGameOver(true);
    } else {
      long survivalTime = model.getSurvivalTime();
      String formattedTime = formatSurvivalTime(survivalTime); // Convert to HH:mm:ss format
      view.updateGameOver(false);
      view.updateSurvivalTime(formattedTime);
    }
  }

  /**
   * format the timer.
   */
  private String formatSurvivalTime(long seconds) {
    long hours = seconds / 3600;
    long minutes = (seconds % 3600) / 60;
    long secs = seconds % 60;

    StringBuilder sb = new StringBuilder();

    if (hours > 0) {
      sb.append(hours).append(hours == 1 ? " hour" : " hours");
    }

    if (minutes > 0) {
      if (sb.length() > 0) {
        sb.append(", ");
      }
      sb.append(minutes).append(minutes == 1 ? " minute" : " minutes");
    }

    if (secs > 0 || sb.length() == 0) { // Always show seconds if no other units exist
      if (sb.length() > 0) {
        sb.append(", and ");
      } // Add "and" for the last item
      sb.append(secs).append(secs == 1 ? " second" : " seconds");
    }

    return sb.toString();
  }

  /**
   * Starts the game by resetting the model and restarting the update timer.
   */
  @Override
  public void startGame() {
    model.resetGame();
    updateTimer.start();
    updateView();
  }
}
