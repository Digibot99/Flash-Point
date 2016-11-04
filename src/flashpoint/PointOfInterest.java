package flashpoint;
import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.Math;
public class PointOfInterest {
    
    private int currentRow;
    private Color color;
    private int currentColumn;
    private boolean isFalseAlarm;
    PointOfInterest(){
        currentColumn = (int)(Math.random()*Board.numColumns);
        currentRow = (int)(Math.random()*Board.numRows);
        color = Color.BLUE;
        isFalseAlarm = this.setFalseAlarm();
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
    public boolean setFalseAlarm ()
    {
        int temp;
        temp = (int)(Math.random()*2)+1;
        if (temp == 0)
        {
            return(true);
        }
        else if (temp == 1)
        {
            return(false);
        }
        else
            return(false);
    }
    public boolean getFalseAlarm ()
    {
        return(isFalseAlarm);
    }
}
