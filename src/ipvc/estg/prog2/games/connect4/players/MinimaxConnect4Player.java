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

        return longest;
    }

    private int minimax(Connect4State state, int depth, boolean initial) {
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
            int a = Integer.MIN_VALUE;
            int selectedPos = -1;

            for(int pos = 0; pos < state.getnCols(); ++pos) {
                Connect4Action action = new Connect4Action(pos);
                if(state.validateAction(action)) {
                    int previousA = a;
                    Connect4State nextState = state.getClone();
                    nextState.play(action);
                    a = Math.max(a, minimax(nextState, depth - 1, false));
                    if(a >= previousA) {
                        selectedPos = pos;
                    }
                }
            }

            if(initial) {
                return selectedPos;
            }

            return a;
        } else { // adversário a jogar
            int a = Integer.MAX_VALUE;
            for(int pos = 0; pos < state.getnCols(); ++pos) {
                Connect4Action action = new Connect4Action(pos);
                if(state.validateAction(action)) {
                    Connect4State nextState = state.getClone();
                    nextState.play(action);
                    a = Math.min(a, minimax(nextState, depth - 1, false));
                }
            }

            return a;
        }
    }

    @Override
    public Connect4Action getAction(Connect4State state) {
        return new Connect4Action(minimax(state, 5, true));
    }

    @Override
    public void eventAction(int playerPos, Connect4Action action, Connect4State newState) {
    }

    @Override
    public void eventEndGame(Connect4State finalState) {
    }

}