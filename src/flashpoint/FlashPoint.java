package flashpoint;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class FlashPoint extends JFrame implements Runnable {

    boolean animateFirstTime = true;
    Image image;
    Graphics2D g;
    
    int smokeNum = 5;
    int fireNum = 5;
    int wallNum = 10;
    int playersNum = 4;
    int doorNum = 4;
    int timecount;
    boolean gameover;
    double frameRate = 10.0;
    int POInum = 3;

    ArrayList<Wall> numWall = new ArrayList<Wall>();
    static ArrayList<Smoke> numSmoke = new ArrayList<Smoke>();
    static ArrayList<Fire> numFire = new ArrayList<Fire>();
    ArrayList<PointOfInterest> numPois = new ArrayList<PointOfInterest>();

    Player PlayerRed = new Player(Color.red);
    Player PlayerBlue = new Player(Color.cyan);
    Player PlayerYellow = new Player(Color.yellow);
    Player PlayerGreen = new Player(Color.green);

    static FlashPoint frame1;

    public static void main(String[] args) {
        frame1 = new FlashPoint();
        frame1.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
        frame1.setResizable(false);
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
                if (e.VK_RIGHT == e.getKeyCode()) {
                        SetBoardandPlayer.PlayerMove(PlayerRed,PlayerBlue,SetBoardandPlayer.String.Right);
                        SetBoardandPlayer.PlayerMove(PlayerBlue,PlayerGreen,SetBoardandPlayer.String.Right);
                        SetBoardandPlayer.PlayerMove(PlayerGreen,PlayerYellow,SetBoardandPlayer.String.Right);
                        SetBoardandPlayer.PlayerMove(PlayerYellow, PlayerRed,SetBoardandPlayer.String.Right);
                }

                if (e.VK_LEFT == e.getKeyCode()) {
                        SetBoardandPlayer.PlayerMove(PlayerRed,PlayerBlue,SetBoardandPlayer.String.Left);
                        SetBoardandPlayer.PlayerMove(PlayerBlue,PlayerGreen,SetBoardandPlayer.String.Left);
                        SetBoardandPlayer.PlayerMove(PlayerGreen,PlayerYellow,SetBoardandPlayer.String.Left);
                        SetBoardandPlayer.PlayerMove(PlayerYellow, PlayerRed,SetBoardandPlayer.String.Left);
                }

                if (e.VK_UP == e.getKeyCode()) {
                    
                        SetBoardandPlayer.PlayerMove(PlayerRed,PlayerBlue,SetBoardandPlayer.String.Up);
                        SetBoardandPlayer.PlayerMove(PlayerBlue,PlayerGreen,SetBoardandPlayer.String.Up);
                        SetBoardandPlayer.PlayerMove(PlayerGreen,PlayerYellow,SetBoardandPlayer.String.Up);
                        SetBoardandPlayer.PlayerMove(PlayerYellow, PlayerRed,SetBoardandPlayer.String.Up);
                }

                if (e.VK_DOWN == e.getKeyCode()) {
                        SetBoardandPlayer.PlayerMove(PlayerRed,PlayerBlue,SetBoardandPlayer.String.Down);
                        SetBoardandPlayer.PlayerMove(PlayerBlue,PlayerGreen,SetBoardandPlayer.String.Down);
                        SetBoardandPlayer.PlayerMove(PlayerGreen,PlayerYellow,SetBoardandPlayer.String.Down);
                        SetBoardandPlayer.PlayerMove(PlayerYellow, PlayerRed,SetBoardandPlayer.String.Down);
                }
                    
                if (e.VK_ENTER == e.getKeyCode()) {
                        SetBoardandPlayer.PlayerSkip(PlayerRed, PlayerBlue);
                        SetBoardandPlayer.PlayerSkip(PlayerBlue, PlayerGreen);
                        SetBoardandPlayer.PlayerSkip(PlayerGreen, PlayerYellow);
                        SetBoardandPlayer.PlayerSkip(PlayerYellow, PlayerRed);
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
        for (int zi = 1; zi < Board.numRows; zi++) {
            g.drawLine(Window.getX(0), Window.getY(0) + zi * Window.getHeight2() / Board.numRows,
                    Window.getX(Window.getWidth2()), Window.getY(0) + zi * Window.getHeight2() / Board.numRows);
        }
//vertical lines
        for (int zi = 1; zi < Board.numColumns; zi++) {
            g.drawLine(Window.getX(0) + zi * Window.getWidth2() / Board.numColumns, Window.getY(0),
                    Window.getX(0) + zi * Window.getWidth2() / Board.numColumns, Window.getY(Window.getHeight2()));
        }

//Display the objects of the board
        for (int zrow = 0; zrow < Board.numRows; zrow++) {
            for (int zcolumn = 0; zcolumn < Board.numColumns; zcolumn++) {
                if (Board.board[zrow][zcolumn] == Board.DOOR) {

                }
//              Point of intreasts
////////////////////////////////////////////////////////////////////////////////////
                for(int i = 0;i < numPois.size();i++)
        {
        g.setColor(numPois.get(i).getColor());
        g.fillRect(Window.getX(0) + numPois.get(i).getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                Window.getY(0) + numPois.get(i).getCurrentRow() * Window.getHeight2() / Board.numRows,
                Window.getWidth2() / Board.numColumns,
                Window.getHeight2() / Board.numRows);
        }
//              Point of intreasts
////////////////////////////////////////////////////////////////////////////////////
                for(int i = 0;i < numFire.size();i++)
        {
        g.setColor(numFire.get(i).getColor());
        g.fillRect(Window.getX(0) + numFire.get(i).getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                Window.getY(0) + numFire.get(i).getCurrentRow() * Window.getHeight2() / Board.numRows,
                Window.getWidth2() / Board.numColumns,
                Window.getHeight2() / Board.numRows);
        }
//              Point of intreasts
////////////////////////////////////////////////////////////////////////////////////
                for(int i = 0;i < numSmoke.size();i++)
        {
        g.setColor(numSmoke.get(i).getColor());
        g.fillRect(Window.getX(0) + numSmoke.get(i).getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                Window.getY(0) + numSmoke.get(i).getCurrentRow() * Window.getHeight2() / Board.numRows,
                Window.getWidth2() / Board.numColumns,
                Window.getHeight2() / Board.numRows);
        }
//                  players
////////////////////////////////////////////////////////////////////////////////////
                    g.setColor(PlayerRed.getColor());
                    g.fillRect(Window.getX(0)+PlayerRed.currentColumn*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+PlayerRed.currentRow*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows);
        
                    g.setColor(PlayerBlue.getColor());
                    g.fillRect(Window.getX(0)+PlayerBlue.currentColumn*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+PlayerBlue.currentRow*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows);
        
                    g.setColor(PlayerGreen.getColor());
                    g.fillRect(Window.getX(0)+PlayerGreen.currentColumn*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+PlayerGreen.currentRow*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows);
        
                    g.setColor(PlayerYellow.getColor());
                    g.fillRect(Window.getX(0)+PlayerYellow.currentColumn*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+PlayerYellow.currentRow*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows);
//                  inner doors
////////////////////////////////////////////////////////////////////////////////////
                    g.setColor(Color.yellow);
                    g.fillRect(Window.getX(0)+8*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+2*Window.getHeight2()/Board.numRows+70,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows-70);
                    Board.board[2][8] = Board.DOOR;
                    
                    g.setColor(Color.yellow);
                    g.fillRect(Window.getX(0)+2*Window.getWidth2()/Board.numColumns+70,
                    Window.getY(0)+3*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns-70,
                    Window.getHeight2()/Board.numRows);
                    
                    g.setColor(Color.yellow);
                    g.fillRect(Window.getX(0)+3*Window.getWidth2()/Board.numColumns+70,
                    Window.getY(0)+1*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns-70,
                    Window.getHeight2()/Board.numRows);
                    
                    g.setColor(Color.yellow);
                    g.fillRect(Window.getX(0)+4*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+4*Window.getHeight2()/Board.numRows+70,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows-70);
                    
                    g.setColor(Color.yellow);
                    g.fillRect(Window.getX(0)+5*Window.getWidth2()/Board.numColumns+70,
                    Window.getY(0)+6*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns-70,
                    Window.getHeight2()/Board.numRows);
                    
                    g.setColor(Color.yellow);
                    g.fillRect(Window.getX(0)+7*Window.getWidth2()/Board.numColumns+70,
                    Window.getY(0)+6*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns-70,
                    Window.getHeight2()/Board.numRows);
                    
                    g.setColor(Color.yellow);
                    g.fillRect(Window.getX(0)+6*Window.getWidth2()/Board.numColumns+70,
                    Window.getY(0)+4*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns-70,
                    Window.getHeight2()/Board.numRows);
                    
                    g.setColor(Color.yellow);
                    g.fillRect(Window.getX(0)+5*Window.getWidth2()/Board.numColumns+70,
                    Window.getY(0)+2*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns-70,
                    Window.getHeight2()/Board.numRows);
//                  inner walls
////////////////////////////////////////////////////////////////////////////////////
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0)+2*Window.getWidth2()/Board.numColumns+70,
                    Window.getY(0)+4*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns-70,
                    Window.getHeight2()/Board.numRows);
                    
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0)+3*Window.getWidth2()/Board.numColumns+70,
                    Window.getY(0)+2*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns-70,
                    Window.getHeight2()/Board.numRows);
                    
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0)+3*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+2*Window.getHeight2()/Board.numRows+70,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows-70);
                    
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0)+1*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+4*Window.getHeight2()/Board.numRows+70,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows-70);
                    
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0)+2*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+4*Window.getHeight2()/Board.numRows+70,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows-70);
                    
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0)+3*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+4*Window.getHeight2()/Board.numRows+70,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows-70);
                    
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0)+5*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+4*Window.getHeight2()/Board.numRows+70,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows-70);
                    
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0)+6*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+4*Window.getHeight2()/Board.numRows+70,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows-70);
                    
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0)+4*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+2*Window.getHeight2()/Board.numRows+70,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows-70);
                    
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0)+5*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+2*Window.getHeight2()/Board.numRows+70,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows-70);
                    
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0)+6*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+2*Window.getHeight2()/Board.numRows+70,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows-70);
                    
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0)+7*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+4*Window.getHeight2()/Board.numRows+70,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows-70);
                    
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0)+8*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+4*Window.getHeight2()/Board.numRows+70,
                    Window.getWidth2()/Board.numColumns,
                   Window.getHeight2()/Board.numRows-70);
                    
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0)+5*Window.getWidth2()/Board.numColumns+70,
                    Window.getY(0)+5*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns-70,
                    Window.getHeight2()/Board.numRows);
                    
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0)+7*Window.getWidth2()/Board.numColumns+70,
                    Window.getY(0)+5*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns-70,
                    Window.getHeight2()/Board.numRows);
                    
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0)+6*Window.getWidth2()/Board.numColumns+70,
                    Window.getY(0)+3*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns-70,
                    Window.getHeight2()/Board.numRows);
                    
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0)+5*Window.getWidth2()/Board.numColumns+70,
                    Window.getY(0)+1*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns-70,
                    Window.getHeight2()/Board.numRows);
                    
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0)+7*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+2*Window.getHeight2()/Board.numRows+70,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows-70);
                    
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0)+2*Window.getWidth2()/Board.numColumns+70,
                    Window.getY(0)+2*Window.getHeight2()/Board.numRows+70,
                    Window.getWidth2()/Board.numColumns-70,
                    Window.getHeight2()/Board.numRows-70);
