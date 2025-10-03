# SOFTENG 281 Assignment 2
This repository is an archive of an assignment completed for **SOFTENG 281 Object-Oriented Programming** at The University of Auckland.

Grade: A+

## Learning Outcomes
- Gain more confidence programming in Java.
- Design an object-oriented programming (OOP) solution to a problem.
- Apply object-oriented design patterns

## Assignment Brief

You are to implement a Mind Game, where a human player competes against an Artificial Intelligence (AI) called HAL-9000 (I hope you get the cinematic reference) in a colour-based guessing game. This is a game of chance, but also a game of psychology! The AI will try to guess the player’s next colour.

**Game Rules**

Each round, both the human and the AI must:

- Select a colour to play.
- Make a guess about the opponent’s chosen colour.

**Scoring System**

- +1 point for each correct guess.
- +2 bonus points if:
  - The correct guess matches the Power Colour, and
  - The round number is a multiple of 3.

The Power Colour is a randomly selected colour that is assigned every third round. Importantly, neither the human player nor the AI knows what the Power Colour will be before choosing their colours for that round.

The game ends after a predefined number of rounds, and the winner is the player with the highest total score.

**AI Difficulty Levels** (Implemented using Strategy design pattern)

The AI’s behavior adapts based on the selected difficulty:

- EASY: Chooses and guesses completely at random.
- MEDIUM: Starts randomly, then switches to a strategy from round 2 onward.
- HARD: Starts randomly, then adapts based on the win/loss history of previous rounds.
