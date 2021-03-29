package ipvc.estg.prog2.games.connect4.players;

import ipvc.estg.prog2.games.connect4.Connect4Action;
import ipvc.estg.prog2.games.connect4.Connect4Player;
import ipvc.estg.prog2.games.connect4.Connect4Result;
import ipvc.estg.prog2.games.connect4.Connect4State;
import ipvc.estg.prog2.misc.RandomSingleton;

import javax.swing.*;

public class MinimaxConnect4Player extends Connect4Player {

    public MinimaxConnect4Player(String name)
    {
        super(name);
    }

    private int heuristic(Connect4State state) {
        int[][] grid = state.getGrid();

        // verificar todas as linhas
        int longest = 0;

        // por cada linha
        for(int row = 0; row < grid.length; ++row) {

            int seq = 0;

            // por cada coluna dessa linha
            for(int col = 0; col < grid[row].length; ++col) {
                if(grid[row][col] == this.getCurrentPos()) {
                    seq++;
                } else {
                    if(seq > longest) {
                        longest = seq;
                    }
                    seq = 0;
                }
            }

            if(seq > longest) {
                longest = seq;
            }
        }

        // por cada coluna
        for(int col = 0; col < grid[0].length; ++col) {

            int seq = 0;

            // por cada linha dessa coluna
            for(int row = 0; row < grid.length; ++row) {
                if(grid[row][col] == this.getCurrentPos()) {
                    seq++;
                } else {
                    if(seq > longest) {
                        longest = seq;
                    }
                    seq = 0;
                }
            }

            if(seq > longest) {
                longest = seq;
            }
        }

        //check upward diagonal
        for(int row = 3; row < state.getnRows(); row++){
            for(int col = 0; col < state.getnCols() - 3; col++){

                int seq1 = (grid[row][col] == this.getCurrentPos()?1:0) +
                        (grid[row-1][col+1] == this.getCurrentPos()?1:0) +
                        (grid[row-2][col+2] == this.getCurrentPos()?1:0);

                int seq2 = (grid[row-1][col+1] == this.getCurrentPos()?1:0) +
                        (grid[row-2][col+2] == this.getCurrentPos()?1:0) +
                        (grid[row-3][col+3] == this.getCurrentPos()?1:0);

                if(seq1 > longest) {
                    longest = seq1;
                }

                if(seq2 > longest) {
                    longest = seq2;
                }
            }
        }
        //check downward diagonal
        for(int row = 0; row < state.getnRows() - 3; row++){
            for(int col = 0; col < state.getnCols() - 3; col++){
                int seq1 = (grid[row][col] == this.getCurrentPos()?1:0)  +
                        (grid[row+1][col+1] == this.getCurrentPos()?1:0) +
                        (grid[row+2][col+2] == this.getCurrentPos()?1:0);

                int seq2 = (grid[row+1][col+1] == this.getCurrentPos() ? 1:0) +
                        (grid[row+2][col+2] == this.getCurrentPos() ? 1:0) +
                        (grid[row+3][col+3] == this.getCurrentPos() ? 1:0);

                if(seq1 > longest) {
                    longest = seq1;
                }

                if(seq2 > longest) {
                    longest = seq2;
                }
            }
        }

        return longest;
    }

    /**
     * Efetua uma pesquisa minimax (recursivamente)
     * @param state estado atual sobre o qual a pesquisa deve ser feita
     * @param depth profundidade máxima de pesquisa
     * @param alpha parametro alpha para pruning
     * @param beta parametro beta para pruning
     * @param isInitialNode se verdadeiro, a função retorna a posição com maior valor esperado, caso contrário retorna o valor esperado da melhor ação
     * @return
     */
    private int minimax(Connect4State state, int depth, int alpha, int beta, boolean isInitialNode) {
        // verificar se o jogo terminou (empate, vitória ou derrota)
        if(state.isFinished()) {
            Connect4Result result = state.getResult(this.getCurrentPos());
            switch (result) {
                case WIN:
                    return 4;
                case LOOSE:
                    return -4;
                case DRAW:
                    return 0;
            }
        }

        // profundidade máxima atingida
        if(depth == 0) {
            return heuristic(state);
        }

        // se for o minimax a jogar
        if(this.getCurrentPos() == state.getActingPlayer()) {
            int value = Integer.MIN_VALUE;
            int selectedPos = -1;

            for(int pos = 0; pos < state.getnCols(); ++pos) {
                Connect4Action action = new Connect4Action(pos);
                if(state.validateAction(action)) {
                    int previousA = value;
                    Connect4State nextState = state.getClone();
                    nextState.play(action);
                    value = Math.max(value, minimax(nextState, depth - 1, alpha, beta,false));
                    alpha = Math.max(alpha, value);

                    if(value >= previousA) {
                        selectedPos = pos;
                    }

                    if(alpha >= beta) {
                        break;
                    }
                }
            }

            if(isInitialNode) {
                return selectedPos;
            }

            return value;
        } else { // adversário a jogar
            int value = Integer.MAX_VALUE;
            for(int pos = 0; pos < state.getnCols(); ++pos) {
                Connect4Action action = new Connect4Action(pos);
                if(state.validateAction(action)) {
                    Connect4State nextState = state.getClone();
                    nextState.play(action);
                    value = Math.min(value, minimax(nextState, depth - 1, alpha, beta, false));
                    beta = Math.min(beta, value);
                    if(beta <= alpha) {
                        break;
                    }
                }
            }

            return value;
        }
    }

    @Override
    public Connect4Action getAction(Connect4State state) {
        return new Connect4Action(minimax(state, 5, Integer.MIN_VALUE, Integer.MAX_VALUE, true));
    }

    @Override
    public void eventAction(int playerPos, Connect4Action action, Connect4State newState) {
    }

    @Override
    public void eventEndGame(Connect4State finalState) {
    }

}