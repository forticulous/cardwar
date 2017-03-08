package com.codechallenge;

import com.codechallenge.card.CardRank;
import com.codechallenge.card.Suit;
import com.codechallenge.deck.DeckImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test class for War
 */
public class WarTest {
    /**
     * Test that dealCards evenly distributes the deck
     */
    @Test
    public void dealCardsTest() throws Exception {
        War war = new War();

        DeckImpl deck = new DeckImpl();
        deck.push(new Card(CardRank.Four, Suit.Club));
        deck.push(new Card(CardRank.Three, Suit.Heart));

        List<DeckImpl> playerPiles = war.dealCards(2, deck);

        assertEquals(1, playerPiles.get(0).size());
        assertEquals(1, playerPiles.get(1).size());
    }

    /**
     * Simple battle scenario with no war
     */
    @Test
    public void battleTest() throws Exception {
        War war = new War();

        List<DeckImpl> playerPiles = new ArrayList<>();
        {
            DeckImpl playerOnePile = new DeckImpl();
            playerOnePile.push(new Card(CardRank.King, Suit.Diamond));

            playerPiles.add(playerOnePile);
        }

        {
            DeckImpl playerTwoPile = new DeckImpl();
            playerTwoPile.push(new Card(CardRank.Three, Suit.Club));

            playerPiles.add(playerTwoPile);
        }

        war.battle(playerPiles);

        assertEquals(2, playerPiles.get(0).size());
        assertEquals(0, playerPiles.get(1).size());
    }

    /**
     * War scenario
     */
    @Test
    public void warTest() throws Exception {
        War war = new War();

        List<DeckImpl> playerPiles = new ArrayList<>();
        {
            DeckImpl playerOnePile = new DeckImpl();
            playerOnePile.push(new Card(CardRank.Two, Suit.Diamond));
            playerOnePile.push(new Card(CardRank.Queen, Suit.Diamond));
            playerOnePile.push(new Card(CardRank.Three, Suit.Diamond));

            playerPiles.add(playerOnePile);
        }

        {
            DeckImpl playerTwoPile = new DeckImpl();
            playerTwoPile.push(new Card(CardRank.Four, Suit.Club));
            playerTwoPile.push(new Card(CardRank.King, Suit.Club));
            playerTwoPile.push(new Card(CardRank.Three, Suit.Club));

            playerPiles.add(playerTwoPile);
        }

        war.battle(playerPiles);

        assertEquals(0, playerPiles.get(0).size());
        assertEquals(6, playerPiles.get(1).size());
    }

    @Test
    public void outOfCardsDuringWarTest() throws Exception {
        War war = new War();

        List<DeckImpl> playerPiles = new ArrayList<>();
        {
            DeckImpl playerOnePile = new DeckImpl();
            playerOnePile.push(new Card(CardRank.Two, Suit.Diamond));
            playerOnePile.push(new Card(CardRank.Queen, Suit.Diamond));
            playerOnePile.push(new Card(CardRank.Three, Suit.Diamond));

            playerPiles.add(playerOnePile);
        }

        {
            DeckImpl playerTwoPile = new DeckImpl();
            playerTwoPile.push(new Card(CardRank.King, Suit.Club));
            playerTwoPile.push(new Card(CardRank.Three, Suit.Club));

            playerPiles.add(playerTwoPile);
        }

        war.battle(playerPiles);

        assertEquals(5, playerPiles.get(0).size());
        assertEquals(0, playerPiles.get(1).size());
    }
}