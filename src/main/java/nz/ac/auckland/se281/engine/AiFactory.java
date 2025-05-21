package nz.ac.auckland.se281.engine;

import nz.ac.auckland.se281.Main.Difficulty;

public class AiFactory {

  public static Ai createAi(Difficulty difficulty, Game game) {
    if (difficulty == Difficulty.EASY) {
      return new EasyAi(game);
    } else if (difficulty == Difficulty.MEDIUM) {
      return new MediumAi(game);
    } else if (difficulty == Difficulty.HARD) {
      return new HardAi(game);
    } else {
      return new EasyAi(game);
    }
  }
}
