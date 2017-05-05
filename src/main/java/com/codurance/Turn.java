package com.codurance;

import java.util.Collections;
import java.util.List;

class Turn {

    private final List<Integer> tries;
    private final TurnType type;

    Turn(List<Integer> tries, TurnType type) {
        this.tries = Collections.unmodifiableList(tries);
        this.type = type;
    }

    TurnType getType() {
        return type;
    }

    int getTotalScore() {
        return tries.stream().mapToInt(d -> d).sum();
    }

    int ballsPlayed() {
        return TurnType.Strike == type ? 1 : 2;
    }

    int getFirstBallScore() {
        return tries.get(0);
    }

}
