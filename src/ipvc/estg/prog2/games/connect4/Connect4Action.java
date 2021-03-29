package ipvc.estg.prog2.games.connect4;

public class Connect4Action {

    private int col;

    /**
     * @param col coluna na qual pretendemos jogar
     */
    public Connect4Action(int col) {
        this.col = col;
    }

    /**
     * @return obtem o valor da coluna jogada
     */
    public int getCol() {
        return this.col;
    }

}
