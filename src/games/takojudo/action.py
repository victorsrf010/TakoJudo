class TakoJudoAction:
    """
    A Tako Judo action consists of a column and a row.
    """
    __col: int
    __row: int
    __dest_col: int
    __dest_row: int

    def __init__(self, col: int, row: int, dest_col: int, dest_row: int):
        self.__col = col
        self.__row = row
        self.__dest_col = dest_col
        self.__dest_row = dest_row

    def get_col(self):
        return self.__col

    def get_row(self):
        return self.__row

    def get_dest_col(self):
        return self.__dest_col

    def get_dest_row(self):
        return self.__dest_row
