package flashpoint;

import java.io.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.geom.*;
import java.awt.event.*;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.*;
import java.util.ArrayList;

public class FlashPoint extends JFrame implements Runnable {

    boolean animateFirstTime = true;
    Image image;
    Graphics2D g;
    
    Image SmokeImage;
    Image SmokeImage1;
    Image SmokeImage2;
    Image SmokeImage3;
    Image SmokeImage4;
    
    
    Image FireImage;
    Image FireImage1;
    Image FireImage2;
    Image FireImage3;
    Image FireImage4;
    Image FireImage5;
    Image FireImage6;
    Image FireImage7;
    Image FireImage8;
    Image FireImage9;
    
    
    Image Player_Red;
    Image Player_Blue;
    Image Player_Yellow;
    Image Player_Green;
    
    
    private static BufferedReader in;
    private static PrintWriter out;
    
    Image BoardImage;
    private static final int PORT = 4000;
    
    int wallSize = 140;

    int smokeNum = 5;
    int fireNumAtStart = 5;
    int wallNum = 10;
    int playersNum = 4;
    int doorNum = 4;
    int timecount;
    boolean gameover;
    double frameRate = 1.0;
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

    public static void main(String[] args) throws Exception{
        frame1 = new FlashPoint();
        frame1.setSize(Window.WINDOW_WIDTH*2, Window.WINDOW_HEIGHT*2);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
//        frame1.setResizable(false);
        Server server = new Server();
Socket socket = new Socket(InetAddress.getLocalHost(), 4000);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
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
                    if (PlayerRed.getisTurn())
                    {
                        SetBoardandPlayer.PlayerMove(PlayerRed, PlayerBlue, SetBoardandPlayer.String.Right);
                        return;
                    }
                    if (PlayerBlue.getisTurn())
                    {
                    SetBoardandPlayer.PlayerMove(PlayerBlue, PlayerGreen, SetBoardandPlayer.String.Right);
                        return;
                    }
                    if (PlayerGreen.getisTurn())
                    {
                    SetBoardandPlayer.PlayerMove(PlayerGreen, PlayerYellow, SetBoardandPlayer.String.Right);
                        return;
                    }
                    if (PlayerYellow.getisTurn())
                    {
                    SetBoardandPlayer.PlayerMove(PlayerYellow, PlayerRed, SetBoardandPlayer.String.Right);
                        return;
                    }
                }

                if (e.VK_LEFT == e.getKeyCode()) {
                    if (PlayerRed.getisTurn())
                    {
                    SetBoardandPlayer.PlayerMove(PlayerRed, PlayerBlue, SetBoardandPlayer.String.Left);
                        return;
                    }
                    if (PlayerBlue.getisTurn())
                    {
                    SetBoardandPlayer.PlayerMove(PlayerBlue, PlayerGreen, SetBoardandPlayer.String.Left);
                        return;
                    }
                    if (PlayerGreen.getisTurn())
                    {
                    SetBoardandPlayer.PlayerMove(PlayerGreen, PlayerYellow, SetBoardandPlayer.String.Left);
                        return;
                    }
                    if (PlayerYellow.getisTurn())
                    {
                    SetBoardandPlayer.PlayerMove(PlayerYellow, PlayerRed, SetBoardandPlayer.String.Left);
                    return;
                    }
                }

                if (e.VK_UP == e.getKeyCode()) {
                    if (PlayerRed.getisTurn())
                    {
                    SetBoardandPlayer.PlayerMove(PlayerRed, PlayerBlue, SetBoardandPlayer.String.Up);
                        return;
                    }
                    if (PlayerBlue.getisTurn())
                    {
                    SetBoardandPlayer.PlayerMove(PlayerBlue, PlayerGreen, SetBoardandPlayer.String.Up);
                        return;
                    }
                    if (PlayerGreen.getisTurn())
                    {
                    SetBoardandPlayer.PlayerMove(PlayerGreen, PlayerYellow, SetBoardandPlayer.String.Up);
                        return;
                    }
                    if (PlayerYellow.getisTurn())
                    {
                    SetBoardandPlayer.PlayerMove(PlayerYellow, PlayerRed, SetBoardandPlayer.String.Up);
                    return;
                    }
                }

