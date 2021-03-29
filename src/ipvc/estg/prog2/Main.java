package ipvc.estg.prog2;

import ipvc.estg.prog2.games.GameSimulator;
import ipvc.estg.prog2.games.connect4.Connect4Simulator;
import ipvc.estg.prog2.games.connect4.players.GreedyConnect4Player;
import ipvc.estg.prog2.games.connect4.players.MinimaxConnect4Player;
import ipvc.estg.prog2.games.connect4.players.RandomConnect4Player;
import ipvc.estg.prog2.games.poker.KuhnPokerSimulator;
import ipvc.estg.prog2.games.poker.players.*;
import me.tongfei.progressbar.ProgressBar;

public class Main {

    public static void runSimulation(String desc, GameSimulator simulator, long iterations) {
        ProgressBar pb = new ProgressBar(desc, iterations);
        for(long i = 0; i < iterations; ++i, pb.step()) {
            simulator.changePlayerPositions();
            simulator.runSimulation();
        }
        pb.stepTo(iterations);
        pb.close();

        System.out.println("Results for the game:");
        simulator.printStats();
    }

    public static void main(String[] args) {
        runSimulation("Connect4 - Random VS Random",
                new Connect4Simulator(
                    new RandomConnect4Player("Random 1"),
                    new RandomConnect4Player("Random 2")),
                10000);

        runSimulation("Connect4 - Greedy VS Random",
                new Connect4Simulator(
                        new GreedyConnect4Player("Greedy"),
                        new RandomConnect4Player("Random")),
                10000);

        runSimulation("Connect4 - Minimax VS Random",
                new Connect4Simulator(
                        new MinimaxConnect4Player("Minimax"),
                        new RandomConnect4Player("Random")),
                10000);

        runSimulation("Connect4 - Minimax VS Greedy",
                new Connect4Simulator(
                        new MinimaxConnect4Player("Minimax"),
                        new GreedyConnect4Player("Greedy")),
                10000);

        runSimulation("Kuhn Poker - Random VS Random",
                new KuhnPokerSimulator(
                        new RandomKuhnPokerPlayer("Random 1"),
                        new RandomKuhnPokerPlayer("Random 2")),
                10000);

        runSimulation("Kuhn Poker - AlwaysBet VS Random",
                new KuhnPokerSimulator(
                        new AlwaysBetKuhnPokerPlayer("AlwaysBet"),
                        new RandomKuhnPokerPlayer("Random")),
                10000);

        runSimulation("Kuhn Poker - AlwaysPass VS Random",
                new KuhnPokerSimulator(
                        new AlwaysPassKuhnPokerPlayer("AlwaysPass"),
                        new RandomKuhnPokerPlayer("Random")),
                10000);

        runSimulation("Kuhn Poker - AlwaysBet VS AlwaysPass",
                new KuhnPokerSimulator(
                        new AlwaysBetKuhnPokerPlayer("AlwaysBet"),
                        new AlwaysPassKuhnPokerPlayer("AlwaysPass")),
                10000);

        runSimulation("Kuhn Poker - AlwaysBet VS AlwaysBetKing",
                new KuhnPokerSimulator(
                        new AlwaysBetKuhnPokerPlayer("AlwaysBet"),
                        new AlwaysBetKingKuhnPokerPlayer("AlwaysBetKing")),
                10000);

        runSimulation("Kuhn Poker - CFR VS Random",
                new KuhnPokerSimulator(
                        new CFRKuhnPokerPlayer("CFR"),
                        new RandomKuhnPokerPlayer("Random")),
                10000);

        runSimulation("Kuhn Poker - CFR VS AlwaysPass",
                new KuhnPokerSimulator(
                        new CFRKuhnPokerPlayer("CFR"),
                        new AlwaysPassKuhnPokerPlayer("AlwaysPass")),
                10000);

        runSimulation("Kuhn Poker - CFR VS AlwaysBet",
                new KuhnPokerSimulator(
                        new CFRKuhnPokerPlayer("CFR"),
                        new AlwaysBetKuhnPokerPlayer("AlwaysBet")),
                10000);

        runSimulation("Kuhn Poker - CFR VS AlwaysBetKing",
                new KuhnPokerSimulator(
                        new CFRKuhnPokerPlayer("CFR"),
                        new AlwaysBetKingKuhnPokerPlayer("AlwaysBetKing")),
                10000);

    }

}
