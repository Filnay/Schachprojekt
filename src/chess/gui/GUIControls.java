package chess.gui;
//imports
import chess.Board;
import chess.Field;
import chess.chesspiece.ChessPiece;
import chess.kI.IntelligentKI;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GUIControls extends JFrame {
    //Initialize all Buttons
    JButton undo = new JButton("Undo");
    JButton reset = new JButton("Reset");
    JButton toggleLegend = new JButton("Show Legend");
    JButton changeSkin = new JButton("ChangeSkin");
    JButton toggleScore = new JButton("Toggle Score");
    JButton close = new JButton("Exit Game");

    //Initialize Panel
    JPanel controlPanel = new JPanel();

    //Declares legend; only one that needs to be declared here because otherwise the Changing of the Skins wouldn't affect the legend/toggling wound't work
    GUILegend legends;
    private int legendCount;

    //Constructor
    public GUIControls(ChessPiece.Color kIColor) {
        super("Chess Controls");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setupControls(kIColor);
    }

    //get and Set-Methods, only needed for Legend

    //Setup for the ControlPanel
    public void setupControls(ChessPiece.Color kIColor) {

        //declaration of the chessGUI
        GUI chessGUI;

        //calling of the GUI-Constructor whether you want to play against a KI or an other Player
        if (kIColor != null) {
            chessGUI = new GUI(kIColor);
        } else {
            chessGUI = new GUI();
        }
        reset(chessGUI);

        //get the Position of the GUI Window to arrange the Controls to the right of the GUI
        int guiX = chessGUI.getX();
        int guiWidth = chessGUI.getWidth();
        int guiY = chessGUI.getY();
        int guiHeight = chessGUI.getHeight();
        setLocation(guiX + guiWidth, guiY);
        setSize(300, guiHeight);

        /*Declarations and Initializations*/
        //Legend-Window, arranged to the left to the GUI
        legends = new GUILegend(chessGUI.getX(), chessGUI.getY(), chessGUI.getHeight(), chessGUI);
        //ExitGame-Window
        GUIExitGame exit = new GUIExitGame();
        //ChangeSkin-Window
        GUIChangeSkin changeChessPieceSkin = new GUIChangeSkin(chessGUI, this);

        //ControlPanel
        controlPanel.setBorder(new LineBorder(Color.WHITE, 30));
        GridLayout controlPanelLayout = new GridLayout(6, 1, 20, 20);
        controlPanel.setLayout(controlPanelLayout);
        controlPanel.setBackground(Color.WHITE);


        /*Settings for the Buttons*/
        //All Buttons that open a new Window are Toggle Buttons, so they toggle the Windows

        //Undo-Button
        undo.setBackground(Color.lightGray);
        undo.setBorder(null);
        undo.addActionListener(e -> chessGUI.undoMove());
        controlPanel.add(undo);

        //Reset-Button: only one more complex, needs to set an New KI with the color of the last one
        reset.setBackground(Color.lightGray);
        reset.setBorder(null);
        reset.addActionListener(e -> {
            reset(chessGUI);
        });
        controlPanel.add(reset);

        //Legend-Button
        toggleLegend.setBackground(Color.lightGray);
        toggleLegend.setBorder(null);
        toggleLegend.addActionListener(e -> {
            if (legendCount == 0) {
                setNewLegend(chessGUI, true);
                legendCount = 1;
            } else if (legendCount == 1) {
                setNewLegend(chessGUI, false);
                legendCount = 0;

            }
        });
        controlPanel.add(toggleLegend);

        //Score-Button
        toggleScore.setBackground(Color.lightGray);
        toggleScore.setBorder(null);
        chessGUI.getProgressBar().setVisible(true);
        toggleScore.addActionListener(e -> {
            JFrame progressBar = chessGUI.getProgressBar();
            progressBar.setVisible(!progressBar.isVisible());
        });
        controlPanel.add(toggleScore);

        //ChangeSkin-Button
        changeSkin.setBackground(Color.lightGray);
        changeSkin.setBorder(null);

        changeSkin.addActionListener(e -> changeChessPieceSkin.setVisible(!changeChessPieceSkin.isVisible()));
        controlPanel.add(changeSkin);

        //Close-Button
        close.setBackground(Color.lightGray);
        close.setBorder(null);
        close.addActionListener(e -> exit.setVisible(!exit.isVisible()));
        controlPanel.add(close);

        controlPanel.setVisible(true);
        add(controlPanel);
        setVisible(true);
    }

    public void setLegendCount(int count) {
        legendCount = count;
    }

    public void setNewLegend(GUI chessGUI, boolean state) {
        legends.dispose();
        legends = new GUILegend(chessGUI.getX(), chessGUI.getY(), chessGUI.getHeight(), chessGUI);
        legends.setVisible(state);
    }

    private void reset(GUI chessGUI) {
        Board newBoard = new Board();
        chessGUI.setBoard(newBoard);
        chessGUI.setPlayerStatus(ChessPiece.Color.WHITE);
        chessGUI.updateBoard();
        chessGUI.setUndoCounter(0);
        if (chessGUI.getKi() != null) {
            if (chessGUI.getKi().getColor().equals(ChessPiece.Color.WHITE)) {
                chessGUI.getKi().move();
                chessGUI.setPlayerStatus(ChessPiece.Color.BLACK);
                chessGUI.updateBoard();
            }
        }
    }

    //current StartPosition, SetupGame doesn't work
    public static void main(String[] args) {
        ChessPiece.Color color = ChessPiece.Color.WHITE;
        if (args.length > 0 && args[0].toLowerCase().startsWith("b")) color = ChessPiece.Color.BLACK;
        new GUIControls(color);
    }
}
