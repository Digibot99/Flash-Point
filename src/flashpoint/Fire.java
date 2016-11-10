package flashpoint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.Math;

public class Fire {

    private int currentRow;
    private Color color;
    private int currentColumn;
    private int GIFNum;

    Fire(int _currentRow, int _currentColumn, int _gifnum) {
        currentColumn = _currentColumn;
        currentRow = _currentRow;
        System.out.println(currentRow + " " + currentColumn);
        GIFNum = _gifnum;
    }

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
