package com.codechallenge;

/**
 * Instance of a card with suit and rank attributes
 */
public class Card {
    private final int suit;
    private final int rank;

    public Card(int suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        return suit == card.suit &&
                rank == card.rank;
    }

    @Override
    public int hashCode() {
        int result = 7;
        result = 31 * result + suit;
        result = 31 * result + rank;
        return result;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", rank=" + rank +
                '}';
    }
}
