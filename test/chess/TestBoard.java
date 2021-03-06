package chess;

import chess.chesspiece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.chesspiece.ChessPiece.Color.BLACK;
import static chess.chesspiece.ChessPiece.Color.WHITE;
import static org.junit.jupiter.api.Assertions.*;

    //Das TestBoard testet ob alle Methoden des boards funktioniert, indem sie verschiedene Fälle mit bekannten Lösungen testet
class TestBoard {

    private Board board;

    //Vor jeder Methode wird ein neues Board erstellt
    @BeforeEach
    void before(){
        board = new Board();
    }

    @Test
    void testGetMovesTiedUp(){
        board.putChessPieceOn(0, 4, null);
        board.putChessPieceOn(2,2,new King(WHITE));
        board.putChessPieceOn(3, 2, new Bishop(WHITE));
        board.putChessPieceOn(5, 2, new Queen(BLACK));


        List<Field> tiedUp = board.getMoves(3, 2);

        assertEquals(0, tiedUp.size());
    }

    @Test
    void testKingCantAttackDefendedField(){
        board.putChessPieceOn(0, 4, null);
        board.putChessPieceOn(2,2,new King(WHITE));
        board.putChessPieceOn(3, 2, new Rook(BLACK));
        board.putChessPieceOn(5, 2, new Queen(BLACK));

        List<Field> kingMoves = board.getMoves(2, 2);

        assertFalse(kingMoves.contains(new Field(3,2)));
    }
    @Test
    void testGetMovesKnightBottomLeft() {
        List<Field> moves = board.getMoves(0, 1);

        assertEquals(2, moves.size());
        assertTrue(moves.contains(new Field(2,2)));
        assertTrue(moves.contains(new Field(2,0)));
    }

    @Test
    void testGetMovesKnightBottomRight() {
        List<Field> moves = board.getMoves(0, 6);

        assertEquals(2, moves.size());
        assertTrue(moves.contains(new Field(2,5)));
        assertTrue(moves.contains(new Field(2,7)));
    }

    @Test
    void testGetMovesBishopBottomLeft() {
        List<Field> moves = board.getMoves(0, 2);

        assertEquals(0, moves.size());
    }

    @Test
    void testGetMovesBishop(){
        board.putChessPieceOn(2, 0, new Bishop(WHITE));

        List<Field> moves = board.getMoves(2, 0);

        assertEquals(4, moves.size());
        assertTrue(moves.contains(new Field(3,1)));
        assertTrue(moves.contains(new Field(4,2)));
        assertTrue(moves.contains(new Field(5,3)));
        assertTrue(moves.contains(new Field(6,4)));

    }

    @Test
    void testGetMovesRookBottomLeft(){
        List<Field> moves = board.getMoves(0, 0);

        assertEquals(0, moves.size());
    }
    @Test
    void testGetMovesRook(){
        board.putChessPieceOn(2, 0, new Rook(WHITE));

        List<Field> moves = board.getMoves(2, 0);

        assertEquals(11, moves.size());
        assertTrue(moves.contains(new Field(3,0)));
        assertTrue(moves.contains(new Field(4,0)));
        assertTrue(moves.contains(new Field(5,0)));
        assertTrue(moves.contains(new Field(6,0)));
        assertTrue(moves.contains(new Field(2,1)));
        assertTrue(moves.contains(new Field(2,2)));
        assertTrue(moves.contains(new Field(2,3)));
        assertTrue(moves.contains(new Field(2,4)));
        assertTrue(moves.contains(new Field(2,5)));
        assertTrue(moves.contains(new Field(2,6)));
        assertTrue(moves.contains(new Field(2,7)));

    }

    @Test
    void testGetMovesQueenWhite(){
        List<Field> moves = board.getMoves(0, 3);

        assertEquals(0, moves.size());
    }

