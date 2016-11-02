package flashpoint;

public class Movement {

    static int newColumn;
    static int newRow;

    public static int MoveRight(int _currentColumn) {
        newColumn = _currentColumn + 1;
        return (newColumn);
    }

    public static int MoveLeft(int _currentColumn) {
        newColumn = _currentColumn - 1;
        return (newColumn);
    }

    public static int MoveUp(int _currentRow) {
        newRow = _currentRow - 1;
        return (newRow);
    }

    public static int MoveDown(int _currentRow) {
        newRow = _currentRow + 1;
        return (newRow);
    }
}
