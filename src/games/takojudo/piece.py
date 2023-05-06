class TakoJudoPiece:
    """
    A piece used in Tako Judo.
    """
    def __init__(self, player, row, col):
        self.__player = player
        self.__row = row
        self.__col = col

    @property
    def player(self):
        return self.__player

    @property
    def row(self):
        return self.__row

    @property
    def col(self):
        return self.__col

    @row.setter
    def row(self, row):
        self.__row = row

    @col.setter
    def col(self, col):
        self.__col = col