                if (e.VK_DOWN == e.getKeyCode()) {
                    if (PlayerRed.getisTurn())
                    {
                    SetBoardandPlayer.PlayerMove(PlayerRed, PlayerBlue, SetBoardandPlayer.String.Down);
                        return;
                    }
                    if (PlayerBlue.getisTurn())
                    {
                    SetBoardandPlayer.PlayerMove(PlayerBlue, PlayerGreen, SetBoardandPlayer.String.Down);
                        return;
                    }
                    if (PlayerGreen.getisTurn())
                    {
                    SetBoardandPlayer.PlayerMove(PlayerGreen, PlayerYellow, SetBoardandPlayer.String.Down);
                        return;
                    }
                    if (PlayerYellow.getisTurn())
                    {
                    SetBoardandPlayer.PlayerMove(PlayerYellow, PlayerRed, SetBoardandPlayer.String.Down);
                        return;
                    }
                }

                if (e.VK_ENTER == e.getKeyCode()) {
                    if (PlayerRed.getisTurn())
                    {
                    SetBoardandPlayer.PlayerSkip(PlayerRed, PlayerBlue);
                        return;
                    }
                    if (PlayerBlue.getisTurn())
                    {
                    SetBoardandPlayer.PlayerSkip(PlayerBlue, PlayerGreen);
                        return;
                    }
                    if (PlayerGreen.getisTurn())
                    {
                    SetBoardandPlayer.PlayerSkip(PlayerGreen, PlayerYellow);
                        return;
                    }
                    if (PlayerYellow.getisTurn())
                    {
                    SetBoardandPlayer.PlayerSkip(PlayerYellow, PlayerRed);
                        return;
                    }
                
                }

