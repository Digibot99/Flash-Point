package flashpoint;

import javax.swing.*;

public class SideWindow extends JFrame{

    static JButton button1 = new JButton();
    static SideWindow frame2;
    public static void main(String[] args)
    {
        frame2 = new SideWindow();
        frame2.setSize(WindowSide.WINDOW_WIDTH, WindowSide.WINDOW_HEIGHT);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setVisible(true);
    }

    public static int GetPlayerInput(Player _PlayerRed, Player _PlayerBlue, Player _PlayerGreen, Player _PlayerYellow) {
        button1.setVisible(false);
        frame2.getco

        if (_PlayerRed.getActionPoints() == 0 && _PlayerRed.getisTurn() == true) {
            button1.setVisible(false);
            button1.setLocation(WIDTH, WIDTH);
            button1.setSize(WIDTH, HEIGHT);
            button1.setText("hi");
            button1.setVisible(true);
        }

    }
}
