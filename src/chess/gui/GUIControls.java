package chess.gui;

import chess.Board;
import chess.chesspiece.ChessPiece;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIControls extends JFrame {
    GUI chessGUI = new GUI();

    JButton reset = new JButton("Reset");
    JButton showLegend = new JButton("Show Legend");
    JButton changeSkin = new JButton("ChangeSkin");
    JButton undo = new JButton("Undo");
    JButton close = new JButton("Exit Game");
    JButton showScore = new JButton("Show Score");

    public GUIControls() {
        super("Chess Controls");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int guiX = chessGUI.getX();
        int guiWidth = chessGUI.getWidth();
        int guiY = chessGUI.getY();
        int guiHeight = chessGUI.getHeight();
        setLocation(guiX + guiWidth, guiY);
        setSize(300, guiHeight);
        setResizable(false);
        setupControls();
    }

    public void setupControls() {
        JPanel controlPanel = new JPanel();
        JFrame legends = new GUILegend(chessGUI.getX(), chessGUI.getY(), chessGUI.getHeight());
        JFrame exit = new GUIExitGame();
        JFrame changeChessPieceSkin = new GUIChangeSkin();
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
                chessGUI.setBoard(new Board());
                chessGUI.setPlayerStatus(ChessPiece.Color.WHITE);
                chessGUI.updateBoard();
            }
        });
        controlPanel.add(reset);

        showScore.setBackground(Color.lightGray);
        showScore.setBorder(null);
        showScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        controlPanel.add(showScore);

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
        new GUIControls();
    }
}
