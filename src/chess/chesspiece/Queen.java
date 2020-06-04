package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {

    public Queen(Color color) {
        super(color, "Q");
    }

    @Override
    public List<ArrayList<Field>> getMoves(int row, int column) {
        List<ArrayList<Field>> moves = new ArrayList<>();
        Field[] right = new Field[7];
        Field[] left = new Field[7];
        Field[] up = new Field[7];
        Field[] down = new Field[7];
        Field[] fieldsRightUp = new Field[7];
        Field[] fieldsLeftUp = new Field[7];
        Field[] fieldsLeftDown = new Field[7];
        Field[] fieldsRightDown = new Field[7];

        for (int i = 1 ; i < 8; i++) {
            right[i-1] = new Field(row, column + i);
            left[i-1] = new Field(row, column - i);
            up[i-1] = new Field(row + i, column);
            down[i-1] = new Field(row - i, column);
            fieldsRightUp[i-1] = new Field(row + i, column + i);
            fieldsLeftUp[i-1] = new Field(row + i, column - i);
            fieldsLeftDown[i-1] = new Field(row - i, column - i);
            fieldsRightDown[i-1] = new Field(row - i, column + i);
        }
        addMove(moves, right);
        addMove(moves, left);
        addMove(moves, down);
        addMove(moves, up);
        addMove(moves, fieldsRightUp);
        addMove(moves, fieldsLeftUp);
        addMove(moves, fieldsLeftDown);
        addMove(moves, fieldsRightDown);
        return moves;
    }
}
