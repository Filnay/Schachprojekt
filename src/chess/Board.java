package chess;

import chess.chesspiece.*;
import chess.chesspiece.ChessPiece.Color;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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
        castlingBlackLeft = true;
        castlingBlackRight = true;
        castlingWhiteLeft = true;
        castlingWhiteRight = true;
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

    public void setCastlingBlackLeft(boolean castling){
        castlingBlackLeft = castling;
    }
    public void setCastlingBlackRight(boolean castling){
        castlingBlackRight = castling;
    }
    public void setCastlingWhiteLeft(boolean castling){
        castlingWhiteLeft = castling;
    }
    public void setCastlingWhiteRight(boolean castling){
        castlingWhiteRight = castling;
    }

    public boolean getCastlingBlackLeft(){
        return castlingBlackLeft;
    }
    public boolean getCastlingBlackRight(){
        return castlingBlackRight;
    }
    public boolean getCastlingWhiteLeft(){
        return castlingWhiteLeft;
    }
    public boolean getCastlingWhiteRight(){
        return castlingWhiteRight;
    }
    public ChessPiece getChessPiece(Field field) {
        return board[field.row][field.column];
    }

    public ChessPiece[][] getBoard() {
        return board;
    }

    public void putPawnOn(int row, int column, Color color) {
        board[row][column] = new Pawn(color, this.board);
    }

    public void putChessPieceOn(int row, int column, ChessPiece chessPiece){
        board[row][column] = chessPiece;
    }

    public  List<Field> getMoves(int row, int column){
        if(row < 0  || row > 7 || column < 0 || column > 7){
            throw new IllegalArgumentException("column or row out of bounds");
        }
        ChessPiece chessPiece = board[row][column];
        List<Field> moves = getMoves(row, column, true);
        if(chessPiece instanceof King){
            moves.addAll(castling(chessPiece.getColor()));
        }
        return moves;
    }

    private List<Field> getMoves(int row, int column, boolean checkKingIsAttacked){
        if(row < 0  || row > 7 || column < 0 || column > 7){
            throw new IllegalArgumentException("column or row out of bounds");
        }
        ChessPiece chessPiece = board[row][column];
        ArrayList<Field> moves = new ArrayList<>();
        if(chessPiece == null) return moves;
        List<ArrayList<Field>> possibleMoves = chessPiece.getMoves(row,column);
        for (List<Field> direction: possibleMoves) {
            removeOutOfBounds(direction);
            for (Field move : direction) {
                Field from = new Field(row, column);
                if (board[move.row][move.column] == null) {
                    addMove(checkKingIsAttacked, chessPiece, moves, move, from);
                } else {
                    if (board[move.row][move.column].getColor() != chessPiece.getColor()) {
                        addMove(checkKingIsAttacked, chessPiece, moves, move, from);
                    }
                    break;
                }
            }
        }

        return moves;
    }

    private void removeOutOfBounds(List<Field> possibleMoves) {
            possibleMoves.removeIf(move -> move.row > 7 || move.row < 0 || move.column > 7 || move.column < 0);
    }

    private void addMove(boolean checkKingIsAttacked, ChessPiece chessPiece, ArrayList<Field> moves, Field move, Field from) {
        if (checkKingIsAttacked) {
            List<Field> ownKings = findChessPiece(new King(chessPiece.getColor()));
            Field ownKing = ownKings.get(0);
            if (isChessPieceNotAttackedAfterMove(ownKing, from, move)) {
                moves.add(move);
            }
        } else {
            moves.add(move);
        }
    }

    public boolean isChessPieceNotAttackedAfterMove(Field attackedField, Field from, Field to){
        Board current = this.clone();
        current.move(from, to);
        boolean isNotAttacked = false;
        if(!current.isAttacked(attackedField, null, false)){
            isNotAttacked = true;
        }
        return isNotAttacked;
    }

    public List<Field> whoAttacks(Field attackedField, Color from) {
        return whoAttacks(attackedField, from, true);
    }

    public List<Field> whoAttacks(Field attackedField) {
        return whoAttacks(attackedField, null);
    }

    private @NotNull
    List<Field> whoAttacks(Field attackedField, Color from, boolean checkKingIsAttacked) {
        List<Field> whoAttacks = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (getMoves(row, column,checkKingIsAttacked).contains(attackedField)
                        && (from == null || board[row][column].getColor() != from)) {
                    whoAttacks.add(new Field(row, column));
                }
            }
        }
        return whoAttacks;
    }

    public boolean isAttacked(Field attackedField){
        return isAttacked(attackedField, null);
    }

    public boolean isAttacked(Field attackedField, Color from){
        return isAttacked(attackedField, from, true);
    }
    public boolean isAttacked(Field attackedField, Color from, boolean checkKingIsAttacked) {
        List<Field> attackers = whoAttacks(attackedField, from, checkKingIsAttacked);
        return (attackers.size() != 0);
    }
    public List<Field> castling(Color color) {
        List<Field> moves = new ArrayList<>();
        if(color == BLACK) {
            if (castlingBlackLeft()) {
                moves.add(new Field(7, 2));
            }
            if (castlingBlackRight()) {
                moves.add(new Field(7, 6));
            }
        }
        if (color == WHITE){
            if (castlingWhiteLeft()){
                moves.add(new Field(0, 2));
            }
            if (castlingWhiteRight()){
                moves.add(new Field(0, 6));
            }
        }
        return moves;
    }

    public boolean castlingWhiteLeft() {
        boolean i = isAttacked(new Field(0, 1), WHITE);
        boolean k = isAttacked(new Field(0, 2), WHITE);
        boolean j = isAttacked(new Field(0, 3), WHITE);
        boolean l = isAttacked(new Field(0, 4), WHITE);

        return(castlingWhiteLeft && board[0][1] == null && board[0][2] == null && board[0][3] == null
                && !isAttacked(new Field(0, 1), WHITE) && !isAttacked(new Field(0, 2), WHITE)
                && !isAttacked(new Field(0, 3), WHITE) && !isAttacked(new Field(0, 4), WHITE));
    }

    public boolean castlingWhiteRight() {
        return(castlingWhiteRight && board[0][5] == null && board[0][6] == null
                && !isAttacked(new Field(0, 4), WHITE) && !isAttacked(new Field(0, 5), WHITE)
                && !isAttacked(new Field(0, 6), WHITE));
    }

    public boolean castlingBlackLeft() {
        return(castlingBlackLeft && board[7][1] == null && board[7][2] == null && board[7][3] == null
                && !isAttacked(new Field(7, 1), BLACK) && !isAttacked(new Field(7, 2), BLACK)
                && !isAttacked(new Field(7, 3), BLACK) && !isAttacked(new Field(7, 4), BLACK));
    }

    public boolean castlingBlackRight() {
        return(castlingBlackRight && board[7][5] == null && board[7][6] == null
                && !isAttacked(new Field(7, 4), BLACK) && !isAttacked(new Field(7, 5), BLACK)
                && !isAttacked(new Field(7, 6), BLACK));
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



    public void move(Field from, Field to){
        ChessPiece currentChessPiece = board[from.row][from.column];
        board[from.row][from.column] = null;
        board[to.row][to.column] = currentChessPiece;
        if (currentChessPiece instanceof King){
            if (currentChessPiece.getColor() == WHITE){
                castlingWhiteRight = false;
                castlingWhiteLeft = false;
            } else {
                castlingBlackRight = false;
                castlingBlackLeft = false;
            }
        }
        if (currentChessPiece instanceof Rook){
            if (from.row == 0 && from.column == 0){
                castlingWhiteLeft = false;
            }
            if (from.row == 0 && from.column == 7){
                castlingWhiteRight = false;
            }
            if (from.row == 7 && from.column == 0){
                castlingBlackLeft = false;
            }
            if (from.row == 7 && from.column == 7){
                castlingBlackRight = false;
            }
        }
    }

    public boolean isCheck(Color person){
        List<Field> fieldOfKing = findChessPiece(new King(person));
        return isAttacked(fieldOfKing.get(0));
    }

    public boolean isCheckmate(Color person){
        List<Field> fieldOfKings = findChessPiece(new King(person));
        Field fieldOfKing = fieldOfKings.get(0);
        List<Field> movesOfKing = getMoves(fieldOfKing.row, fieldOfKing.column);

        return (movesOfKing.size() == 0 && isCheck(person));
    }

    private boolean isDefended(Field field, Color color) {
        ChessPiece current = getChessPiece(field);
        putChessPieceOn(field.row, field.column, null);
        boolean isDefended = isAttacked(field, color.otherColor());
        putChessPieceOn(field.row, field.column, current);
        return isDefended;
    }

    @Override
    public Board clone(){
        Board cloned = new Board();
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                cloned.putChessPieceOn(row, column, this.getChessPiece(new Field(row, column)));
            }
        }
        cloned.setCastlingBlackLeft(castlingBlackLeft);
        cloned.setCastlingBlackRight(castlingBlackRight);
        cloned.setCastlingWhiteLeft(castlingWhiteLeft);
        cloned.setCastlingWhiteRight(castlingWhiteRight);
        return cloned;
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
