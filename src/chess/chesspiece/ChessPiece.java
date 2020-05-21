package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ChessPiece {


    public enum Color{
        BLACK("B"), WHITE("W");
        public final String name;
        Color(String name) {
            this.name = name;
        }
    }
    private final Color color;
    private final String name;

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public ChessPiece(Color color, String name) {
        this.color = color;
        this.name = name;
    }
    public abstract List<ArrayList<Field>> getMoves(int row, int column);

    protected void addMove(List<ArrayList<Field>> move, Field... f) {
        move.add(new ArrayList(Arrays.asList(f)));
    }


}
