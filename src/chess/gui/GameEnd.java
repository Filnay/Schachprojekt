package chess.gui;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameEnd extends JFrame {

    /*
    is Called when one King is Checkmate, or when there is a stalemate.
    Indicates the Winner and gives the possibility to play again or to exit the Program.
    */
    
    /*
    you cant play your second game against an KI, that throws a Error
    */
    
    
    //Constructor
    public GameEnd(String status) {
        super("Game Over!");
        setVisible(true);
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridLayout(2, 3, 10, 10));
        panel.setBorder(new LineBorder(Color.WHITE, 20));

        //Label that contains the Message, e.G. that Black won
        JLabel label = new JLabel(status, SwingConstants.CENTER);
        label.setBackground(Color.WHITE);
        label.setVisible(true);
        panel.add(label);

        //Spacers so the arrangement of the Elements is good
        JPanel spacer1  = new JPanel();
        spacer1.setBackground(Color.WHITE);
        panel.add(spacer1);

        JPanel spacer2  = new JPanel();
        spacer2.setBackground(Color.WHITE);
        panel.add(spacer2);

        JPanel spacer3  = new JPanel();
        spacer3.setBackground(Color.WHITE);
        panel.add(spacer3);

        //Button that creates a new SetupGame-Window when clicked
        //doesnt Work //TODO
        JButton restart = new JButton("Play Again");
        restart.setBackground(Color.lightGray);
        restart.setBorder(null);
        restart.addActionListener(e -> {
            Frame[] frames = Frame.getFrames();
            for (Frame frame : frames) {
                frame.dispose();
            }
            new SetupGame();
        });
        restart.setVisible(true);
        panel.add(restart);

        //Button that exits the Program
        JButton close = new JButton("Close Game");
        close.setBackground(Color.lightGray);
        restart.setBorder(null);
        close.addActionListener(e -> System.exit(0));
        close.setVisible(true);
        panel.add(close);

        panel.setVisible(true);
        add(panel);
    }

    //for Testing, works fine when started from here, but not if the GUI has already been Initialized once.
    public static void main(String[] args) {
        new GameEnd("Black Wins!");
    }
}
