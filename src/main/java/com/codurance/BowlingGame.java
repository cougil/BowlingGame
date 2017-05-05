package com.codurance;

import com.codurance.turn.Turn;

import java.util.stream.IntStream;

class BowlingGame {

    private static final int NUMBER_OF_TURNS = 10;

    int getScore(String game) {
        TurnsList turns = TurnsList.of(game);
        return getNormalTurnsScore(turns) + getBonusScore(turns);
    }

    private int getNormalTurnsScore(TurnsList turns) {
        return IntStream.range(0, NUMBER_OF_TURNS)
                .map(turnNumber -> getTurnScore(turns, turnNumber))
                .sum();
    }

    private int getBonusScore(TurnsList turns) {
        Turn bonus = turns.getTurn(NUMBER_OF_TURNS);
        return bonus.getTotalScore();
    }

    private int getTurnScore(TurnsList turns, int turnNumber) {
        Turn turn = turns.getTurn(turnNumber);
        return turn.getTotalScore() + calculateAdditionalScore(turns, turnNumber+1,
                turn.additionalBalls());
    }

    private int calculateAdditionalScore(TurnsList turns, int turnNumber, AdditionalBalls additionalBalls) {
        if (turnNumber >= NUMBER_OF_TURNS || AdditionalBalls.NONE == additionalBalls) {
            return 0;
        }
        Turn turn = turns.getTurn(turnNumber);
        if (AdditionalBalls.SINGLE == additionalBalls) {
            return turn.getFirstBallScore();
        }
        if (turn.ballsPlayed() == 2) {
            return turn.getTotalScore();
        }
        return turn.getFirstBallScore() + calculateAdditionalScore(turns, turnNumber+1,
                additionalBalls.decrement());
    }

}
