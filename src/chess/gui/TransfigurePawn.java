package chess.gui;
import chess.Field;
import chess.chesspiece.*;
import javax.swing.*;
import java.awt.*;

public class TransfigurePawn extends JFrame {
    
    /*
    Called, when there is a Pawn to be transfigured. Offers 4 Chess Pieces to transfigure
    your Pawn into and places them on the Position of the Pawn.
     */
    
    
    /*
    Attributes
     */
    private boolean transfigured = false;
    
    GUI gui;
    Field field;
    ChessPiece.Color color;

    //Constructor
    public TransfigurePawn(ChessPiece.Color color, GUI gui, Field field) {
        super("choose new Figure");
        setVisible(true);
        setSize(500, 200);
        setLocationRelativeTo(null);
        this.field = field;
        this.gui = gui;
        this.color = color;
        offerFigures();
    }
    
    public boolean getTransfigured() {
        return transfigured;
    }
    
    public void setTransfigured(boolean transfigured) {
        this.transfigured = transfigured;
    }
    
    public void transfigureBackToPawn() {
        gui.getBoard().putChessPieceOn(field.row, field.column, new Pawn(color));
    }
    
    //offer possible Figures: Rook, Queen, Knight and Bishop
    public void offerFigures() {
        
        JPanel offeredFigures = new JPanel();
        offeredFigures.setLayout(new GridLayout(0, 5));
        offeredFigures.setBackground(Color.WHITE);
        final String[] url = {""};
        final ChessPiece[] chosenChessPiece = new ChessPiece[1];
        
        
        ChessField rook = new ChessField(null);
        rook.setSize(70, 70);
        url[0] = gui.getURLFromChessPiece(new Rook(color));
        rook.setButtonIconTo(gui.getSkin().name + "/" + url[0]);
        rook.setBackground(Color.WHITE);
        rook.setBorder(null);
        rook.addActionListener(e -> chosenChessPiece[0] = new Rook(color));
        offeredFigures.add(rook);


        ChessField knight = new ChessField(null);
        knight.setSize(70, 70);
        url[0] = gui.getURLFromChessPiece(new Knight(color));
        knight.setButtonIconTo(gui.getSkin().name + "/" + url[0]);
        knight.setBackground(Color.WHITE);
        knight.setBorder(null);
        knight.addActionListener(e -> chosenChessPiece[0] = new Knight(color));
        offeredFigures.add(knight);

        
        ChessField bishop = new ChessField(null);
        bishop.setSize(70, 70);
        url[0] = gui.getURLFromChessPiece(new Bishop(color));
        bishop.setButtonIconTo(gui.getSkin().name + "/" + url[0]);
        bishop.setBackground(Color.WHITE);
        bishop.setBorder(null);
        bishop.addActionListener(e -> chosenChessPiece[0] = new Bishop(color));
        offeredFigures.add(bishop);

        
        ChessField queen = new ChessField(null);
        queen.setSize(70, 70);
        url[0] = gui.getURLFromChessPiece(new Queen(color));
        queen.setButtonIconTo(gui.getSkin().name + "/" + url[0]);
        queen.setBackground(Color.WHITE);
        queen.setBorder(null);
        queen.addActionListener(e -> chosenChessPiece[0] = new Queen(color));
        offeredFigures.add(queen);

        JButton submit = new JButton("Choose");
        submit.setVisible(true);
        submit.setSize(70, 70);
        submit.setBackground(Color.lightGray);
        submit.setBorder(null);
        submit.addActionListener(e -> {
            if (chosenChessPiece[0] != null) {
                gui.getBoard().putChessPieceOn(field.row, field.column, chosenChessPiece[0]);
                transfigured = true;
                gui.updateBoard();
                dispose();
            }
        });
        offeredFigures.add(submit);
        add(offeredFigures);
    }

    //for Testing, should place the selected Figure on the Position of the enemy Queen
    public static void main(String[] args) {
        new TransfigurePawn(ChessPiece.Color.WHITE, new GUI(), new Field(7, 3));
    }
}
