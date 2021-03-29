package ipvc.estg.prog2.games.poker.players;

import ipvc.estg.prog2.games.poker.KuhnPokerAction;
import ipvc.estg.prog2.games.poker.KuhnPokerPlayer;
import ipvc.estg.prog2.games.poker.KuhnPokerState;
import ipvc.estg.prog2.misc.RandomSingleton;

public class RandomKuhnPokerPlayer extends KuhnPokerPlayer {

    /**
     * @param name nome do jogador
     */
    public RandomKuhnPokerPlayer(String name) {
        super(name);
    }

    @Override
    public KuhnPokerAction getAction(KuhnPokerState state) {
        return RandomSingleton.nextBoolean()?KuhnPokerAction.PASS:KuhnPokerAction.BET;
    }

    @Override
    public void eventAction(int playerPos, KuhnPokerAction action, KuhnPokerState newState) {
    }

    @Override
    public void eventEndGame(KuhnPokerState finalState) {
    }

}
