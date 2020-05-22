package chess.chesspiece;

import chess.Field;

import java.util.ArrayList;
import java.util.List;

import static chess.chesspiece.ChessPiece.Color.WHITE;
import static chess.chesspiece.ChessPiece.Color.BLACK;

public class Pawn extends ChessPiece {

    private final ChessPiece[][] board;

    public Pawn(Color color, ChessPiece[][] board) {
        super(color, "P");
        this.board = board;
    }

    @Override
    public List<ArrayList<Field>> getMoves(int row, int column) {
        List<ArrayList<Field>> moves = new ArrayList<>();
        if (this.getColor() == WHITE) {
            if(board[row + 1][column] == null) {
                if (row == 1 && board[3][column] == null) {
                    Field[] twoFields = new Field[2];
                    twoFields[0] = new Field(row + 1, column);
                    twoFields[1] = new Field(row + 2, column);
                    addMove(moves, twoFields);
                } else{
                    addMove(moves, new Field(row + 1, column));
                }
            }
            if (row + 1 < 8 && column + 1 < 8) {
                if (board[row + 1][column + 1] != null && board[row + 1][column + 1].getColor() == BLACK) {
                    addMove(moves, new Field(row + 1, column + 1));
                }
            }
            if (row + 1 < 8 && column - 1 > 0) {
                if (board[row + 1][column - 1] != null && board[row + 1][column - 1].getColor() == BLACK) {
                    addMove(moves, new Field(row + 1, column - 1));
                }
            }
        } else {
            if(board[row - 1][column] == null) {
                if (row == 6 && board[4][column] == null) {
                    Field[] twoFields = new Field[2];
                    twoFields[0] = new Field(row - 1, column);
                    twoFields[1] = new Field(row - 2, column);
                    addMove(moves, twoFields);
                } else {
                    addMove(moves, new Field(row - 1, column));
                }
            }
            if (row - 1 > 0 && column + 1 < 8) {
                if (board[row - 1][column + 1] != null && board[row - 1][column + 1].getColor() == WHITE) {
                    addMove(moves, new Field(row - 1, column + 1));
                }
            }
            if (row - 1 > 0 && column - 1 > 0) {
                if (board[row - 1][column - 1] != null && board[row - 1][column - 1].getColor() == WHITE) {
                    addMove(moves, new Field(row - 1, column - 1));
                }
            }
        }
        return moves;
    }
}
