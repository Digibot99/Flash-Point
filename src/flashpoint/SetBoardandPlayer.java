package flashpoint;

import java.awt.Color;

public class SetBoardandPlayer {

    public static enum String {
        Up, Down, Left, Right, Random
    }

    public static void PlayerMove(Player player, Player nextPlayer, String _direction) {
        if (player.getActionPoints() >= 0 && player.getisTurn() == true) {
                Board.board[player.getCurrentRow()][player.getCurrentColumn()] = Board.EMPTY;
                
                if (_direction == String.Random)
                {
                    int dir = (int)(Math.random()*4+1);
                    if (dir == 1)
                    {
                        _direction = String.Down;
                    }
                    if (dir == 2)
                    {
                        _direction = String.Left;
                    }
                    if (dir == 3)
                    {
                        _direction = String.Right;
                    }
                    else
                    {
                        _direction = String.Up;
                    }
                }
                
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
    public static int  PlayerInteract(Player player) {
//        if (player.getCurrentColumn() >= 0 && player.getCurrentColumn() <= Board.numColumns)
        {
            if (Board.board[player.getCurrentRow()][player.getCurrentColumn() + 1] == Board.FIRE)
            {
                System.out.println(player.getCurrentRow() + " " + player.getCurrentColumn());
                return(Board.board[player.getCurrentRow()][player.getCurrentColumn() + 1]);
            }
            else if (Board.board[player.getCurrentRow()][player.getCurrentColumn() - 1] == Board.FIRE)
            {
                System.out.println(player.getCurrentRow() + " " + player.getCurrentColumn());
                return(Board.board[player.getCurrentRow()][player.getCurrentColumn() - 1]);   
            }
        }
//        if (player.getCurrentRow() >= 0 && player.getCurrentRow() <= Board.numRows)
        {
            if (Board.board[player.getCurrentRow() - 1 ][player.getCurrentColumn()] == Board.FIRE)
            {
                System.out.println(player.getCurrentRow() + " " + player.getCurrentColumn());
                return(Board.board[player.getCurrentRow() - 1][player.getCurrentColumn()]);   
            }
            else if (Board.board[player.getCurrentRow() + 1 ][player.getCurrentColumn()] == Board.FIRE)
            {
                System.out.println(player.getCurrentRow() + " " + player.getCurrentColumn());
                return(Board.board[player.getCurrentRow() + 1][player.getCurrentColumn()]);   
            }
        }
        
//        if (Board.board[player.getCurrentRow()][player.getCurrentColumn() + 1 ] == Board.SMOKE ||
//            Board.board[player.getCurrentRow()][player.getCurrentColumn() - 1 ] == Board.SMOKE ||
//            Board.board[player.getCurrentRow() + 1 ][player.getCurrentColumn()] == Board.SMOKE ||
//            Board.board[player.getCurrentRow() - 1 ][player.getCurrentColumn()] == Board.SMOKE)
//        {
//            return (true);
//        }
//        if (Board.board[player.getCurrentRow()][player.getCurrentColumn() + 1 ] == Board.POI ||
//            Board.board[player.getCurrentRow()][player.getCurrentColumn() - 1 ] == Board.POI ||
//            Board.board[player.getCurrentRow() + 1 ][player.getCurrentColumn()] == Board.POI ||
//            Board.board[player.getCurrentRow() - 1 ][player.getCurrentColumn()] == Board.POI)
//        {
//            return (true);
//        }
            return (0);
    }
}
