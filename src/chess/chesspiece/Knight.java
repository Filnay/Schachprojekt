package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;

public class Knight extends ChessPiece {


    public Knight(Color color) {
        super(color, "S");
    }


    public ArrayList<Field> getMove(int row, int column) {
        //Board
        ArrayList<Field> possibleFields = new ArrayList<>();
        int left = 0;
        int right = 0;
        int up = 0;
        int down = 0;
        for (int i = column; i > 0; i--) {
            left++;
        }
        for (int i = column; i < 8; i++) {
            right++;
        }
        for (int i = row; i > 0; i--) {
            down++;
        }
        for (int i = row; i < 8; i++) {
            up++;
        }
        if (down >= 1 && left >= 2){
            possibleFields.add(new Field(row - 1, column - 2));
        }
        if (down >= 2 && left >= 1){
            possibleFields.add(new Field(row - 2, column - 1));
        }
        if (down >= 2 && right >= 1){
            possibleFields.add(new Field(row - 2, column + 1));
        }
        if (down >= 1 && right >= 2){
            possibleFields.add(new Field(row - 1, column + 2));
        }
        if (up >= 1 && right >= 2){
            possibleFields.add(new Field(row + 1, column + 2));
        }
        if (up >= 2 && right >= 1) {
            possibleFields.add(new Field(row + 2, column + 1));
        }
        if (up >= 2 && left >= 1){
            possibleFields.add(new Field(row + 2, column - 1));
        }
        if (up >= 1 && left >= 2){
            possibleFields.add(new Field(row + 1, column - 2));
        }
        return possibleFields;
    }
}