//              Upper half of wall
////////////////////////////////////////////////////////////////////////////////////
                for (int i = 1; i < Board.numColumns - 1; i++) {
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0) + i * Window.getWidth2() / Board.numColumns,
                            Window.getY(0) + 1 * Window.getHeight2() / Board.numRows,
                            Window.getWidth2() / Board.numColumns,
                            Window.getHeight2() / Board.numRows - 70);
                    Board.board[1][i] = Board.WALL;
                }
//              Lower half of wall
////////////////////////////////////////////////////////////////////////////////////
                for (int i = 1; i < Board.numColumns - 1; i++) {
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0) + i * Window.getWidth2() / Board.numColumns,
                            Window.getY(0) + (Board.numRows - 2) * Window.getHeight2() / Board.numRows + 70,
                            Window.getWidth2() / Board.numColumns,
                            Window.getHeight2() / Board.numRows - 70);
                    Board.board[Board.numRows - 2][i] = Board.WALL;
                }
//                    Right of house
//////////////////////////////////////////////////////////////////////////////////////
                for (int i = 1; i < Board.numRows - 1; i++) {
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0) + (Board.numColumns - 2) * Window.getWidth2() / Board.numColumns + 70,
                            Window.getY(0) + i * Window.getHeight2() / Board.numRows,
                            Window.getWidth2() / Board.numColumns - 70,
                            Window.getHeight2() / Board.numRows);
                    Board.board[i][Board.numColumns - 2] = Board.WALL;
                }
