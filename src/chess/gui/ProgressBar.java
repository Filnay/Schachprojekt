package chess.gui;

import chess.Board;
import chess.kI.IntelligentKI;
import javax.swing.*;
import java.awt.*;

public class ProgressBar extends JFrame {

    JLabel score = new JLabel("0");

    public ProgressBar(int x, int y, int width, int height) {
        super("Progress Bar");
        setBounds(x, y - 100, width, 100);
        setResizable(true);
        setVisible(true);
        updateScore(new Board());
    }


    public void updateScore(Board board) {
        int evaluate = IntelligentKI.evaluate(board);
        score.setText("" + evaluate + "");
        score.setVisible(true);
        score.setHorizontalAlignment(SwingConstants.CENTER);
        add(score);
    }

    public static void main(String[] args) {
        new ProgressBar(100, 200, 700, 400);
    }
}
