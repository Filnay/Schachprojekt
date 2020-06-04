package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece {
    public King(Color color) {
        super(color, "K");
    }

    @Override
    public List<ArrayList<Field>> getMoves(int row, int column) {
        List<ArrayList<Field>> moves = new ArrayList<>();
        Field right = new Field(row, column + 1);
        Field left = new Field(row, column - 1);
        Field up = new Field(row + 1, column);
        Field down = new Field(row - 1, column);
        Field fieldsRightUp = new Field(row + 1, column + 1);
        Field fieldsLeftUp = new Field(row + 1, column -1);
        Field fieldsLeftDown = new Field(row - 1, column - 1);
        Field fieldsRightDown = new Field(row - 1, column + 1);

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
