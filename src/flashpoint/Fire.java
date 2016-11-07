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
        System.out.println(currentRow + " " + currentColumn);
        color = Color.ORANGE;
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
