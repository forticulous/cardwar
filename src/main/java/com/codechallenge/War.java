package com.codechallenge;

import com.codechallenge.card.CardRank;
import com.codechallenge.card.Suit;
import com.codechallenge.deck.DeckImpl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Program that plays the card game war
 */
public class War {
    public static void main(String[] args) {
        War war = new War();
        war.play(Suit.values().length, CardRank.values().length, 2);
    }

    private void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
        // Create new deck
        Deck deck = new DeckImpl();
        deck.create(numberOfSuits, numberOfRanks);
        deck.shuffle();

        // Distribute cards evenly among players
        List<Deque<Card>> playerPiles = new ArrayList<>();
        for (int playerNumber = 0; playerNumber < numberOfPlayers; playerNumber++) {
            playerPiles.add(new ArrayDeque<>());
        }

        int playerNumber = 0;
        for (Card card = deck.deal(); card != null; card = deck.deal()) {
            Deque<Card> playerPile = playerPiles.get(playerNumber);
            playerPile.push(card);

            playerNumber = (playerNumber + 1) % playerPiles.size();
        }

        pretty(playerPiles);

        // battle until one player has all cards
        int totalCards = numberOfSuits * numberOfRanks;
        while (playerPiles.stream().noneMatch((pile) -> pile.size() == totalCards)) {
            battle(playerPiles);
            pretty(playerPiles);
        }
    }

    private void pretty(List<Deque<Card>> playerPiles) {
        for (int player = 0; player < playerPiles.size(); player++) {
            System.out.println(String.format("Player %s has %s cards", player, playerPiles.get(player).size()));
            //System.out.println(playerPiles.get(player));
        }
    }

    private void battle(List<Deque<Card>> playerPiles) {
        List<Card> spoils = new ArrayList<>();

        for (Deque<Card> playerPile : playerPiles) {
            if (!playerPile.isEmpty()) {
                spoils.add(playerPile.pop());
            }
        }

        // Determine winner
        // TODO player 0 always wins
        int winner = 0;

        // Add spoils to bottom of player's pile
        spoils.forEach((card) -> playerPiles.get(winner).addLast(card));
    }
}
