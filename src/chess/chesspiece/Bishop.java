package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece {

    public Bishop(Color color) {
        super(color, "L");
    }

    @Override
    public List<ArrayList<Field>> getMove(int row, int column) {
        ArrayList<ArrayList<Field>> moves = new ArrayList<>();
        Field[] fieldsRightUp = new Field[7];
        Field[] fieldsLeftUp = new Field[7];
        Field[] fieldsLeftDown = new Field[7];
        Field[] fieldsRightDown = new Field[7];
        for (int i = 1; i < 8; i++) {
            fieldsRightUp[i-1] = new Field(row + i, column + i);
        }
        for (int i = 1; i < 8; i++) {
            fieldsLeftUp[i-1] = new Field(row + i, column - i);
        }
        for (int i = 1; i < 8; i++) {
            fieldsLeftDown[i-1] = new Field(row - i, column - i);
        }
        for (int i = 1; i < 8; i++) {
            fieldsRightDown[i-1] = new Field(row - i, column + i);
        }
        addFields(moves, fieldsRightUp);
        addFields(moves, fieldsLeftUp);
        addFields(moves, fieldsLeftDown);
        addFields(moves, fieldsRightDown);
        return moves;
    }
}
