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
    protected Deque<Card> cardStack;

    @Override
    public void create(int numberOfSuits, int numberOfRanks) {
        cardStack = new ArrayDeque<>();

        for (int suit = 0; suit < numberOfSuits; suit++) {
            for (int rank = 0; rank < numberOfRanks; rank++) {
                cardStack.push(new Card(suit, rank));
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
}
