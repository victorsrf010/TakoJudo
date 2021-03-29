package ipvc.estg.prog2.games.connect4;

import ipvc.estg.prog2.games.State;

public class Connect4State extends State<Connect4Action, Connect4Result, Connect4State> {

    // conta o número de turnos decorridos no jogo atual
    private int turnsCount = 1;

    // determina quem é o jogador a jogar
    private int actingPlayer = 0;

    // determina se já foi encontrado um vencedor
    private boolean winner = false;

    // representação da grelha do jogo
    private int[][] grid;

    // numero de linhas (altura)
    private int nRows = 6;

    // numero de colunas (largura)
    private int nCols = 7;

    public Connect4State() {
        init(6, 7);
    }

    public Connect4State(int nRows, int nCols) {
        init(nRows, nCols);
    }

    private void init(int nRows, int nCols) {
        if(nRows < 4) {
            throw new IllegalArgumentException("the number of rows must be 4 or over");
        }

        if(nCols < 4) {
            throw new IllegalArgumentException("the number of cols must be 4 or over");
        }

        this.nRows = nRows;
        this.nCols = nCols;
        this.grid = new int[nRows][nCols];

        for(int row = 0; row < nRows; ++row) {
            //this.grid[row] = new Integer[nCols];
            for (int col = 0; col < nCols; col++){
                grid[row][col] = -1;
            }
        }
    }

    private boolean hasWinner(int player){
        //check for 4 across
        for(int row = 0; row < nRows; row++){
            for (int col = 0;col < nCols - 3;col++){
                if (grid[row][col] == player   &&
                        grid[row][col+1] == player &&
                        grid[row][col+2] == player &&
                        grid[row][col+3] == player){
                    return true;
                }
            }
        }
        //check for 4 up and down
        for(int row = 0; row < nRows - 3; row++){
            for(int col = 0; col < nCols; col++){
                if (grid[row][col] == player   &&
                        grid[row+1][col] == player &&
                        grid[row+2][col] == player &&
                        grid[row+3][col] == player){
                    return true;
                }
            }
        }
        //check upward diagonal
        for(int row = 3; row < nRows; row++){
            for(int col = 0; col < nCols - 3; col++){
                if (grid[row][col] == player   &&
                        grid[row-1][col+1] == player &&
                        grid[row-2][col+2] == player &&
                        grid[row-3][col+3] == player){
                    return true;
                }
            }
        }
        //check downward diagonal
        for(int row = 0; row < nRows - 3; row++){
            for(int col = 0; col < nCols - 3; col++){
                if (grid[row][col] == player   &&
                        grid[row+1][col+1] == player &&
                        grid[row+2][col+2] == player &&
                        grid[row+3][col+3] == player){
                    return true;
                }
            }
        }
        return false;
    }

    public int[][] getGrid() {
        return grid;
    }

    @Override
    public int getNumPlayers() {
        return 2;
    }

    @Override
    public boolean validateAction(Connect4Action action) {
        int column = action.getCol();

        //valid column?
        if (column < 0 || column > this.nCols){
            return false;
        }

        //full column?
        if (this.grid[0][column] >= 0){
            return false;
        }

        return true;
    }

    @Override
    protected void update(Connect4Action action) {
        int col = action.getCol();

        //drop the checker
        for (int row = grid.length-1; row >= 0; row--){
            if(grid[row][col] < 0){
                grid[row][col] = this.actingPlayer;
                break;
            }
        }

        //determine if there is a winner
        this.winner = hasWinner(this.actingPlayer);

        // switch playing color
        this.actingPlayer = this.actingPlayer == 0 ? 1 : 0;

        turnsCount++;
    }

    private void displayCell(int row, int col) {
        switch (this.grid[row][col]) {
            case 0:
                System.out.print('R');
                break;
            case 1:
                System.out.print('B');
                break;
            default:
                System.out.print(' ');
                break;
        }
    }

    private void displayNumbers() {
        for(int col = 0; col < this.nCols; ++col) {
            if(col < 10) {
                System.out.print(" ");
            }
            System.out.print(col);
        }
        System.out.println();
    }

    private void displaySeparator() {
        for(int col = 0; col < this.nCols; ++col) {
            System.out.print("--");
        }
        System.out.println("-");
    }

    @Override
    public void display() {
        this.displayNumbers();
        this.displaySeparator();

        for (int row = 0; row < grid.length; row++){
            System.out.print("|");
            for (int col = 0; col < grid[0].length; col++){
                displayCell(row, col);
                System.out.print("|");
            }
            System.out.println();
            this.displaySeparator();
        }

        this.displayNumbers();
        System.out.println();
    }

    private boolean isFull() {
        return this.turnsCount > (this.nCols * this.nRows);
    }

    @Override
    public boolean isFinished() {
        return this.winner || this.isFull();
    }

    @Override
    public int getActingPlayer() {
        return this.actingPlayer;
    }

    @Override
    public Connect4State getClone() {
        Connect4State clonedState = new Connect4State(this.nRows, this.nCols);
        clonedState.turnsCount = this.turnsCount;
        clonedState.actingPlayer = this.actingPlayer;
        clonedState.winner = this.winner;
        for (int row = 0; row < this.nRows; row++){
            for (int col = 0; col < this.nCols; col++){
                clonedState.grid[row][col] = this.grid[row][col];
            }
        }
        return clonedState;
    }

    @Override
    public Connect4Result getResult(int pos) {
        if(this.winner) {
            return pos == this.actingPlayer ? Connect4Result.LOOSE : Connect4Result.WIN;
        }
        if(this.isFull()) {
            return Connect4Result.DRAW;
        }
        return null;
    }

    public int getnRows() {
        return nRows;
    }

    public int getnCols() {
        return nCols;
    }
}
