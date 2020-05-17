package chess;

import chess.chesspiece.*;

import java.util.ArrayList;

import static chess.chesspiece.ChessPiece.Color.BLACK;
import static chess.chesspiece.ChessPiece.Color.WHITE;

public class Board {
    private final ChessPiece[] a = new ChessPiece[8];
    private final ChessPiece[] b = new ChessPiece[8];
    private final ChessPiece[] c = new ChessPiece[8];
    private final ChessPiece[] d = new ChessPiece[8];
    private final ChessPiece[] e = new ChessPiece[8];
    private final ChessPiece[] f = new ChessPiece[8];
    private final ChessPiece[] g = new ChessPiece[8];
    private final ChessPiece[] h = new ChessPiece[8];
    public final ChessPiece[][] board = new ChessPiece[][]{a,b,c,d,e,f,g,h};

    public Board(){
        setUpBoard();
    }

    private void setUpBoard(){
        putChessPieceOn(0, 0, new Rook(WHITE));
        putChessPieceOn(0, 1, new Knight(WHITE));
        putChessPieceOn(0, 2, new Bishop(WHITE));
        putChessPieceOn(0, 3, new Queen(WHITE));
        putChessPieceOn(0, 4, new King(WHITE));
        putChessPieceOn(0, 5, new Bishop(WHITE));
        putChessPieceOn(0, 6, new Knight(WHITE));
        putChessPieceOn(0, 7, new Rook(WHITE));
        putChessPieceOn(1, 0, new Pawn(WHITE));
        putChessPieceOn(1, 1, new Pawn(WHITE));
        putChessPieceOn(1, 2, new Pawn(WHITE));
        putChessPieceOn(1, 3, new Pawn(WHITE));
        putChessPieceOn(1, 4, new Pawn(WHITE));
        putChessPieceOn(1, 5, new Pawn(WHITE));
        putChessPieceOn(1, 6, new Pawn(WHITE));
        putChessPieceOn(1, 7, new Pawn(WHITE));
        putChessPieceOn(7, 0, new Rook(BLACK));
        putChessPieceOn(7, 1, new Knight(BLACK));
        putChessPieceOn(7, 2, new Bishop(BLACK));
        putChessPieceOn(7, 3, new Queen(BLACK));
        putChessPieceOn(7, 4, new King(BLACK));
        putChessPieceOn(7, 5, new Bishop(BLACK));
        putChessPieceOn(7, 6, new Knight(BLACK));
        putChessPieceOn(7, 7, new Rook(BLACK));
        putChessPieceOn(6, 0, new Pawn(BLACK));
        putChessPieceOn(6, 1, new Pawn(BLACK));
        putChessPieceOn(6, 2, new Pawn(BLACK));
        putChessPieceOn(6, 3, new Pawn(BLACK));
        putChessPieceOn(6, 4, new Pawn(BLACK));
        putChessPieceOn(6, 5, new Pawn(BLACK));
        putChessPieceOn(6, 6, new Pawn(BLACK));
        putChessPieceOn(6, 7, new Pawn(BLACK));
    }

    private void putChessPieceOn(int row, int column, ChessPiece chessPiece){
        board[row][column] = chessPiece;
    }

    public ArrayList<Field> getMove(int row, int column){
        if(row < 0  || row > 7 || column < 0 || column > 7){
            throw new IllegalArgumentException("column or row out of bounce");
        }
        ChessPiece chesspiece = board[row][column];
        ArrayList<Field> moves = new ArrayList<>();
        if(chesspiece != null){
            ArrayList<Field> possibleMoves = chesspiece.getMove(row,column);
            removeOutOfBounds(possibleMoves);
            for (Field move: possibleMoves) {
                if(board[move.row][move.column] == null){
                    moves.add(move);
                    System.out.printf("Field %d %d%n", move.row, move.column);
                }
                else if(board[move.row][move.column].getColor() != board[row][column].getColor()){
                    moves.add(move);
                    System.out.printf("Attack on Field %d %d%n", move.row, move.column);
                }
            }
        }
        return moves;
    }

    private void removeOutOfBounds(ArrayList<Field> possibleMoves) {
        possibleMoves.removeIf(move -> move.row > 7 || move.row < 0 || move.column > 7 || move.column < 0);
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for (int row = 7; row >= 0 ; row--) {
            builder.append("|");
            for (int column = 0; column < 8 ; column++) {
                ChessPiece chessPiece = board[row][column];
                if (chessPiece != null){
                    builder.append(chessPiece.getColor().name);
                    builder.append(chessPiece.getName());
                    builder.append("|");
                }else builder.append("__|");
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }


    public static void main(String[] args) {
        Board test = new Board();
        System.out.print(test.toString());
        test.getMove(0, 1);
    }
}
