package chess.kI;

import chess.Board;
import chess.Field;
import chess.chesspiece.*;

import java.util.List;

public class IntelligentKI {

    private Board board;
    private ChessPiece.Color color;

    public IntelligentKI(Board board, ChessPiece.Color color) {
        this.board = board;
        this.color = color;
    }


    public Board getBoard() {
        return board;
    }

    public ChessPiece.Color getColor() {
        return color;
    }


    public void move() {
        Field from = null;
        Field to = null;
        int bestEvaluate;
        if (color == ChessPiece.Color.WHITE) {
            bestEvaluate = 1000;
        } else {
            bestEvaluate = -1000;
        }



        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                List<Field> possibleMoves = board.getMoves(row, column);
                for (Field move : possibleMoves) {
                    Board newBoard = new Board(board);
                    if (!(board.getChessPiece(move) instanceof King)) {
                        newBoard.move(new Field(row, column), move);
                        int evaluate = evaluate(newBoard);
                        if (color == ChessPiece.Color.BLACK && evaluate > bestEvaluate) {
                            bestEvaluate = evaluate;
                            from = new Field(row, column);
                            to = move;
                        }
                        if (color == ChessPiece.Color.WHITE && evaluate < bestEvaluate) {
                            bestEvaluate = evaluate;
                            from = new Field(row, column);
                            to = move;
                        }
                    }
                }
            }
        }
        board.move(from, to);
    }

    public static int evaluate(Board board) {
        int points = 0;
        if (board.findChessPiece(new Queen(ChessPiece.Color.WHITE)).size() > 0 && board.findChessPiece(new Queen(ChessPiece.Color.BLACK)).size() > 0) {
            points = evaluateMidgame(board);
        } else {
            points = evaluateEndgame(board);
        }
        return points;
    }

    public static int evaluateEndgame(Board board) {
        int points = 0;
        boolean knightCombineWhite = true;
        boolean bishopCombineWhite = true;
        boolean rookCombineWhite = true;
        boolean knightCombineBlack = true;
        boolean bishopCombineBlack = true;
        boolean rookCombineBlack = true;

        List<Field> whiteKnights = board.findChessPiece(new Knight(ChessPiece.Color.WHITE));
        if (whiteKnights.size() >= 2) {
            points = points - (whiteKnights.size() * 300);
            knightCombineWhite = false;
        }
        List<Field> blackKnights = board.findChessPiece(new Knight(ChessPiece.Color.BLACK));
        if (blackKnights.size() >= 2) {
            points = points + (blackKnights.size() * 300);
            knightCombineBlack = false;
        }
        List<Field> whiteBishop = board.findChessPiece(new Bishop(ChessPiece.Color.WHITE));
        if (whiteBishop.size() >= 2) {
            points = points - (whiteBishop.size() * 400);
            bishopCombineWhite = false;
        }
        List<Field> blackBishop = board.findChessPiece(new Bishop(ChessPiece.Color.BLACK));
        if (blackBishop.size() >= 2) {
            points = points + (blackBishop.size() * 400);
            bishopCombineBlack = false;
        }
        List<Field> blackRook = board.findChessPiece(new Rook(ChessPiece.Color.BLACK));
        if (blackRook.size() >= 2) {
            points = points - (blackRook.size() * 600);
            rookCombineWhite = false;
        }
        List<Field> whiteRook = board.findChessPiece(new Rook(ChessPiece.Color.WHITE));
        if (whiteRook.size() >= 2) {
            points = points + (whiteRook.size() * 600);
            rookCombineBlack = false;
        }
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                ChessPiece current = board.getChessPiece(new Field(row, column));
                if (current != null) {
                    if (current.getColor() == ChessPiece.Color.BLACK) {
                        if (current instanceof Knight && knightCombineBlack) {
                            points = points + 275;
                        } else if (current instanceof Bishop && bishopCombineBlack) {
                            points = points + 350;
                        } else if (current instanceof Rook && rookCombineBlack) {
                            points = points + 550;
                        } else if (current instanceof Pawn) {
                            points = points + 110;
                        }
                        points = points + board.getMoves(row, column).size() * 2;
                        points = points + (7 - row) * 2;
                    }
                    if (current.getColor() == ChessPiece.Color.WHITE) {
                        if (current instanceof Knight && knightCombineWhite) {
                            points = points - 275;
                        } else if (current instanceof Bishop && bishopCombineWhite) {
                            points = points - 350;

                        } else if (current instanceof Rook && rookCombineWhite) {
                            points = points - 550;

                        } else if (current instanceof Pawn) {
                            points = points - 110;
                        }
                    }
                    points = points + board.getMoves(row, column).size() * 2;
                    points = points - (row * 2);
                }
            }
        }
        return points;
    }

    public static int evaluateMidgame(Board board) {
        int points = 0;
        boolean knightCombineWhite = true;
        boolean bishopCombineWhite = true;
        boolean rookCombineWhite = true;
        boolean knightCombineBlack = true;
        boolean bishopCombineBlack = true;
        boolean rookCombineBlack = true;

        List<Field> whiteKnights = board.findChessPiece(new Knight(ChessPiece.Color.WHITE));
        if (whiteKnights.size() >= 2) {
            points = points - (whiteKnights.size() * 300);
            knightCombineWhite = false;
        }
        List<Field> blackKnights = board.findChessPiece(new Knight(ChessPiece.Color.BLACK));
        if (blackKnights.size() >= 2) {
            points = points + (blackKnights.size() * 300);
            knightCombineBlack = false;
        }
        List<Field> whiteBishop = board.findChessPiece(new Bishop(ChessPiece.Color.WHITE));
        if (whiteBishop.size() >= 2) {
            points = points - (whiteBishop.size() * 350);
            bishopCombineWhite = false;
        }
        List<Field> blackBishop = board.findChessPiece(new Bishop(ChessPiece.Color.BLACK));
        if (blackBishop.size() >= 2) {
            points = points + (blackBishop.size() * 350);
            bishopCombineBlack = false;
        }
        List<Field> blackRook = board.findChessPiece(new Rook(ChessPiece.Color.BLACK));
        if (blackRook.size() >= 2) {
            points = points - (blackRook.size() * 500);
            rookCombineWhite = false;
        }
        List<Field> whiteRook = board.findChessPiece(new Rook(ChessPiece.Color.WHITE));
        if (whiteRook.size() >= 2) {
            points = points + (whiteRook.size() * 500);
            rookCombineBlack = false;
        }
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                ChessPiece current = board.getChessPiece(new Field(row, column));
                if (current != null) {
                    if (current.getColor() == ChessPiece.Color.BLACK) {
                        if (current instanceof Knight && knightCombineBlack) {
                            points = points + 275;
                        } else if (current instanceof Bishop && bishopCombineBlack) {
                            points = points + 325;
                        } else if (current instanceof Rook && rookCombineBlack) {
                            points = points + 465;
                        } else if (current instanceof Pawn) {
                            points = points + 100;
                            if ((row == 3 || row == 4) && (column == 3 || column == 4)) {
                                points = points + 60;
                            }
                        } else if (current instanceof King) {
                            if (board.castlingBlackLeft()) points = points + 30;
                            if (board.castlingBlackRight()) points = points + 50;
                            if (row == 7 && column == 6) points = points + 100;
                            if (row == 7 && column == 2) points = points + 60;
                        }
                        points = points + board.getMoves(row, column).size() * 5;
                        points = points + (7 - row) * 5;
                    } else if (current.getColor() == ChessPiece.Color.WHITE) {
                        if (current instanceof Knight && knightCombineWhite) {
                            points = points - 275;
                        } else if (current instanceof Bishop && bishopCombineWhite) {
                            points = points - 325;

                        } else if (current instanceof Rook && rookCombineWhite) {
                            points = points - 465;

                        } else if (current instanceof Pawn) {
                            points = points - 100;
                            if ((row == 3 || row == 4) && (column == 3 || column == 4)) {
                                points = points - 60;
                            }
                        } else if (current instanceof King) {
                            if (board.castlingWhiteLeft()) points = points - 30;
                            if (board.castlingWhiteRight()) points = points - 50;
                            if (row == 0 && column == 6) points = points - 100;
                            if (row == 0 && column == 2) points = points - 60;
                        }

                        points = points - board.getMoves(row, column).size() * 5;
                        points = points - (row * 5);
                    }

                }
            }
        }

        return points;
    }

    public static void main(String[] args) {
        Board board = new Board();
        IntelligentKI test = new IntelligentKI(board, ChessPiece.Color.WHITE);
        System.out.println(board.toString());
        test.move();
        System.out.println(board.toString());
    }
}
