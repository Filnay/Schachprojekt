package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece {

    public Bishop(Color color) {
        super(color, "L");
    }

    @Override
    public List<ArrayList<Field>> getMoves(int row, int column) {
        ArrayList<ArrayList<Field>> moves = new ArrayList<>();
        Field[] fieldsRightUp = new Field[7];
        Field[] fieldsLeftUp = new Field[7];
        Field[] fieldsLeftDown = new Field[7];
        Field[] fieldsRightDown = new Field[7];
        for (int i = 1; i < 8; i++) {
            fieldsRightUp[i-1] = new Field(row + i, column + i);
            fieldsLeftUp[i-1] = new Field(row + i, column - i);
            fieldsLeftDown[i-1] = new Field(row - i, column - i);
            fieldsRightDown[i-1] = new Field(row - i, column + i);
        }
        addMove(moves, fieldsRightUp);
        addMove(moves, fieldsLeftUp);
        addMove(moves, fieldsLeftDown);
        addMove(moves, fieldsRightDown);
        return moves;
    }
}
