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
        List<ArrayList<Field>> moves = new ArrayList<>();
        Field[] right = new Field[7];
        Field[] left = new Field[7];
        Field[] up = new Field[7];
        Field[] down = new Field[7];

        for (int i = 1 ; i < 8; i++) {
            right[i-1] = new Field(row, column + i);
            left[i-1] = new Field(row, column - i);
            up[i-1] = new Field(row + i, column);
            down[i-1] = new Field(row - i, column);
        }
        addMove(moves, right);
        addMove(moves, left);
        addMove(moves, down);
        addMove(moves, up);
        return moves;
    }
}
