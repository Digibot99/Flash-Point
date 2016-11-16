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
//        while (Board.board[currentRow][currentColumn] != Board.EMPTY || Board.board[currentRow][currentColumn] == Board.SMOKE) 
        {
            while (currentRow == 0 || currentRow >= Board.numRows - 1) {
                currentRow = (int) (Math.random() * Board.numRows);
            }
            while (currentColumn == 0 || currentColumn >= Board.numColumns - 1) {
                currentColumn = (int) (Math.random() * Board.numColumns);
            }
        }
        Board.board[currentRow][currentColumn] = Board.FIRE;
        GIFNum = _gifnum;
    }

    public Color getColor() {
        return (color);
    }

    
    public boolean isNextToPlayer(Player player) {
        if (player.getColor() == Color.red) {
            if (Board.board[this.getCurrentRow()][this.getCurrentColumn() + 1] == Board.PLAYERRED) {
                return (true);
            }
            if (Board.board[this.getCurrentRow()][this.getCurrentColumn() - 1] == Board.PLAYERRED) {
                return (true);
            }
            if (Board.board[this.getCurrentRow() + 1][this.getCurrentColumn()] == Board.PLAYERRED) {
                return (true);
            }
            if (Board.board[this.getCurrentRow() - 1][this.getCurrentColumn()] == Board.PLAYERRED) {
                return (true);
            }
        }
        else if (player.getColor() == Color.blue) {
            if (Board.board[this.getCurrentRow()][this.getCurrentColumn() + 1] == Board.PLAYERBLUE) {
                return (true);
            }
            if (Board.board[this.getCurrentRow()][this.getCurrentColumn() - 1] == Board.PLAYERBLUE) {
                return (true);
            }
            if (Board.board[this.getCurrentRow() + 1][this.getCurrentColumn()] == Board.PLAYERBLUE) {
                return (true);
            }
            if (Board.board[this.getCurrentRow() - 1][this.getCurrentColumn()] == Board.PLAYERBLUE) {
                return (true);
            }
        }
        else if (player.getColor() == Color.green) {
            if (Board.board[this.getCurrentRow()][this.getCurrentColumn() + 1] == Board.PLAYERGREEN) {
                return (true);
            }
            if (Board.board[this.getCurrentRow()][this.getCurrentColumn() - 1] == Board.PLAYERGREEN) {
                return (true);
            }
            if (Board.board[this.getCurrentRow() + 1][this.getCurrentColumn()] == Board.PLAYERGREEN) {
                return (true);
            }
            if (Board.board[this.getCurrentRow() - 1][this.getCurrentColumn()] == Board.PLAYERGREEN) {
                return (true);
            }
        }
        else if (player.getColor() == Color.yellow) {
            if (Board.board[this.getCurrentRow()][this.getCurrentColumn() + 1] == Board.PLAYERYELLOW) {
                return (true);
            }
            if (Board.board[this.getCurrentRow()][this.getCurrentColumn() - 1] == Board.PLAYERYELLOW) {
                return (true);
            }
            if (Board.board[this.getCurrentRow() + 1][this.getCurrentColumn()] == Board.PLAYERYELLOW) {
                return (true);
            }
            if (Board.board[this.getCurrentRow() - 1][this.getCurrentColumn()] == Board.PLAYERYELLOW) {
                return (true);
            }
        }
        return (false);
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