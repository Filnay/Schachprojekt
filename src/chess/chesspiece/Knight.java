package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;

public class Knight extends ChessPiece {


    public Knight(Color color) {
        super(color, "S");
    }


    public ArrayList<Field> getMove(int row, int column) {
        ArrayList<Field> possibleFields = new ArrayList<>();
        possibleFields.add(new Field(row - 1, column - 2));
        possibleFields.add(new Field(row - 2, column - 1));
        possibleFields.add(new Field(row - 2, column + 1));
        possibleFields.add(new Field(row - 1, column + 2));
        possibleFields.add(new Field(row + 1, column + 2));
        possibleFields.add(new Field(row + 2, column + 1));
        possibleFields.add(new Field(row + 2, column - 1));
        possibleFields.add(new Field(row + 1, column - 2));
        return possibleFields;
    }
}
