package flashpoint;
import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.Math;

public class Player {
//    public enum Players{
//        Red,Blue,Green,Yellow
//    }
    private int actionPoints;
    int currentRow;
    int currentColumn;
    private Color color;
    private boolean isTurn;
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
        actionPoints = 4;
        return(actionPoints);
    }
    public int addActionPoints()
    {
        int morePoints = 4;
        actionPoints += morePoints;
        
        if (this.getActionPoints() > 8)
        {
            actionPoints = 8;
        }
        return (actionPoints);
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
    public void skipTurn ()
    {
        this.setisTurn(false);
        System.out.println("hi");
    }
}
