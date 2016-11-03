/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashpoint;

/**
 *
 * @author 384000346
 */
public class Board {
//    final static int numRows = 6;
//    final static int numColumns = 8;
    final static int numRows = 8;
    final static int numColumns = 10;
    final static int EMPTY = 0;
    final static int PLAYERRED = 1;
    final static int PLAYERBLUE = 2;
    final static int PLAYERGREEN = 3;
    final static int PLAYERYELLOW = 4;
    final static int SMOKE = 5;
    final static int FIRE = 6;
    final static int WALL = 7;
    final static int DOOR = 8;
    static int board[][];
    
    public static int getCursorColumn(int _cursorXPos)
    {
        int i;
        for (i = numColumns; i > 0; i--)
        {
        if (_cursorXPos > Window.WINDOW_WIDTH/numColumns*i)
        {
            break;
        }
        }
        return(i);
    }
    public static int getCursorRow(int _cursorYPos)
    {
        int i;
        for (i = numRows; i > 0; i--)
        {
        if (_cursorYPos > Window.WINDOW_HEIGHT/numRows*i)
        {
            break;
        }
        }
        return(i);
    }
}