                if (e.VK_SPACE == e.getKeyCode()) {
                    for (Fire numFire1 : numFire) 
                    {
                        
                    if (PlayerRed.getisTurn())
                    {
                        if (Board.board[numFire1.getCurrentRow()][numFire1.getCurrentColumn()] == SetBoardandPlayer.PlayerInteract(PlayerRed) && numFire1.isNextToPlayer(PlayerRed))
                        {
                            Board.board[numFire1.getCurrentRow()][numFire1.getCurrentRow()] = Board.EMPTY;
                System.out.println(numFire1.getCurrentRow() + " " + numFire1.getCurrentColumn());
                            numFire.remove(numFire1);
                        }
                        return;
                    }
                    if (PlayerBlue.getisTurn())
                    {
                        if (Board.board[numFire1.getCurrentRow()][numFire1.getCurrentColumn()] == SetBoardandPlayer.PlayerInteract(PlayerBlue) && numFire1.isNextToPlayer(PlayerBlue))
                        {
                            Board.board[numFire1.getCurrentRow()][numFire1.getCurrentRow()] = Board.EMPTY;
                            numFire.remove(numFire1);
                        }
                        return;
                    }
                    if (PlayerGreen.getisTurn())
                    {
                        if (Board.board[numFire1.getCurrentRow()][numFire1.getCurrentColumn()] == SetBoardandPlayer.PlayerInteract(PlayerGreen) && numFire1.isNextToPlayer(PlayerGreen))
                        {
                            Board.board[numFire1.getCurrentRow()][numFire1.getCurrentRow()] = Board.EMPTY;
                            numFire.remove(numFire1);
                        }
                        return;
                    }
                    if (PlayerYellow.getisTurn())
                    {
                        if (Board.board[numFire1.getCurrentRow()][numFire1.getCurrentColumn()] == SetBoardandPlayer.PlayerInteract(PlayerYellow) && numFire1.isNextToPlayer(PlayerYellow))
                        {
                            Board.board[numFire1.getCurrentRow()][numFire1.getCurrentRow()] = Board.EMPTY;
                            numFire.remove(numFire1);
                        }
                        return;
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
            }
        }
//                  players
////////////////////////////////////////////////////////////////////////////////////
                g.setColor(PlayerRed.getColor());
                    g.drawImage(Player_Red,Window.getX(0) + PlayerRed.getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + PlayerRed.getCurrentRow() * Window.getHeight2() / Board.numRows,
                Window.getWidth2() / Board.numColumns,Window.getHeight2() / Board.numRows,this);

                g.setColor(PlayerBlue.getColor());
                    g.drawImage(Player_Blue,Window.getX(0) + PlayerBlue.getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + PlayerBlue.getCurrentRow() * Window.getHeight2() / Board.numRows,
                Window.getWidth2() / Board.numColumns,Window.getHeight2() / Board.numRows,this);

                g.setColor(PlayerGreen.getColor());
                    g.drawImage(Player_Green,Window.getX(0) + PlayerGreen.getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + PlayerGreen.getCurrentRow() * Window.getHeight2() / Board.numRows,
                Window.getWidth2() / Board.numColumns,Window.getHeight2() / Board.numRows,this);

                g.setColor(PlayerYellow.getColor());
                    g.drawImage(Player_Yellow,Window.getX(0) + PlayerYellow.getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + PlayerYellow.getCurrentRow() * Window.getHeight2() / Board.numRows,
                Window.getWidth2() / Board.numColumns,Window.getHeight2() / Board.numRows,this);
//                  inner doors
////////////////////////////////////////////////////////////////////////////////////
                g.setColor(Color.yellow);
                g.fillRect(Window.getX(0) + 8 * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + 2 * Window.getHeight2() / Board.numRows +wallSize,
                        Window.getWidth2() / Board.numColumns,
                        Window.getHeight2() / Board.numRows - wallSize);
                Board.board[2][8] = Board.DOOR;

                g.setColor(Color.yellow);
                g.fillRect(Window.getX(0) + 2 * Window.getWidth2() / Board.numColumns + wallSize,
                        Window.getY(0) + 3 * Window.getHeight2() / Board.numRows,
                        Window.getWidth2() / Board.numColumns - wallSize,
                        Window.getHeight2() / Board.numRows);

                g.setColor(Color.yellow);
                g.fillRect(Window.getX(0) + 3 * Window.getWidth2() / Board.numColumns + wallSize,
                        Window.getY(0) + 1 * Window.getHeight2() / Board.numRows,
                        Window.getWidth2() / Board.numColumns - wallSize,
                        Window.getHeight2() / Board.numRows);

                g.setColor(Color.yellow);
                g.fillRect(Window.getX(0) + 4 * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + 4 * Window.getHeight2() / Board.numRows + wallSize,
                        Window.getWidth2() / Board.numColumns,
                        Window.getHeight2() / Board.numRows - wallSize);

                g.setColor(Color.yellow);
                g.fillRect(Window.getX(0) + 5 * Window.getWidth2() / Board.numColumns + wallSize,
                        Window.getY(0) + 6 * Window.getHeight2() / Board.numRows,
                        Window.getWidth2() / Board.numColumns - wallSize,
                        Window.getHeight2() / Board.numRows);

                g.setColor(Color.yellow);
                g.fillRect(Window.getX(0) + 7 * Window.getWidth2() / Board.numColumns + wallSize,
                        Window.getY(0) + 6 * Window.getHeight2() / Board.numRows,
                        Window.getWidth2() / Board.numColumns - wallSize,
                        Window.getHeight2() / Board.numRows);

                g.setColor(Color.yellow);
                g.fillRect(Window.getX(0) + 6 * Window.getWidth2() / Board.numColumns + wallSize,
                        Window.getY(0) + 4 * Window.getHeight2() / Board.numRows,
                        Window.getWidth2() / Board.numColumns - wallSize,
                        Window.getHeight2() / Board.numRows);

                g.setColor(Color.yellow);
                g.fillRect(Window.getX(0) + 5 * Window.getWidth2() / Board.numColumns + wallSize,
                        Window.getY(0) + 2 * Window.getHeight2() / Board.numRows,
                        Window.getWidth2() / Board.numColumns - wallSize,
                        Window.getHeight2() / Board.numRows);
//                  inner walls
////////////////////////////////////////////////////////////////////////////////////
                g.setColor(Color.BLACK);
                g.fillRect(Window.getX(0) + 2 * Window.getWidth2() / Board.numColumns + wallSize,
                        Window.getY(0) + 4 * Window.getHeight2() / Board.numRows,
                        Window.getWidth2() / Board.numColumns - wallSize,
                        Window.getHeight2() / Board.numRows);

                g.setColor(Color.BLACK);
                g.fillRect(Window.getX(0) + 3 * Window.getWidth2() / Board.numColumns + wallSize,
                        Window.getY(0) + 2 * Window.getHeight2() / Board.numRows,
                        Window.getWidth2() / Board.numColumns - wallSize,
                        Window.getHeight2() / Board.numRows);

                g.setColor(Color.BLACK);
                g.fillRect(Window.getX(0) + 3 * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + 2 * Window.getHeight2() / Board.numRows + wallSize,
                        Window.getWidth2() / Board.numColumns,
                        Window.getHeight2() / Board.numRows - wallSize);

                g.setColor(Color.BLACK);
                g.fillRect(Window.getX(0) + 1 * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + 4 * Window.getHeight2() / Board.numRows + wallSize,
                        Window.getWidth2() / Board.numColumns,
                        Window.getHeight2() / Board.numRows - wallSize);

                g.setColor(Color.BLACK);
                g.fillRect(Window.getX(0) + 2 * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + 4 * Window.getHeight2() / Board.numRows + wallSize,
                        Window.getWidth2() / Board.numColumns,
                        Window.getHeight2() / Board.numRows - wallSize);

                g.setColor(Color.BLACK);
                g.fillRect(Window.getX(0) + 3 * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + 4 * Window.getHeight2() / Board.numRows + wallSize,
                        Window.getWidth2() / Board.numColumns,
                        Window.getHeight2() / Board.numRows - wallSize);

                g.setColor(Color.BLACK);
                g.fillRect(Window.getX(0) + 5 * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + 4 * Window.getHeight2() / Board.numRows + wallSize,
                        Window.getWidth2() / Board.numColumns,
                        Window.getHeight2() / Board.numRows - wallSize);

                g.setColor(Color.BLACK);
                g.fillRect(Window.getX(0) + 6 * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + 4 * Window.getHeight2() / Board.numRows + wallSize,
                        Window.getWidth2() / Board.numColumns,
                        Window.getHeight2() / Board.numRows - wallSize);

                g.setColor(Color.BLACK);
                g.fillRect(Window.getX(0) + 4 * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + 2 * Window.getHeight2() / Board.numRows + wallSize,
                        Window.getWidth2() / Board.numColumns,
                        Window.getHeight2() / Board.numRows - wallSize);

                g.setColor(Color.BLACK);
                g.fillRect(Window.getX(0) + 5 * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + 2 * Window.getHeight2() / Board.numRows + wallSize,
                        Window.getWidth2() / Board.numColumns,
                        Window.getHeight2() / Board.numRows - wallSize);

                g.setColor(Color.BLACK);
                g.fillRect(Window.getX(0) + 6 * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + 2 * Window.getHeight2() / Board.numRows + wallSize,
                        Window.getWidth2() / Board.numColumns,
                        Window.getHeight2() / Board.numRows - wallSize);

                g.setColor(Color.BLACK);
                g.fillRect(Window.getX(0) + 7 * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + 4 * Window.getHeight2() / Board.numRows + wallSize,
                        Window.getWidth2() / Board.numColumns,
                        Window.getHeight2() / Board.numRows - wallSize);

                g.setColor(Color.BLACK);
                g.fillRect(Window.getX(0) + 8 * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + 4 * Window.getHeight2() / Board.numRows + wallSize,
                        Window.getWidth2() / Board.numColumns,
                        Window.getHeight2() / Board.numRows - wallSize);

                g.setColor(Color.BLACK);
                g.fillRect(Window.getX(0) + 5 * Window.getWidth2() / Board.numColumns + wallSize,
                        Window.getY(0) + 5 * Window.getHeight2() / Board.numRows,
                        Window.getWidth2() / Board.numColumns - wallSize,
                        Window.getHeight2() / Board.numRows);

                g.setColor(Color.BLACK);
                g.fillRect(Window.getX(0) + 7 * Window.getWidth2() / Board.numColumns + wallSize,
                        Window.getY(0) + 5 * Window.getHeight2() / Board.numRows,
                        Window.getWidth2() / Board.numColumns - wallSize,
                        Window.getHeight2() / Board.numRows);

                g.setColor(Color.BLACK);
                g.fillRect(Window.getX(0) + 6 * Window.getWidth2() / Board.numColumns + wallSize,
                        Window.getY(0) + 3 * Window.getHeight2() / Board.numRows,
                        Window.getWidth2() / Board.numColumns - wallSize,
                        Window.getHeight2() / Board.numRows);

                g.setColor(Color.BLACK);
                g.fillRect(Window.getX(0) + 5 * Window.getWidth2() / Board.numColumns + wallSize,
                        Window.getY(0) + 1 * Window.getHeight2() / Board.numRows,
                        Window.getWidth2() / Board.numColumns - wallSize,
                        Window.getHeight2() / Board.numRows);

                g.setColor(Color.BLACK);
                g.fillRect(Window.getX(0) + 7 * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + 2 * Window.getHeight2() / Board.numRows + wallSize,
                        Window.getWidth2() / Board.numColumns,
                        Window.getHeight2() / Board.numRows - wallSize);

                g.setColor(Color.BLACK);
                g.fillRect(Window.getX(0) + 2 * Window.getWidth2() / Board.numColumns + wallSize,
                        Window.getY(0) + 2 * Window.getHeight2() / Board.numRows + wallSize,
                        Window.getWidth2() / Board.numColumns - wallSize,
                        Window.getHeight2() / Board.numRows - wallSize);
//              Upper half of wall
////////////////////////////////////////////////////////////////////////////////////
                for (int i = 1; i < Board.numColumns - 1; i++) {
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0) + i * Window.getWidth2() / Board.numColumns,
                            Window.getY(0) + 1 * Window.getHeight2() / Board.numRows,
                            Window.getWidth2() / Board.numColumns,
                            Window.getHeight2() / Board.numRows - wallSize);
                    Board.board[1][i] = Board.WALL;
                }
