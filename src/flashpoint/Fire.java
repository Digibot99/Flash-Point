package flashpoint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.Math;

public class Fire {

    private int currentRow;
    private Color color;
    private int currentColumn;

    Fire(int _currentRow, int _currentColumn) {
        currentColumn = _currentColumn;
        currentRow = _currentRow;
//        while (Board.board[currentRow][currentColumn] != Board.EMPTY || Board.board[currentRow][currentColumn] == Board.SMOKE) 
        {
            while (currentRow == 0 || currentRow >= Board.numRows - 1) {
                currentRow = (int) (Math.random() * Board.numRows);
            }
            while (currentColumn == 0 || currentColumn >= Board.numColumns - 1) {
                currentColumn = (int) (Math.random() * Board.numColumns);
            }
        }
    }

    public Color getColor() {
        return (color);
    }

    public int getCurrentRow() {
        return (currentRow);
    }

    public int getCurrentColumn() {
        return (currentColumn);
    }
}
