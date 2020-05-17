package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;

public class Bishop extends ChessPiece {

    public Bishop(Color color) {
        super(color, "L");
    }

    @Override
    public ArrayList<Field> getMove(int row, int column) {
        ArrayList<Field> moves = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            moves.add(new Field(row - i, column - i));
            moves.add(new Field(row + i, column - i));
            moves.add(new Field(row - i, column + i));
            moves.add(new Field(row + i, column + i));
        }
        return moves;
    }
}
