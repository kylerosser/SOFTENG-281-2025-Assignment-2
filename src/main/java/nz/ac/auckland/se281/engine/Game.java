package nz.ac.auckland.se281.engine;

import nz.ac.auckland.se281.Main.Difficulty;
import nz.ac.auckland.se281.cli.MessageCli;
import nz.ac.auckland.se281.cli.Utils;
import nz.ac.auckland.se281.model.Colour;;

public class Game {
  public static String AI_NAME = "HAL-9000";
  private int thisRoundNumber;
  private int numberOfRounds;
  private String playerName;

  public Game() {}

  public void newGame(Difficulty difficulty, int numRounds, String[] options) {
    this.playerName = options[0];
    this.thisRoundNumber = 1;
    this.numberOfRounds = numRounds;
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

    Colour aiChosenColour = Colour.getRandomColourForAi();
    Colour aiGuessColour = Colour.getRandomColourForAi();
    MessageCli.PRINT_INFO_MOVE.printMessage(
      this.AI_NAME, 
      aiChosenColour.toString(), 
      aiGuessColour.toString()
    );

    MessageCli.PRINT_INFO_MOVE.printMessage(
      this.playerName, 
      chosenColour.toString(), 
      guessColour.toString()
    );

    Boolean isPowerColourRound = false;
    Colour powerColour;
    if (this.thisRoundNumber % 3 == 0) {
      isPowerColourRound = true;
      powerColour = Colour.getRandomColourForPowerColour();
      MessageCli.PRINT_POWER_COLOUR.printMessage(powerColour.toString());
    }

    this.thisRoundNumber += 1;
    
  }

  public void showStats() {}
}
