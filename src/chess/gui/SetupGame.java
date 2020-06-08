package chess.gui;
import chess.chesspiece.ChessPiece;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SetupGame extends JFrame {
    
    /*
    Creating a new JFrame to choose whether you want to play against an KI or another player, and what color you want to play,
    the KI plays the other color. Starts the game.
    */
    
    private ChessPiece.Color colorOfKi;
    
    //Constructor
    public SetupGame() {
        super("Chess Setup");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setupGUI();
    }
    
    
    public void setupGUI() {
        JPanel panel = new JPanel();
        JLabel chooseMode = new JLabel("<html>Do you want to play against KI or other Player?</html>");
        JRadioButton otherPlayer = new JRadioButton("Other Player");
        JRadioButton kI = new JRadioButton("KI");
        JLabel switchColor = new JLabel("<html>Do you want to play as Black or as White?</html>");
        JRadioButton white = new JRadioButton("White");
        JRadioButton black = new JRadioButton("Black");
        JLabel note = new JLabel("<html>The KI isn't fast. Please be patient while it is calculating how to beat you.</html>");
        JLabel label1 = new JLabel("The KI will Play as:");
        JLabel labelColor = new JLabel();

        
        panel.setLayout(new GridLayout(3, 5, 10, 10));
        panel.setBorder(new LineBorder(Color.WHITE, 30));
        panel.setBackground(Color.WHITE);

        chooseMode.setVisible(true);
        panel.add(chooseMode);

        ButtonGroup mode = new ButtonGroup();
        

        kI.setBackground(Color.WHITE);
        kI.setVisible(true);
        kI.addActionListener(e -> {
            switchColor.setVisible(true);
            black.setVisible(true);
            white.setVisible(true);
            note.setVisible(true);
            label1.setVisible(true);
        });
        mode.add(kI);
        panel.add(kI);

        otherPlayer.setBackground(Color.WHITE);
        otherPlayer.setVisible(true);
        otherPlayer.addActionListener(e -> {
            switchColor.setVisible(false);
            black.setVisible(false);
            white.setVisible(false);
            note.setVisible(false);
            label1.setVisible(false);
            labelColor.setVisible(false);
        });
        mode.add(otherPlayer);
        panel.add(otherPlayer);

        //Spacer, so that the Elements are arranged properly
        JPanel spacer1 = new JPanel();
        spacer1.setBackground(Color.WHITE);
        panel.add(spacer1);

        switchColor.setVisible(false);
        panel.add(switchColor);

        ButtonGroup color = new ButtonGroup();

        white.setBackground(Color.WHITE);
        white.setVisible(false);
        white.addActionListener(e -> {
            labelColor.setVisible(true);
            labelColor.setText("Black");
        });
        color.add(white);
        panel.add(white);

        black.setBackground(Color.WHITE);
        black.setVisible(false);
        black.addActionListener(e -> {
            labelColor.setVisible(true);
            labelColor.setText("White");
        });
        color.add(black);
        panel.add(black);
        
        note.setBackground(Color.WHITE);
        note.setForeground(Color.RED);
        note.setVisible(false);
        panel.add(note);
        
        
        label1.setAlignmentX(RIGHT_ALIGNMENT);
        label1.setBackground(Color.WHITE);
        label1.setVisible(false);
        panel.add(label1);
        
        labelColor.setVisible(false);
        panel.add(labelColor);

        JPanel spacer2 = new JPanel();
        spacer2.setBackground(Color.WHITE);
        panel.add(spacer2);


        //Button to start the game
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);

        Runnable runGuiControls = () -> {
            if (kI.isSelected()) {
                if(white.isSelected()) colorOfKi = ChessPiece.Color.BLACK;
                if(black.isSelected()) colorOfKi = ChessPiece.Color.WHITE;
                dispose();
                new GUIControls(colorOfKi);
            } else {
                dispose();
                new GUIControls(null);
            }
        };
        Thread startGuiControls = new Thread(runGuiControls);

        JButton startGame = new JButton("Start Game!");
        startGame.setBackground(Color.lightGray);
        startGame.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        startGame.addActionListener(e -> startGuiControls.start());
        startGame.setVisible(true);

        buttonPanel.add(startGame);
        panel.add(buttonPanel);

        panel.setVisible(true);
        add(panel);
    }
    
    //for testing
    
    /*
    Start Point of the Game.
    */
    
    public static void main(String[] args) {
        new SetupGame();
    }
}
