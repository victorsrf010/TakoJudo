package ipvc.estg.prog2.games.poker.players;

import ipvc.estg.prog2.games.poker.KuhnPokerAction;
import ipvc.estg.prog2.games.poker.KuhnPokerCard;
import ipvc.estg.prog2.games.poker.KuhnPokerPlayer;
import ipvc.estg.prog2.games.poker.KuhnPokerState;
import ipvc.estg.prog2.misc.RandomSingleton;

public class AlwaysBetKingKuhnPokerPlayer extends KuhnPokerPlayer {

    /**
     * @param name nome do jogador
     */
    public AlwaysBetKingKuhnPokerPlayer(String name) {
        super(name);
    }

    @Override
    public KuhnPokerAction getAction(KuhnPokerState state) {
        if(this.getCurrentCard() == KuhnPokerCard.King) {
            return KuhnPokerAction.BET;
        } else {
            return RandomSingleton.nextBoolean()?KuhnPokerAction.BET:KuhnPokerAction.PASS;
        }
    }

    @Override
    public void eventAction(int playerPos, KuhnPokerAction action, KuhnPokerState newState) {

    }

    @Override
    public void eventEndGame(KuhnPokerState finalState) {
    }
}
