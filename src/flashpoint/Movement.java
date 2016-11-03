package flashpoint;

public class Movement {

    static int newColumn;
    static int newRow;

    public static int MoveRight(int _currentRow, int _currentColumn) {
        if (Board.board[_currentRow][_currentColumn + 1] == Board.EMPTY)
        {
        newColumn = _currentColumn + 1;
        return (newColumn);
        }
        else
        {
            return (_currentColumn);
        }
    }

    public static int MoveLeft(int _currentRow, int _currentColumn) {
        if (Board.board[_currentRow][_currentColumn - 1] == Board.EMPTY)
        {
        newColumn = _currentColumn - 1;
        return (newColumn);
        }
        else
        {
            return (_currentColumn);
        }
    }

    public static int MoveUp(int _currentRow, int _currentColumn) {
        if (Board.board[_currentRow - 1][_currentColumn] == Board.EMPTY)
        {
        newRow = _currentRow - 1;
        return (newRow);
        }
        else
        {
            return (newRow);
        }
    }

    public static int MoveDown(int _currentRow, int _currentColumn) {
        if (Board.board[_currentRow + 1][_currentColumn] == Board.EMPTY)
        {
        newRow = _currentRow + 1;
        return (newRow);
        }
        else
        {
            return (newRow);
        }
    }
}
