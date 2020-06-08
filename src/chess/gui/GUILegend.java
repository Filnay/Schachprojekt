package chess.gui;
//import
import chess.chesspiece.*;
import javax.swing.*;
import java.awt.*;

public class GUILegend extends JFrame {

    //Constructor
    public GUILegend(int x, int y, int height, GUI gui) {
        super("Chess Legend");
        setBounds(x - 300, y, 300, height);
        setResizable(false);
        setVisible(false);
        setupLegend(gui);
    }

    //sets up the Legend
    public void setupLegend(GUI gui) {

        //Declaring and Initializing a new JPanel with a GridLayout
        JPanel legend = new JPanel();
        legend.setLayout(new GridLayout(6, 2));
        legend.setBackground(Color.WHITE);
        String url;


        //Creating a new ChessField-Button and loading the Image of an Pawn into it
        ChessField pawn = new ChessField(null);
        pawn.setSize(70, 70);
        url = gui.getURLFromChessPiece(new Pawn(ChessPiece.Color.BLACK));
        pawn.setButtonIconTo(gui.getSkin().name + "/" + url);
        pawn.setBackground(Color.WHITE);
        pawn.setBorder(null);
        legend.add(pawn);

        //Setting the Text next to the ChessField-Button to Pawn
        JLabel pawnText = new JLabel("Pawn");
        legend.add(pawnText);



        //Creating a new ChessField-Button and loading the Image of an Rook into it
        ChessField rook = new ChessField(null);
        rook.setSize(70, 70);
        url = gui.getURLFromChessPiece(new Rook(ChessPiece.Color.BLACK));
        rook.setButtonIconTo(gui.getSkin().name + "/" + url);
        rook.setBackground(Color.WHITE);
        rook.setBorder(null);
        legend.add(rook);

        //Setting the Text next to the ChessField-Button to Rook
        JLabel rookText = new JLabel("Rook");
        legend.add(rookText);



        //Creating a new ChessField-Button and loading the Image of an Knight into it
        ChessField knight = new ChessField(null);
        knight.setSize(70, 70);
        url = gui.getURLFromChessPiece(new Knight(ChessPiece.Color.BLACK));
        knight.setButtonIconTo(gui.getSkin().name + "/" + url);
        knight.setBackground(Color.WHITE);
        knight.setBorder(null);
        legend.add(knight);

        //Setting the Text next to the ChessField-Button to Knight
        JLabel knightText = new JLabel("Knight");
        legend.add(knightText);



        //Creating a new ChessField-Button and loading the Image of an Bishop into it
        ChessField bishop = new ChessField(null);
        bishop.setSize(70, 70);
        url = gui.getURLFromChessPiece(new Bishop(ChessPiece.Color.BLACK));
        bishop.setButtonIconTo(gui.getSkin().name + "/" + url);
        bishop.setBackground(Color.WHITE);
        bishop.setBorder(null);
        legend.add(bishop);

        //Setting the Text next to the ChessField-Button to Bishop
        JLabel bishopText = new JLabel("Bishop");
        legend.add(bishopText);



        //Creating a new ChessField-Button and loading the Image of an Queen into it
        ChessField queen = new ChessField(null);
        queen.setSize(70, 70);
        url = gui.getURLFromChessPiece(new Queen(ChessPiece.Color.BLACK));
        queen.setButtonIconTo(gui.getSkin().name + "/" + url);
        queen.setBackground(Color.WHITE);
        queen.setBorder(null);
        legend.add(queen);

        //Setting the Text next to the ChessField-Button to Queen
        JLabel queenText = new JLabel("Queen");
        legend.add(queenText);



        //Creating a new ChessField-Button and loading the Image of an King into it
        ChessField king = new ChessField(null);
        king.setSize(70, 70);
        url = gui.getURLFromChessPiece(new King(ChessPiece.Color.BLACK));
        king.setButtonIconTo(gui.getSkin().name + "/" + url);
        king.setBackground(Color.WHITE);
        king.setBorder(null);
        legend.add(king);

        //Setting the Text next to the ChessField-Button to King
        JLabel kingText = new JLabel("King");
        legend.add(kingText);

        add(legend);
    }

    //for Testing
//    public static void main(String[] args) {
//        new GUILegend(300, 300, 700, new GUI()).setVisible(true);
//    }
}
