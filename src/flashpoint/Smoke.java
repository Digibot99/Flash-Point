package flashpoint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.Math;

public class Smoke {

    private int currentRow;
    private Color color;
    private int currentColumn;
    private int GIFNum;

    Smoke(int _gifnum) {
//        while (Board.board[currentRow][currentColumn] != Board.EMPTY || Board.board[currentRow][currentColumn] == Board.SMOKE) 
        {
            while (currentRow == 0 || currentRow >= Board.numRows - 1) {
                currentRow = (int) (Math.random() * Board.numRows);
            }
            while (currentColumn == 0 || currentColumn >= Board.numColumns - 1) 
            while (currentRow == 0 || currentRow >= Board.numRows - 1) {
                currentRow = (int) (Math.random() * Board.numRows);
            }
                currentColumn = (int) (Math.random() * Board.numColumns);
            }
            GIFNum = _gifnum;
        }
//        color = Color.DARK_GRAY;
    

    public Color getColor() {
        return (color);
    }
    
    public int getGIFNum() {
        return (GIFNum);
    }

    public int getCurrentRow() {
        return (currentRow);
    }

    public int getCurrentColumn() {
        return (currentColumn);
    }
}
