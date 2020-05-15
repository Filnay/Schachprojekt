package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;

public class Rook extends ChessPiece {

    public Rook(Color color) {
        super(color, "R");
    }

    @Override
    public ArrayList<Field> getMove(int row, int column) {
        return null;
    }
}
