package com.codechallenge.card;

/**
 * Enumeration of card types
 */
public enum CardRank {
    Two,
    Three,
    Four,
    Five,
    Six,
    Seven,
    Eight,
    Nine,
    Ten,
    Jack,
    Queen,
    King,
    Ace;

    public static CardRank fromInt(int num) {
        if (num < 0 || num > values().length) {
            return null;
        }
        return values()[num];
    }
}
