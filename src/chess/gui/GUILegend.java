package chess.gui;

import javax.swing.*;
import java.awt.*;

import static icons.Icons.getImageIcon;

public class GUILegend extends JFrame {

    public GUILegend(int x, int y, int height) {
        super("Chess Legend");
        setBounds(x - 300, y, 300, height);
        setResizable(false);
        setVisible(false);
        showLegend();
    }

    public void showLegend() {
        JPanel legend = new JPanel();
        legend.setLayout(new GridLayout(6, 2));
        legend.setBackground(Color.WHITE);

        Chessfield pawn = new Chessfield(null);
        pawn.setSize(70, 70);
        pawn.setButtonIconTo("Pawn_Black.png");
        pawn.setBackground(Color.WHITE);
        pawn.setBorder(null);
        legend.add(pawn);

        JLabel pawnText = new JLabel("Pawn");
        legend.add(pawnText);

        Chessfield rook = new Chessfield(null);
        rook.setSize(70, 70);
        rook.setButtonIconTo("Rook_Black.png");
        rook.setBackground(Color.WHITE);
        rook.setBorder(null);

        JLabel rookText = new JLabel("Rook");
        legend.add(rook);

        legend.add(rookText);
        Chessfield knight = new Chessfield(null);
        knight.setSize(70, 70);
        knight.setButtonIconTo("Knight_Black.png");
        knight.setBackground(Color.WHITE);
        knight.setBorder(null);
        legend.add(knight);

        JLabel knightText = new JLabel("Knight");
        legend.add(knightText);

        Chessfield bishop = new Chessfield(null);
        bishop.setSize(70, 70);
        bishop.setButtonIconTo("Bishop_Black.png");
        bishop.setBackground(Color.WHITE);
        bishop.setBorder(null);
        legend.add(bishop);

        JLabel bishopText = new JLabel("Bishop");
        legend.add(bishopText);

        Chessfield queen = new Chessfield(null);
        queen.setSize(70, 70);
        queen.setButtonIconTo("Queen_Black.png");
        queen.setBackground(Color.WHITE);
        queen.setBorder(null);
        legend.add(queen);

        JLabel queenText = new JLabel("Queen");
        legend.add(queenText);


        Chessfield king = new Chessfield(null);
        king.setSize(70, 70);
        king.setButtonIconTo("King_Black.png");
        king.setBackground(Color.WHITE);
        king.setBorder(null);
        legend.add(king);

        JLabel kingText = new JLabel("King");
        legend.add(kingText);

        add(legend);
        setVisible(false);
    }

    public static void main(String[] args) {
        new GUILegend(300, 300, 700);
    }
}
