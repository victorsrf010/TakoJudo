package ipvc.estg.prog2.games.connect4;

public abstract class Connect4Player extends ipvc.estg.prog2.games.Player<Connect4Action, Connect4State, Connect4Result> {

    private int[] stats;
    private int games;

    public Connect4Player(String name) {
        super(name);
        this.stats = new int[Connect4Result.values().length];
        this.games = 0;
    }



    /*

    public void startGame(char color) {
        this.games++;
        eventNewGame(color);
    }

    public void endGame(Connect4State state, Result result) {
        this.stats[result.ordinal()]++;
        eventEndGame(state, result);
    }

    public abstract void eventNewGame(char color);
    public abstract void eventEndGame(Connect4State state, Result result);
    public abstract void eventAction(char playingColor, int play);*/

    @Override
    public void printStats() {
        System.out.println(String.format("Player %s: %d/%d wins (%f win rate)",
                this.getName(),
                this.stats[Connect4Result.WIN.ordinal()],
                this.games,
                this.stats[Connect4Result.WIN.ordinal()]*100.0 / this.games));
    }

}
