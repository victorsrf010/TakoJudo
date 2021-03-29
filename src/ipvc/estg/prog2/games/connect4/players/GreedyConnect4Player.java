package ipvc.estg.prog2.games.connect4.players;

import ipvc.estg.prog2.games.connect4.Connect4Action;
import ipvc.estg.prog2.games.connect4.Connect4Player;
import ipvc.estg.prog2.games.connect4.Connect4State;
import ipvc.estg.prog2.misc.RandomSingleton;

public class GreedyConnect4Player extends Connect4Player {

    public GreedyConnect4Player(String name)
    {
        super(name);
    }

    @Override
    public Connect4Action getAction(Connect4State state) {
        int[][] grid = state.getGrid();

        int selectedCol = -1;
        int maxCount = 0;

        for (int col = 0; col < state.getnCols(); col++){
            int count = 0;
            for (int row = 0; row < state.getnRows(); row++){
                if(grid[row][col] == this.getCurrentPos()) {
                    count++;
                }
            }

            if(count > maxCount) {
                selectedCol = col;
                maxCount = count;
            }
        }

        Connect4Action action = selectedCol >= 0 ? new Connect4Action(selectedCol) : null;

        return action != null && state.validateAction(action) ?
                action:
                new Connect4Action(RandomSingleton.nextInt(state.getnCols()));
    }

    @Override
    public void eventAction(int playerPos, Connect4Action action, Connect4State newState) {
    }

    @Override
    public void eventEndGame(Connect4State finalState) {
    }

}