//                    left side of house
////////////////////////////////////////////////////////////////////////////////////
                for (int i = 1; i < Board.numRows - 1; i++) {
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0) + 1 * Window.getWidth2() / Board.numColumns,
                            Window.getY(0) + i * Window.getHeight2() / Board.numRows,
                            Window.getWidth2() / Board.numColumns - 70,
                            Window.getHeight2() / Board.numRows);
                    Board.board[i][1] = Board.WALL;
                }
                
                
                
                if (Board.board[zrow][zcolumn] == Board.SMOKE) {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(Window.getX(0) + zcolumn * Window.getWidth2() / Board.numColumns,
                            Window.getY(0) + zrow * Window.getHeight2() / Board.numRows,
                            Window.getWidth2() / Board.numColumns,
                            Window.getHeight2() / Board.numRows);
                } 
            }
        }
        
        if (PlayerRed.getisTurn())
        {
            g.setColor(PlayerRed.getColor());
            g.drawString("Player Reds Turn  Number of turns left: " + PlayerRed.getActionPoints(), Window.getX(20), Window.getY(-10));
        }
        else if (PlayerBlue.getisTurn())
        {
            g.setColor(Color.CYAN);
            g.drawString("Player Blues Turn  Number of turns left: " + PlayerBlue.getActionPoints(), Window.getX(20), Window.getY(-10));
        }
        else if (PlayerGreen.getisTurn())
        {
            g.setColor(PlayerGreen.getColor());
            g.drawString("Player Greens Turn  Number of turns left: " + PlayerGreen.getActionPoints(), Window.getX(20), Window.getY(-10));
        }
        else if (PlayerYellow.getisTurn())
        {
            g.setColor(PlayerYellow.getColor());
            g.drawString("Player Yellows Turn  Number of turns left: " + PlayerYellow.getActionPoints(), Window.getX(20), Window.getY(-10));
        }
        
        if (gameover == true) {
            g.setColor(Color.black);
            g.drawString("Game Over", 250, 250);

        }
        gOld.drawImage(image, 0, 0, null);
    }
