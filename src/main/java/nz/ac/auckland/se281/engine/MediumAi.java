package nz.ac.auckland.se281.engine;

import nz.ac.auckland.se281.model.Colour;

public class MediumAi extends Ai {
  private RandomStrategy randomStrategy;
  private AvoidLastStrategy avoidLastStrategy;

  public MediumAi(Game game) {
    super(game);
    this.randomStrategy = new RandomStrategy();
    this.avoidLastStrategy = new AvoidLastStrategy();
    setStrategy(this.randomStrategy);
  }

  public Colour getGuessColour() {
    if (this.game.getThisRoundNumber() == 1) {
      setStrategy(this.randomStrategy);
    } else {
      setStrategy(this.avoidLastStrategy);
    }
    return this.currentStrategy.getGuessColour(this.game);
  }
}
