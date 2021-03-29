package ipvc.estg.prog2;

import ipvc.estg.prog2.games.connect4.Connect4Simulator;
import ipvc.estg.prog2.games.connect4.players.RandomConnect4Player;
import ipvc.estg.prog2.games.poker.PokerSimulator;
import ipvc.estg.prog2.games.poker.players.RandomPokerPlayer;

public class Main {

    /*public static void testPoker() {
        PokerSimulator simulator = new PokerSimulator(new RandomPokerPlayer("Luís"), new RandomPokerPlayer("Jorge"));

        for(int i = 0; i < 1000000; ++i) {
            simulator.changePlayerPositions();
            simulator.runSimulation();
        }

        simulator.printStats();
    }*/

    public static void testConnect4() {
        Connect4Simulator simulator = new Connect4Simulator(new RandomConnect4Player("Luís"), new RandomConnect4Player("Jorge"));

        for(int i = 0; i < 1000000; ++i) {
            simulator.changePlayerPositions();
            simulator.runSimulation();
        }

        simulator.printStats();
    }

    public static void main(String[] args) {
        //testPoker();
        testConnect4();
    }

}
