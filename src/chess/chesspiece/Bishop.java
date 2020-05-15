package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;

public class Bishop extends ChessPiece {

    public Bishop(Color color) {
        super(color, "L");
    }

    @Override
    public ArrayList<Field> getMove(int row, int column) {
        return null;
    }
}
