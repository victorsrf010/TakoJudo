package ipvc.estg.prog2.games.poker;

public abstract class PokerPlayer /*extends ipvc.estg.prog2.games.Player*/ {

    /*private int score = 0;
    private int numGames = 0;
    private Card currentCard;

    public PokerPlayer(String name) { super(name); }

    public int getScore() {
        return score;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public double getExpectedValue() {
        return (double)score/(double)numGames;
    }

    public void startGame(Card card) {
        this.numGames++;
        eventNewGame(card);
        this.currentCard = card;
    }

    public void endGame(int obtainedScore, Card opponentCard) {
        this.score += obtainedScore;
        eventEndGame(obtainedScore, opponentCard);
    }

    public abstract void eventNewGame(Card card);
    public abstract void eventAction(PokerAction opponentAction);
    public abstract void eventEndGame(int score, Card opponentCard);
    public abstract PokerAction getAction();

    @Override
    public void printStats() {
        System.out.println(String.format("Player %s | Total profit: %d€ | Profit per game: %f€", this.getName(), this.getScore(), this.getExpectedValue()));
    }*/
}
