from games.takojudo.player import TakoJudoPlayer
from games.takojudo.state import TakoJudoState
from games.game_simulator import GameSimulator

from src.games.takojudo.board import Board


class TakoJudoSimulator(GameSimulator):

    def __init__(self, player1: TakoJudoPlayer, player2: TakoJudoPlayer):
        super(TakoJudoSimulator, self).__init__([player1, player2])

    def init_game(self):
        return Board.__init__(Board.board)

    def before_end_game(self, state: TakoJudoState):
        # ignored for this simulator
        pass

    def end_game(self, state: TakoJudoState):
        # ignored for this simulator
        pass
