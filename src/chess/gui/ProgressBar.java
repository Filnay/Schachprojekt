package chess.gui;
import chess.Board;
import chess.kI.IntelligentKI;
import javax.swing.*;
import java.awt.*;

public class ProgressBar extends JFrame {

    /*
    Called by the GUI. Sets up a Progressbar, that indicates whether Black or White is in the better Position.
    */
    
    
    /*
    Attributes
    */
    
    JProgressBar progressBar;
    int evaluate;

    //Constructor
    public ProgressBar(int x, int y, int width) {
        super("Progress Bar");
        setBounds(x, y - 100, width, 100);
        setResizable(true);
        setVisible(true);
        setScore(new Board());
    }

    public void setScore(Board board) {
        evaluate = IntelligentKI.evaluate(board);
        progressBar = new JProgressBar(0, 2000);
        progressBar.setVisible(true);
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.BLACK);
        add(progressBar);
        updateScoreBar(board);
    }

    public void updateScoreBar(Board board) {
        evaluate = IntelligentKI.evaluate(board);
        progressBar.setString(String.valueOf(evaluate));
        progressBar.setValue(evaluate + 1000);
    }
}
