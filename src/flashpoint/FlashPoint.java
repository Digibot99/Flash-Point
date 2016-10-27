/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flashpoint; 

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class FlashPoint extends JFrame implements Runnable {
    boolean animateFirstTime = true;
    Image image;
    Graphics2D g;
   
    int currentRow;
    int currentColumn;
    int nextRow;
    int nextColumn;
    int RowDir;
    int ColumnDir;
    int timecount;
    int score;
    int highScore;
    int badBoxNum = 10; 
    int PowerupRow;
    int PowerupColumn;
    boolean gameover;
    boolean PowerupOn;
    double frameRate = 10.0;
    double SpeedUp;
    
    static FlashPoint frame1;
    public static void main(String[] args) {
        frame1 = new FlashPoint();
        frame1.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }

    public FlashPoint() {

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) {
                    //left button
                }
                if (e.BUTTON3 == e.getButton()) {
                    //right button
                    reset();
                }
                repaint();
            }
        });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        repaint();
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.VK_RIGHT == e.getKeyCode())
                {
                    Movement.MoveRight(currentColumn);
                }
                if (e.VK_LEFT == e.getKeyCode())
                {
                    nextColumn = currentColumn - 1;
                    ColumnDir = -1;
                    RowDir = 0;
                }
                if (e.VK_UP == e.getKeyCode())
                {
                    nextRow = currentRow - 1;
                    RowDir = -1;
                    ColumnDir = 0;
                }
                if (e.VK_DOWN == e.getKeyCode())
                {
                    nextRow = currentRow + 1;
                    RowDir = 1;
                    ColumnDir = 0;
                }
                 if (e.VK_SPACE == e.getKeyCode())
                {
                    if(gameover)
                    {
                        
                    }
                    else
                    {
                    currentRow = (int)(Math.random()*Board.numRows);
                    currentColumn = (int)(Math.random()*Board.numColumns);
                    if (Board.board[currentRow][currentColumn] == Board.EMPTY)
                    {
                    Board.board[currentRow][currentColumn] = Board.SNAKE;
                    }
                    }
                }
                repaint();
            }
        });
        init();
        start();
    }




    Thread relaxer;
////////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////
    public void destroy() {
    }
////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics gOld) {
        if (image == null || Window.xsize != getSize().width || Window.ysize != getSize().height) {
            Window.xsize = getSize().width;
            Window.ysize = getSize().height;
            image = createImage(Window.xsize, Window.ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

//fill background
        g.setColor(Color.black);

        g.fillRect(0, 0, Window.xsize, Window.ysize);

        int x[] = {Window.getX(0), Window.getX(Window.getWidth2()), Window.getX(Window.getWidth2()), Window.getX(0), Window.getX(0)};
        int y[] = {Window.getY(0), Window.getY(0), Window.getY(Window.getHeight2()), Window.getY(Window.getHeight2()), Window.getY(0)};
//fill border
        g.setColor(Color.white);
        g.fillPolygon(x, y, 4);
// draw border
        g.setColor(Color.black);
        g.drawPolyline(x, y, 5);

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }

        g.setColor(Color.gray);
//horizontal lines
        for (int zi=1;zi<Board.numRows;zi++)
        {
            g.drawLine(Window.getX(0) ,Window.getY(0)+zi*Window.getHeight2()/Board.numRows ,
            Window.getX(Window.getWidth2()) ,Window.getY(0)+zi*Window.getHeight2()/Board.numRows );
        }
//vertical lines
        for (int zi=1;zi<Board.numColumns;zi++)
        {
            g.drawLine(Window.getX(0)+zi*Window.getWidth2()/Board.numColumns ,Window.getY(0) ,
            Window.getX(0)+zi*Window.getWidth2()/Board.numColumns,Window.getY(Window.getHeight2())  );
        }

//Display the objects of the board
        for (int zrow=0;zrow<Board.numRows;zrow++)
        {
            for (int zcolumn=0;zcolumn<Board.numColumns;zcolumn++)
            {
                if (Board.board[zrow][zcolumn] == Board.SNAKE)
                {
                    g.setColor(Color.green);
                    g.fillRect(Window.getX(0)+zcolumn*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+zrow*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows);
                }     
                if (Board.board[zrow][zcolumn] == Board.BAD_BOX)
                {
                    g.setColor(Color.red);
                    g.fillRect(Window.getX(0)+zcolumn*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+zrow*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows);
                } 
            }
        }
        
        g.setColor(Color.cyan);
        g.fillRect(Window.getX(0)+PowerupColumn*Window.getWidth2()/Board.numColumns,
        Window.getY(0)+PowerupRow*Window.getHeight2()/Board.numRows,
        Window.getWidth2()/Board.numColumns,
        Window.getHeight2()/Board.numRows);
        
        
        g.setColor(Color.white);
        g.drawString("Score: " + score, 50, 45);
        g.drawString("High Score: " + highScore, 250, 45);
        if (gameover == true)
        {
            g.setColor(Color.black);
            g.drawString("Game Over", 250, 250);
            RowDir = 0;
            ColumnDir = 0;
            
        }

        gOld.drawImage(image, 0, 0, null);
    }


