package chess.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


public class GUIChangeSkin extends JFrame {
    private final Chessfield[][] fields = new Chessfield[2][2];
    
    public GUIChangeSkin() {
        super("Chess Skins!");
        setVisible(true);
        setSize(700, 290);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(Color.WHITE, 30));
        panel.setLayout(new GridLayout(1, 3, 30, 30));

//        Chessfield skin1 = new Chessfield();


        panel.setVisible(true);
        add(panel);
    }

    public static void main(String[] args) {
        new GUIChangeSkin();
    }
}
