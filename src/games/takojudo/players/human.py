from games.takojudo.action import TakoJudoAction
from games.takojudo.state import TakoJudoState
from games.takojudo.player import TakoJudoPlayer


class HumanTakoJudoPlayer(TakoJudoPlayer):

    def __init__(self, name):
        super().__init__(name)

    def get_action(self, state: TakoJudoState):
        state.display()
        while True:
            # noinspection PyBroadException
            try:
                row = int(input(f"Player {self.get_name()}, choose a row: "))
                col = int(input(f"Player {self.get_name()}, choose a column: "))
                dest_row = int(input(f"Player {self.get_name()}, choose a destination row: "))
                dest_col = int(input(f"Player {self.get_name()}, choose a destination column: "))
                return TakoJudoAction(col, row, dest_col, dest_row)
            except Exception:
                continue

    def event_action(self, pos: int, action, new_state: TakoJudoState):
        # ignore
        pass

    def event_end_game(self, final_state: TakoJudoState):
        # ignore
        pass