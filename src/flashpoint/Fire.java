package flashpoint;
import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.Math;
public class Fire {
    
    private int currentRow;
    private Color color;
    private int currentColumn;
    Fire(){
        currentColumn = (int)(Math.random()*Board.numColumns);
        currentRow = (int)(Math.random()*Board.numRows);
        color = Color.ORANGE;
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
}
