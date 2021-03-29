package ipvc.estg.prog2.games.poker;

import ipvc.estg.prog2.games.GameSimulator;
import ipvc.estg.prog2.misc.RandomSingleton;

import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.Collections;

public class KuhnPokerSimulator extends GameSimulator<
        KuhnPokerAction,
        Integer,
        KuhnPokerState,
        KuhnPokerPlayer> {

    private ArrayList<KuhnPokerCard> deck = new ArrayList<>();

    public KuhnPokerSimulator(KuhnPokerPlayer player1, KuhnPokerPlayer player2) {
        super(player1, player2);
        this.deck.add(KuhnPokerCard.Jack);
        this.deck.add(KuhnPokerCard.Queen);
        this.deck.add(KuhnPokerCard.King);
    }

    @Override
    protected KuhnPokerState initGame() {
        Collections.shuffle(this.deck, RandomSingleton.getInstance());
        KuhnPokerPlayer[] positions = this.getPlayerPositions();
        for(int pos = 0; pos < positions.length; ++pos) {
            positions[pos].setCurrentCard(this.deck.get(pos));
        }
        return new KuhnPokerState();
    }

    @Override
    protected void beforeEndGame(KuhnPokerState state) {
        super.beforeEndGame(state);
        if(state.isShowdown()) {
            for(int pos = 0; pos < state.getNumPlayers(); ++pos) {
                state.drawCard(pos, this.deck.get(pos));
            }
        }
    }
}