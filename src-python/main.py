from games.connect4.players.greedy import GreedyConnect4Player
from games.connect4.players.minimax import MinimaxConnect4Player
from games.connect4.players.random import RandomConnect4Player
from games.connect4.simulator import Connect4Simulator
from games.game_simulator import GameSimulator


def run_simulation(desc: str, simulator: GameSimulator, iterations: int):
    print(f"----- {desc} -----")

    for i in range(0, iterations):
        simulator.change_player_positions()
        simulator.run_simulation()

    print("Results for the game:")
    simulator.print_stats()


def main():
    print("ESTG IA Games Simulator")

    num_iterations = 1000

    run_simulation("Connect4 - Random VS Random",
                   Connect4Simulator(RandomConnect4Player("Random 1"), RandomConnect4Player("Random 2")),
                   num_iterations)

    run_simulation("Connect4 - Greedy VS Random",
                   Connect4Simulator(GreedyConnect4Player("Greedy"), RandomConnect4Player("Random")),
                   num_iterations)

    run_simulation("Connect4 - Minimax VS Random",
                   Connect4Simulator(MinimaxConnect4Player("Minimax"), RandomConnect4Player("Random")),
                   num_iterations)

    run_simulation("Connect4 - Minimax VS Greedy",
                   Connect4Simulator(MinimaxConnect4Player("Minimax"), GreedyConnect4Player("Greedy")),
                   num_iterations)


if __name__ == "__main__":
    main()
