class Board:

    board = [['', '', '', '', '', '', '', ''],
             ['', '', '', '', '', '', '', ''],
             ['T', 'T', 'T', '', '', 't', 't', 't'],
             ['H', 'H', 'T', '', '', 't', 'h', 'h'],
             ['H', 'H', 'T', '', '', 't', 'h', 'h'],
             ['T', 'T', 'T', '', '', 't', 't', 't'],
             ['', '', '', '', '', '', '', ''],
             ['', '', '', '', '', '', '', '']]

    tentacle_x = 0
    tentacle_y = 0

    dest_x = 0
    dest_y = 0

    def __init__(self):
        print('x--> 0     1     2     3     4     5     6     7')
        print('  -------------------------------------------------')
        for y in range(8):
            row = str(y) + ' | '
            for x in range(8):
                if self[y][x] == 'H':
                    row += ' H '
                elif self[y][x] == 'T':
                    row += ' T '
                elif self[y][x] == 'h':
                    row += ' h '
                elif self[y][x] == 't':
                    row += ' t '
                else:
                    row += '   '
                row += ' | '
            print(row)
            print('  -------------------------------------------------')


    def empty_cell(self, cell_x, cell_y):
        if self.board[cell_x][ cell_y] =='':
            return True
        else: return False

    def contains_tentacle(self, cell_x, cell_y):
        if self.board[cell_x][cell_y] == 't' or 'T':
            return True
        else: return False


