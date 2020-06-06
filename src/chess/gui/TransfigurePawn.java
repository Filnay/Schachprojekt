package chess.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransfigurePawn extends JFrame {
    public TransfigurePawn(String color) {
        super("choose new Figure");
        setVisible(true);
        setSize(500, 400);
        setLocationRelativeTo(null);
        offerFigures(color);
    }

    public void offerFigures(String color) {
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 3, 30, 20));
        panel.setBorder(new LineBorder(Color.WHITE, 20));
        panel.setBackground(Color.WHITE);

        JPanel figurePanel1 = new JPanel();
        figurePanel1.setSize(this.getWidth() / 3, this.getHeight()/ 3);

        Chessfield figure1 = new Chessfield(null);
        figure1.setSize(100, 100);
        figure1.setButtonIconTo("Queen_" + color.toString() + ".png");
        figure1.setBackground(Color.lightGray);
        figure1.setBorder(null);

        figure1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        figurePanel1.setVisible(true);
        figurePanel1.add(figure1);
        panel.add(figurePanel1);

        JPanel figurePanel2 = new JPanel();
        figurePanel2.setSize(100, 100);

        Chessfield figure2 = new Chessfield(null);
        figure2.setSize(100, 100);
        figure2.setButtonIconTo("Knight_" + color.toString() + ".png");
        figure2.setBackground(Color.lightGray);
        figure2.setBorder(null);

        figure2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        figurePanel2.setVisible(true);
        figurePanel2.add(figure2);
        panel.add(figurePanel2);

        JPanel figurePanel3 = new JPanel();
        figurePanel3.setSize(100, 100);

        Chessfield figure3 = new Chessfield(null);
        figure3.setSize(100, 100);
        figure3.setButtonIconTo("Rook_" + color.toString() + ".png");
        figure3.setBackground(Color.lightGray);
        figure3.setBorder(null);

        figure3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        figurePanel3.setVisible(true);
        figurePanel3.add(figure3);
        panel.add(figurePanel3);


        JPanel figurePanel4 = new JPanel();
        figurePanel4.setSize(100, 100);

        Chessfield figure4 = new Chessfield(null);
        figure4.setSize(100, 100);
        figure4.setButtonIconTo("Pawn_" + color.toString() + ".png");
        figure4.setBackground(Color.lightGray);
        figure4.setBorder(null);

        figure4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        figurePanel4.setVisible(true);
        figurePanel4.add(figure4);
        panel.add(figurePanel4);
        panel.setVisible(true);
    }

    public static void main(String[] args) {
        new TransfigurePawn("White");
    }
}
