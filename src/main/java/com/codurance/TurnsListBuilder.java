package com.codurance;

import com.codurance.turn.NormalTurn;
import com.codurance.turn.SpareTurn;
import com.codurance.turn.StrikeTurn;
import com.codurance.turn.Turn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

class TurnsListBuilder {

    private static final int NUMBER_OF_TURNS_PLUS_BONUS = 11;
    private static final String TURN_SEPARATOR = "\\|";
    private static final int MAX_SCORE = 10;

    static TurnsList of(String game) {
        String[] turns = game.split(TURN_SEPARATOR, NUMBER_OF_TURNS_PLUS_BONUS);
        return new TurnsList(
                Arrays.stream(turns)
                        .map(prepareTurns())
                        .map(parseTurns())
                        .collect(toList())
        );
    }

    private static Function<String, Turn> parseTurns() {
        return turn -> {
            char[] tries = turn.toCharArray();

            List<Integer> balls = new ArrayList<>();
            balls.add(parseFirstPosition(tries[0]));
            balls.add(parseSecondPosition(balls.get(0), tries[1]));

            if (tries[0] == Symbols.STRIKE) return new StrikeTurn(balls);
            if (tries[1] == Symbols.SPARE) return new SpareTurn(balls);
            return new NormalTurn(balls);
        };
    }

    private static Function<String, String> prepareTurns() {
        return turn -> {
            StringBuilder buffer = new StringBuilder();
            buffer.append(turn.replaceAll(TURN_SEPARATOR,""));
            for (int i = buffer.length(); i < 2; i++) buffer.append(Symbols.MISS);
            return buffer.toString();
        };
    }

    private static int parseFirstPosition(char position) {
        return position == Symbols.STRIKE ? MAX_SCORE : getNumericValue(position);
    }

    private static int parseSecondPosition(int previousValue, char position) {
        return position == Symbols.SPARE ? MAX_SCORE - previousValue : parseFirstPosition(position);
    }

    private static int getNumericValue(char position) {
        return position == Symbols.MISS ? 0 : Character.getNumericValue(position);
    }

}
