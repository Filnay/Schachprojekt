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

    // Standard Board
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

    //copy board
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


    //checkKingIsAttacked un CanMoveOnOwnChessPieces brauchen nur wenige Methoden. Deswegen die anderen Move Methoden.
    //Da nur die Oberste public iist war bei der die Rochade relevant
    //Die getMoves Methode der einzelnen Figuren geben die möglichen Felder wieder, die sie auf einen lehren Feld haben
    //Hierbei gibt es nichtmal eine richtige Boarder. Alle Felder werden dann von der getMoves Methode dann aktzeptiert,
    //wenn sie regelkonform sind.
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

    //
    private void removeOutOfBounds(List<Field> possibleMoves) {
            possibleMoves.removeIf(move -> move.row > 7 || move.row < 0 || move.column > 7 || move.column < 0);
    }

    //Bevor der Move hinzugefügt werden kann muss geguckt werden ob der König nach dem zug angegriffen ist (Fesselung)
    //Der König muss aber auch wegziehen können
    private void addMove(boolean checkKingIsAttacked, ChessPiece chessPiece, ArrayList<Field> moves, Field to, Field from) {
        if (checkKingIsAttacked) {
            Field ownKing = findKing(chessPiece.getColor());
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

    //Auf einer Kopie vom Board wird der Zug gezogen und gegucckt ob der König angegriffen wird.
    //Auf einer Kopie, damit die Rochade nicht verfälscht wird.
    public boolean isChessPieceNotAttackedAfterMove(Field attackedField, Field from, Field to){
        Board current = new Board(this);
        current.move(from, to);
        boolean isNotAttacked = false;
        if(!current.isAttacked(attackedField, null, false)){
            isNotAttacked = true;
        }
        return isNotAttacked;
    }

    //Es wird geguckt welche Figur das eine Feld angreifen kann. Hierbei kann die Farbe der Figuren angegeben werden)
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

    //boolean von whoAttacks
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

    //Verteidigung ist nichts anderes als, dass das Feld von der eigenen Farbe angegriffen wird.
    public List<Field> whoDefends(Field field){
        Color defendedColor = getChessPiece(field).getColor();
        return whoAttacks(field, defendedColor.otherColor(), false, true);
    }

    public boolean isDefended(Field field){
        return whoDefends(field).size() > 0;
    }

    //Gibt alle möglichen Rochaden wieder. Da der König nur zwei Felder weit ziehen kan wenn er rochiert,
    // wird die Rochade dem König angezeigt
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

    //alle Punkte die berücksichtigt werden müssen, damit die Rochade möglich ist
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

    //guckt alle Felder nach der Figur ab
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


    //move Methode ist sehr klein für alle Figuren bis auf König und Turm, da die beiden and der Rochade beteiligt sind
    //und die Rochade nicht mehr Möglich ist, wenn eine der Beiden Figuren sich bewegt hat
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

    //ist der König angegriffen?
    public boolean isCheck(Color person){
        Field fieldOfKing = findKing(person);
        boolean attacked = isAttacked(fieldOfKing, person, false);
        return attacked;
    }

    //Schneller, da findChessPiece eine Liste wiedergibt, da alle anderen Figuren öfter als einmal verwendet werden
    public Field findKing(Color color){
        List<Field> fieldOfKing = findChessPiece(new King(color));
        return fieldOfKing.get(0);
    }


    public boolean isCheckmate(Color person){
        Field fieldOfKing = findKing(person);
        List<Field> movesOfKing = getMoves(fieldOfKing.row, fieldOfKing.column);

        return (movesOfKing.size() == 0 && isCheck(person) && !canMoveBetweenOrAttack(fieldOfKing, person));
    }

    //Nur die Person die dran ist kann Patt sein
    public boolean isStalemate(Color person){
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

    //Kann eine deiner Figuren dazwischenziehen (Feld angreifen) oder die generische Figur schlagen, ohne das dein König schach Wird?
    //Mehr als einen angreifer kann man nicht schlagen und auch nicht dazwischenziehen (in einer Runde)
    public boolean canMoveBetweenOrAttack(Field attackedField, Color by) {
        List<Field> attackers = whoAttacks(attackedField, by);
        if (attackers.size() > 1) return false;
        if (attackers.size() == 0) return true;
        if (isAttacked(attackers.get(0), by.otherColor())) return true;

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

        Field fieldOfKing = findKing(by);
        boolean canMoveBetween = false;
        for (Field inWayField: directionOfAttack){
            List<Field> defenders = whoAttacks(inWayField, by.otherColor());
            defenders.remove(attackedField);
            if(defenders.size() > 0) {
                for(Field defender: defenders){
                    if(isChessPieceNotAttackedAfterMove(fieldOfKing, defender, inWayField)) {
                        canMoveBetween = true;
                    }
                }
            }
            if(inWayField.equals(attackedField)) break;
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


    //Zum debuggen und solange die GUI noch nicht funktionierte
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
