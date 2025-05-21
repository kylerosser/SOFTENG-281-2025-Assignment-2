package nz.ac.auckland.se281.engine;

import nz.ac.auckland.se281.model.Colour;

interface Strategy {
  Colour getGuessColour(Game game);
}
