package com.codechallenge.deck;

import com.codechallenge.Card;
import com.codechallenge.Deck;

import java.security.SecureRandom;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Random;

/**
 * Deck implementation
 */
public class DeckImpl implements Deck {
    /** Stack of cards */
    Deque<Card> cardStack;

    public DeckImpl() {
        this.cardStack = new ArrayDeque<>();
    }

    @Override
    public void create(int numberOfSuits, int numberOfRanks) {
        for (int rank = 0; rank < numberOfRanks; rank++) {
            for (int suit = 0; suit < numberOfSuits; suit++) {
                cardStack.push(new Card(rank, suit));
            }
        }
    }

    @Override
    public void shuffle() {
        List<Card> cards = new ArrayList<>(cardStack);

        Random rand = new SecureRandom();
        Collections.shuffle(cards, rand);

        cardStack = new ArrayDeque<>(cards);
    }

    @Override
    public Card deal() {
        if (cardStack.isEmpty()) {
            return null;
        }
        return cardStack.pop();
    }

    /**
     * Add to top of the deck
     */
    public void push(Card card) {
        cardStack.push(card);
    }

    /**
     * Add to the bottom of the deck
     */
    public void addToBottom(Card card) {
        cardStack.addLast(card);
    }

    /**
     * Is this deck empty
     */
    public boolean isEmpty() {
        return cardStack.isEmpty();
    }

    /**
     * Number of cards in the deck
     */
    public int size() {
        return cardStack.size();
    }

    @Override
    public String toString() {
        return "DeckImpl{" +
                cardStack +
                '}';
    }
}
