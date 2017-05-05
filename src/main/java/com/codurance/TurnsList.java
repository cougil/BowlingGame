package com.codurance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

class TurnsList {

    private static final int NUMBER_OF_TURNS_PLUS_BONUS = 11;
    private static final String TURN_SEPARATOR = "\\|";
    private static final int MAX_SCORE = 10;

    private final List<Turn> list;

    private TurnsList(List<Turn> list) {
        this.list = Collections.unmodifiableList(list);
    }

    Turn getTurn(int turnNumber) {
        return list.get(turnNumber);
    }

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
            balls.add(parsePosition0(tries[0]));
            balls.add(parsePosition1(balls.get(0), tries[1]));

            TurnType turnType = TurnType.Numeric;
            if (tries[0] == Symbols.STRIKE) turnType = TurnType.Strike;
            if (tries[1] == Symbols.SPARE) turnType = TurnType.Spare;

            return new Turn(balls, turnType);
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

    private static int parsePosition0(char position) {
        return position == Symbols.STRIKE ? MAX_SCORE : getNumericValue(position);
    }

    private static int parsePosition1(int previousValue, char position) {
        return position == Symbols.SPARE ? MAX_SCORE - previousValue : parsePosition0(position);
    }

    private static int getNumericValue(char position) {
        return position == Symbols.MISS ? 0 : Character.getNumericValue(position);
    }

}
