package flashpoint;
import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.Math;
public class Smoke {
    
    private int currentRow;
    private Color color;
    private int currentColumn;
    Smoke(){
        currentColumn = (int)(Math.random()*Board.numColumns);
        currentRow = (int)(Math.random()*Board.numRows);
        color = Color.DARK_GRAY;
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
    public void addSmoke () 
    {
        
    }
}
