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
  private Difficulty difficulty;
  private int playerPoints;
  private int aiPoints;
  private boolean didAiWinLastRound;
  private Ai ai;
  private ArrayList<Colour> playerChosenColours;

  public Game() {}

  public void newGame(Difficulty difficulty, int numRounds, String[] options) {
    this.playerName = options[0];
    this.thisRoundNumber = 1;
    this.numberOfRounds = numRounds;
    this.difficulty = difficulty;
    this.playerPoints = 0;
    this.aiPoints = 0;
    this.didAiWinLastRound = false;
    this.ai = AiFactory.createAi(difficulty, this);
    this.playerChosenColours = new ArrayList<Colour>();
    MessageCli.WELCOME_PLAYER.printMessage(this.playerName);
  }

  public void play() {
    if (thisRoundNumber == 0 || thisRoundNumber > numberOfRounds) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }
    MessageCli.START_ROUND.printMessage(this.thisRoundNumber, this.numberOfRounds);

    MessageCli.ASK_HUMAN_INPUT.printMessage();

    String chosenColourString, guessColourString;
    Colour chosenColour, guessColour;

    while (true) {
      String input = Utils.scanner.nextLine();

      if (input.split(" ").length != 2) {
        MessageCli.INVALID_HUMAN_INPUT.printMessage();
        continue;
      }

      chosenColourString = input.split(" ")[0];
      chosenColour = Colour.fromInput(chosenColourString);
      guessColourString = input.split(" ")[1];
      guessColour = Colour.fromInput(guessColourString);

      if (chosenColour == null || guessColour == null) {
        MessageCli.INVALID_HUMAN_INPUT.printMessage();
      } else {
        break;
      }
    }

    Colour aiChosenColour = this.ai.getChosenColour();
    Colour aiGuessColour = this.ai.getGuessColour();
    MessageCli.PRINT_INFO_MOVE.printMessage(
        Game.AI_NAME, aiChosenColour.toString(), aiGuessColour.toString());

    MessageCli.PRINT_INFO_MOVE.printMessage(
        this.playerName, chosenColour.toString(), guessColour.toString());

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
      this.didAiWinLastRound = true;
    } else {
      this.didAiWinLastRound = false;
    }
    MessageCli.PRINT_OUTCOME_ROUND.printMessage(playerName, playerPointsThisRound);
    MessageCli.PRINT_OUTCOME_ROUND.printMessage(Game.AI_NAME, aiPointsThisRound);

    this.playerPoints += playerPointsThisRound;
    this.aiPoints += aiPointsThisRound;

    this.thisRoundNumber += 1;
  }

  public void showStats() {
    if (thisRoundNumber == 0 || thisRoundNumber > numberOfRounds) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }
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
