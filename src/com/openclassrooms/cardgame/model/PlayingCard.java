package com.openclassrooms.cardgame.model;

public class PlayingCard {
    private Rank rank;
    private Suit suit;
    private boolean faceUp;

    public PlayingCard(Rank rank, Suit suit) {
        super();
        this.rank = rank;
        this.suit = suit;
    }


    public Suit getSuit() {
        return this.suit;
    }

    public Rank getRank() {
        return this.rank;
    }

    public boolean isFaceUp() {
        return this.faceUp;
    }

    public void flip() {
        this.faceUp = !this.faceUp;
    }
}
