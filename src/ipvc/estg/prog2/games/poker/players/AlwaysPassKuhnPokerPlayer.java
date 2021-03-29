package ipvc.estg.prog2.games.poker.players;

import ipvc.estg.prog2.games.poker.KuhnPokerAction;
import ipvc.estg.prog2.games.poker.KuhnPokerPlayer;
import ipvc.estg.prog2.games.poker.KuhnPokerState;

public class AlwaysPassKuhnPokerPlayer extends KuhnPokerPlayer {

    /**
     * @param name nome do jogador
     */
    public AlwaysPassKuhnPokerPlayer(String name) {
        super(name);
    }

    @Override
    public KuhnPokerAction getAction(KuhnPokerState state) {
        return KuhnPokerAction.PASS;
    }

    @Override
    public void eventAction(int playerPos, KuhnPokerAction action, KuhnPokerState newState) {
    }

    @Override
    public void eventEndGame(KuhnPokerState finalState) {
    }

}
