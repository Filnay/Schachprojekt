package chess;

import chess.chesspiece.*;
import chess.chesspiece.ChessPiece.Color;

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

    public Board (Board board){
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                putChessPieceOn(row, column, board.getChessPiece(new Field(row, column)));
            }
        }
        setCastlingBlackLeft(board.getCastlingBlackLeft());
        setCastlingBlackRight(board.getCastlingBlackRight());
        setCastlingWhiteLeft(board.getCastlingWhiteLeft());
        setCastlingWhiteRight(board.getCastlingWhiteRight());
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
        return getMoves(row, column, checkKingIsAttacked, false);
    }

    private List<Field> getMoves(int row, int column, boolean checkKingIsAttacked, boolean canMoveOnOwnChessPieces){
        if(row < 0  || row > 7 || column < 0 || column > 7){
            throw new IllegalArgumentException("column or row out of bounds");
        }
        ChessPiece chessPiece = board[row][column];
        ArrayList<Field> moves = new ArrayList<>();
        if(chessPiece == null) return moves;
        List<ArrayList<Field>> possibleMoves = null;
        if(chessPiece instanceof Pawn){
            possibleMoves = ((Pawn) chessPiece).getMoves(row,column,board);
        } else {
            possibleMoves = chessPiece.getMoves(row, column);
        }
        for (List<Field> direction: possibleMoves) {
            removeOutOfBounds(direction);
            for (Field move : direction) {
                Field from = new Field(row, column);
                if (board[move.row][move.column] == null) {
                    addMove(checkKingIsAttacked, chessPiece, moves, move, from);
                } else {
                    if (board[move.row][move.column].getColor() != chessPiece.getColor() || canMoveOnOwnChessPieces) {
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

    private void addMove(boolean checkKingIsAttacked, ChessPiece chessPiece, ArrayList<Field> moves, Field to, Field from) {
        if (checkKingIsAttacked) {
            List<Field> ownKings = findChessPiece(new King(chessPiece.getColor()));
            Field ownKing = ownKings.get(0);
            if (ownKing.equals(from)){
                if (isChessPieceNotAttackedAfterMove(to, from, to)) {
                    moves.add(to);
                }
            }
            if (isChessPieceNotAttackedAfterMove(ownKing, from, to)) {
                moves.add(to);
            }
        } else {
            moves.add(to);
        }
    }

    public boolean isChessPieceNotAttackedAfterMove(Field attackedField, Field from, Field to){
        Board current = new Board(this);
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
    private List<Field> whoAttacks(Field attackedField, Color from, boolean checkKingIsAttacked) {
        return whoAttacks(attackedField, from, checkKingIsAttacked, false);
    }

    private List<Field> whoAttacks(Field attackedField, Color from, boolean checkKingIsAttacked, boolean canMoveOnOwnChessPieces) {
        List<Field> whoAttacks = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                List<Field> dsa= getMoves(row, column,checkKingIsAttacked);
                if (getMoves(row, column,checkKingIsAttacked, canMoveOnOwnChessPieces).contains(attackedField)
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

    public List<Field> whoDefends(Field field){
        Color defendedColor = getChessPiece(field).getColor();
        return whoAttacks(field, defendedColor.otherColor(), false, true);
    }

    public boolean isDefended(Field field){
        return whoDefends(field).size()
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
            if(castlingWhiteRight && to.equals(new Field(0, 6 ))){
                move(new Field(0,7), new Field(0,5));
            }
            if(castlingWhiteLeft && to.equals(new Field(0, 2 ))){
                move(new Field(0,0), new Field(0,3));
            }
            if(castlingBlackRight && to.equals(new Field(7, 6 ))){
                move(new Field(7,7), new Field(7,5));
            }
            if(castlingBlackLeft && to.equals(new Field(7, 2 ))){
                move(new Field(7,0), new Field(7,3));
            }
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
        boolean attacked = isAttacked(fieldOfKing.get(0), person, false);
        return attacked;
    }

    public boolean isCheckmate(Color person){
        List<Field> fieldOfKings = findChessPiece(new King(person));
        Field fieldOfKing = fieldOfKings.get(0);
        List<Field> movesOfKing = getMoves(fieldOfKing.row, fieldOfKing.column);

        return (movesOfKing.size() == 0 && isCheck(person) && canMoveBetween(fieldOfKing, WHITE));
    }

    public boolean stalemate(Color person){
        boolean stalemate = true;
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                ChessPiece chessPiece = getChessPiece(new Field(row, column));
                if(chessPiece != null && chessPiece.getColor() == person && getMoves(row, column).size() > 0){
                    stalemate = false;
                }
            }
        }
        if (isCheck(person)) stalemate = false;
        return stalemate;
    }

    public boolean canMoveBetween(Field attackedField, Color by) {
        List<Field> attackers = whoAttacks(attackedField, by);
        if (attackers.size() > 1) return false;
        if (attackers.size() == 0) return true;

        Field fieldAttack = attackers.get(0);
        ChessPiece attacker = getChessPiece(fieldAttack);
        List<ArrayList<Field>> attackerDirections = attacker.getMoves(fieldAttack.row, fieldAttack.column);
        ArrayList<Field> directionOfAttack = null;

        for (ArrayList<Field> attackerDirection: attackerDirections){
            if(attackerDirection.contains(attackedField)) {
                directionOfAttack = attackerDirection;
                break;
            }
        }
        removeOutOfBounds(directionOfAttack);
        cutDirectionBeforeField(directionOfAttack, attackedField);

        boolean canMoveBetween = false;
        for (Field inWayField: directionOfAttack){
            List<Field> defender = whoAttacks(inWayField, by.otherColor());
            defender.remove(attackedField);
            if(defender.size() > 0) {
                canMoveBetween = true;
            }
        }
        return canMoveBetween;
    }

    public void cutDirectionBeforeField(ArrayList<Field> direction, Field field) {
        ArrayList<Field> current = new ArrayList<>();
        for (Field inWayField: direction){
            if(inWayField.equals(field)){
                break;
            } else {
                current.add(inWayField);
            }
        }
        direction = current;
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
}
