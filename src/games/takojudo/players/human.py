from src.games.takojudo.action import TakoJudoAction
from src.games.takojudo.player import TakoJudoPlayer
from src.games.takojudo.state import TakoJudoState


class HumanTakoJudoPlayer(TakoJudoPlayer):

    def __init__(self, name):
        super().__init__(name)

    def get_action(self, state: TakoJudoState):
        state.display()
        while True:
            # noinspection PyBroadException
            try:
                return TakoJudoAction(int(input(f"Player {state.get_acting_player()}, choose a column: ")))
            except Exception:
                continue

    def event_action(self, pos: int, action, new_state: TakoJudoState):
        # ignore
        pass

    def event_end_game(self, final_state: TakoJudoState):
        # ignore
        pass