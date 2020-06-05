package chess.GUI;

import javax.swing.*;
import java.awt.*;

public class GUILegend extends JFrame {

    public GUILegend(int x, int y) {
        super("Chess Legend!");
        setBounds(x - 300, y, 300, 700);
        setResizable(false);
        setVisible(false);
        showLegend();
    }

    public void showLegend() {
        JPanel legend = new JPanel();
        legend.setLayout(new GridLayout(8, 2));
        JPanel pawn = new JPanel();
        pawn.setBackground(Color.BLACK);
        legend.add(pawn);
        legend.setVisible(true);
    }


//    public static void main(String[] args) {
//        new GUILegend(500, 500);
//    }
}
