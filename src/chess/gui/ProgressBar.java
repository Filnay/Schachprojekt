package chess.gui;

import javax.swing.*;
import java.awt.*;

public class ProgressBar extends JFrame {
    public ProgressBar(int x, int y, int width, int height) {
        super("Progress Bar");
        setBounds(x, y - 100, width, 100);
        setResizable(true);
        setVisible(true);
        setupProgressBar();
    }

    public void setupProgressBar() {
        JPanel progressBar = new JPanel();
        progressBar.setBounds(10, 10, this.getWidth() - 20, this.getHeight() - 20);
        progressBar.setVisible(true);
        progressBar.setBackground(Color.BLACK);
        add(progressBar);
    }

    public static void main(String[] args) {
        new ProgressBar(100, 200, 700, 400);
    }
}
