
package flashpoint;

public class Movement {
    static int newColumn;
    public static int MoveRight(int _currentColumn)
    {
        newColumn = _currentColumn + 1;
        return(newColumn);
    }
}
