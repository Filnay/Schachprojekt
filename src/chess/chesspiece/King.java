package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;

public class King extends ChessPiece {
    public King(Color color) {
        super(color, "K");
    }

    @Override
    public ArrayList<Field> getMove(int row, int column) {
        return null;
    }
}
