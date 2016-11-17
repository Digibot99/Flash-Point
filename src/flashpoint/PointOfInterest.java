package flashpoint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.Math;

public class PointOfInterest {

    private int currentRow;
    private Color color;
    private int currentColumn;
    private boolean isFalseAlarm;

    PointOfInterest() {
//        while (Board.board[currentRow][currentColumn] != Board.EMPTY || Board.board[currentRow][currentColumn] == Board.SMOKE) 
        {
            while (currentRow == 0 || currentRow >= Board.numRows - 1) {
                currentRow = (int) (Math.random() * Board.numRows);
            }
            while (currentColumn == 0 || currentColumn >= Board.numColumns - 1) {
                currentColumn = (int) (Math.random() * Board.numColumns);
            }
        }
        color = Color.BLUE;
        Board.board[currentRow][currentColumn] = Board.POI;
        isFalseAlarm = this.setFalseAlarm();
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

    public boolean setFalseAlarm() {
        int temp;
        temp = (int) (Math.random() * 2) + 1;
        if (temp == 0) {
            return (true);
        } else if (temp == 1) {
            return (false);
        } else {
            return (false);
        }
    }

    public boolean getFalseAlarm() {
        return (isFalseAlarm);
    }
    public boolean isNexttoPlayer(Player player) {
        if (Board.board[this.getCurrentRow() + 1][this.getCurrentColumn()] == Board.board[player.getCurrentRow()][player.getCurrentColumn()])
        {
        return (true);
        }
        else if (Board.board[this.getCurrentRow() - 1][this.getCurrentColumn()] == Board.board[player.getCurrentRow()][player.getCurrentColumn()])
        {
        return (true);
        }
        else if (Board.board[this.getCurrentRow()][this.getCurrentColumn() + 1] == Board.board[player.getCurrentRow()][player.getCurrentColumn()])
        {
        return (true);
        }
        else if (Board.board[this.getCurrentRow()][this.getCurrentColumn() - 1] == Board.board[player.getCurrentRow()][player.getCurrentColumn()])
        {
        return (true);
        }
        return (false);
    }
}
