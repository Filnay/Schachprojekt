package chess.kI;

import chess.Board;
import chess.Field;
import chess.chesspiece.ChessPiece;
import chess.chesspiece.King;

import java.util.List;

class FindBestMove {
    private Field bestFrom;
    private Field bestTo;
    private int bestEvaluate;
    private Board board;
    private ChessPiece.Color color;

    public FindBestMove(ChessPiece.Color color, int bestEvaluate, Board board) {
        this.board = board;
        this.bestFrom = null;
        this.bestTo = null;
        this.bestEvaluate = bestEvaluate;
        this.color = color;
    }

    public Field getBestFrom() {
        return bestFrom;
    }

    public Field getBestTo() {
        return bestTo;
    }

    public FindBestMove invoke() {

        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                List<Field> possibleMoves = board.getMoves(row, column);
                for (Field move : possibleMoves) {
                    Board newBoard = new Board(board);
                    ChessPiece chessPiece = board.getChessPiece(new Field(row, column));
                    if (!(newBoard.getChessPiece(move) instanceof King && chessPiece.getColor() == color)) {
                        newBoard.move(new Field(row, column), move);
                        int evaluate = IntelligentKI.evaluate(newBoard);
                        if (color == ChessPiece.Color.BLACK && evaluate > bestEvaluate && chessPiece.getColor() == color) {
                            bestEvaluate = evaluate;
                            bestFrom = new Field(row, column);
                            bestTo = move;
                        }
                        if (color == ChessPiece.Color.WHITE && evaluate < bestEvaluate && chessPiece.getColor() == color) {
                            bestEvaluate = evaluate;
                            bestFrom = new Field(row, column);
                            bestTo = move;
                        }
                    }
                }
            }
        }
        return this;
    }
}
