package nz.ac.auckland.se281.engine;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.Difficulty;
import nz.ac.auckland.se281.cli.MessageCli;
import nz.ac.auckland.se281.cli.Utils;
import nz.ac.auckland.se281.model.Colour;

public class Game {
  public static String AI_NAME = "HAL-9000";
  private int thisRoundNumber;
  private int numberOfRounds;
  private String playerName;
  private int playerPoints;
  private int aiPoints;
  private boolean didAiWinLastRound;
  private Ai ai;
  private ArrayList<Colour> playerChosenColours;

  public void newGame(Difficulty difficulty, int numRounds, String[] options) {
    this.playerName = options[0];
    this.thisRoundNumber = 1;
    this.numberOfRounds = numRounds;
    this.playerPoints = 0;
    this.aiPoints = 0;
    this.didAiWinLastRound = false;
    // Create an Ai instance using the factory design pattern
    this.ai = AiFactory.createAi(difficulty, this);
    // Create a list for storing the previously chosen colours by the player
    this.playerChosenColours = new ArrayList<Colour>();
    MessageCli.WELCOME_PLAYER.printMessage(this.playerName);
  }

  public void play() {
    // If no game is running print an appropriate message
    if (thisRoundNumber == 0 || thisRoundNumber > numberOfRounds) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    // Prompt the human for input
    MessageCli.START_ROUND.printMessage(this.thisRoundNumber, this.numberOfRounds);
    MessageCli.ASK_HUMAN_INPUT.printMessage();

    String chosenColourString;
    String guessColourString;
    Colour chosenColour;
    Colour guessColour;

    // Keep retrying until the human enters valid input
    while (true) {
      String input = Utils.scanner.nextLine();

      // Reject anything else than 2 arguments
      if (input.split(" ").length != 2) {
        MessageCli.INVALID_HUMAN_INPUT.printMessage();
        continue;
      }

      // Split the colours into two Colour type variables
      chosenColourString = input.split(" ")[0];
      chosenColour = Colour.fromInput(chosenColourString);
      guessColourString = input.split(" ")[1];
      guessColour = Colour.fromInput(guessColourString);

      // If any colours are invalid, the human needs to try again
      if (chosenColour == null || guessColour == null) {
        MessageCli.INVALID_HUMAN_INPUT.printMessage();
      } else {
        break;
      }
    }

    // Use the Ai instance to appropriately generate the ai's colour
    Colour aiChosenColour = this.ai.getChosenColour();
    Colour aiGuessColour = this.ai.getGuessColour();
    MessageCli.PRINT_INFO_MOVE.printMessage(
        AI_NAME, aiChosenColour.toString(), aiGuessColour.toString());

    MessageCli.PRINT_INFO_MOVE.printMessage(
        this.playerName, chosenColour.toString(), guessColour.toString());

    // Every third round select a power colour
    Boolean isPowerColourRound = false;
    Colour powerColour = null;
    if (this.thisRoundNumber % 3 == 0) {
      isPowerColourRound = true;
      powerColour = Colour.getRandomColourForPowerColour();
      MessageCli.PRINT_POWER_COLOUR.printMessage(powerColour.toString());
    }

    this.playerChosenColours.add(chosenColour);

    int aiPointsThisRound = 0;
    int playerPointsThisRound = 0;

    // Assign a point for correctly guessing the other player's colour
    // Assign +2 bonus points for a guess that also guesses the power colour
    if (aiChosenColour == guessColour) {
      playerPointsThisRound += 1;
      if (isPowerColourRound && guessColour == powerColour) {
        playerPointsThisRound += 2;
      }
    }
    if (chosenColour == aiGuessColour) {
      aiPointsThisRound += 1;
      if (isPowerColourRound && aiGuessColour == powerColour) {
        aiPointsThisRound += 2;
      }
      // take note of whether the AI won last round for strategy purposes
      this.didAiWinLastRound = true;
    } else {
      this.didAiWinLastRound = false;
    }
    MessageCli.PRINT_OUTCOME_ROUND.printMessage(playerName, playerPointsThisRound);
    MessageCli.PRINT_OUTCOME_ROUND.printMessage(AI_NAME, aiPointsThisRound);

    this.playerPoints += playerPointsThisRound;
    this.aiPoints += aiPointsThisRound;

    // If this is the last round, print out the end of game info
    if (this.thisRoundNumber == this.numberOfRounds) {
      MessageCli.PRINT_END_GAME.printMessage();
      this.showStats();
      if (this.playerPoints > this.aiPoints) {
        MessageCli.PRINT_WINNER_GAME.printMessage(this.playerName);
      } else if (this.aiPoints > this.playerPoints) {
        MessageCli.PRINT_WINNER_GAME.printMessage(AI_NAME);
      } else {
        MessageCli.PRINT_TIE_GAME.printMessage();
      }
    }

    this.thisRoundNumber += 1;
  }

  public void showStats() {
    if (thisRoundNumber == 0 || thisRoundNumber > numberOfRounds) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }
    MessageCli.PRINT_PLAYER_POINTS.printMessage(this.playerName, this.playerPoints);
    MessageCli.PRINT_PLAYER_POINTS.printMessage(AI_NAME, this.aiPoints);
  }

  public ArrayList<Colour> getPlayerChosenColours() {
    return this.playerChosenColours;
  }

  public int getThisRoundNumber() {
    return this.thisRoundNumber;
  }

  public boolean getDidAiWinLastRound() {
    return this.didAiWinLastRound;
  }
}
