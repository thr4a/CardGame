package com.openclassrooms.cardgame.controller;

import com.openclassrooms.cardgame.model.Deck;
import com.openclassrooms.cardgame.model.Player;
import com.openclassrooms.cardgame.model.PlayingCard;

import java.util.ArrayList;
import java.util.List;

class View {
    public void something() {
    };

    public void setController(GameController gc) {
    }
}

public class GameController {
    enum GameState {
        AddingPlayers, CardsDealt, WinnerRevealed;
    }

    private Deck deck;
    private List<Player> players;
    private Player winner;
    private View view;
    private GameState gameState;

    public GameController(Deck deck, View view) {
        super();
        this.deck = deck;
        this.view = view;
        this.players = new ArrayList<Player>();
        this.gameState = GameState.AddingPlayers;
        view.setController(this);
    }

    public void run() {
        while (this.gameState == GameState.AddingPlayers) {
            view.something();
        }

        switch (this.gameState) {
            case CardsDealt:
                view.something();
                break;
            case WinnerRevealed:
                view.something();
                break;
        }
    }

    public void addPlayer(String playerName) {
        if (this.gameState == GameState.AddingPlayers) {
            this.players.add(new Player(playerName));
            view.something();
        }
    }

    public void startGame() {
        if (this.gameState != GameState.CardsDealt) {
            deck.shuffle();
            for (Player player : this.players) {
                player.addCardToHand(deck.removeTopCard());
            }
            this.gameState = GameState.CardsDealt;
        }
        this.run();
    }

    public void flipCards() {
        for (Player player : this.players) {
            PlayingCard pc = player.getCard(0);
            pc.flip();
            view.something();
        }

        this.evaluateWinner();
        this.displayWinner();
        this.rebuildDeck();
        this.gameState = GameState.WinnerRevealed;
        this.run();
    }

    private void rebuildDeck() {
        for (Player player : this.players) {
            deck.returnCardToDeck(player.removeCard());
        }
    }

    private void displayWinner() {
        view.something();
    }

    private void evaluateWinner() {
        Player bestPlayer = null;
        int bestRank = -1;
        int bestSuit = -1;

        for (Player player : this.players) {
            boolean newBestPlayer = false;

            if (bestPlayer == null) {
                newBestPlayer = true;
            } else {
                PlayingCard pc = player.getCard(0);
                int thisRank = pc.getRank().value();
                if (thisRank >= bestRank) {
                    if (thisRank > bestRank) {
                        newBestPlayer = true;
                    } else {
                        if (pc.getSuit().value() > bestSuit) {
                            newBestPlayer = true;
                        }
                    }
                }
            }

            if (newBestPlayer) {
                bestPlayer = player;
                PlayingCard pc = player.getCard(0);
                bestRank = pc.getRank().value();
                bestSuit = pc.getSuit().value();
            }
        }
    }
}
