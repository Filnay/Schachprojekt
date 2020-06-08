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
                Field current = new Field(row, column);
                ChessPiece chessPiece = board.getChessPiece(current);
                if(chessPiece != null && board.getChessPiece(current).getColor() == color){
                    List<Field> possibleMoves = board.getMoves(row, column);
                    for (Field move : possibleMoves) {
                        Board newBoard = new Board(board);
                        if (!(newBoard.getChessPiece(move) instanceof King)) {
                            newBoard.move(current, move);
                            int evaluate = IntelligentKI.evaluate(newBoard);
                            if (color == ChessPiece.Color.BLACK && evaluate > bestEvaluate && chessPiece.getColor() == color) {
                                bestEvaluate = evaluate;
                                bestFrom = current;
                                bestTo = move;
                            }
                            if (color == ChessPiece.Color.WHITE && evaluate < bestEvaluate && chessPiece.getColor() == color) {
                                bestEvaluate = evaluate;
                                bestFrom = current;
                                bestTo = move;
                            }
                        }
                    }
                }
            }
        }
        return this;
    }
}
