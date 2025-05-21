package nz.ac.auckland.se281.engine;

import nz.ac.auckland.se281.model.Colour;

public class RandomStrategy implements Strategy {
  public Colour getGuessColour(Game game) {
    return Colour.getRandomColourForAi();
  }
}
