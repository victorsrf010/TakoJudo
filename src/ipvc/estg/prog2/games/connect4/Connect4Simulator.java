package ipvc.estg.prog2.games.connect4;

import ipvc.estg.prog2.games.GameSimulator;

public class Connect4Simulator extends GameSimulator<
        Connect4Action,
        Connect4Result,
        Connect4State,
        Connect4Player> {

    /**
     * Simulador do 4 em linha. Apenas suporta 2 jogadores
     * @param player1 jogador 1
     * @param player2 jogador 2
     */
    public Connect4Simulator(Connect4Player player1, Connect4Player player2) {
        super(player1, player2);
    }

    @Override
    protected Connect4State initGame() {
        return new Connect4State();
    }

}
