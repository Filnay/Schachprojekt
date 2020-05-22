package chess;

import chess.chesspiece.*;
import com.sun.source.tree.WhileLoopTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.chesspiece.ChessPiece.Color.BLACK;
import static chess.chesspiece.ChessPiece.Color.WHITE;
import static org.junit.jupiter.api.Assertions.*;

class TestBoard {

    private Board board;

    @BeforeEach
    void before(){
        board = new Board();
    }

    @Test
    void testGetMovesTiedUp(){
        board.putChessPieceOn(0, 4, null);
        board.putChessPieceOn(2,3,new King(WHITE));
        board.putChessPieceOn(3, 2, new Bishop(WHITE));
        board.putChessPieceOn(5, 2, new Queen(BLACK));


        List<Field> tiedUp = board.getMoves(3, 2);

        assertEquals(0, tiedUp.size());
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
        board.putPawnOn(2, 1, WHITE);

        List<Field> moves = board.getMoves(1,0);
        List<Field> move = board.getMoves(2,1);

        assertEquals(2, moves.size());
        assertTrue(moves.contains(new Field(2, 0)));
        assertTrue(moves.contains(new Field(3, 0)));
        assertEquals(1, move.size());
        assertTrue(move.contains(new Field(3, 1)));
    }

    @Test
    void testGetMovesKing(){
        board.putChessPieceOn(2, 0, new King(WHITE));

        List<Field> moves = board.getMoves(2, 0);

        assertEquals(3, moves.size());
        assertTrue(moves.contains(new Field(2, 1)));
        assertTrue(moves.contains(new Field(3, 1)));
        assertTrue(moves.contains(new Field(3, 0)));
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
        board.putPawnOn(5, 5, WHITE);

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
    void testFindChessPiece(){
        List<Field> king = board.findChessPiece(new King(WHITE));
        List<Field> whitePawn = board.findChessPiece(new Pawn(WHITE, new ChessPiece[8][8]));

        assertEquals(1, king.size());
        assertTrue(king.contains(new Field(0,4)));
        assertEquals(8, whitePawn.size());
        assertTrue(whitePawn.contains(new Field(1,4)));
    }

    @Test
    void testIsCheck() {
        board.putChessPieceOn(2, 3, new Knight(BLACK));

        boolean check = board.isCheck(WHITE);

        assertTrue(check);
    }

    @Test
    void testCheckmate(){
        board.putChessPieceOn(2, 3, new Knight(BLACK));
        board.move(new Field(7,4), new Field(5,0));
        board.putChessPieceOn(4,1, new Queen(WHITE));
        board.putChessPieceOn(3, 2, new Bishop(WHITE));

       // boolean notCheckmate = board.isCheckmate(WHITE);
        boolean check = board.isCheck(BLACK);
        boolean Checkmate = board.isCheckmate(BLACK);

        //assertFalse(notCheckmate);
        assertTrue(check);
        assertTrue(Checkmate);
    }
}