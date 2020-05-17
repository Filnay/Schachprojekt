package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {

    public Pawn(Color color) {
        super(color, "B");
    }

    @Override
    public List<ArrayList<Field>> getMove(int row, int column) {
        return null;
    }
}
