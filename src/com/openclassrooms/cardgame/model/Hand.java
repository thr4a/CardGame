package com.openclassrooms.cardgame.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<PlayingCard> cards;

    public Hand() {
        this.cards = new ArrayList<PlayingCard>();
    }

    public void addCard(PlayingCard pc) {
        this.cards.add(pc);
    }

    public PlayingCard getCard(int index) {
        return this.cards.get(index);
    }

    public PlayingCard removeCard() {
        return this.cards.remove(0);
    }
}
