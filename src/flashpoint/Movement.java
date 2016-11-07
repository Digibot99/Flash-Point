package flashpoint;

public class Movement {

    static int newColumn;
    static int newRow;

    public static int MoveRight(int _currentRow, int _currentColumn) {
        if (Board.board[_currentRow][_currentColumn] == Board.WALL) {
            System.out.println("g");
            return (_currentColumn);
        } else if (Board.board[_currentRow][_currentColumn + 1] == Board.WALL) {
            System.out.println("l");
            newColumn = _currentColumn + 1;
            return (newColumn);
        }
        if (Board.board[_currentRow][_currentColumn + 1] == Board.EMPTY) {
            System.out.println("j");
            newColumn = _currentColumn + 1;
            return (newColumn);
        } else {
            return (_currentColumn);
        }
    }

    public static int MoveLeft(int _currentRow, int _currentColumn) {
        if (Board.board[_currentRow][_currentColumn] == Board.WALL) {
            System.out.println("g");
            return (_currentColumn);
        } else if (Board.board[_currentRow][_currentColumn - 1] == Board.WALL) {
            System.out.println("l");
            newColumn = _currentColumn - 1;
            return (newColumn);
        }
        if (Board.board[_currentRow][_currentColumn - 1] == Board.EMPTY) {
            newColumn = _currentColumn - 1;
            return (newColumn);
        } else {
            return (_currentColumn);
        }
    }

    public static int MoveUp(int _currentRow, int _currentColumn) {
        if (Board.board[_currentRow][_currentColumn] == Board.WALL) {
            System.out.println("g");
            return (_currentRow);
        } else if (Board.board[_currentRow - 1][_currentColumn] == Board.WALL) {
            System.out.println("l");
            newRow = _currentRow - 1;
            return (newRow);
        }
        if (Board.board[_currentRow - 1][_currentColumn] == Board.EMPTY) {
            newRow = _currentRow - 1;
            return (newRow);
        } else {
            return (_currentRow);
        }
    }

    public static int MoveDown(int _currentRow, int _currentColumn) {
        if (Board.board[_currentRow][_currentColumn] == Board.WALL) {
            System.out.println("g");
            return (_currentRow);
        }
        if (Board.board[_currentRow + 1][_currentColumn] == Board.WALL) {
            System.out.println("l");

            newRow = _currentRow + 1;
            return (newRow);
        }
        if (Board.board[_currentRow + 1][_currentColumn] == Board.EMPTY) {
            newRow = _currentRow + 1;
            return (newRow);
        } else {
            return (_currentRow);
        }
    }
}
