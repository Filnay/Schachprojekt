package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rook extends ChessPiece {

    public Rook(Color color) {
        super(color, "R");
    }

    @Override
    public List<ArrayList<Field>> getMoves(int row, int column) {
        List<ArrayList<Field>> moves = new ArrayList<>();
        Field[] right = new Field[8];
        Field[] left = new Field[8];
        Field[] up = new Field[8];
        Field[] down = new Field[8];

        return moves;
    }
}
