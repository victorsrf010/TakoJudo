package ipvc.estg.prog2.games.connect4.players;

import ipvc.estg.prog2.games.connect4.Connect4Action;
import ipvc.estg.prog2.games.connect4.Connect4Player;
import ipvc.estg.prog2.games.connect4.Connect4State;
import ipvc.estg.prog2.misc.RandomSingleton;

public class RandomConnect4Player extends Connect4Player {

    public RandomConnect4Player(String name)
    {
        super(name);
    }

    @Override
    public Connect4Action getAction(Connect4State state) {
        return new Connect4Action(RandomSingleton.nextInt(state.getnCols()));
    }

    @Override
    public void eventAction(int playerPos, Connect4Action action, Connect4State newState) {
    }

    @Override
    public void eventEndGame(Connect4State finalState) {
    }

}
