package com.codechallenge.card;

/**
 * Enumeration of card suits
 */
public enum Suit {
    Heart,
    Diamond,
    Spade,
    Club;

    public static Suit fromInt(int num) {
        if (num < 0 || num > values().length) {
            return null;
        }
        return values()[num];
    }
}