    @Test
    void testGetMovesQueen(){
        board.putChessPieceOn(2, 0, new Queen(WHITE));

        List<Field> moves = board.getMoves(2, 0);

        assertEquals(15, moves.size());
        assertTrue(moves.contains(new Field(3,0)));
        assertTrue(moves.contains(new Field(4,0)));
        assertTrue(moves.contains(new Field(5,0)));
        assertTrue(moves.contains(new Field(6,0)));
        assertTrue(moves.contains(new Field(2,1)));
        assertTrue(moves.contains(new Field(2,2)));
        assertTrue(moves.contains(new Field(2,3)));
        assertTrue(moves.contains(new Field(2,4)));
        assertTrue(moves.contains(new Field(2,5)));
        assertTrue(moves.contains(new Field(2,6)));
        assertTrue(moves.contains(new Field(2,7)));
        assertTrue(moves.contains(new Field(3,1)));
        assertTrue(moves.contains(new Field(4,2)));
        assertTrue(moves.contains(new Field(5,3)));
        assertTrue(moves.contains(new Field(6,4)));
    }
    @Test
    void testGetMovesPawn(){
        board.putChessPieceOn(2, 1, new Pawn(WHITE));

        List<Field> moves = board.getMoves(1,0);
        List<Field> move = board.getMoves(2,1);

        assertEquals(2, moves.size());
        assertTrue(moves.contains(new Field(2, 0)));
        assertTrue(moves.contains(new Field(3, 0)));
        assertEquals(1, move.size());
        assertTrue(move.contains(new Field(3, 1)));
    }

    @Test
    void testGetMovesPawn2(){
        board.putChessPieceOn(6, 1, new Pawn(WHITE));
        System.out.println(board.toString());

        List<Field> moves = board.getMoves(6, 1);
        assertEquals(2, moves.size() );
    }
    @Test
    void testGetMovesKing(){
        board.putChessPieceOn(2, 0, new King(WHITE));
        board.putChessPieceOn(0, 4, null);

        List<Field> moves = board.getMoves(2, 0);

        assertEquals(3, moves.size());
        assertTrue(moves.contains(new Field(2, 1)));
        assertTrue(moves.contains(new Field(3, 1)));
        assertTrue(moves.contains(new Field(3, 0)));
    }

    @Test
    void testGetMovesKing2(){
        board.putChessPieceOn(2, 4, new Pawn(BLACK));
        board.putChessPieceOn(1, 4, null);
        System.out.println(board.toString());
        List<Field> moves = board.getMoves(0, 4);

        assertEquals(1, moves.size());
        assertTrue(moves.contains(new Field(1, 4)));
    }
    @Test
    void testMove(){
        Field from = new Field(1,3);
        ChessPiece chessPiece = board.getChessPiece(from);
        assertNotNull(chessPiece);
        Field to = new Field(2,3);

        board.move(from, to);

        assertNull(board.getChessPiece(from));
        assertEquals(chessPiece, board.getChessPiece(to));
    }

    @Test
    void testIsAttacked(){
        board.putChessPieceOn( 5,3, new Knight(WHITE));
        board.putChessPieceOn(5, 5, new Pawn(WHITE));

        boolean correct = board.isAttacked(new Field(7,4));
        boolean correctPawn = board.isAttacked(new Field(6,6));
        boolean correctPawnAttackKnight = board.isAttacked(new Field(5,3));
        boolean notRight = board.isAttacked(new Field(0,0));

        assertTrue(correct);
        assertTrue(correctPawn);
        assertTrue(correctPawnAttackKnight);
        assertFalse(notRight);
    }

    @Test
    void testCastlingTrue(){
        board.putChessPieceOn(7, 1, null);
        board.putChessPieceOn(7, 2, null);
        board.putChessPieceOn(7, 3, null);

        board.putChessPieceOn(7, 5, null);
        board.putChessPieceOn(7, 6, null);

        board.putChessPieceOn(0, 1, null);
        board.putChessPieceOn(0, 2, null);
        board.putChessPieceOn(0, 3, null);

        board.putChessPieceOn(0, 5, null);
        board.putChessPieceOn(0, 6, null);

        List<Field> castlingWhite = board.castling(WHITE);
        List<Field> castlingBlack = board.castling(BLACK);

        assertEquals(2, castlingWhite.size());
        assertTrue(castlingWhite.contains(new Field(0, 2)));
        assertTrue(castlingWhite.contains(new Field(0, 6)));
        assertEquals(2, castlingBlack.size());
        assertTrue(castlingBlack.contains(new Field(7, 2)));
        assertTrue(castlingBlack.contains(new Field(7, 6)));
    }

    @Test
    void testCastlingFalse(){
        List<Field> castlingWhite = board.castling(WHITE);
        List<Field> castlingBlack = board.castling(BLACK);

        assertEquals(0, castlingWhite.size());
        assertEquals(0, castlingBlack.size());
    }