//              Lower half of wall
////////////////////////////////////////////////////////////////////////////////////
                for (int i = 1; i < Board.numColumns - 1; i++) {
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0) + i * Window.getWidth2() / Board.numColumns,
                            Window.getY(0) + (Board.numRows - 2) * Window.getHeight2() / Board.numRows + wallSize,
                            Window.getWidth2() / Board.numColumns,
                            Window.getHeight2() / Board.numRows - wallSize);
                    Board.board[Board.numRows - 2][i] = Board.WALL;
                }
//                    Right of house
//////////////////////////////////////////////////////////////////////////////////////
                for (int i = 1; i < Board.numRows - 1; i++) {
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0) + (Board.numColumns - 2) * Window.getWidth2() / Board.numColumns + wallSize,
                            Window.getY(0) + i * Window.getHeight2() / Board.numRows,
                            Window.getWidth2() / Board.numColumns - wallSize,
                            Window.getHeight2() / Board.numRows);
                    Board.board[i][Board.numColumns - 2] = Board.WALL;
                }
//                    left side of house
////////////////////////////////////////////////////////////////////////////////////
                for (int i = 1; i < Board.numRows - 1; i++) {
                    g.setColor(Color.BLACK);
                    g.fillRect(Window.getX(0) + 1 * Window.getWidth2() / Board.numColumns,
                            Window.getY(0) + i * Window.getHeight2() / Board.numRows,
                            Window.getWidth2() / Board.numColumns - wallSize,
                            Window.getHeight2() / Board.numRows);
                    Board.board[i][1] = Board.WALL;
                }
