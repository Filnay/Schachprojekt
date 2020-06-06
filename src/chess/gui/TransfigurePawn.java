package chess.gui;

import chess.chesspiece.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransfigurePawn extends JFrame {

    public TransfigurePawn(String color, GUI gui) {
        super("choose new Figure");
        setVisible(true);
        setSize(500, 200);
        setLocationRelativeTo(null);
        offerFigures(color, gui);
    }

    public void offerFigures(String color, GUI gui) {

        JPanel offeredFigures = new JPanel();
        offeredFigures.setLayout(new GridLayout(0, 4));
        offeredFigures.setBackground(Color.WHITE);
        String url = "";



        Chessfield rook = new Chessfield(null);
        rook.setSize(70, 70);
        url = gui.getURLFromChessPiece(new Rook(ChessPiece.Color.BLACK));
        rook.setButtonIconTo(gui.getSkin().name + "/" + url);
        rook.setBackground(Color.WHITE);
        rook.setBorder(null);
        offeredFigures.add(rook);




        Chessfield knight = new Chessfield(null);
        knight.setSize(70, 70);
        url = gui.getURLFromChessPiece(new Knight(ChessPiece.Color.BLACK));
        knight.setButtonIconTo(gui.getSkin().name + "/" + url);
        knight.setBackground(Color.WHITE);
        knight.setBorder(null);
        offeredFigures.add(knight);




        Chessfield bishop = new Chessfield(null);
        bishop.setSize(70, 70);
        url = gui.getURLFromChessPiece(new Bishop(ChessPiece.Color.BLACK));
        bishop.setButtonIconTo(gui.getSkin().name + "/" + url);
        bishop.setBackground(Color.WHITE);
        bishop.setBorder(null);
        offeredFigures.add(bishop);


        Chessfield queen = new Chessfield(null);
        queen.setSize(70, 70);
        url = gui.getURLFromChessPiece(new Queen(ChessPiece.Color.BLACK));
        queen.setButtonIconTo(gui.getSkin().name + "/" + url);
        queen.setBackground(Color.WHITE);
        queen.setBorder(null);
        offeredFigures.add(queen);
        add(offeredFigures);
    }

    public static void main(String[] args) {
        new TransfigurePawn("White", new GUI());
    }
}
