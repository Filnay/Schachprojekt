package chess.gui;
//imports
import chess.chesspiece.ChessPiece;
import chess.gui.GUIControls;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.TreeMap;

public class SetupGame extends JFrame {

    public GUIControls guiControls;
    private ChessPiece.Color colorOfKi;

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
        JLabel chooseMode = new JLabel("<html><body>Do you want to play<br> against KI or other Player?</body></html>");
        JRadioButton otherPlayer = new JRadioButton("Other Player");
        JLabel switchColor = new JLabel("<html><body>Do you want to play<br>as Black or as White?</body></html>");
        JRadioButton white = new JRadioButton("White");
        JRadioButton black = new JRadioButton("Black");

        JRadioButton kI = new JRadioButton("KI");
        panel.setLayout(new GridLayout(3, 5, 10, 10));
        panel.setBorder(new LineBorder(Color.WHITE, 30));
        panel.setBackground(Color.WHITE);


        //Label te choose whether to play against KI or other Player

        chooseMode.setVisible(true);
        panel.add(chooseMode);

        //ButtonGroup, so you can only choose one of them
        ButtonGroup mode = new ButtonGroup();


        //Button to choose KI

        kI.setBackground(Color.WHITE);
        kI.setVisible(true);
        kI.addActionListener(e -> {
            switchColor.setVisible(true);
            black.setVisible(true);
            white.setVisible(true);
        });
        mode.add(kI);
        panel.add(kI);

        //Button to choose other Player
        otherPlayer.setBackground(Color.WHITE);
        otherPlayer.setVisible(true);
        otherPlayer.addActionListener(e -> {
            switchColor.setVisible(false);
            black.setVisible(false);
            white.setVisible(false);

        });
        mode.add(otherPlayer);
        panel.add(otherPlayer);

        //Spacer, so that the Elements are arranged properly
        JPanel spacer1 = new JPanel();
        spacer1.setBackground(Color.WHITE);
        panel.add(spacer1);

        switchColor.setVisible(false);
        panel.add(switchColor);

        //ButtonGroup, so you can only choose one of them
        ButtonGroup color = new ButtonGroup();

        white.setBackground(Color.WHITE);
        white.setVisible(false);
        color.add(white);
        panel.add(white);

        black.setBackground(Color.WHITE);
        black.setVisible(false);
        color.add(black);
        panel.add(black);

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

        Runnable runGuiControls = new Runnable() {
            @Override
            public void run() {
                if (kI.isSelected()) {
                    if(white.isSelected()) colorOfKi = ChessPiece.Color.BLACK;
                    if(black.isSelected()) colorOfKi = ChessPiece.Color.WHITE;
                    new GUIControls(colorOfKi);
                } else {
                    new GUIControls(null);
                }
            }
        };
        Thread startGuiControls = new Thread(runGuiControls);

        JButton startGame = new JButton("Start Game!");
        startGame.setBackground(Color.lightGray);
        startGame.addActionListener(e -> {
        startGuiControls.start();
        });
        startGame.setVisible(true);

        buttonPanel.add(startGame);
        panel.add(buttonPanel);

        panel.setVisible(true);
        add(panel);
    }
    private void createGUI(ChessPiece.Color kiColor){
        guiControls = new GUIControls(kiColor);
    }
    //for testing
    public static void main(String[] args) {
        new SetupGame(); }
}
