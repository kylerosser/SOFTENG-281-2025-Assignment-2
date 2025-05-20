package nz.ac.auckland.se281.engine;

import nz.ac.auckland.se281.Main.Difficulty;
import nz.ac.auckland.se281.cli.MessageCli;
import nz.ac.auckland.se281.cli.Utils;

public class Game {
  public static String AI_NAME = "HAL-9000";
  private int thisRoundNumber;
  private int numberOfRounds;

  public Game() {}

  public void newGame(Difficulty difficulty, int numRounds, String[] options) {
    String namePlayer = options[0];
    this.thisRoundNumber = 1;
    this.numberOfRounds = numRounds;
    MessageCli.WELCOME_PLAYER.printMessage(namePlayer);
  }

  public void play() {
    MessageCli.START_ROUND.printMessage(this.thisRoundNumber, this.numberOfRounds);
    this.thisRoundNumber += 1;
    MessageCli.ASK_HUMAN_INPUT.printMessage();
    String input = Utils.scanner.nextLine();
  }

  public void showStats() {}
}
