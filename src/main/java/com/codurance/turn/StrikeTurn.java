package com.codurance.turn;

import com.codurance.AdditionalBalls;

import java.util.List;

public class StrikeTurn extends Turn {

    public StrikeTurn(List<Integer> tries) {
        super(tries);
    }

    @Override
    public int ballsPlayed() {
        return 1;
    }

    @Override
    public AdditionalBalls additionalBalls() {
        return AdditionalBalls.TWICE;
    }

}
