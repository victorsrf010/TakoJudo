package ipvc.estg.prog2.games.poker.players;

import ipvc.estg.prog2.games.poker.KuhnPokerAction;
import ipvc.estg.prog2.games.poker.KuhnPokerCard;
import ipvc.estg.prog2.games.poker.KuhnPokerPlayer;
import ipvc.estg.prog2.games.poker.KuhnPokerState;
import ipvc.estg.prog2.games.poker.cfr.Trainer;
import ipvc.estg.prog2.misc.RandomSingleton;

public class CFRKuhnPokerPlayer extends KuhnPokerPlayer {

    private Trainer trainer = new Trainer();

    /**
     * @param name nome do jogador
     */
    public CFRKuhnPokerPlayer(String name) {
        super(name);
        this.trainer = new Trainer();
        this.trainer.train(1000000);
    }

    @Override
    public KuhnPokerAction getAction(KuhnPokerState state) {
        String infoSet = "" + (getCurrentCard().ordinal()+1);

        for(KuhnPokerAction action : state.getSequence()) {
            if(action == KuhnPokerAction.PASS) {
                infoSet += 'p';
            } else {
                infoSet += 'b';
            }
        }

        double prob = trainer.nodeMap.get(infoSet).getAverageStrategy()[0];

        return RandomSingleton.nextDouble() <= prob ? KuhnPokerAction.PASS : KuhnPokerAction.BET;
    }

    @Override
    public void eventAction(int playerPos, KuhnPokerAction action, KuhnPokerState newState) {

    }

    @Override
    public void eventEndGame(KuhnPokerState finalState) {
    }
}
