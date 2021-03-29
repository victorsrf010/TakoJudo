package ipvc.estg.prog2.games.poker;

public abstract class KuhnPokerPlayer extends ipvc.estg.prog2.games.Player<
        KuhnPokerAction,
        KuhnPokerState,
        Integer> {

    private int score = 0;
    private int numGames = 0;
    private KuhnPokerCard currentCard = null;

    /**
     * @param name nome do jogador
     */
    public KuhnPokerPlayer(String name) {
        super(name);
    }

    /**
     * Muda a carta do jogador
     * @param card
     */
    public void setCurrentCard(KuhnPokerCard card) {
        this.currentCard = card;
    }

    /**
     * Obtem a carta atual do jogador
     * @return card
     */
    public KuhnPokerCard getCurrentCard() {
        return this.currentCard;
    }

    public int getScore() {
        return score;
    }

    public double getExpectedValue() {
        return (double)score/(double)numGames;
    }

    @Override
    public void eventNewGame() {
        this.numGames++;
    }

    @Override
    public void eventResult(int playerPos, Integer result) {
        if(this.getCurrentPos() == playerPos) {
            this.score += result;
        }
    }

    @Override
    public void printStats() {
        System.out.println(String.format("Player %s | Total profit: %d€ | Profit per game: %f€", this.getName(), this.getScore(), this.getExpectedValue()));
    }
}
