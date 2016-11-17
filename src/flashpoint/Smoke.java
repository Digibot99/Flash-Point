package flashpoint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.Math;

public class Smoke {

    private int currentRow;
    private Color color;
    private int currentColumn;
    private int GIFNum;
    
    Smoke (int _currentRow, int _currentColumn, int _gifnum) {
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
        Board.board[currentRow][currentColumn] = Board.SMOKE;
        GIFNum = _gifnum;
    }
    
    Smoke (int _gifnum) {
//        while (Board.board[currentRow][currentColumn] != Board.EMPTY || Board.board[currentRow][currentColumn] == Board.SMOKE) 
        {
            while (currentRow == 0 || currentRow >= Board.numRows - 1) {
                currentRow = (int) (Math.random() * Board.numRows);
            }
            while (currentColumn == 0 || currentColumn >= Board.numColumns - 1) {
                currentColumn = (int) (Math.random() * Board.numColumns);
            }
        }
        Board.board[currentRow][currentColumn] = Board.SMOKE;
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
