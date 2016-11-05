/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashpoint;

import java.awt.Color;

/**
 *
 * @author Seth
 */
public class SetBoardandPlayer {
    public static enum String{
        Up,Down,Left,Right
    }
    public static void PlayerMove (Player player, Player nextPlayer, String _direction) {
        if (player.getActionPoints() != 0 && player.getisTurn() == true) {
        if (player.getCurrentRow() != Board.numRows - 1) 
        {
                            Board.board[player.getCurrentRow()][player.getCurrentColumn()] = Board.EMPTY;
                            if (_direction == String.Down){ System.out.println("ki");
                                player.setCurrentRow(Movement.MoveDown(player.getCurrentRow(), player.getCurrentColumn()));}
                            if (_direction == String.Up){ System.out.println("km");
                                player.setCurrentRow(Movement.MoveUp(player.getCurrentRow(), player.getCurrentColumn()));}
                            
                            player.playerLoseActionPoint();
        }
        if (player.getCurrentColumn() != Board.numColumns - 1){
            Board.board[player.getCurrentRow()][player.getCurrentColumn()] = Board.EMPTY;
                            if (_direction == String.Left){ System.out.println("kl");
                                player.setCurrentColumn(Movement.MoveLeft(player.getCurrentRow(), player.getCurrentColumn()));}
                            if (_direction == String.Right){ System.out.println("ka");
                                player.setCurrentColumn(Movement.MoveRight(player.getCurrentRow(), player.getCurrentColumn()));}
                            
                            player.playerLoseActionPoint();
        }
                            
                            
                            if (player.getColor() == Color.red)
                                Board.board[player.getCurrentRow()][player.getCurrentColumn()] = Board.PLAYERRED;
                            if (player.getColor() == Color.blue)
                                Board.board[player.getCurrentRow()][player.getCurrentColumn()] = Board.PLAYERBLUE;
                            if (player.getColor() == Color.green)
                                Board.board[player.getCurrentRow()][player.getCurrentColumn()] = Board.PLAYERGREEN;
                            if (player.getColor() == Color.yellow)
                                Board.board[player.getCurrentRow()][player.getCurrentColumn()] = Board.PLAYERYELLOW;
                            if (player.getActionPoints() == 0) {
                                player.setisTurn(false);
                                player.setActionPoints();
                                FlashPoint.addAndCheckSmoke();
                                nextPlayer.setisTurn(true);
                                return;
                            }
                        }
    }
    public static void PlayerSkip (Player player, Player nextPlayer) {
        
                        if (player.getActionPoints() < 8 && player.getisTurn() == true)
                        {
                        player.skipTurn();
                        player.addActionPoints();
                        nextPlayer.setisTurn(true);
                        }
    }
}
