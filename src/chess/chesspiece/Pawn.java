package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {

    public Pawn(Color color) {
        super(color, "B");
    }

    @Override
    public List<ArrayList<Field>> getMoves(int row, int column) {
        List<ArrayList<Field>> moves = new ArrayList<>();
        addMove(moves,new Field(row + 1,column));
        if (row == 1){
            addMove(moves, new Field(row + 2, column));
        }
        return moves;
    }
}
