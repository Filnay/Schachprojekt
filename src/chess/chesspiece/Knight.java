package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessPiece {


    public Knight(Color color) {
        super(color, "S");
    }


    public List<ArrayList<Field>> getMove(int row, int column) {
        List<ArrayList<Field>> possibleFields = new ArrayList<>();
        addFields(possibleFields, new Field(row - 1, column - 2));
        addFields(possibleFields, new Field(row - 2, column - 1));
        addFields(possibleFields, new Field(row - 2, column + 1));
        addFields(possibleFields, new Field(row - 1, column + 2));
        addFields(possibleFields, new Field(row + 1, column + 2));
        addFields(possibleFields, new Field(row + 2, column + 1));
        addFields(possibleFields, new Field(row + 2, column - 1));
        addFields(possibleFields, new Field(row + 1, column - 2));
        return possibleFields;
    }

}
