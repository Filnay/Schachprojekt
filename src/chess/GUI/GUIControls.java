package chess.GUI;

import chess.Board;
import chess.chesspiece.ChessPiece;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIControls extends JFrame {
    GUI chessGUI = new GUI();
    int toggleCounter = 0;

    JButton reset = new JButton("Reset");
    JButton showLegend = new JButton("Show Legend");
    JButton changeSkin = new JButton("ChangeSkin");
    JButton undo = new JButton("Undo");

    public GUIControls() {
        super("Chess Controls!");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int guiX = chessGUI.getX();
        int guiWidth = chessGUI.getWidth();
        int guiY = chessGUI.getY();
        setLocation(guiX + guiWidth, guiY);
        setSize(300, 700);
        setResizable(false);
        setupControls();
    }

//    public void GUILegend() {
//        super("Chess Legend!");
//        setVisible(true);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setSize(300, 700);
//        setResizable(false);
//    }

    public void setupControls() {
        JPanel controlPanel = new JPanel();
        JFrame legends = new GUILegend(chessGUI.getX(), chessGUI.getY());
        controlPanel.setBorder(new LineBorder(Color.WHITE, 30));
        GridLayout controlPanelLayout = new GridLayout(4, 1, 20, 20);
        controlPanel.setLayout(controlPanelLayout);
        controlPanel.setBackground(Color.WHITE);



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

        showLegend.setBackground(Color.lightGray);
        showLegend.setBorder(null);
        showLegend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (toggleCounter == 0) {
                    legends.setVisible(true);
                    toggleCounter = 1;
                }
                else if (toggleCounter == 1) {
                    legends.setVisible(false);
                    toggleCounter = 0;
                }
            }
        });
        controlPanel.add(showLegend);

        changeSkin.setBackground(Color.lightGray);
        changeSkin.setBorder(null);
        controlPanel.add(changeSkin);

        undo.setBackground(Color.lightGray);
        undo.setBorder(null);
        controlPanel.add(undo);

        controlPanel.setVisible(true);
        add(controlPanel);
    }

    public static void main(String[] args) {
        new GUIControls();
    }
}
