from typing import Optional

from games.takojudo.action import TakoJudoAction
from games.takojudo.result import TakoJudoResult
from games.state import State


class TakoJudoState(State):
    EMPTY_CELL = -1

    def __init__(self, num_rows: int = 8, num_cols: int = 8):
        super().__init__()

        """
        the dimensions of the board
        """
        self.__num_rows = num_rows
        self.__num_cols = num_cols

        """
        the grid
        """
        self.__grid = [[TakoJudoState.EMPTY_CELL for _i in range(self.__num_cols)] for _j in range(self.__num_rows)]
        self.__grid[0][2] = 0
        self.__grid[1][2] = 0
        self.__grid[2][2] = 0
        self.__grid[2][3] = 0
        self.__grid[2][4] = 0
        self.__grid[2][5] = 0
        self.__grid[1][5] = 0
        self.__grid[0][5] = 0
        self.__grid[0][3] = 1
        self.__grid[1][3] = 4
        self.__grid[0][4] = 4
        self.__grid[1][4] = 4

        self.__grid[7][2] = 2
        self.__grid[6][2] = 2
        self.__grid[5][2] = 2
        self.__grid[5][3] = 2
        self.__grid[5][4] = 2
        self.__grid[5][5] = 2
        self.__grid[6][5] = 2
        self.__grid[7][5] = 2
        self.__grid[7][3] = 5
        self.__grid[6][3] = 3
        self.__grid[7][4] = 5
        self.__grid[6][4] = 5

        self.__head1 = {
            self.__grid[0][3],
            self.__grid[1][3],
            self.__grid[0][4],
            self.__grid[1][4]
        }

        self.__head2 = {
            self.__grid[7][3],
            self.__grid[6][3],
            self.__grid[7][4],
            self.__grid[6][4]
        }

        """
        counts the number of turns in the current game
        """
        self.__turns_count = 1

        """
        the index of the current acting player
        """
        self.__acting_player = 0

        """
        determine if a winner was found already 
        """
        self.__has_winner = False

    def __check_winner(self, player):
        # check if the player's octopus head is pinned
        head1_pinned = False
        head2_pinned = False

        head1_row, head1_col = None, None
        head2_row, head2_col = None, None

        for row1 in range(self.__num_rows):
            for col1 in range(self.__num_cols):
                if self.__grid[row1][col1] == 1:
                    head1_row = row1
                    head1_col = col1

        for row2 in range(self.__num_rows):
            for col2 in range(self.__num_cols):
                if self.__grid[row2][col2] == 3:
                    head2_row = row2
                    head2_col = col2

        #check if the octopus is in a corner
        if head1_row == 0 and head1_col == 0:
            if ((self.__grid[head1_row + 2][head1_col] == 2 or self.__grid[head1_row + 2][head1_col + 1] == 2)
                    and (self.__grid[head1_row][head1_col + 2] == 2 or self.__grid[head1_row + 1][head1_col + 2] == 2)):
                head1_pinned = True
                self.__acting_player = player

        elif head1_row == 6 and head1_col == 0:
            if ((self.__grid[head1_row - 1][head1_col] == 2 or self.__grid[head1_row - 1][head1_col + 1] == 2)
                    and (self.__grid[head1_row][head1_col + 2] == 2 or self.__grid[head1_row + 1][head1_col + 2] == 2)):
                head1_pinned = True
                self.__acting_player = player

        elif head1_row == 6 and head1_col == 6:
            if ((self.__grid[head1_row - 1][head1_col] == 2 or self.__grid[head1_row - 1][head1_col + 1] == 2)
                    and (self.__grid[head1_row][head1_col - 1] == 2 or self.__grid[head1_row + 1][head1_col - 1] == 2)):
                head1_pinned = True
                self.__acting_player = player

        elif head1_row == 0 and head1_col == 6:
            if ((self.__grid[head1_row + 2][head1_col] == 2 or self.__grid[head1_row + 2][head1_col + 1] == 2)
                    and (self.__grid[head1_row][head1_col - 1] == 2 or self.__grid[head1_row + 1][head1_col - 1] == 2)):
                head1_pinned = True
                self.__acting_player = player

        #check if the octopus is against a border of the board
        elif head1_row == 0:
            if ((self.__grid[head1_row][head1_col - 1] == 2 or self.__grid[head1_row + 1][head1_col - 1] == 2)
                    and (self.__grid[head1_row + 2][head1_col] == 2 or self.__grid[head1_row + 2][head1_col + 1] == 2)
                    and (self.__grid[head1_row][head1_col + 2] == 2 or self.__grid[head1_row + 1][head1_col + 2] == 2)):
                head1_pinned = True
                self.__acting_player = player

        elif head1_row == 6:
            if ((self.__grid[head1_row][head1_col - 1] == 2 or self.__grid[head1_row + 1][head1_col - 1] == 2)
                    and (self.__grid[head1_row - 1][head1_col] == 2 or self.__grid[head1_row - 1][head1_col + 1] == 2)
                    and (self.__grid[head1_row][head1_col + 2] == 2 or self.__grid[head1_row + 1][head1_col + 2] == 2)):
                head1_pinned = True
                self.__acting_player = player

        elif head1_col == 0:
            if ((self.__grid[head1_row + 2][head1_col] == 2 or self.__grid[head1_row + 2][head1_col + 1] == 2)
                    and (self.__grid[head1_row][head1_col + 2] == 2 or self.__grid[head1_row + 1][head1_col + 2] == 2)
                    and (self.__grid[head1_row - 1][head1_col] == 2 or self.__grid[head1_row - 1][head1_col + 1] == 2)):
                head1_pinned = True
                self.__acting_player = player

        elif head1_col == 6:
            if ((self.__grid[head1_row + 2][head1_col] == 2 or self.__grid[head1_row + 2][head1_col + 1] == 2)
                    and (self.__grid[head1_row][head1_col - 1] == 2 or self.__grid[head1_row + 1][head1_col - 1] == 2)
                    and (self.__grid[head1_row - 1][head1_col] == 2 or self.__grid[head1_row - 1][head1_col + 1] == 2)):
                head1_pinned = True
                self.__acting_player = player

        elif head1_col in [1, 2, 3, 4, 5] and head1_row in [1, 2, 3, 4, 5]:
            if ((self.__grid[head1_row][head1_col - 1] == 2 or self.__grid[head1_row + 1][head1_col - 1] == 2)
                    and (self.__grid[head1_row + 2][head1_col] == 2 or self.__grid[head1_row + 2][head1_col + 1] == 2)
                    and (self.__grid[head1_row][head1_col + 2] == 2 or self.__grid[head1_row + 1][head1_col + 2] == 2)
                    and (self.__grid[head1_row - 1][head1_col] == 2 or self.__grid[head1_row - 1][head1_col + 1] == 2)):
                head1_pinned = True
                self.__acting_player = player

        # check if the octopus is in a corner
        if head2_row == 0 and head2_col == 0:
            if ((self.__grid[head2_row + 2][head2_col] == 0 or self.__grid[head2_row + 2][head2_col + 1] == 0)
                    and (self.__grid[head2_row][head2_col + 2] == 0 or self.__grid[head2_row + 1][head2_col + 2] == 0)):
                head2_pinned = True
                self.__acting_player = player

        elif head2_row == 6 and head2_col == 0:
            if ((self.__grid[head2_row - 1][head2_col] == 0 or self.__grid[head2_row - 1][head2_col + 1] == 0)
                    and (self.__grid[head2_row][head2_col + 2] == 0 or self.__grid[head2_row + 1][head2_col + 2] == 0)):
                head2_pinned = True
                self.__acting_player = player

        elif head2_row == 6 and head2_col == 6:
            if ((self.__grid[head2_row - 1][head2_col] == 0 or self.__grid[head2_row - 1][head2_col + 1] == 0)
                    and (self.__grid[head2_row][head2_col - 1] == 0 or self.__grid[head2_row + 1][head2_col - 1] == 0)):
                head2_pinned = True
                self.__acting_player = player

        elif head2_row == 0 and head2_col == 6:
            if ((self.__grid[head2_row + 2][head2_col] == 0 or self.__grid[head2_row + 2][head2_col + 1] == 0)
                    and (self.__grid[head2_row][head2_col - 1] == 0 or self.__grid[head2_row + 1][head2_col - 1] == 0)):
                head2_pinned = True
                self.__acting_player = player

        # check if the octopus is against a border of the board
        elif head2_row == 0:
            if ((self.__grid[head2_row][head2_col - 1] == 0 or self.__grid[head2_row + 1][head2_col - 1] == 0)
                    and (self.__grid[head2_row + 2][head2_col] == 0 or self.__grid[head2_row + 2][head2_col + 1] == 0)
                    and (self.__grid[head2_row][head2_col + 2] == 0 or self.__grid[head2_row + 1][head2_col + 2] == 0)):
                head2_pinned = True
                self.__acting_player = player

        elif head2_row == 6:
            if ((self.__grid[head2_row][head2_col - 1] == 0 or self.__grid[head2_row + 1][head2_col - 1] == 0)
                    and (self.__grid[head2_row - 1][head2_col] == 0 or self.__grid[head2_row - 1][head2_col + 1] == 0)
                    and (self.__grid[head2_row][head2_col + 2] == 0 or self.__grid[head2_row + 1][head2_col + 2] == 0)):
                head2_pinned = True
                self.__acting_player = player

        elif head2_col == 0:
            if ((self.__grid[head2_row + 2][head2_col] == 0 or self.__grid[head2_row + 2][head2_col + 1] == 0)
                    and (self.__grid[head2_row][head2_col + 2] == 0 or self.__grid[head2_row + 1][head2_col + 2] == 0)
                    and (self.__grid[head2_row - 1][head2_col] == 0 or self.__grid[head2_row - 1][head2_col + 1] == 0)):
                head2_pinned = True
                self.__acting_player = player

        elif head2_col == 6:
            if ((self.__grid[head2_row + 2][head2_col] == 0 or self.__grid[head2_row + 2][head2_col + 1] == 0)
                    and (self.__grid[head2_row][head2_col - 1] == 0 or self.__grid[head2_row + 1][head2_col - 1] == 0)
                    and (self.__grid[head2_row - 1][head2_col] == 0 or self.__grid[head2_row - 1][head2_col + 1] == 0)):
                head2_pinned = True
                self.__acting_player = player

        elif head2_col in [1, 2, 3, 4, 5] and head2_row in [1, 2, 3, 4, 5]:
            if ((self.__grid[head2_row][head2_col - 1] == 0 or self.__grid[head2_row + 1][head2_col - 1] == 0)
                    and (self.__grid[head2_row + 2][head2_col] == 0 or self.__grid[head2_row + 2][head2_col + 1] == 0)
                    and (self.__grid[head2_row][head2_col + 2] == 0 or self.__grid[head2_row + 1][head2_col + 2] == 0)
                    and (self.__grid[head2_row - 1][head2_col] == 0 or self.__grid[head2_row - 1][head2_col + 1] == 0)):
                head2_pinned = True
                self.__acting_player = player

        if head1_pinned or head2_pinned:
            return True
        else:
            return False


    def get_grid(self):
        return self.__grid

    def get_num_players(self):
        return 2

    def validate_action(self, action: TakoJudoAction) -> bool:
        # get the destination position of the piece being moved
        row = action.get_row()
        col = action.get_col()
        dest_row = action.get_dest_row()
        dest_col = action.get_dest_col()

        # check if selected cell is valid
        if self.__grid[row][col] in [TakoJudoState.EMPTY_CELL, 4, 5]:
            return False

        # check if the piece is the head of the octopus
        if self.__grid[row][col] in [1, 3]:

            if dest_row >=7 or dest_col >=7:
                return False

            #check if the surrounding cells are also empty
            if (self.__grid[dest_row][dest_col] == TakoJudoState.EMPTY_CELL
                and self.__grid[dest_row][dest_col +1] == TakoJudoState.EMPTY_CELL
                and self.__grid[dest_row + 1][dest_col] == TakoJudoState.EMPTY_CELL
                and self.__grid[dest_row + 1][dest_col + 1] == TakoJudoState.EMPTY_CELL):
                return True
            else:
                return False

        # check if the destination cell is empty
        if self.__grid[dest_row][dest_col] == TakoJudoState.EMPTY_CELL:
            return True
        else:
            return False

    def update(self, action: TakoJudoAction):
        piece_row = action.get_row()
        piece_col = action.get_col()
        dest_row = action.get_dest_row()
        dest_col = action.get_dest_col()

        # Get the piece at the starting position
        piece = self.__grid[piece_row][piece_col]

        if piece == 1:

            self.__grid[dest_row][dest_col] = piece
            self.__grid[dest_row][dest_col + 1] = 4
            self.__grid[dest_row + 1][dest_col] = 4
            self.__grid[dest_row + 1][dest_col + 1] = 4

            self.__grid[piece_row][piece_col] = TakoJudoState.EMPTY_CELL
            self.__grid[piece_row][piece_col + 1] = TakoJudoState.EMPTY_CELL
            self.__grid[piece_row + 1][piece_col] = TakoJudoState.EMPTY_CELL
            self.__grid[piece_row + 1][piece_col + 1] = TakoJudoState.EMPTY_CELL

        if piece == 3:

            self.__grid[dest_row][dest_col] = piece
            self.__grid[dest_row][dest_col + 1] = 5
            self.__grid[dest_row + 1][dest_col] = 5
            self.__grid[dest_row + 1][dest_col + 1] = 5

            self.__grid[piece_row][piece_col] = TakoJudoState.EMPTY_CELL
            self.__grid[piece_row][piece_col + 1] = TakoJudoState.EMPTY_CELL
            self.__grid[piece_row + 1][piece_col] = TakoJudoState.EMPTY_CELL
            self.__grid[piece_row + 1][piece_col + 1] = TakoJudoState.EMPTY_CELL

        # Move the piece to the destination cell
        self.__grid[dest_row][dest_col] = piece
        self.__grid[piece_row][piece_col] = TakoJudoState.EMPTY_CELL

        # Determine if there is a winner
        self.__has_winner = self.__check_winner(self.__acting_player)

        # Switch to the next player
        self.__acting_player = (self.__acting_player + 1) % self.get_num_players()

        # Increment the turn counter
        self.__turns_count += 1

    def __display_cell(self, row, col):
        print({
                  0: 'T',
                  1: 'H',
                  2: 't',
                  3: 'h',
                  4: 'O',
                  5: 'o',
                  TakoJudoState.EMPTY_CELL: ' '
              }[self.__grid[row][col]], end="")

    def __display_numbers(self):
        for col in range(0, self.__num_cols):
            if col < 10:
                print('   ', end="")
            print(col, end="")
        print("")

    def __display_separator(self):
        for col in range(0, self.__num_cols):
            print("----", end="")
        print("--")

    def display(self):
        self.__display_numbers()
        self.__display_separator()
        row_number = 0

        for row in range(0, self.__num_rows):
            print(f'{row_number}| ', end="")
            row_number += 1
            for col in range(0, self.__num_cols):
                self.__display_cell(row, col)
                print(' | ', end="")
            print("")
            self.__display_separator()

        print("")

    def is_finished(self) -> bool:
        return self.__has_winner

    def get_acting_player(self) -> int:
        return self.__acting_player

    def clone(self):
        cloned_state = TakoJudoState(self.__num_rows, self.__num_cols)
        cloned_state.__turns_count = self.__turns_count
        cloned_state.__acting_player = self.__acting_player
        cloned_state.__has_winner = self.__has_winner
        for row in range(0, self.__num_rows):
            for col in range(0, self.__num_cols):
                cloned_state.__grid[row][col] = self.__grid[row][col]
        return cloned_state

    def get_result(self, pos) -> Optional[TakoJudoResult]:
        if self.__has_winner:
            return TakoJudoResult.LOOSE if pos == self.__acting_player else TakoJudoResult.WIN
        if self.__is_full():
            return TakoJudoResult.DRAW
        return None

    def get_num_rows(self):
        return self.__num_rows

    def get_num_cols(self):
        return self.__num_cols

    def before_results(self):
        pass

    def get_possible_actions(self):
        actions = []
        for col in range(0, self.__num_cols):
            for row in range(0, self.__num_rows):
                if self.__grid[row][col] == -1:
                    actions.append(TakoJudoAction(col, row))
                    break
        return actions

    def sim_play(self, action):
        new_state = self.clone()
        new_state.play(action)
        return new_state
