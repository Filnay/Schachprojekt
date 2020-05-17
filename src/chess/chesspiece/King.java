package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece {
    public King(Color color) {
        super(color, "K");
    }

    @Override
    public List<ArrayList<Field>> getMove(int row, int column) {
        return null;
    }
}
