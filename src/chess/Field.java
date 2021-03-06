package chess;

import java.util.Objects;

//Reine Wissensklasse, die Reihe und Spalte vereint
public class Field {
    public final int row;
    public final int column;

    public Field(int row, int column){

        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return row == field.row &&
                column == field.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString(){
        return "" + row + " " + column;
    }
}