    @Test
    void testCastlingFalseBecAttack(){
        board.putChessPieceOn(7, 1, null);
        board.putChessPieceOn(7, 2, null);
        board.putChessPieceOn(7, 3, null);
        board.putChessPieceOn(5, 2, new Knight(WHITE));
        board.putChessPieceOn(0, 1, null);
        board.putChessPieceOn(0, 2, null);
        board.putChessPieceOn(0, 3, null);
        board.putChessPieceOn(2, 4, new Knight(BLACK));
        board.putChessPieceOn(7, 5, null);
        board.putChessPieceOn(7, 6, null);
        board.putChessPieceOn(5, 6, new Knight(WHITE));
        board.putChessPieceOn(0, 5, null);
        board.putChessPieceOn(0, 6, null);
        board.putChessPieceOn(2, 6, new Knight(BLACK));

        List<Field> castlingWhite = board.castling(WHITE);
        List<Field> castlingBlack = board.castling(BLACK);

        assertEquals(0, castlingWhite.size());
        assertEquals(0, castlingBlack.size());
    }

    @Test
    void testCastlingFalseMove(){
        board.putChessPieceOn(7, 1, null);
        board.putChessPieceOn(7, 2, null);
        board.putChessPieceOn(7, 3, null);
        board.putChessPieceOn(0, 1, null);
        board.putChessPieceOn(0, 2, null);
        board.putChessPieceOn(0, 3, null);
        board.putChessPieceOn(7, 5, null);
        board.putChessPieceOn(7, 6, null);
        board.putChessPieceOn(0, 5, null);
        board.putChessPieceOn(0, 6, null);
        board.move(new Field(0, 4), new Field(0,5));
        board.move(new Field(0, 5), new Field(0,4));
        board.move(new Field(7, 4), new Field(7,5));
        board.move(new Field(7, 5), new Field(7,4));

        List<Field> castlingWhite = board.castling(WHITE);
        List<Field> castlingBlack = board.castling(BLACK);

        assertEquals(0, castlingWhite.size());
        assertEquals(0, castlingBlack.size());
    }

    @Test
    void testCastling(){
        board.putChessPieceOn(0, 1, null);
        board.putChessPieceOn(0, 2, null);
        board.putChessPieceOn(0, 3, null);
        board.putChessPieceOn(7, 1, null);
        board.putChessPieceOn(7, 2, null);
        board.putChessPieceOn(7, 3, null);

        board.move(new Field(0,4), new Field(0,2));
        board.move(new Field(7,4), new Field(7,2));

        assertEquals(board.getChessPiece(new Field(0, 3)), new Rook(WHITE));
        assertEquals(board.getChessPiece(new Field(7, 3)), new Rook(BLACK));
    }

    @Test
    void testFindChessPiece(){
        List<Field> king = board.findChessPiece(new King(WHITE));
        List<Field> whitePawn = board.findChessPiece(new Pawn(WHITE));

        assertEquals(1, king.size());
        assertTrue(king.contains(new Field(0,4)));
        assertEquals(8, whitePawn.size());
        assertTrue(whitePawn.contains(new Field(1,4)));
    }

    @Test
    void testIsCheck() {
        board.putChessPieceOn(2, 3, new Knight(BLACK));
        board.move(new Field(0, 4), new Field(4, 4));

        boolean check = board.isCheck(WHITE);

        assertTrue(check);
    }

    @Test
    void testCanMoveBetween(){
        board.putChessPieceOn(3, 5, new Rook(WHITE));
        board.putChessPieceOn(4, 7, new Rook(WHITE));
        board.putChessPieceOn(5, 5, new Queen(BLACK));

        boolean whiteTrue = board.canMoveBetweenOrAttack(new Field(3, 5),WHITE);
        boolean blackTrue = board.canMoveBetweenOrAttack(new Field(0,0), BLACK);
        boolean whiteFalse = board.canMoveBetweenOrAttack(new Field (4,5), BLACK);
        System.out.println(board.toString());
        assertTrue(whiteTrue);

        assertTrue(blackTrue);
        assertFalse(whiteFalse);
    }

