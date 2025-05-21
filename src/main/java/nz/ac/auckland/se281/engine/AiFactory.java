package nz.ac.auckland.se281.engine;

import nz.ac.auckland.se281.Main.Difficulty;

public class AiFactory {

  public static Ai createAi(Difficulty difficulty, Game game) {
    if (difficulty == Difficulty.EASY) {
      EasyAi easyAi = new EasyAi(game);
      return easyAi;
    } else if (difficulty == Difficulty.MEDIUM) {
      EasyAi easyAi = new EasyAi(game);
      return easyAi;
    } else {
      EasyAi easyAi = new EasyAi(game);
      return easyAi;
    }
  }
}