//              Point of intrests
////////////////////////////////////////////////////////////////////////////////////
                for (int i = 0; i < numPois.size(); i++) {
                    g.setColor(numPois.get(i).getColor());
                    g.fillRect(Window.getX(0) + numPois.get(i).getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                            Window.getY(0) + numPois.get(i).getCurrentRow() * Window.getHeight2() / Board.numRows,
                            Window.getWidth2() / Board.numColumns,
                            Window.getHeight2() / Board.numRows);
                }
//              Point of fire
////////////////////////////////////////////////////////////////////////////////////
                for (int i = 0; i < numFire.size(); i++) {
                    if (numFire.get(i).getGIFNum() == 1){
                    g.drawImage(FireImage,Window.getX(0) + numFire.get(i).getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + numFire.get(i).getCurrentRow() * Window.getHeight2() / Board.numRows,
                Window.getWidth2() / Board.numColumns,Window.getHeight2() / Board.numRows,this);}
                    if (numFire.get(i).getGIFNum() == 2){
                    g.drawImage(FireImage2,Window.getX(0) + numFire.get(i).getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + numFire.get(i).getCurrentRow() * Window.getHeight2() / Board.numRows,
                Window.getWidth2() / Board.numColumns,Window.getHeight2() / Board.numRows,this);}
                    if (numFire.get(i).getGIFNum() == 3){
                    g.drawImage(FireImage3,Window.getX(0) + numFire.get(i).getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + numFire.get(i).getCurrentRow() * Window.getHeight2() / Board.numRows,
                Window.getWidth2() / Board.numColumns,Window.getHeight2() / Board.numRows,this);}
                    if (numFire.get(i).getGIFNum() == 4){
                    g.drawImage(FireImage4,Window.getX(0) + numFire.get(i).getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + numFire.get(i).getCurrentRow() * Window.getHeight2() / Board.numRows,
                Window.getWidth2() / Board.numColumns,Window.getHeight2() / Board.numRows,this);}
                    if (numFire.get(i).getGIFNum() == 5){
                    g.drawImage(FireImage5,Window.getX(0) + numFire.get(i).getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + numFire.get(i).getCurrentRow() * Window.getHeight2() / Board.numRows,
                Window.getWidth2() / Board.numColumns,Window.getHeight2() / Board.numRows,this);}
                    if (numFire.get(i).getGIFNum() == 6){
                    g.drawImage(FireImage6,Window.getX(0) + numFire.get(i).getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + numFire.get(i).getCurrentRow() * Window.getHeight2() / Board.numRows,
                Window.getWidth2() / Board.numColumns,Window.getHeight2() / Board.numRows,this);}
                    if (numFire.get(i).getGIFNum() == 7){
                    g.drawImage(FireImage7,Window.getX(0) + numFire.get(i).getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + numFire.get(i).getCurrentRow() * Window.getHeight2() / Board.numRows,
                Window.getWidth2() / Board.numColumns,Window.getHeight2() / Board.numRows,this);}
                    if (numFire.get(i).getGIFNum() == 8){
                    g.drawImage(FireImage8,Window.getX(0) + numFire.get(i).getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + numFire.get(i).getCurrentRow() * Window.getHeight2() / Board.numRows,
                Window.getWidth2() / Board.numColumns,Window.getHeight2() / Board.numRows,this);}
                    if (numFire.get(i).getGIFNum() == 9){
                    g.drawImage(FireImage9,Window.getX(0) + numFire.get(i).getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + numFire.get(i).getCurrentRow() * Window.getHeight2() / Board.numRows,
                Window.getWidth2() / Board.numColumns,Window.getHeight2() / Board.numRows,this);}
                }
