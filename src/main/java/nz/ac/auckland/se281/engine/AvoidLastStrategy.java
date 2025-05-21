package nz.ac.auckland.se281.engine;

import java.util.ArrayList;
import nz.ac.auckland.se281.model.Colour;

public class AvoidLastStrategy implements Strategy {
  public Colour getGuessColour(Game game) {
    ArrayList<Colour> playerChosenColours = game.getPlayerChosenColours();
    int lastIndex = playerChosenColours.size() - 1;
    Colour lastChosenColour = playerChosenColours.get(lastIndex);
    return Colour.getRandomColourExcluding(lastChosenColour);
  }
}
