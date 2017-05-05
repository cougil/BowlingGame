package com.codurance;

import com.codurance.turn.Turn;

import java.util.Collections;
import java.util.List;

class TurnsList {

    private final List<Turn> list;

    TurnsList(List<Turn> list) {
        this.list = Collections.unmodifiableList(list);
    }

    Turn getTurn(int turnNumber) {
        return list.get(turnNumber);
    }

}
