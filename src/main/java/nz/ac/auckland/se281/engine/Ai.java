package nz.ac.auckland.se281.engine;

import nz.ac.auckland.se281.model.Colour;

public abstract class Ai {
  protected Game game;
  protected Strategy currentStrategy;

  public Ai(Game game) {
    this.game = game;
  }

  protected void setStrategy(Strategy strategy) {
    this.currentStrategy = strategy;
  }

  public Colour getChosenColour() {
    return Colour.getRandomColourForAi();
  }

  public abstract Colour getGuessColour();
}
