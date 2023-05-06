from src.games.takojudo import board
from src.games.takojudo.board import Board


class TakoJudoAction:

    __origin_x = 0
    __origin_y = 0

    __dest_x = 0
    __dest_y = 0

    def __init__(self, origin_x: int, origin_y: int, dest_x: int, dest_y: int):
        self.__origin_x = origin_x
        self.__origin_y = origin_y
        self.__dest_x = dest_x
        self.__dest_y = dest_y

    def get_origin(self):

        while True:
            # Prompt player to select a tentacle
            tentacle_x = int(input("Choose the X position of the tentacle to move: "))
            tentacle_y = int(input("Choose the Y position of the tentacle to move: "))

            # Check if the selected position is a valid tentacle
            if Board.contains_tentacle(Board.board, tentacle_x, tentacle_y) == 'T':
                print("Valid")
                self.__origin_x = tentacle_x
                self.__origin_y = tentacle_y
                break  # Exit loop if input is valid
            else:
                print("Invalid")

    def get_dest(self):
        while True:
            # Prompt player to select a cell
            cell_x = int(input("Choose the X position of the tentacle to move: "))
            cell_y = int(input("Choose the Y position of the tentacle to move: "))

            # Check if the selected position is empty
            if Board.empty_cell(Board.board, cell_x, cell_y):
                print("Valid")
                self.__origin_x = cell_x
                self.__origin_y = cell_y
                break  # Exit loop if input is valid
            else:
                print("Invalid")
