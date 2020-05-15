package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;

public class Pawn extends ChessPiece {

    public Pawn(Color color) {
        super(color, "B");
    }

    @Override
    public ArrayList<Field> getMove(int row, int column) {
        return null;
    }
}
