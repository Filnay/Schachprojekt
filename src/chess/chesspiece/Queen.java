package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;

public class Queen extends ChessPiece {

    public Queen(Color color) {
        super(color, "Q");
    }

    @Override
    public ArrayList<Field> getMove(int row, int column) {
        return null;
    }
}
