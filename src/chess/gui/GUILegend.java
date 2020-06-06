package chess.gui;

import chess.chesspiece.*;

import javax.swing.*;
import java.awt.*;

public class GUILegend extends JFrame {

    public GUILegend(int x, int y, int height, GUI gui) {
        super("Chess Legend");
        setBounds(x - 300, y, 300, height);
        setResizable(false);
        setVisible(false);
        updateLegend(gui);
    }

    public void updateLegend(GUI gui) {
        JPanel legend = new JPanel();
        legend.setLayout(new GridLayout(6, 2));
        legend.setBackground(Color.WHITE);
        String url = "";


        Chessfield pawn = new Chessfield(null);
        pawn.setSize(70, 70);
        url = gui.getURLFromChessPiece(new Pawn(ChessPiece.Color.BLACK));
        pawn.setButtonIconTo(gui.getSkin().name + "/" + url);
        pawn.setBackground(Color.WHITE);
        pawn.setBorder(null);
        legend.add(pawn);

        JLabel pawnText = new JLabel("Pawn");
        legend.add(pawnText);



        Chessfield rook = new Chessfield(null);
        rook.setSize(70, 70);
        url = gui.getURLFromChessPiece(new Rook(ChessPiece.Color.BLACK));
        rook.setButtonIconTo(gui.getSkin().name + "/" + url);
        rook.setBackground(Color.WHITE);
        rook.setBorder(null);
        legend.add(rook);

        JLabel rookText = new JLabel("Rook");
        legend.add(rookText);



        Chessfield knight = new Chessfield(null);
        knight.setSize(70, 70);
        url = gui.getURLFromChessPiece(new Knight(ChessPiece.Color.BLACK));
        knight.setButtonIconTo(gui.getSkin().name + "/" + url);
        knight.setBackground(Color.WHITE);
        knight.setBorder(null);
        legend.add(knight);



        JLabel knightText = new JLabel("Knight");
        legend.add(knightText);

        Chessfield bishop = new Chessfield(null);
        bishop.setSize(70, 70);
        url = gui.getURLFromChessPiece(new Bishop(ChessPiece.Color.BLACK));
        bishop.setButtonIconTo(gui.getSkin().name + "/" + url);
        bishop.setBackground(Color.WHITE);
        bishop.setBorder(null);
        legend.add(bishop);

        JLabel bishopText = new JLabel("Bishop");
        legend.add(bishopText);



        Chessfield queen = new Chessfield(null);
        queen.setSize(70, 70);
        url = gui.getURLFromChessPiece(new Queen(ChessPiece.Color.BLACK));
        queen.setButtonIconTo(gui.getSkin().name + "/" + url);
        queen.setBackground(Color.WHITE);
        queen.setBorder(null);
        legend.add(queen);

        JLabel queenText = new JLabel("Queen");
        legend.add(queenText);



        Chessfield king = new Chessfield(null);
        king.setSize(70, 70);
        url = gui.getURLFromChessPiece(new King(ChessPiece.Color.BLACK));
        king.setButtonIconTo(gui.getSkin().name + "/" + url);
        king.setBackground(Color.WHITE);
        king.setBorder(null);
        legend.add(king);

        JLabel kingText = new JLabel("King");
        legend.add(kingText);



        add(legend);
    }

    public static void main(String[] args) {
//        new GUILegend(300, 300, 700).setVisible(true);
    }
}
