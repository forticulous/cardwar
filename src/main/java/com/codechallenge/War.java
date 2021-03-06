package com.codechallenge;

import com.codechallenge.card.CardRank;
import com.codechallenge.card.Suit;
import com.codechallenge.deck.DeckImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

        List<DeckImpl> playerPiles = dealCards(numberOfPlayers, deck);
        prettyPrint(playerPiles);

        // battle until one player has all cards
        int totalCards = numberOfSuits * numberOfRanks;
        while (playerPiles.stream().noneMatch((pile) -> pile.size() == totalCards)) {
            battle(playerPiles);
            prettyPrint(playerPiles);
        }
    }

    List<DeckImpl> dealCards(int numberOfPlayers, Deck deck) {
        // Distribute cards evenly among players
        List<DeckImpl> playerPiles = Stream.generate(DeckImpl::new)
                .limit(numberOfPlayers)
                .collect(Collectors.toList());

        for (int playerNumber = 0; ; playerNumber = (playerNumber + 1) % numberOfPlayers) {
            Card card = deck.deal();
            if (card == null) {
                break;
            }

            playerPiles.get(playerNumber).push(card);
        }

        return playerPiles;
    }

    private void prettyPrint(List<DeckImpl> playerPiles) {
        for (int player = 0; player < playerPiles.size(); player++) {
            System.out.println(String.format("Player %s has %s cards", player, playerPiles.get(player).size()));
        }
    }

    void battle(List<DeckImpl> playerPiles) {
        List<Card> spoils = new ArrayList<>();

        // all players with cards join the battle initially
        Set<Integer> playersIn = IntStream.range(0, playerPiles.size())
                .filter((player) -> !playerPiles.get(player).isEmpty())
                .boxed()
                .collect(Collectors.toSet());

        // sub-battles continue until there is one winner
        boolean isWar = false;
        while (playersIn.size() > 1) {
            System.out.println(isWar ? "War!" : "Battle!");

            List<PlayerCard> thisBattle = new ArrayList<>();
            Iterator<Integer> iter = playersIn.iterator();
            while (iter.hasNext()) {
                Integer player = iter.next();

                Card card = playerPiles.get(player).deal();

                // if war, players play an additional card into the spoils
                if (isWar) {
                    System.out.println(String.format("Player %s plays %s face down", player, card));
                    spoils.add(card);

                    card = playerPiles.get(player).deal();

                    // if the player is out of cards then they lose
                    if (card == null) {
                        System.out.println(String.format("Player %s ran out of cards!", player));
                        iter.remove();
                        continue;
                    }
                }

                System.out.println(String.format("Player %s plays %s", player, card));

                thisBattle.add(new PlayerCard(card, player));
            }

            // find the highest rank in this sub-battle
            int highestRank = thisBattle.stream()
                    .map(PlayerCard::getCard)
                    .mapToInt(Card::getRank)
                    .max().getAsInt();

            // remove players who didn't have the highest rank
            thisBattle.stream()
                    .filter((playerCard) -> playerCard.getCard().getRank() != highestRank)
                    .map(PlayerCard::getPlayer)
                    .forEach(playersIn::remove);

            // add all cards in this sub-battle to the spoils
            thisBattle.stream()
                    .map(PlayerCard::getCard)
                    .forEach(spoils::add);

            isWar = true;
        }

        // Determine winner
        int winner = playersIn.stream().findFirst().get();

        System.out.println(String.format("Player %s wins %s cards!", winner, spoils.size()));

        // Add spoils to bottom of winner's pile
        spoils.forEach((card) -> playerPiles.get(winner).addToBottom(card));
    }

    /**
     * Helper class associating a card with a player
     */
    private class PlayerCard {
        private final Card card;
        private final int player;

        private PlayerCard(Card card, int player) {
            this.card = card;
            this.player = player;
        }

        public Card getCard() {
            return card;
        }

        public int getPlayer() {
            return player;
        }
    }
}
