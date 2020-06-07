package chess.gui;

import chess.Board;
import chess.Field;
import chess.chesspiece.ChessPiece;
import chess.kI.IntelligentKI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIControls extends JFrame {

    JButton undo = new JButton("Undo");
    JButton reset = new JButton("Reset");
    JButton showLegend = new JButton("Show Legend");
    JButton changeSkin = new JButton("ChangeSkin");
    JButton toggleScore = new JButton("Toggle Score");
    JButton close = new JButton("Exit Game");

    JPanel controlPanel = new JPanel();

    GUILegend legends;


    public GUIControls(boolean gameMode, ChessPiece.Color kIColor) {
        super("Chess Controls");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setupControls(gameMode, kIColor);
    }


    public void setNewLegend(GUI chessGUI) {
        legends.dispose();
        legends = new GUILegend(chessGUI.getX(), chessGUI.getY(), chessGUI.getHeight(), chessGUI);
        legends.setVisible(true);
    }


    public void setupControls(boolean gameMode, ChessPiece.Color kIColor) {
        GUI chessGUI;

        if (gameMode) {
            chessGUI = new GUI(kIColor);
        }
        else{
            chessGUI = new GUI();
        }

        int guiX = chessGUI.getX();
        int guiWidth = chessGUI.getWidth();
        int guiY = chessGUI.getY();
        int guiHeight = chessGUI.getHeight();
        setLocation(guiX + guiWidth, guiY);
        setSize(300, guiHeight);

        GUILegend legends = new GUILegend(chessGUI.getX(), chessGUI.getY(), chessGUI.getHeight(), chessGUI);
        GUIExitGame exit = new GUIExitGame();
        GUIChangeSkin changeChessPieceSkin = new GUIChangeSkin(chessGUI, legends, this);

        controlPanel.setBorder(new LineBorder(Color.WHITE, 30));
        GridLayout controlPanelLayout = new GridLayout(6, 1, 20, 20);
        controlPanel.setLayout(controlPanelLayout);
        controlPanel.setBackground(Color.WHITE);


        undo.setBackground(Color.lightGray);
        undo.setBorder(null);
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chessGUI.undoMove();
            }
        });
        controlPanel.add(undo);

        reset.setBackground(Color.lightGray);
        reset.setBorder(null);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Board newBoard = new Board();
                chessGUI.setBoard(newBoard);
                chessGUI.setPlayerStatus(ChessPiece.Color.WHITE);
                chessGUI.updateBoard();
                chessGUI.setUndoCounter(0);
                if(chessGUI.getKi() != null ){
                    chessGUI.setKi(new IntelligentKI(newBoard, chessGUI.getKi().getColor()));
                    if (chessGUI.getKi().getColor().equals(ChessPiece.Color.WHITE)) {
                        chessGUI.processMove(new Field(4, 1), new Field(4, 3));
                        chessGUI.setPlayerStatus(ChessPiece.Color.BLACK);
                    }
                }
            }
        });
        controlPanel.add(reset);

        showLegend.setBackground(Color.lightGray);
        showLegend.setBorder(null);
        showLegend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!legends.isVisible()) {
                    legends.setVisible(true);
                } else if (legends.isVisible()) {
                    legends.setVisible(false);
                }
            }
        });
        controlPanel.add(showLegend);

        toggleScore.setBackground(Color.lightGray);
        toggleScore.setBorder(null);
        toggleScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame progressBar = chessGUI.getProgressBar();
                if (!progressBar.isVisible()) {
                    progressBar.setVisible(true);
                } else if (progressBar.isVisible()) {
                    progressBar.setVisible(false);
                }
            }
        });
        controlPanel.add(toggleScore);



        changeSkin.setBackground(Color.lightGray);
        changeSkin.setBorder(null);

        changeSkin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (changeChessPieceSkin.isVisible()) {
                    changeChessPieceSkin.setVisible(false);
                } else if (!changeChessPieceSkin.isVisible()) {
                    changeChessPieceSkin.setVisible(true);
                }
            }
        });
        controlPanel.add(changeSkin);

        close.setBackground(Color.lightGray);
        close.setBorder(null);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (exit.isVisible()) {
                    exit.setVisible(false);
                } else {
                    exit.setVisible(true);
                }
            }
        });
        controlPanel.add(close);


        controlPanel.setVisible(true);
        add(controlPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GUIControls(true, ChessPiece.Color.BLACK);
    }
}
