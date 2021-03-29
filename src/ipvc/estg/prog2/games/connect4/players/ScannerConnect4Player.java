package ipvc.estg.prog2.games.connect4.players;

import ipvc.estg.prog2.games.connect4.Connect4Action;
import ipvc.estg.prog2.games.connect4.Connect4Player;
import ipvc.estg.prog2.games.connect4.Connect4Result;
import ipvc.estg.prog2.games.connect4.Connect4State;

import java.util.Scanner;

public class ScannerConnect4Player extends Connect4Player {

    public ScannerConnect4Player(String name) {
        super(name);
    }

    @Override
    public Connect4Action getAction(Connect4State state) {
        state.display();
        System.out.print("Player " + state.getActingPlayer() + ", choose a column: ");
        Scanner in = new Scanner(System.in);
        return new Connect4Action(in.nextInt());
    }

    @Override
    public void eventNewGame(int position) {

    }

    @Override
    public void eventAction(int playerPos, Connect4Action action, Connect4State newState) {

    }

    @Override
    public void eventResult(int playerPos, Connect4Result result) {

    }

    @Override
    public void eventEndGame(Connect4State finalState) {

    }


}
