package chess.GUI;

import javax.swing.*;
import java.awt.*;

public class GUILegend extends JFrame {

    public GUILegend(int x, int y) {
        super("Chess Legend!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(x - 300, y, 300, 700);
        setResizable(false);
        showLegend();
    }

    public void showLegend() {
        JPanel legend = new JPanel();
        legend.setLayout(new GridLayout(8, 2));
        for (int piecePicture = 0; piecePicture < 8; piecePicture++) {

        }
    }
}
