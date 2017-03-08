package com.codechallenge.deck;

import com.codechallenge.Card;
import com.codechallenge.Deck;
import com.codechallenge.card.CardRank;
import com.codechallenge.card.Suit;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

/**
 * Test class for DeckImpl
 */
public class DeckImplTest {
    /**
     * Create a deck and assert the correct number of cards
     */
    @Test
    public void createTest() throws Exception {
        Deck deck = new DeckImpl();
        deck.create(Suit.values().length, CardRank.values().length);

        assertEquals(Suit.values().length * CardRank.values().length, ((DeckImpl) deck).size());
    }

    /**
     * Create a deck, shuffle it, then assert that the cards are in a different order
     */
    @Test
    public void shuffleTest() throws Exception {
        Deck deck = new DeckImpl();
        deck.create(Suit.values().length, CardRank.values().length);

        List<Card> unshuffled = new ArrayList<>(((DeckImpl) deck).cardStack);
        System.out.println(unshuffled);

        deck.shuffle();

        List<Card> shuffled = new ArrayList<>(((DeckImpl) deck).cardStack);
        System.out.println(shuffled);

        assertEquals(unshuffled.size(), shuffled.size());
        assertNotEquals(unshuffled, shuffled);
    }

    /**
     * Create a deck then deal the top card, asserting it was the card on the top of the deck
     */
    @Test
    public void dealTest() throws Exception {
        Deck deck = new DeckImpl();
        deck.create(Suit.values().length, CardRank.values().length);

        Card topCard = ((DeckImpl) deck).cardStack.peek();
        int numCards = ((DeckImpl) deck).size();

        Card dealtCard = deck.deal();

        assertSame(topCard, dealtCard);
        assertEquals(numCards - 1, ((DeckImpl) deck).size());
    }
}