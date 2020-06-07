package chess.gui;
//imports
import chess.Board;
import chess.kI.IntelligentKI;
import javax.swing.*;
import java.awt.*;

public class ProgressBar extends JFrame {

    //Declaration of a new JProgressBar
    JProgressBar progressBar;

    //Declaration of a new int, saves the value oft the ChessField-State
    int evaluate;

    //Constructor
    public ProgressBar(int x, int y, int width, Board board) {
        super("Progress Bar");
        setBounds(x, y - 100, width, 100);
        setResizable(true);
        setVisible(true);
        setScore(board);
    }

    //setup of the ProgressBar
    public void setScore(Board board) {
        evaluate = IntelligentKI.evaluate(board);
        progressBar = new JProgressBar(0, 2000);
        progressBar.setVisible(true);
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.BLACK);
        add(progressBar);
        updateScoreBar(board);
    }

    //updating of the ProgressBar
    public void updateScoreBar(Board board) {
        evaluate = IntelligentKI.evaluate(board);
        progressBar.setString(String.valueOf(evaluate));
        progressBar.setValue(evaluate + 1000);
    }

    //for Testing
//    public static void main(String[] args) {
//        new ProgressBar(100, 200, 700, new Board());
//    }
}
