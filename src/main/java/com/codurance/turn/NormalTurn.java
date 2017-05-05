package com.codurance.turn;

import com.codurance.AdditionalBalls;

import java.util.List;

public class NormalTurn extends Turn {

    public NormalTurn(List<Integer> balls) {
        super(balls);
    }

    @Override
    public AdditionalBalls additionalBalls() {
        return AdditionalBalls.NONE;
    }

}
