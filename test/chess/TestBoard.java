package chess;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        List<Field> moves = board.getMove(0, 1);

        assertEquals(2, moves.size());
        assertTrue(moves.contains(new Field(2,2)));
        assertTrue(moves.contains(new Field(2,0)));
    }

    @Test
    void testGetMoveKnightBottomRight() {
        List<Field> moves = board.getMove(0, 6);

        assertEquals(2, moves.size());
        assertTrue(moves.contains(new Field(2,5)));
        assertTrue(moves.contains(new Field(2,7)));
    }

    @Test
    void testGetMoveKBishopBottomLeft() {
        List<Field> moves = board.getMove(0, 2);
        assertEquals(0, moves.size());
    }
}