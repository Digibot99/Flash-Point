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
    final static int DOOR = 11;
    static int board[][];
    
    public static boolean getBoardLocation(int _Piece) {
        for (int i = 0; i < numRows - 1; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (board[i][j] == _Piece) {
                    return (true);
                }
            }
        }
        return (false);
    }
}
