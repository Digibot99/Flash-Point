/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashpoint;

/**
 *
 * @author Seth
 */
public class Wall {
    int currentHealth;
    int currentRow;
    int currentColumn;
    Wall()
    {
        currentColumn = (int)(Math.random()*Board.numColumns);
        currentRow = (int)(Math.random()*Board.numRows);
    }
    public int getCurrentRow ()
    {
        return(currentRow);
    }
    public int getCurrentColumn ()
    {
        return(currentColumn);
    }
    public int getCurrentHealth ()
    {
        return(currentHealth);
    }
}
