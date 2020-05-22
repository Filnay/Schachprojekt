package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class ChessPiece {


    public enum Color{
        BLACK("B"), WHITE("W");
        public final String name;
        Color(String name) {
            this.name = name;
        }
        public Color otherColor(){
            if(this == WHITE)return BLACK;
            return WHITE;}
    }
    private final Color color;
    private final String name;

    public ChessPiece(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public abstract List<ArrayList<Field>> getMoves(int row, int column);

    protected void addMove(List<ArrayList<Field>> move, Field... f) {
        move.add(new ArrayList(Arrays.asList(f)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessPiece)) return false;
        ChessPiece that = (ChessPiece) o;
        return color == that.color &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, name);
    }
}
