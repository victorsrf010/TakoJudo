from random import randint

from games.takojudo.action import TakoJudoAction
from games.takojudo.player import TakoJudoPlayer
from games.takojudo.state import TakoJudoState
from games.state import State


class RandomTakoJudoPlayer(TakoJudoPlayer):

    def __init__(self, name):
        super().__init__(name)

    def get_action(self, state: TakoJudoState):

        # Find all coordinates with value 0, 1, or 4 in the grid for player 0
        pieces_player0 = []
        for row in range(state.get_num_rows()):
            for col in range(state.get_num_cols()):
                if state.get_grid()[row][col] in [0, 1, 4]:
                    pieces_player0.append((row, col))

        # Find all coordinates with value 2, 3, or 5 in the grid for player 1
        pieces_player1 = []
        for row in range(state.get_num_rows()):
            for col in range(state.get_num_cols()):
                if state.get_grid()[row][col] in [2, 3, 5]:
                    pieces_player1.append((row, col))

        # Choose a random piece from the corresponding player's pieces
        if state.get_acting_player() == 0:
            piece_row, piece_col = pieces_player0[randint(0, len(pieces_player0) - 1)]
        else:
            piece_row, piece_col = pieces_player1[randint(0, len(pieces_player1) - 1)]

        # Find all coordinates with value -1 (empty cells) in the grid
        empty_cells = []
        for row in range(state.get_num_rows()):
            for col in range(state.get_num_cols()):
                if state.get_grid()[row][col] == -1:
                    empty_cells.append((row, col))

        # Choose a random empty cell as the destination
        dest_row, dest_col = empty_cells[randint(0, len(empty_cells) - 1)]

        # Return the action
        return TakoJudoAction(piece_col, piece_row, dest_col, dest_row)

    def event_action(self, pos: int, action, new_state: State):
        # ignore
        pass

    def event_end_game(self, final_state: State):
        # ignore
        pass