//              Point of smoke
////////////////////////////////////////////////////////////////////////////////////
                for (int i = 0; i < numSmoke.size(); i++) {
                    if (numSmoke.get(i).getGIFNum() == 1){
                    g.drawImage(SmokeImage,Window.getX(0) + numSmoke.get(i).getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + numSmoke.get(i).getCurrentRow() * Window.getHeight2() / Board.numRows,
                Window.getWidth2() / Board.numColumns,Window.getHeight2() / Board.numRows,this);}
                    if (numSmoke.get(i).getGIFNum() == 2){
                    g.drawImage(SmokeImage2,Window.getX(0) + numSmoke.get(i).getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + numSmoke.get(i).getCurrentRow() * Window.getHeight2() / Board.numRows,
                Window.getWidth2() / Board.numColumns,Window.getHeight2() / Board.numRows,this);}
                    if (numSmoke.get(i).getGIFNum() == 3){
                    g.drawImage(SmokeImage3,Window.getX(0) + numSmoke.get(i).getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + numSmoke.get(i).getCurrentRow() * Window.getHeight2() / Board.numRows,
                Window.getWidth2() / Board.numColumns,Window.getHeight2() / Board.numRows,this);}
                    if (numSmoke.get(i).getGIFNum() == 4){
                        g.drawImage(SmokeImage4,Window.getX(0) + numSmoke.get(i).getCurrentColumn() * Window.getWidth2() / Board.numColumns,
                        Window.getY(0) + numSmoke.get(i).getCurrentRow() * Window.getHeight2() / Board.numRows,
                        Window.getWidth2() / Board.numColumns,Window.getHeight2() / Board.numRows,this);}
                }
                

        if (PlayerRed.getisTurn()) {
            g.setColor(PlayerRed.getColor());
            g.drawString("Player Reds Turn  Number of turns left: " + PlayerRed.getActionPoints(), Window.getX(20), Window.getY(-10));
        } else if (PlayerBlue.getisTurn()) {
            g.setColor(Color.CYAN);
            g.drawString("Player Blues Turn  Number of turns left: " + PlayerBlue.getActionPoints(), Window.getX(20), Window.getY(-10));
        } else if (PlayerGreen.getisTurn()) {
            g.setColor(PlayerGreen.getColor());
            g.drawString("Player Greens Turn  Number of turns left: " + PlayerGreen.getActionPoints(), Window.getX(20), Window.getY(-10));
        } else if (PlayerYellow.getisTurn()) {
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
            double seconds = 1 / frameRate * 10;    //time that 1 frame takes.
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
        numPois.clear();
        numFire.clear();
        numSmoke.clear();
        for (int i = 0; i < POInum; i++) {
            numPois.add(i, new PointOfInterest());
        }
        for (int i = 0; i < fireNumAtStart; i++) {
            numFire.add(new Fire((int)(Math.random()*Board.numRows-2) + 1,(int)(Math.random()*Board.numColumns-2) + 1, (int)(Math.random()*9+1)));
        }
        numFire.get(fireNumAtStart - 1).equals(null);
        PlayerRed.setActionPoints();
        PlayerBlue.setActionPoints();
        PlayerGreen.setActionPoints();
        PlayerYellow.setActionPoints();
        PlayerRed.setisTurn(true);
        gameover = false;
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
        FireImage = Toolkit.getDefaultToolkit().getImage("./fire.GIF");
        SmokeImage = Toolkit.getDefaultToolkit().getImage("./smoke.GIF");
        FireImage1 = Toolkit.getDefaultToolkit().getImage("./fire_1.GIF");
        SmokeImage1 = Toolkit.getDefaultToolkit().getImage("./smoke_1.GIF");
        FireImage2 = Toolkit.getDefaultToolkit().getImage("./fire_2.GIF");
        SmokeImage2 = Toolkit.getDefaultToolkit().getImage("./smoke_2.GIF");
        FireImage3 = Toolkit.getDefaultToolkit().getImage("./fire_3.GIF");
        SmokeImage3 = Toolkit.getDefaultToolkit().getImage("./smoke_3.GIF");
        FireImage4 = Toolkit.getDefaultToolkit().getImage("./fire_4.GIF");
        SmokeImage4 = Toolkit.getDefaultToolkit().getImage("./smoke_4.GIF");
        FireImage5 = Toolkit.getDefaultToolkit().getImage("./fire_5.GIF");
        FireImage6 = Toolkit.getDefaultToolkit().getImage("./fire_6.GIF");
        FireImage7 = Toolkit.getDefaultToolkit().getImage("./fire_7.GIF");
        FireImage8 = Toolkit.getDefaultToolkit().getImage("./fire_8.GIF");
        FireImage9 = Toolkit.getDefaultToolkit().getImage("./fire_9.GIF");
        
        
        Player_Red = Toolkit.getDefaultToolkit().getImage("./Player_Red.png");
        Player_Blue = Toolkit.getDefaultToolkit().getImage("./Player_Blue.png");
        Player_Yellow = Toolkit.getDefaultToolkit().getImage("./Player_Yellow.png");
        Player_Green = Toolkit.getDefaultToolkit().getImage("./Player_Green.png");
        
        
        BoardImage = Toolkit.getDefaultToolkit().getImage("./board.jpg");

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

    public static void addAndCheckSmoke() {
        for (int i = 0; i < 1; i++) {
            numSmoke.add(new Smoke((int)(Math.random()* 4 + 1)));

            for (int j = 2; j < numSmoke.size(); j++) {
//                System.out.println("j = " + j);
                if (numSmoke.get(i).getCurrentColumn() == numSmoke.get(j - 1).getCurrentColumn()
                        && numSmoke.get(i).getCurrentRow() == numSmoke.get(j - 1).getCurrentRow()) {
                    addFire(numSmoke.get(j - 1).getCurrentRow(),numSmoke.get(j - 1).getCurrentColumn());
                    numSmoke.remove(i);
                    numSmoke.remove(j - 1);
        }
                }
            }
        }
    

    public static void addFire(int _currentRow, int _currentColumn) {
        for (int i = 0; i < 1; i++) {
            System.out.println(_currentRow + " " + _currentColumn);
            numFire.add(i, new Fire(_currentRow, _currentColumn, (int)(Math.random()*9+1)));
            System.out.println(numFire.size());
            return;
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
