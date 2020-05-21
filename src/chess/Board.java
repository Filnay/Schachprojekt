package chess;

import chess.chesspiece.*;
import chess.chesspiece.ChessPiece.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        putPawnOn(1, 0, WHITE);
        putPawnOn(1, 1, WHITE);
        putPawnOn(1, 2, WHITE);
        putPawnOn(1, 3, WHITE);
        putPawnOn(1, 4, WHITE);
        putPawnOn(1, 5, WHITE);
        putPawnOn(1, 6, WHITE);
        putPawnOn(1, 7, WHITE);
        putChessPieceOn(7, 0, new Rook(BLACK));
        putChessPieceOn(7, 1, new Knight(BLACK));
        putChessPieceOn(7, 2, new Bishop(BLACK));
        putChessPieceOn(7, 3, new Queen(BLACK));
        putChessPieceOn(7, 4, new King(BLACK));
        putChessPieceOn(7, 5, new Bishop(BLACK));
        putChessPieceOn(7, 6, new Knight(BLACK));
        putChessPieceOn(7, 7, new Rook(BLACK));
        putPawnOn(6, 0, BLACK);
        putPawnOn(6, 1, BLACK);
        putPawnOn(6, 2, BLACK);
        putPawnOn(6, 3, BLACK);
        putPawnOn(6, 4, BLACK);
        putPawnOn(6, 5, BLACK);
        putPawnOn(6, 6, BLACK);
        putPawnOn(6, 7, BLACK);
    }

    public void putPawnOn(int row, int column, Color color) {
        board[row][column] = new Pawn(color, this.board);
    }

    public void putChessPieceOn(int row, int column, ChessPiece chessPiece){
        board[row][column] = chessPiece;
    }

    public List<Field> getMoves(int row, int column){
        if(row < 0  || row > 7 || column < 0 || column > 7){
            throw new IllegalArgumentException("column or row out of bounce");
        }
        ChessPiece chesspiece = board[row][column];
        ArrayList<Field> moves = new ArrayList<>();
        if(chesspiece != null){
            System.out.println(chesspiece.getName());
            List<ArrayList<Field>> possibleMoves = chesspiece.getMoves(row,column);
            for (List<Field> pMoves: possibleMoves) {
                removeOutOfBounds(pMoves);
                for (Field move : pMoves) {
                    if (board[move.row][move.column] == null) {
                        moves.add(move);
                        System.out.printf("Field %d %d%n", move.row, move.column);
                    } else if (board[move.row][move.column].getColor() != board[row][column].getColor()) {
                        moves.add(move);
                        System.out.printf("Attack on Field %d %d%n", move.row, move.column);
                        break;
                    } else if (board[move.row][move.column].getColor() == board[row][column].getColor()){
                        System.out.println("break");
                        break;
                    }
                }
            }
        }
        return moves;
    }

    private void removeOutOfBounds(List<Field> possibleMoves) {
            possibleMoves.removeIf(move -> move.row > 7 || move.row < 0 || move.column > 7 || move.column < 0);
    }

    public boolean isAttacked(Field fieldOfChessPiece){
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
               if (getMoves(row, column).contains(fieldOfChessPiece)) {
                    return true;
               }
            }
        }
        return false;
    }

    public void move(Field from, Field to){
        ChessPiece currentChessPiece = board[from.row][from.column];
        board[from.row][from.column] = null;
        board[to.row][to.column] = currentChessPiece;
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
        test.getMoves(1, 3);
    }

    public ChessPiece getChessPiece(Field field) {
        return board[field.row][field.column];
    }
}