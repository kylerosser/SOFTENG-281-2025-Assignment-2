package nz.ac.auckland.se281.engine;

import nz.ac.auckland.se281.Main.Difficulty;

public class AiFactory {

  public static Ai createAi(Difficulty difficulty, Game game) {
    if (difficulty == Difficulty.EASY) {
      EasyAi easyAi = new EasyAi(game);
      return easyAi;
    } else if (difficulty == Difficulty.MEDIUM) {
      MediumAi mediumAi = new MediumAi(game);
      return mediumAi;
    } else {
      EasyAi easyAi = new EasyAi(game);
      return easyAi;
    }
  }
}
