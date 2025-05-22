package nz.ac.auckland.se281.engine;

import nz.ac.auckland.se281.model.Colour;

public class EasyAi extends Ai {
  private RandomStrategy randomStrategy;

  public EasyAi(Game game) {
    super(game);
    this.randomStrategy = new RandomStrategy();
    setStrategy(this.randomStrategy);
  }

  @Override
  public Colour getGuessColour() {
    return this.currentStrategy.getGuessColour(this.game);
  }
}
