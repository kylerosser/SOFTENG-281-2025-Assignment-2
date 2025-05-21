package nz.ac.auckland.se281.engine;

import java.util.ArrayList;
import java.util.HashMap;
import nz.ac.auckland.se281.model.Colour;

public class LeastUsedStrategy implements Strategy {
  public Colour getGuessColour(Game game) {
    // Access the list of all previous player chosen colours
    ArrayList<Colour> playerChosenColours = game.getPlayerChosenColours();

    // Count the number of times each colour appears and store it
    HashMap<Colour, Integer> colourCountMap = new HashMap<Colour, Integer>();
    // Initialise all colour counts to 0
    for (Colour thisColour : Colour.values()) {
      colourCountMap.put(thisColour, 0);
    }
    // Start the count
    for (Colour thisColour : playerChosenColours) {
      // Increment the hashmap value for that colour
      int currentCount = colourCountMap.get(thisColour);
      colourCountMap.put(thisColour, currentCount + 1);
    }

    // Determine which colour appears the least
    int lowestCount = -1;
    Colour lowestColour = null;
    for (Colour thisColour : Colour.values()) {
      int thisCount = colourCountMap.get(thisColour);
      if (lowestCount == -1 || thisCount < lowestCount) {
        lowestColour = thisColour;
        lowestCount = thisCount;
      }
    }

    return lowestColour;
  }
}
