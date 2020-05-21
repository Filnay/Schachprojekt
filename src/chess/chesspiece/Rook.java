package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece {

    public Rook(Color color) {
        super(color, "R");
    }

    @Override
    public List<ArrayList<Field>> getMoves(int row, int column) {
        return null;
    }
}
