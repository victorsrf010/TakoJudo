from games.game_simulator import GameSimulator
from games.takojudo.player import TakoJudoPlayer
from games.takojudo.state import TakoJudoState


class TakoJudoSimulator(GameSimulator):

    def __init__(self, player1: TakoJudoPlayer, player2: TakoJudoPlayer, num_rows: int = 8, num_cols: int = 8):
        super(TakoJudoSimulator, self).__init__([player1, player2])
        """
        the number of rows and cols from the Tako Judo grid
        """
        self.__num_rows = num_rows
        self.__num_cols = num_cols

    def init_game(self):
        return TakoJudoState(self.__num_rows, self.__num_cols)

    def before_end_game(self, state: TakoJudoState):
        # ignored for this simulator
        pass

    def end_game(self, state: TakoJudoState):
        # ignored for this simulator
        pass

