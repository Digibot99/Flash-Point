package flashpoint;

import java.awt.Color;

public class SetBoardandPlayer {

    public static enum String {
        Up, Down, Left, Right
    }

    public static void PlayerMove(Player player, Player nextPlayer, String _direction) {
        if (player.getActionPoints() >= 0 && player.getisTurn() == true) {
                Board.board[player.getCurrentRow()][player.getCurrentColumn()] = Board.EMPTY;
                
                if (player.getCurrentRow() != Board.numRows - 1) {
                    if (_direction == String.Down) {
                        player.setCurrentRow(Movement.MoveDown(player.getCurrentRow(), player.getCurrentColumn()));
                        player.playerLoseActionPoint();
                    }
                }
                if (player.getCurrentRow() >= 1) {
                    if (_direction == String.Up) {
                        player.setCurrentRow(Movement.MoveUp(player.getCurrentRow(), player.getCurrentColumn()));
                        player.playerLoseActionPoint();
                    }
                }
            if (player.getCurrentColumn() >= 1) {
                if (_direction == String.Left) {
                    player.setCurrentColumn(Movement.MoveLeft(player.getCurrentRow(), player.getCurrentColumn()));
                    player.playerLoseActionPoint();
                }
            }
            if (player.getCurrentColumn() != Board.numColumns - 1) {
                if (_direction == String.Right) {
                    player.setCurrentColumn(Movement.MoveRight(player.getCurrentRow(), player.getCurrentColumn()));
                    player.playerLoseActionPoint();
                }
            }
            
            if (player.getColor() == Color.red) {
                Board.board[player.getCurrentRow()][player.getCurrentColumn()] = Board.PLAYERRED;
            }
            if (player.getColor() == Color.blue) {
                Board.board[player.getCurrentRow()][player.getCurrentColumn()] = Board.PLAYERBLUE;
            }
            if (player.getColor() == Color.green) {
                Board.board[player.getCurrentRow()][player.getCurrentColumn()] = Board.PLAYERGREEN;
            }
            if (player.getColor() == Color.yellow) {
                Board.board[player.getCurrentRow()][player.getCurrentColumn()] = Board.PLAYERYELLOW;
            }
            if (player.getActionPoints() == 0) {
                player.setisTurn(false);
                player.setActionPoints();
                for (int i = 0; i < 1; i++)
                {
                FlashPoint.addAndCheckSmoke();
                }nextPlayer.setisTurn(true);
            }
        }
    }

    public static void PlayerSkip(Player player, Player nextPlayer) {
        
        if (player.getActionPoints() < 8 && player.getisTurn() == true) {
            player.skipTurn();
            player.addActionPoints();
            nextPlayer.setisTurn(true);
        }
    }
}
