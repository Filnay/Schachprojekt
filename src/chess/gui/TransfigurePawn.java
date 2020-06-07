package chess.gui;
//imports
import chess.Field;
import chess.chesspiece.*;
import javax.swing.*;
import java.awt.*;

public class TransfigurePawn extends JFrame {

    //Constructor
    public TransfigurePawn(ChessPiece.Color color, GUI gui, Field field) {
        super("choose new Figure");
        setVisible(true);
        setSize(500, 200);
        setLocationRelativeTo(null);
        offerFigures(color, gui, field);
    }

    //offer possible Figures: Rook, Queen, Knight and Bishop
    public void offerFigures(ChessPiece.Color color, GUI gui, Field field) {

        //setting up the Panel with a GidLayout
        JPanel offeredFigures = new JPanel();
        offeredFigures.setLayout(new GridLayout(0, 5));
        offeredFigures.setBackground(Color.WHITE);
        final String[] url = {""};
        final ChessPiece[] chosenChessPiece = new ChessPiece[1];


        //load the Image of the Rook into the first ChessField-Button
        Chessfield rook = new Chessfield(null);
        rook.setSize(70, 70);
        url[0] = gui.getURLFromChessPiece(new Rook(color));
        rook.setButtonIconTo(gui.getSkin().name + "/" + url[0]);
        rook.setBackground(Color.WHITE);
        rook.setBorder(null);
        rook.addActionListener(e -> chosenChessPiece[0] = new Rook(color));
        offeredFigures.add(rook);



        //load the Image of the Knight into the second ChessField-Button
        Chessfield knight = new Chessfield(null);
        knight.setSize(70, 70);
        url[0] = gui.getURLFromChessPiece(new Knight(color));
        knight.setButtonIconTo(gui.getSkin().name + "/" + url[0]);
        knight.setBackground(Color.WHITE);
        knight.setBorder(null);
        knight.addActionListener(e -> chosenChessPiece[0] = new Knight(color));
        offeredFigures.add(knight);



        //load the Image of the Bishop into the third ChessField-Button
        Chessfield bishop = new Chessfield(null);
        bishop.setSize(70, 70);
        url[0] = gui.getURLFromChessPiece(new Bishop(color));
        bishop.setButtonIconTo(gui.getSkin().name + "/" + url[0]);
        bishop.setBackground(Color.WHITE);
        bishop.setBorder(null);
        bishop.addActionListener(e -> chosenChessPiece[0] = new Bishop(color));
        offeredFigures.add(bishop);


        //load the Image of the Queen into the forth ChessField-Button
        Chessfield queen = new Chessfield(null);
        queen.setSize(70, 70);
        url[0] = gui.getURLFromChessPiece(new Queen(color));
        queen.setButtonIconTo(gui.getSkin().name + "/" + url[0]);
        queen.setBackground(Color.WHITE);
        queen.setBorder(null);
        queen.addActionListener(e -> chosenChessPiece[0] = new Queen(color));
        offeredFigures.add(queen);

        //Setup the Submit-Button
        JButton submit = new JButton("Choose");
        submit.setVisible(true);
        submit.setSize(70, 70);
        submit.setBackground(Color.lightGray);
        submit.setBorder(null);
        submit.addActionListener(e -> {
            if (chosenChessPiece[0] != null) {
                gui.getBoard().putChessPieceOn(field.row, field.column, chosenChessPiece[0]);
                gui.updateBoard();
                dispose();
            }
        });
        offeredFigures.add(submit);
        add(offeredFigures);
    }

    //for Testing
//    public static void main(String[] args) {
//        new TransfigurePawn(ChessPiece.Color.WHITE, new GUI(), new Field(6, 5));
//    }
}
