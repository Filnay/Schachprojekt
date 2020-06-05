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
    int legendCounter = 0;
    public int skinCounter = 0;

    JButton reset = new JButton("Reset");
    JButton showLegend = new JButton("Show Legend");
    JButton changeSkin = new JButton("ChangeSkin");
    JButton undo = new JButton("Undo");
    JButton close = new JButton(("Exit Game"));

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

    public int getSkinCounter() {
        return skinCounter;
    }

    public void setupControls() {
        JPanel controlPanel = new JPanel();
        JFrame legends = new GUILegend(chessGUI.getX(), chessGUI.getY());
        controlPanel.setBorder(new LineBorder(Color.WHITE, 30));
        GridLayout controlPanelLayout = new GridLayout(5, 1, 20, 20);
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

        showLegend.setBackground(Color.lightGray);
        showLegend.setBorder(null);
        showLegend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (legendCounter == 0) {
                    legends.setVisible(true);
                    legendCounter = 1;
                }
                else if (legendCounter == 1) {
                    legends.setVisible(false);
                    legendCounter = 0;
                }
            }
        });
        controlPanel.add(showLegend);

        changeSkin.setBackground(Color.lightGray);
        changeSkin.setBorder(null);

        changeSkin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (skinCounter == 0) {
                    skinCounter = 1;
                }
                else if (skinCounter == 1) {
                    skinCounter = 2;
                }
                else if (skinCounter == 2) {
                    skinCounter = 0;
                }
            }
        });
        controlPanel.add(changeSkin);

        close.setBackground(Color.lightGray);
        close.setBorder(null);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ExitGame();
            }
        });
        controlPanel.add(close);

        controlPanel.setVisible(true);
        add(controlPanel);
    }

    public static void main(String[] args) {
        new GUIControls();
    }
}
