package ipvc.estg.prog2.games.poker.players;

import ipvc.estg.prog2.games.connect4.Connect4Action;
import ipvc.estg.prog2.games.connect4.Connect4Player;
import ipvc.estg.prog2.games.connect4.Connect4State;
import ipvc.estg.prog2.games.poker.KuhnPokerAction;
import ipvc.estg.prog2.games.poker.KuhnPokerCard;
import ipvc.estg.prog2.games.poker.KuhnPokerPlayer;
import ipvc.estg.prog2.games.poker.KuhnPokerState;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScannerKuhnPokerPlayer extends KuhnPokerPlayer {

    private static Map<String, KuhnPokerAction> ActionsMap = Stream.of( new Object[][] {
        {"p", KuhnPokerAction.PASS },
        {"pass", KuhnPokerAction.PASS },
        {"b", KuhnPokerAction.BET },
        {"bet", KuhnPokerAction.BET }
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (KuhnPokerAction) data[1]));

    public ScannerKuhnPokerPlayer(String name) {
        super(name);
    }

    @Override
    public KuhnPokerAction getAction(KuhnPokerState state) {
        state.display();
        System.out.print("Choose an action (pass/p or bet/b): ");
        Scanner in = new Scanner(System.in);
        return ActionsMap.getOrDefault(in.next(), null);
    }

    @Override
    public void eventAction(int playerPos, KuhnPokerAction action, KuhnPokerState newState) {
        System.out.println("> player " + playerPos + " " + action);
    }

    @Override
    public void eventEndGame(KuhnPokerState finalState) {
    }

    @Override
    public void eventResult(int playerPos, Integer result) {
        super.eventResult(playerPos, result);
        System.out.println("> player " + playerPos + " got " + result);
    }

    @Override
    public void eventNewGame() {
        super.eventNewGame();
        System.out.println("--- New game ---");
        System.out.println("> You are player " + this.getCurrentPos() + " with card " + this.getCurrentCard());
    }
}
