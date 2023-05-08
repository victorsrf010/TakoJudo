from random import choice
from games.takojudo.action import TakoJudoAction
from games.takojudo.player import TakoJudoPlayer
from games.takojudo.state import TakoJudoState
from games.state import State


class GreedyTakoJudoPlayer(TakoJudoPlayer):

    def __init__(self, name):
        super().__init__(name)

    def get_action(self, state: TakoJudoState):
        grid = state.get_grid()

        row = None
        col = None
        dest_row = None
        dest_col = None
        max_count = 0

        #for col in range(0, state.get_num_cols()):



    def event_action(self, pos: int, action, new_state: State):
        # ignore
        pass

    def event_end_game(self, final_state: State):
        # ignore
        pass