////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable

    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = 1 / frameRate;    //time that 1 frame takes.
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
        for (int zrow = 0; zrow < Board.numRows; zrow++) {
            for (int zcolumn = 0; zcolumn < Board.numColumns; zcolumn++) {
                Board.board[zrow][zcolumn] = Board.EMPTY;
            }
        }
        for (int c = 1; c < fireNum; c++) {
            int row = 0;
            int column = 0;
            boolean KeepLoop = true;
            while (KeepLoop) {
                row = (int) (Math.random() * Board.numRows);
                column = (int) (Math.random() * Board.numColumns);
                if (Board.board[row][column] == Board.EMPTY) {
//        Board.board[row][column] = Board.SMOKE;
                    KeepLoop = false;
                }
            }
        }
        for(int i = 0;i < POInum;i++)
        {
            numPois.add(i, new PointOfInterest());
        }
        for(int i = 0;i < 5;i++)
        {
            numFire.add(i, new Fire());
        }
        for(int i = 0;i < 1;i++)
        {
            numSmoke.add(i, new Smoke());
        }
        PlayerRed.setActionPoints();
        PlayerBlue.setActionPoints();
        PlayerGreen.setActionPoints();
        PlayerYellow.setActionPoints();
        PlayerRed.setisTurn(true);
        gameover = false;
        numPois.clear();
        numFire.clear();
        numSmoke.clear();
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
//        FireImage = Toolkit.getDefaultToolkit().getImage("./");
//        SmokeImage = Toolkit.getDefaultToolkit().getImage("./");
 
        if (gameover); else if (timecount % (int) (frameRate) == (int) (frameRate) - 1) {
            for (int zrow = 0; zrow < Board.numRows; zrow++) {
                for (int zcolumn = 0; zcolumn < Board.numColumns; zcolumn++) {
                }
            }
        }

        if (gameover == false) {
            timecount++;
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void addAndCheckSmoke ()
    {
        for(int i = 0;i < 1;i++)
        {
            numSmoke.add(i, new Smoke());

            for (int j = 1;j < numSmoke.size();j++)
            {
                if (numSmoke.get(i).getCurrentColumn() == numSmoke.get(j).getCurrentColumn() && 
                    numSmoke.get(i).getCurrentRow() == numSmoke.get(j).getCurrentRow())
                {
                    numSmoke.remove(i);
                    addFire();
                }
            }
        }
    }
    public static void addFire ()
    {
        for(int i = 0;i < 1;i++)
        {
                    numFire.add(i, new Fire());
                }
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
