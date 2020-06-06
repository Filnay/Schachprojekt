package chess.gui;

import chess.Board;
import chess.kI.IntelligentKI;
import javax.swing.*;
import java.awt.*;

public class ProgressBar extends JFrame {

    JLabel score = new JLabel("0");
    GUI chessGui;

    public ProgressBar(int x, int y, int width, int height, GUI gui) {
        super("Progress Bar");
        chessGui = gui;
        setBounds(x, y - 100, width, 100);
        setResizable(true);
        setVisible(true);
        updateScore();
    }

    public void updateScore() {
        int evaluate = IntelligentKI.evaluate(chessGui.getBoard());
        score.setText("" + evaluate + "");
        score.setVisible(true);
        score.setHorizontalAlignment(SwingConstants.CENTER);
        add(score);
    }

    public static void main(String[] args) {
        new ProgressBar(100, 200, 700, 400, new GUI());
    }
}
