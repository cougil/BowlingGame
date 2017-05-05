package com.codurance;

public enum AdditionalBalls {

    NONE(0),
    SINGLE(1),
    TWICE(2);

    private final int balls;

    AdditionalBalls(int balls) {
        this.balls = balls;
    }

    public AdditionalBalls decrement() {
        if (AdditionalBalls.NONE == this) return this;
        return AdditionalBalls.values()[balls-1];
    }

}
