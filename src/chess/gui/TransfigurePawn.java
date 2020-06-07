package chess.gui;

import chess.Field;
import chess.chesspiece.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransfigurePawn extends JFrame {

    public TransfigurePawn(ChessPiece.Color color, GUI gui, Field field) {
        super("choose new Figure");
        setVisible(true);
        setSize(500, 200);
        setLocationRelativeTo(null);
        offerFigures(color, gui, field);
    }

    public void offerFigures(ChessPiece.Color color, GUI gui, Field field) {

        JPanel offeredFigures = new JPanel();
        offeredFigures.setLayout(new GridLayout(0, 5));
        offeredFigures.setBackground(Color.WHITE);
        final String[] url = {""};
        final ChessPiece[] chosenChessPiece = new ChessPiece[1];



        Chessfield rook = new Chessfield(null);
        rook.setSize(70, 70);
        url[0] = gui.getURLFromChessPiece(new Rook(color));
        rook.setButtonIconTo(gui.getSkin().name + "/" + url[0]);
        rook.setBackground(Color.WHITE);
        rook.setBorder(null);
        rook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 chosenChessPiece[0] = new Rook(color);
            }
        });
        offeredFigures.add(rook);




        Chessfield knight = new Chessfield(null);
        knight.setSize(70, 70);
        url[0] = gui.getURLFromChessPiece(new Knight(color));
        knight.setButtonIconTo(gui.getSkin().name + "/" + url[0]);
        knight.setBackground(Color.WHITE);
        knight.setBorder(null);
        knight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosenChessPiece[0] = new Knight(color);
            }
        });
        offeredFigures.add(knight);




        Chessfield bishop = new Chessfield(null);
        bishop.setSize(70, 70);
        url[0] = gui.getURLFromChessPiece(new Bishop(color));
        bishop.setButtonIconTo(gui.getSkin().name + "/" + url[0]);
        bishop.setBackground(Color.WHITE);
        bishop.setBorder(null);
        bishop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosenChessPiece[0] = new Bishop(color);
            }
        });
        offeredFigures.add(bishop);


        Chessfield queen = new Chessfield(null);
        queen.setSize(70, 70);
        url[0] = gui.getURLFromChessPiece(new Queen(color));
        queen.setButtonIconTo(gui.getSkin().name + "/" + url[0]);
        queen.setBackground(Color.WHITE);
        queen.setBorder(null);
        queen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosenChessPiece[0] = new Queen(color);
            }
        });
        offeredFigures.add(queen);

        JButton submit = new JButton("Choose");
        submit.setVisible(true);
        submit.setSize(70, 70);
        submit.setBackground(Color.lightGray);
        submit.setBorder(null);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chosenChessPiece[0] != null) {
                    gui.getBoard().putChessPieceOn(field.row, field.column, chosenChessPiece[0]);
                    gui.updateBoard();
                    dispose();
                }
            }
        });
        offeredFigures.add(submit);
        add(offeredFigures);
    }

    public static void main(String[] args) {
        new TransfigurePawn(ChessPiece.Color.WHITE, new GUI(), new Field(6, 5));
    }
}
