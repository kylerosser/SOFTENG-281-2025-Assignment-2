package nz.ac.auckland.se281.engine;

import nz.ac.auckland.se281.model.Colour;

public class HardAi extends Ai {
  private RandomStrategy randomStrategy;
  private AvoidLastStrategy avoidLastStrategy;
  private LeastUsedStrategy leastUsedStrategy;

  public HardAi(Game game) {
    super(game);
    this.randomStrategy = new RandomStrategy();
    this.avoidLastStrategy = new AvoidLastStrategy();
    this.leastUsedStrategy = new LeastUsedStrategy();
    setStrategy(this.randomStrategy);
  }

  public Colour getGuessColour() {
    if (this.game.getThisRoundNumber() == 1 || this.game.getThisRoundNumber() == 2) {
      setStrategy(this.randomStrategy);
    } else if (this.game.getThisRoundNumber() == 3) {
      setStrategy(this.leastUsedStrategy);
    } else {
      if (!game.getDidAiWinLastRound()) {
        if (this.currentStrategy == this.avoidLastStrategy) {
          setStrategy(this.leastUsedStrategy);
        } else {
          setStrategy(this.avoidLastStrategy);
        }
      }
    }
    return this.currentStrategy.getGuessColour(this.game);
  }
}
