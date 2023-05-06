from games.takojudo.action import TakoJudoAction
from games.takojudo.result import TakoJudoResult
from games.state import State

from src.games.takojudo.board import Board


class TakoJudoState(State):

    def __init__(self):
        super().__init__()

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

    def get_acting_player(self) -> int:
        return self.__acting_player

    def sim_play(self, action):
        new_state = self.clone()
        new_state.play(action)
        return new_state

    def get_num_players(self):
        return 2

    def update(self, action: TakoJudoAction):
        origin = action.get_origin()
        dest = action.get_dest()




        self.__acting_player = 1 if self.__acting_player == 0 else 0

        self.__turns_count +=1