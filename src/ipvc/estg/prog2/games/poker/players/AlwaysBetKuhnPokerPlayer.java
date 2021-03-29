package ipvc.estg.prog2.games.poker.players;

import ipvc.estg.prog2.games.poker.KuhnPokerAction;
import ipvc.estg.prog2.games.poker.KuhnPokerPlayer;
import ipvc.estg.prog2.games.poker.KuhnPokerState;

public class AlwaysBetKuhnPokerPlayer extends KuhnPokerPlayer {

    /**
     * @param name nome do jogador
     */
    public AlwaysBetKuhnPokerPlayer(String name) {
        super(name);
    }

    @Override
    public KuhnPokerAction getAction(KuhnPokerState state) {
        return KuhnPokerAction.BET;
    }

    @Override
    public void eventAction(int playerPos, KuhnPokerAction action, KuhnPokerState newState) {

    }

    @Override
    public void eventEndGame(KuhnPokerState finalState) {
    }
}
