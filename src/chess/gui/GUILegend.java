package chess.gui;
import chess.chesspiece.*;
import javax.swing.*;
import java.awt.*;

public class GUILegend extends JFrame {

    /*
    Creates a Legend on the Left side of the GUI, shows the Names and Symbols of all Chess Pieces.
    */
    
    //Constructor
    public GUILegend(GUI gui) {
        super("Chess Legend");
        setBounds(gui.getX() - 300, gui.getY(), 300, gui.getHeight());
        setResizable(false);
        setVisible(false);

        JPanel legend = new JPanel();
        legend.setLayout(new GridLayout(6, 2));
        legend.setBackground(Color.WHITE);
        String url;


        //Creates for each Chess Piece a new Button and loads the Image into it.
        //then shows the Name of the Chess Piece right to it.
        
        ChessField pawn = new ChessField(null);
        pawn.setSize(70, 70);
        url = gui.getURLFromChessPiece(new Pawn(ChessPiece.Color.BLACK));
        pawn.setButtonIconTo(gui.getSkin().name + "/" + url);
        pawn.setBackground(Color.WHITE);
        pawn.setBorder(null);
        legend.add(pawn);

        JLabel pawnText = new JLabel("Pawn");
        legend.add(pawnText);



        ChessField rook = new ChessField(null);
        rook.setSize(70, 70);
        url = gui.getURLFromChessPiece(new Rook(ChessPiece.Color.BLACK));
        rook.setButtonIconTo(gui.getSkin().name + "/" + url);
        rook.setBackground(Color.WHITE);
        rook.setBorder(null);
        legend.add(rook);

        JLabel rookText = new JLabel("Rook");
        legend.add(rookText);



        ChessField knight = new ChessField(null);
        knight.setSize(70, 70);
        url = gui.getURLFromChessPiece(new Knight(ChessPiece.Color.BLACK));
        knight.setButtonIconTo(gui.getSkin().name + "/" + url);
        knight.setBackground(Color.WHITE);
        knight.setBorder(null);
        legend.add(knight);

        JLabel knightText = new JLabel("Knight");
        legend.add(knightText);



        ChessField bishop = new ChessField(null);
        bishop.setSize(70, 70);
        url = gui.getURLFromChessPiece(new Bishop(ChessPiece.Color.BLACK));
        bishop.setButtonIconTo(gui.getSkin().name + "/" + url);
        bishop.setBackground(Color.WHITE);
        bishop.setBorder(null);
        legend.add(bishop);

        JLabel bishopText = new JLabel("Bishop");
        legend.add(bishopText);



        ChessField queen = new ChessField(null);
        queen.setSize(70, 70);
        url = gui.getURLFromChessPiece(new Queen(ChessPiece.Color.BLACK));
        queen.setButtonIconTo(gui.getSkin().name + "/" + url);
        queen.setBackground(Color.WHITE);
        queen.setBorder(null);
        legend.add(queen);

        JLabel queenText = new JLabel("Queen");
        legend.add(queenText);



        ChessField king = new ChessField(null);
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

    //for Testing, should show the GUI and the Legend of the currently in the GUI standardly selected skin.
    public static void main(String[] args) {
        new GUILegend(new GUI()).setVisible(true);
    }
}
