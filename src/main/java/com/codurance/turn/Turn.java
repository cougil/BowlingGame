package com.codurance.turn;

import com.codurance.AdditionalBalls;

import java.util.Collections;
import java.util.List;

public abstract class Turn {

    private final List<Integer> tries;

    Turn(List<Integer> tries) {
        this.tries = Collections.unmodifiableList(tries);
    }

    public int getTotalScore() {
        return tries.stream().mapToInt(d -> d).sum();
    }

    public int ballsPlayed() {
        return 2;
    }

    public int getFirstBallScore() {
        return tries.get(0);
    }

    public abstract AdditionalBalls additionalBalls();

}
