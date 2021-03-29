package ipvc.estg.prog2.games.poker;

import ipvc.estg.prog2.games.GameSimulator;

import java.util.Arrays;
import java.util.Collections;

public class PokerSimulator {

    /*public PokerSimulator(PokerPlayer player1, PokerPlayer player2) {
        super(player1, player2);
    }

    private PokerAction getAction(PokerPlayer actingPlayer) {
        // obtain the action from the acting player
        PokerAction act = actingPlayer.getAction();

        // notify both players the action has occured
        getPlayer1().eventAction(act);
        getPlayer2().eventAction(act);

        return act;
    }


    public void runSimulation() {
        // Initialize and shuffle the deck
        Card[] deck = { Card.Jack, Card.Queen, Card.King };
        Collections.shuffle(Arrays.asList(deck));

        // Assign cards to players
        PokerPlayer p1 = getPlayer1();
        PokerPlayer p2 = getPlayer2();
        p1.startGame(deck[0]);
        p2.startGame(deck[1]);

        PokerPlayer winner = null;
        int score = 1;

        if(getAction(p1) == PokerAction.PASS) {
            if (getAction(p2) != PokerAction.PASS) { // player 2 raises
                if(getAction(p1) == PokerAction.PASS) {
                    winner = p2;
                } else {
                    score = 2;
                }
            }
        } else { // player 1 raises
            if(getAction(p2) == PokerAction.PASS) {
                winner = p1;
            } else { // raise
                score = 2;
            }
        }

        // if nobody passed after a bet
        if(winner == null) {
            winner = deck[0].ordinal() > deck[1].ordinal() ? p1 : p2;
        }

        PokerPlayer looser = winner == p1 ? p2 : p1;
        winner.endGame(score, looser.getCurrentCard());
        looser.endGame(-score, winner.getCurrentCard());
    }*/
}