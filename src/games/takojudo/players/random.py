from random import randint

from games.takojudo.action import TakoJudoAction
from games.takojudo.player import TakoJudoPlayer
from games.takojudo.state import TakoJudoState
from games.state import State


class RandomTakoJudoPlayer(TakoJudoPlayer):

    def __init__(self, name):
        super().__init__(name)

    def get_action(self, state: TakoJudoState):
        return TakoJudoAction(randint(0, state.get_num_cols() - 1), randint(0, state.get_num_rows() - 1),
                              randint(0, state.get_num_cols() - 1), randint(0, state.get_num_cols() - 1))

    def get_action(self, state: TakoJudoState):
        # Find all coordinates with value 2 or 3 in the grid
        pieces = []
        for row in range(state.get_num_rows()):
            for col in range(state.get_num_cols()):
                if state.get_grid()[row][col] in [2, 3]:
                    pieces.append((row, col))

        # Choose a random piece
        piece_row, piece_col = pieces[randint(0, len(pieces) - 1)]

        # Find all coordinates with value-1 in the grid
        empty_cells = []
        for row in range(state.get_num_rows()):
            for col in range(state.get_num_cols()):
                if state.get_grid()[row][col] in [-1]:
                    empty_cells.append((row, col))

        # Choose a random piece
        dest_row, dest_col = empty_cells[randint(0, len(empty_cells) - 1)]

        # Return the action
        return TakoJudoAction(piece_col, piece_row, dest_col, dest_row)

    def event_action(self, pos: int, action, new_state: State):
        # ignore
        pass

    def event_end_game(self, final_state: State):
        # ignore
        pass