////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = 1/frameRate;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
/////////////////////////////////////////////////////////////////////////
    public void reset() {
//Allocate memory for the 2D array that represents the board.
        Board.board = new int[Board.numRows][Board.numColumns];
//Initialize the board to be empty.
        for (int zrow = 0;zrow < Board.numRows;zrow++)
        {
            for (int zcolumn = 0;zcolumn < Board.numColumns;zcolumn++)
            {
                Board.board[zrow][zcolumn] = Board.EMPTY;
            }
        }
            for (int c = 1  ; c<badBoxNum; c++ )
           {
       int row = 0;
       int column = 0;
       boolean KeepLoop = true;
       while (KeepLoop)
       {
        row = (int)(Math.random()*Board.numRows);
        column = (int)(Math.random()*Board.numColumns);
        if (Board.board[row][column] == Board.EMPTY)
        {
        Board.board[row][column] = Board.BAD_BOX;
        KeepLoop = false;
        }
        }
       
       {
           boolean KeepLoop2 = true;
       while (KeepLoop2)
       {
        PowerupRow = (int)(Math.random()*Board.numRows);
        PowerupColumn = (int)(Math.random()*Board.numColumns);
        if (Board.board[PowerupRow][PowerupColumn] == Board.EMPTY)
        {
        Board.board[PowerupRow][PowerupColumn] = Board.BAD_BOX;
        KeepLoop2 = false;
        }
        }
       
       }
       }
       gameover = false;
       currentRow = Board.numRows/2;
       currentColumn = Board.numColumns/2;
       Board.board[currentRow][currentColumn] = Board.SNAKE;
       ColumnDir = 1;
       RowDir = 0;
       score = 0;
       SpeedUp = 5;
    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {

        if (animateFirstTime) {
            animateFirstTime = false;
            if (Window.xsize != getSize().width || Window.ysize != getSize().height) {
                Window.xsize = getSize().width;
                Window.ysize = getSize().height;
            }

            reset();
        }
        if (currentRow == PowerupRow && currentColumn == PowerupColumn)
        {
            PowerupOn = true;
                    
            boolean KeepLoop2 = true;
       while (KeepLoop2)
       {
        PowerupRow = (int)(Math.random()*Board.numRows);
        PowerupColumn = (int)(Math.random()*Board.numColumns);
        if (Board.board[PowerupRow][PowerupColumn] == Board.EMPTY)
        {
        Board.board[PowerupRow][PowerupColumn] = Board.BAD_BOX;
        KeepLoop2 = false;
        }
        }        
        }
        if(gameover)
        {

        }
        else
        {
       if (timecount % (int)(frameRate) == (int)(frameRate) -1)
       {
           for (int zrow = 0;zrow < Board.numRows;zrow++)
        {
            for (int zcolumn = 0;zcolumn < Board.numColumns;zcolumn++)
            {
                if (Board.board[zrow][zcolumn] == Board.BAD_BOX)
                {
                    Board.board[zrow][zcolumn] = Board.EMPTY;
                }
            }
        }
       for (int c = 0  ; c<badBoxNum; c++ )
       {
       int row = 0;
       int column = 0;
       boolean KeepLoop = true;
       while (KeepLoop)
       {
        row = (int)(Math.random()*Board.numRows);
        column = (int)(Math.random()*Board.numColumns);
        if (Board.board[row][column] == Board.EMPTY)
        {
        Board.board[row][column] = Board.BAD_BOX;
        KeepLoop = false;
        }
        }
       }
       }
        }
       
       SpeedUp -= .1;
       if (SpeedUp < 1)
           SpeedUp = 1;
       
       if (timecount % (int)(SpeedUp) == (int)(SpeedUp) -1)
               {
       currentColumn += ColumnDir;
       currentRow += RowDir;
       if (currentRow >= Board.numRows )
       {
           currentRow = 0;
       }
       if (currentRow < 0 )
       {
           currentRow = Board.numRows - 1;
       }
       if ( currentColumn >= Board.numColumns)
       {
           currentColumn = 0;
       }
       if (currentColumn < 0)
       {
           currentColumn = Board.numColumns;
       }
       else if (Board.board[currentRow][currentColumn] == Board.SNAKE || Board.board[currentRow][currentColumn] == Board.BAD_BOX)
       {
           gameover = true;
       }
       else{
           Board.board[currentRow][currentColumn] = Board.SNAKE;
       }
       }
       if (gameover == false){
           score += 1;
           timecount++;
           if (score > highScore)
            highScore = score;}
    }

////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }
/////////////////////////////////////////////////////////////////////////
    
}
