package chess.gui;
//imports
import chess.chesspiece.ChessPiece;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SetupGame extends JFrame {

    //Constructor
    public SetupGame() {
        super("Chess Setup");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setupGUI();
    }

    //Creating a new JFrame to choose whether you want to play against an KI or another player, and what color you want to play
    public void setupGUI() {

        //Declaration and Initialization of a Panel with a GridLayout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 5, 10, 10));
        panel.setBorder(new LineBorder(Color.WHITE, 30));
        panel.setBackground(Color.WHITE);


        //Label te choose whether to play against KI or other Player
        JLabel chooseMode = new JLabel("<html><body>Do you want to play<br> against KI or other Player?</body></html>");
        chooseMode.setVisible(true);
        panel.add(chooseMode);

        //ButtonGroup, so you can only choose one of them
        ButtonGroup mode = new ButtonGroup();

        //Button to choose KI
        JRadioButton kI = new JRadioButton("KI");
        kI.setBackground(Color.WHITE);
        kI.setVisible(true);
        mode.add(kI);
        panel.add(kI);

        //Button to choose other Player
        JRadioButton otherPlayer = new JRadioButton("Other Player");
        otherPlayer.setBackground(Color.WHITE);
        otherPlayer.setVisible(true);
        mode.add(otherPlayer);
        panel.add(otherPlayer);

        //Spacer, so that the Elements are arranged properly
        JPanel spacer1 = new JPanel();
        spacer1.setBackground(Color.WHITE);
        panel.add(spacer1);


        //Label, whether you want to play as Black or as White
        JLabel switchColor = new JLabel("<html><body>Do you want to play<br>as Black or as White?</body></html>");
        switchColor.setVisible(true);
        panel.add(switchColor);

        //ButtonGroup, so you can only choose one of them
        ButtonGroup color = new ButtonGroup();

        //Button to choose White
        JRadioButton White = new JRadioButton("White");
        White.setBackground(Color.WHITE);
        White.setVisible(true);
        color.add(White);
        panel.add(White);

        //Button to choose Black
        JRadioButton Black = new JRadioButton("Black");
        Black.setBackground(Color.WHITE);
        Black.setVisible(true);
        color.add(Black);
        panel.add(Black);

        //More Spacers
        JPanel spacer2 = new JPanel();
        spacer2.setBackground(Color.WHITE);
        panel.add(spacer2);

        JPanel spacer3 = new JPanel();
        spacer3.setBackground(Color.WHITE);
        panel.add(spacer3);

        JPanel spacer4 = new JPanel();
        spacer4.setBackground(Color.WHITE);
        panel.add(spacer4);

        JPanel spacer5 = new JPanel();
        spacer5.setBackground(Color.WHITE);
        panel.add(spacer5);


        //Button to start the game
        //Doesnt Work //TODO
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);

        JButton startGame = new JButton("Start Game!");
        startGame.setBackground(Color.lightGray);

        startGame.addActionListener(e -> {
//                ChessPiece.Color color;
//                boolean gameMode;
//
//                if (Black.isSelected()) {
//                    color = ChessPiece.Color.BLACK;
//                } else {
//                    color = ChessPiece.Color.WHITE;
//                }
//
//                gameMode = kI.isSelected();
            dispose();
            new GUIControls(false, ChessPiece.Color.WHITE);
        });

        startGame.setVisible(true);

        buttonPanel.add(startGame);
        panel.add(buttonPanel);

        panel.setVisible(true);
        add(panel);
    }

    //for testing
//    public static void main(String[] args) {
//        new SetupGame();
//    }
}
