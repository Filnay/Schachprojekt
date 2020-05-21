package chess.chesspiece;

import chess.Board;
import chess.Field;

import java.util.ArrayList;
import java.util.List;

import static chess.chesspiece.ChessPiece.Color.WHITE;
import static chess.chesspiece.ChessPiece.Color.BLACK;

public class Pawn extends ChessPiece {

    private ChessPiece[][] board;

    public Pawn(Color color, ChessPiece[][] board) {
        super(color, "B");
        this.board = board;
    }

    @Override
    public List<ArrayList<Field>> getMoves(int row, int column) {
        List<ArrayList<Field>> moves = new ArrayList<>();
        if (getColor() == WHITE) {
            if(board[row + 1][column] == null) {
                addMove(moves, new Field(row + 1, column));
                if (row == 1) {
                    addMove(moves, new Field(row + 2, column));
                }
            } else if (board[row + 1][column + 1] != null && board[row + 1][column + 1].getColor() == BLACK){
                addMove(moves, new Field(row + 1, column + 1));
            } else if (board[row + 1][column - 1] != null && board[row + 1][column - 1].getColor() == BLACK){
                addMove(moves, new Field(row + 1, column - 1));
            }
        } else {
            if(board[row - 1][column] == null) {
                addMove(moves, new Field(row - 1, column));
                if (row == 6) {
                    addMove(moves, new Field(row - 2, column));
                }
            } else if (board[row - 1][column + 1] != null && board[row - 1][column + 1].getColor() == BLACK){
                addMove(moves, new Field(row - 1, column + 1));
            } else if (board[row - 1][column - 1] != null && board[row - 1][column - 1].getColor() == BLACK){
                addMove(moves, new Field(row - 1, column - 1));
            }
        }
        return moves;
    }
}
