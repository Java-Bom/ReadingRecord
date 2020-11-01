package Chap9_GeneralProgrammingPrinciple.item58;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Collection<Suit> suits = Arrays.asList(Suit.values());
        Collection<Rank> ranks = Arrays.asList(Rank.values());

        List<Card> deck = new ArrayList<>();
//        for (Iterator<Suit> i = suits.iterator() ; i.hasNext() ;) {
//            for (Iterator<Rank> j = ranks.iterator() ; j.hasNext() ;) {
//                deck.add(new Card(i.next(), j.next()));
//            }
//        }

        for (Suit suit : suits) {
            for (Rank rank : ranks) {
                deck.add(new Card(suit, rank));
            }
        }
    }

    enum Suit {
        CLUB, DIAMOND, HEART, SPADE
    }

    enum Rank {
        ACE, DEUCE, THREE, FOUR, FIVE,
        SIX, SEVEN, EIGHT, NINE, TEN,
        JACK, QUEEN, KING
    }

    static class Card {
        Suit suit;
        Rank rank;

        public Card(final Suit suit, final Rank rank) {
            this.suit = suit;
            this.rank = rank;
        }
    }
}
