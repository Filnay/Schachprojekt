package chess.gui;

import javax.swing.*;
import java.awt.*;

public class GameEnd extends JFrame {
    public GameEnd(String status) {
        super("Game Over!");
        setVisible(true);
        setSize(300, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        JLabel label = new JLabel(status, SwingConstants.CENTER);
        label.setVisible(true);
        panel.add(label);
        panel.setVisible(true);
        add(panel);
    }


    public static void main(String[] args) {
        new GameEnd("Black Wins!");
    }
}
