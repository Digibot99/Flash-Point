package flashpoint;
import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.Math;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Player {
//    public enum Players{
//        Red,Blue,Green,Yellow
//    }
    private int actionPoints;
    int currentRow;
    int currentColumn;
    private Color color;
    private boolean isTurn;
    Socket socket;
    BufferedReader input;
    PrintWriter output;

    Player(Color _color){

        actionPoints = 0;
        color = _color;
        currentColumn = (int)(Math.random()*Board.numColumns);
        currentRow = (int)(Math.random()*Board.numRows);
        }
    public int getActionPoints()
    {
        return(actionPoints);
    }
    public int setActionPoints()
    {
        actionPoints = (int)(Math.random() * 4 + 1);
        return(actionPoints);
    }
    public void playerLoseActionPoint()
    {
        actionPoints -= 1;
    }
    public Color getColor ()
    {
        return(color);
    }
    public int getCurrentRow ()
    {
        return(currentRow);
    }
    public int getCurrentColumn ()
    {
        return(currentColumn);
    }
    public void setCurrentRow (int _currentRow)
    {
        currentRow = _currentRow;
    }
    public void setCurrentColumn (int _currentColumn)
    {
        currentColumn = _currentColumn;
    }
    
    public boolean getisTurn ()
    {
        return(isTurn);
    }
    public void setisTurn (boolean _temp)
    {
        isTurn = _temp;
    }
}
