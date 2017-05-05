package com.codurance.turn;

import com.codurance.AdditionalBalls;

import java.util.List;

public class SpareTurn extends Turn {

    public SpareTurn(List<Integer> tries) {
        super(tries);
    }

    @Override
    public AdditionalBalls additionalBalls() {
        return AdditionalBalls.SINGLE;
    }

}
