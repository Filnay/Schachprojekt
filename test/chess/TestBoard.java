package chess;

import chess.chesspiece.Bishop;
import chess.chesspiece.ChessPiece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestBoard {

    private Board board;

    @BeforeEach
    void before(){
        board = new Board();
    }

    @Test
    void testGetMoveKnightBottomLeft() {
        List<Field> moves = board.getMoves(0, 1);

        assertEquals(2, moves.size());
        assertTrue(moves.contains(new Field(2,2)));
        assertTrue(moves.contains(new Field(2,0)));
    }

    @Test
    void testGetMoveKnightBottomRight() {
        List<Field> moves = board.getMoves(0, 6);

        assertEquals(2, moves.size());
        assertTrue(moves.contains(new Field(2,5)));
        assertTrue(moves.contains(new Field(2,7)));
    }

    @Test
    void testGetMoveBishopBottomLeft() {
        List<Field> moves = board.getMoves(0, 2);

        assertEquals(0, moves.size());
    }

    @Test
    void testGetMoveBishop(){
        board.putChessPieceOn(2, 0, new Bishop(ChessPiece.Color.WHITE));

        List<Field> moves = board.getMoves(2, 0);

        assertEquals(4, moves.size());
        assertTrue(moves.contains(new Field(3,1)));
        assertTrue(moves.contains(new Field(4,2)));
        assertTrue(moves.contains(new Field(5,3)));
        assertTrue(moves.contains(new Field(6,4)));

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
        board.move(new Field(0,1), new Field(5,2));

        boolean right = board.isAttacked(new Field(7,3));
        boolean notRight = board.isAttacked(new Field(0,0));

        assertFalse(notRight);
        assertTrue(right);
    }
}