package com.openclassrooms.cardgame.model;

public enum Suit {
    DIAMONDS (1), HEARTS (2), SPADES(3), CLUBS (4);

    private int suit;

    private Suit(int value) {
        this.suit = value;
    }

    public int getSuit() {
        return this.suit;
    }
}
