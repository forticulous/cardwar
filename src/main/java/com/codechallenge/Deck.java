package com.codechallenge;

/**
 * Deck public interface
 */
public interface Deck {
    /**
     * Create the deck of cards
     */
    void create(int numberOfSuits, int numberOfRanks);

    /**
     * Shuffle the deck
     */
    void shuffle();

    /**
     * Deal a card from the deck
     */
    Card deal();
}
