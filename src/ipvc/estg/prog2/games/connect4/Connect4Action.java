package ipvc.estg.prog2.games.connect4;

public class Connect4Action {

    private int col;

    public Connect4Action(int col) {
        assert(col >= 0 && col <= 6);
        this.col = col;
    }

    public int getCol() {
        return this.col;
    }

    public static Connect4Action Col0 = new Connect4Action(0);
    public static Connect4Action Col1 = new Connect4Action(0);
    public static Connect4Action Col2 = new Connect4Action(0);
    public static Connect4Action Col3 = new Connect4Action(0);
    public static Connect4Action Col4 = new Connect4Action(0);
    public static Connect4Action Col5 = new Connect4Action(0);
    public static Connect4Action Col6 = new Connect4Action(0);

    public static Connect4Action[] PossibleValues = new Connect4Action[] {
            Col0,
            Col1,
            Col2,
            Col3,
            Col4,
            Col5,
            Col6
    };

}
