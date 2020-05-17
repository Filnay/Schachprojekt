package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {

    public Queen(Color color) {
        super(color, "Q");
    }

    @Override
    public List<ArrayList<Field>> getMove(int row, int column) {
        return null;
    }
}
