package ipvc.estg.prog2.games.poker;

import ipvc.estg.prog2.games.State;

import java.util.ArrayList;
import java.util.Arrays;

public class KuhnPokerState extends State<KuhnPokerAction, Integer, KuhnPokerState> {

    private ArrayList<KuhnPokerAction> sequence;
    private int actingPlayer;
    private boolean finished;
    private KuhnPokerCard[] cards;
    private int[] bets;
    private boolean showdown;

    public KuhnPokerState() {
        this.sequence = new ArrayList<>();
        this.actingPlayer = 0;
        this.finished = false;
        this.cards = new KuhnPokerCard[]{ null, null };
        this.bets = new int[]{ 1, 1 };
        this.showdown = false;
    }

    @Override
    public int getNumPlayers() {
        return 2;
    }

    @Override
    public boolean validateAction(KuhnPokerAction action) {
        return !this.finished && action != null;
    }

    @Override
    protected void update(KuhnPokerAction action) {
        if(!this.sequence.isEmpty()) {
            KuhnPokerAction lastAction = this.sequence.get(this.sequence.size()-1);

            if(lastAction == KuhnPokerAction.BET) {
                this.finished = true;
                if(action == KuhnPokerAction.BET) {
                    this.showdown = true;
                }
            } else { // lastAction == PASS
                if(action == KuhnPokerAction.PASS) {
                    this.finished = true;
                    this.showdown = true;
                }
            }
        }

        sequence.add(action);
        if(action == KuhnPokerAction.BET) {
            this.bets[this.actingPlayer]++;
        }
        this.actingPlayer = this.actingPlayer == 0?1:0;
    }

    @Override
    public void display() {
        for(KuhnPokerAction action : this.sequence) {
            System.out.print(action == KuhnPokerAction.BET ? 'b' : 'p');
        }
        System.out.println(": pot = " + this.getPot());
    }

    @Override
    public boolean isFinished() {
        return this.finished;
    }

    @Override
    public int getActingPlayer() {
        return this.actingPlayer;
    }

    @Override
    public KuhnPokerState getClone() {
        KuhnPokerState cloned = new KuhnPokerState();
        cloned.sequence = (ArrayList<KuhnPokerAction>) this.sequence.clone();
        cloned.finished = this.finished;
        cloned.actingPlayer = this.actingPlayer;
        for(int i = 0; i < this.cards.length; ++i) {
            cloned.cards[i] = this.cards[i];
        }
        cloned.showdown = this.showdown;
        return cloned;
    }

    @Override
    public Integer getResult(int pos) {
        // se não tivermos terminado, não há resultado
        if(!this.finished) {
            return null;
        }

        // se tivermos terminado e houver um showdown, as cartas tem de estar disponíveis
        if(this.showdown) {
            for (int i = 0; i < this.cards.length; ++i) {
                if (this.cards[i] == null) {
                    return null;
                }
            }
        }

        int pot = this.getPot();
        int oppPos = pos == 0 ? 1 : 0;

        if(this.showdown) {
            return (this.cards[pos].ordinal() > this.cards[oppPos].ordinal()?1:-1) * (pot / 2);
        } else {
            return (this.bets[pos] > this.bets[oppPos] ? 1 : -1);
        }
    }

    /**
     * Obtem o valor do pote (soma de todas as apostas
     * @return
     */
    public int getPot() {
        return Arrays.stream(this.bets).sum();
    }

    /**
     * Atribuir uma carta a um jogador
     * @param pos posição do jogador
     * @param card carta a atribuir
     */
    public void drawCard(int pos, KuhnPokerCard card) {
        this.cards[pos] = card;
    }

    public boolean isShowdown() {
        return showdown;
    }

    public ArrayList<KuhnPokerAction> getSequence() {
        return sequence;
    }
}
