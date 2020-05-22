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
    private final ChessPiece[][] board = new ChessPiece[][]{a,b,c,d,e,f,g,h};

    private boolean castlingBlackLeft = true;
    private boolean castlingBlackRight = true;
    private boolean castlingWhiteLeft = true;
    private boolean castlingWhiteRight = true;

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

    public ChessPiece getChessPiece(Field field) {
        return board[field.row][field.column];
    }

    public void putPawnOn(int row, int column, Color color) {
        board[row][column] = new Pawn(color, this.board);
    }

    public void putChessPieceOn(int row, int column, ChessPiece chessPiece){
        board[row][column] = chessPiece;
    }

    public  List<Field> getMoves(int row, int column){
        return getMoves(row, column, true);
    }

    private List<Field> getMoves(int row, int column, boolean level){
        if(row < 0  || row > 7 || column < 0 || column > 7){
            throw new IllegalArgumentException("column or row out of bounce");
        }
        ChessPiece chessPiece = board[row][column];
        ArrayList<Field> moves = new ArrayList<>();
        if(chessPiece != null){
            System.out.println(chessPiece.getName());
            List<ArrayList<Field>> possibleMoves = chessPiece.getMoves(row,column);
            List<Field> ownKings = findChessPiece(new King(chessPiece.getColor()));
            Field ownKing = ownKings.get(0);
            System.out.printf("own King on Field %d %d%n", ownKing.row, ownKing.column);
            for (List<Field> pMoves: possibleMoves) {
                removeOutOfBounds(pMoves);
                for (Field move : pMoves) {
                    Field from = new Field(row, column);
                      if (board[move.row][move.column] == null) {
                          if(level) {
                                if (isChessPieceAttackedAfterMove(ownKing, from, move)) {
                                    moves.add(move);
                                    System.out.printf("Field %d %d%n", move.row, move.column);
                                }
                          } else {
                            moves.add(move);
                            System.out.printf("Field %d %d%n", move.row, move.column);
                          }
                        } else if (board[move.row][move.column].getColor() != board[row][column].getColor()) {
                          if(level) {
                              if(isChessPieceAttackedAfterMove(ownKing, from, move)){
                                  moves.add(move);
                                  System.out.printf("Attack on Field %d %d%n", move.row, move.column);
                              }
                          } else {
                              moves.add(move);
                              System.out.printf("Attack on Field %d %d%n", move.row, move.column);
                          }
                          break;
                        } else if (board[move.row][move.column].getColor() == board[row][column].getColor()) {
                            System.out.println("break");
                            break;
                        }
                }
            }
        }
        return moves;
    }

    public boolean isChessPieceAttackedAfterMove(Field attackedField,Field from, Field to){
        boolean isAttacked = false;
        ChessPiece current = board[to.row][to.column];
        move(from, to);
        if(!isAttacked(attackedField)){
            isAttacked = true;
        }
        move(to, from);
        putChessPieceOn(to.row, to.column, current);
        return isAttacked;
    }

    public List<Field> findChessPiece(ChessPiece chessPiece) {
        ArrayList<Field> chessPieces = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if(board[row][column] != null && board[row][column].equals(chessPiece)){
                    chessPieces.add(new Field(row, column));
                }
            }
        }
        return chessPieces;
    }


    private void removeOutOfBounds(List<Field> possibleMoves) {
            possibleMoves.removeIf(move -> move.row > 7 || move.row < 0 || move.column > 7 || move.column < 0);
    }

    public boolean isAttacked(Field attackedField){
        List<Field> attackers = whoAttacks(attackedField);
        return(attackers.size() != 0);
    }


    public void move(Field from, Field to){
        ChessPiece currentChessPiece = board[from.row][from.column];
        board[from.row][from.column] = null;
        board[to.row][to.column] = currentChessPiece;
    }

    public boolean isCheck(Color person){
        List<Field> fieldOfKing = findChessPiece(new King(person));
        return isAttacked(fieldOfKing.get(0));
    }

   public boolean isCheckmate(Color person){
        List<Field> fieldOfKings = findChessPiece(new King(person));
        Field fieldOfKing = fieldOfKings.get(0);
        List<Field> movesOfKing = getMoves(fieldOfKing.row, fieldOfKing.column);
        List<Field> attackers = whoAttacks(fieldOfKing);
        boolean checkmate = false;

        for(Field attacker: attackers){
            if(!isAttacked(attacker)){
                checkmate = true;
            }
        }
        return (movesOfKing.size() == 0 && isCheck(person) && checkmate);
    }

    private List<Field> whoAttacks(Field attackedField) {
        List<Field> whoAttacks = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (getMoves(row, column,false).contains(attackedField)) {
                    whoAttacks.add(new Field(row, column));
                }
            }
        }
        return whoAttacks;
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

}