    @Test
    void testCheckmate(){
        board.move(new Field(7,4), new Field(5,0));
        board.putChessPieceOn(4,1, new Queen(WHITE));
        board.putChessPieceOn(3, 2, new Bishop(WHITE));

        boolean notCheckmate = board.isCheckmate(WHITE);
        boolean check = board.isCheck(BLACK);
        boolean checkmate = board.isCheckmate(BLACK);

        assertFalse(notCheckmate);
        assertTrue(check);
        assertTrue(checkmate);
    }

    @Test
    void testCheckmateTwo(){
        board.putChessPieceOn(0, 4, null);
        board.putChessPieceOn(2, 2, new King(WHITE));
        board.putChessPieceOn(3, 2, new Bishop(WHITE));
        board.putChessPieceOn(5, 2, new Queen(BLACK));
        board.putChessPieceOn(5, 1, new Rook(BLACK));
        board.putChessPieceOn(5, 3, new Rook(BLACK));
        board.putChessPieceOn(2, 0, new Rook(BLACK));
        board.putChessPieceOn(1, 0, new Pawn(BLACK));
        board.putChessPieceOn(1, 1, new Knight(BLACK));
        board.putChessPieceOn(0, 1, null);

        boolean checkmate = board.isCheckmate(WHITE);

System.out.println(board.toString());
        assertTrue(checkmate);
    }
    @Test
    void testCheckMate3(){
        board.putChessPieceOn(1, 5, null);
        board.putChessPieceOn(1, 6, null);
        board.putChessPieceOn(6, 4, null);
        board.putChessPieceOn(7, 3, null);
        board.putChessPieceOn(3, 7, new Queen(BLACK));
        board.putChessPieceOn(3, 6, new Pawn(WHITE));
        board.putChessPieceOn(2, 5, new Pawn(WHITE));

        boolean isCheckmateTrue = board.isCheckmate(WHITE);
        boolean isCheckmateFalse = board.isCheckmate(BLACK);
        assertTrue(isCheckmateTrue);
        assertFalse(isCheckmateFalse);
    }

    @Test
    void testCheckMate4(){
        board.putChessPieceOn(0, 4, null);
        board.putChessPieceOn(2, 2, new King(WHITE));
        board.putChessPieceOn(3, 2, new Bishop(WHITE));
        board.putChessPieceOn(5, 2, new Queen(BLACK));
        board.putChessPieceOn(5, 1, new Rook(BLACK));
        board.putChessPieceOn(5, 3, new Rook(BLACK));
        board.putChessPieceOn(2, 0, new Rook(BLACK));
        board.putChessPieceOn(1, 0, new Pawn(BLACK));
        board.putChessPieceOn(1, 1, new Bishop(WHITE));

        System.out.println(board.toString());
        boolean checkmate = board.isCheckmate(WHITE);

        assertFalse(checkmate);

    }
    @Test
    void testWhoDefends(){
        List<Field> defenderTrue = board.whoDefends(new Field(0,3));
        boolean defended = board.isDefended(new Field(0, 3));

        assertEquals(1, defenderTrue.size());
        assertTrue(defended);
    }

    @Test
    void testIsStaleMate(){
        board.putChessPieceOn(1, 0, null);
        board.putChessPieceOn(1, 1, null);
        board.putChessPieceOn(1, 2, null);
        board.putChessPieceOn(1, 3, null);
        board.putChessPieceOn(1, 4, null);
        board.putChessPieceOn(1, 5, null);
        board.putChessPieceOn(1, 6, null);
        board.putChessPieceOn(1, 7, null);
        board.putChessPieceOn(0, 0, null);
        board.putChessPieceOn(0, 1, null);
        board.putChessPieceOn(0, 2, null);
        board.putChessPieceOn(0, 3, null);
        board.putChessPieceOn(0, 5, null);
        board.putChessPieceOn(0, 6, null);
        board.putChessPieceOn(0, 7, null);
        board.putChessPieceOn(3, 3, new Rook(BLACK));
        board.putChessPieceOn(3, 5, new Rook(BLACK));
        board.putChessPieceOn(1, 0, new Rook(BLACK));

        boolean whiteStalemateTrue = board.isStalemate(WHITE);
        boolean blackStalemateFalse = board.isStalemate(BLACK);
        System.out.println(board.toString());
        assertTrue(whiteStalemateTrue);
        assertFalse(blackStalemateFalse);
    }
}