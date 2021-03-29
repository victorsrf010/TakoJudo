package ipvc.estg.prog2.games.connect4;

public abstract class Connect4Player extends ipvc.estg.prog2.games.Player<Connect4Action, Connect4State, Connect4Result> {

    private int[] stats;
    private int games;
    private int currentPos;

    public Connect4Player(String name) {
        super(name);
        this.stats = new int[Connect4Result.values().length];
        this.games = 0;
    }

    @Override
    public void eventNewGame(int position) {
        this.games++;
        this.currentPos = position;
    }

    @Override
    public void eventResult(int playerPos, Connect4Result result)
    {
        if(playerPos == this.currentPos) {
            this.stats[result.ordinal()]++;
        }
    }

    /**
     * Obtem a posição atual do jogador na mesa
     * @return
     */
    public int getCurrentPos() {
        return this.currentPos;
    }

    @Override
    public void printStats() {
        System.out.println(String.format("Player %s: %d/%d wins (%f win rate)",
                this.getName(),
                this.stats[Connect4Result.WIN.ordinal()],
                this.games,
                this.stats[Connect4Result.WIN.ordinal()]*100.0 / this.games));
    }

}
