package chess.kI;

import chess.Board;
import chess.ChessPiecesOnBoard;
import chess.Field;
import chess.chesspiece.*;

import java.util.List;

public class IntelligentKI {

    public void setBoard(Board board) {
        this.board = board;
    }

    private Board board;
    private final ChessPiece.Color color;

    public IntelligentKI(ChessPiece.Color color) {
        this.board = new Board();
        this.color = color;
    }


    public Board getBoard() {
        return board;
    }

    public ChessPiece.Color getColor() {
        return color;
    }

    public void move() {
        int bestEvaluate;
        if(color == ChessPiece.Color.WHITE){
            bestEvaluate = Integer.MAX_VALUE;
        } else {
            bestEvaluate = Integer.MIN_VALUE;
        }

        FindBestMove findBestMove = new FindBestMove(color, bestEvaluate, board).invoke();

        board.move(findBestMove.getBestFrom(), findBestMove.getBestTo());
    }

    //Midgame endet durch den verlust der Dame auf einer Seite
    //Wenn Schwarz besser steht gibt es Plus Punkte wenn Weiß besser steht minus
    public static int evaluate(Board board) {
        int points = 0;
        if (board.findChessPiece(new Queen(ChessPiece.Color.WHITE)).size() > 0 && board.findChessPiece(new Queen(ChessPiece.Color.BLACK)).size() > 0) {
            points = evaluateMidgame(board);
        } else {
            points = evaluateEndgame(board);
        }
        return points;
    }

    //Erst werden mögliche Kombinationen bewertet und dann alle Figuren, die nicht in einer Kombination sind.
    //Außerdem bekommt man für jeden möglichen Move Punkte und für das Verteidigen oder Angreifen von Figuren.
    //Midgame und Endgame unterscheiden sich njr durch die Punkte. Und ja man hätte auch eine Methode mit Parametern
    // für jede Möglichkeit Punkte zu bekommen machen können, aber dazu hatte ich keine Zeit.
    public static int evaluateEndgame(Board board) {
        int points = 0;
        boolean knightCombineWhite = true;
        boolean bishopCombineWhite = true;
        boolean rookCombineWhite = true;
        boolean knightCombineBlack = true;
        boolean bishopCombineBlack = true;
        boolean rookCombineBlack = true;

        ChessPiecesOnBoard positions = new ChessPiecesOnBoard();
        positions.findChessPiece(board);

        List<Field> whiteKnights = positions.getWhiteKnights();
        if (whiteKnights.size() >= 2) {
            points = points - (whiteKnights.size() * 300);
            knightCombineWhite = false;
        }
        List<Field> blackKnights = positions.getBlackKnights();
        if (blackKnights.size() >= 2) {
            points = points + (blackKnights.size() * 300);
            knightCombineBlack = false;
        }
        List<Field> whiteBishop = positions.getWhiteBishops();
        if (whiteBishop.size() >= 2) {
            points = points - (whiteBishop.size() * 400);
            bishopCombineWhite = false;
        }
        List<Field> blackBishop = positions.getBlackBishops();
        if (blackBishop.size() >= 2) {
            points = points + (blackBishop.size() * 400);
            bishopCombineBlack = false;
        }
        List<Field> blackRook = positions.getBlackRooks();
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
                Field currentField = new Field(row, column);
                ChessPiece current = board.getChessPiece(currentField);
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
                        } else if (current instanceof Queen) {
                            points = points + 900;
                        }
                        List<Field> defender = board.whoDefends(currentField);
                        for (Field field : defender) {
                            points = points + defenderPoints(board.getChessPiece(field));
                        }
                        List<Field> attacker = board.whoAttacks(currentField);
                        for (Field field : attacker) {
                            points = points - defenderPoints(board.getChessPiece(field));
                        }
                        points = points + board.getMoves(row, column).size() * 2;
                        if(board.isCheckmate(ChessPiece.Color.WHITE)) points = Integer.MAX_VALUE;


                    } else if (current.getColor() == ChessPiece.Color.WHITE) {
                        if (current instanceof Knight && knightCombineWhite) {
                            points = points - 275;
                        } else if (current instanceof Bishop && bishopCombineWhite) {
                            points = points - 350;

                        } else if (current instanceof Rook && rookCombineWhite) {
                            points = points - 550;

                        } else if (current instanceof Pawn) {
                            points = points - 110;
                        } else if (current instanceof Queen) {
                            points = points - 900;
                        }
                        List<Field> defender = board.whoDefends(currentField);
                        for (Field field : defender) {
                            points = points - defenderPoints(board.getChessPiece(field));
                        }
                        List<Field> attacker = board.whoAttacks(currentField);
                        for (Field field : attacker) {
                            points = points + defenderPoints(board.getChessPiece(field));
                        }
                        points = points - board.getMoves(row, column).size() * 2;
                        if(board.isCheckmate(ChessPiece.Color.WHITE)) points = Integer.MAX_VALUE;
                    }
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

        ChessPiecesOnBoard positions = new ChessPiecesOnBoard();
        positions.findChessPiece(board);

        List<Field> whiteKnights = positions.getWhiteKnights();
        if (whiteKnights.size() >= 2) {
            points = points - (whiteKnights.size() * 300);
            knightCombineWhite = false;
        }
        List<Field> blackKnights = positions.getBlackKnights();
        if (blackKnights.size() >= 2) {
            points = points + (blackKnights.size() * 300);
            knightCombineBlack = false;
        }
        List<Field> whiteBishop = positions.getWhiteBishops();
        if (whiteBishop.size() >= 2) {
            points = points - (whiteBishop.size() * 350);
            bishopCombineWhite = false;
        }
        List<Field> blackBishop = positions.getBlackBishops();
        if (blackBishop.size() >= 2) {
            points = points + (blackBishop.size() * 350);
            bishopCombineBlack = false;
        }
        List<Field> blackRook = positions.getBlackRooks();
        if (blackRook.size() >= 2) {
            points = points - (blackRook.size() * 500);
            rookCombineWhite = false;
        }
        List<Field> whiteRook = positions.getWhiteRooks();
        if (whiteRook.size() >= 2) {
            points = points + (whiteRook.size() * 500);
            rookCombineBlack = false;
        }
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Field currentField = new Field(row, column);
                ChessPiece current = board.getChessPiece(currentField);
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
                                points = points + 70;
                            }
                        } else if (current instanceof King) {
                            if (board.castlingBlackLeft()) points = points + 80;
                            if (board.castlingBlackRight()) points = points + 90;
                            if (row == 7 && column == 6) points = points + 100;
                            if (row == 7 && column == 2) points = points + 60;
                        }
                        List<Field> defender = board.whoDefends(currentField);
                        for (Field value : defender) {
                            points = points + defenderPoints(board.getChessPiece(value));
                        }
                        List<Field> attacker = board.whoAttacks(currentField);
                        for (Field field : attacker) {
                            points = points - defenderPoints(board.getChessPiece(field));
                        }
                        points = points + board.getMoves(row, column).size() * 1;
                        if(board.isCheckmate(ChessPiece.Color.WHITE)) points = Integer.MAX_VALUE;

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
                                points = points - 70;
                            }
                        } else if (current instanceof King) {
                            if (board.castlingWhiteLeft()) points = points - 80;
                            if (board.castlingWhiteRight()) points = points - 90;
                            if (row == 0 && column == 6) points = points - 100;
                            if (row == 0 && column == 2) points = points - 60;
                        }
                        List<Field> defender = board.whoDefends(currentField);
                        for (Field field : defender) {
                            points = points - defenderPoints(board.getChessPiece(field));
                        }
                        List<Field> attacker = board.whoAttacks(currentField);
                        for (Field field : attacker) {
                            points = points + defenderPoints(board.getChessPiece(field));
                        }
                        points = points - board.getMoves(row, column).size() * 1;
                        if(board.isCheckmate(ChessPiece.Color.BLACK)) points = Integer.MIN_VALUE;

                    }

                }
            }
        }

        return points;
    }

    public static int defenderPoints(ChessPiece chessPiece){
        int defendPoint = 2;
        if(chessPiece instanceof Pawn) return defendPoint * 9;
        if(chessPiece instanceof Knight) return defendPoint * 6;
        if(chessPiece instanceof Bishop) return defendPoint * 6;
        if(chessPiece instanceof Queen) return defendPoint;
        if(chessPiece instanceof King) return defendPoint;
        if(chessPiece instanceof Rook) return defendPoint * 4;
        return 0;
    }
    public static void main(String[] args) {
        Board board = new Board();
        IntelligentKI test = new IntelligentKI(ChessPiece.Color.WHITE);
        System.out.println(board.toString());
        test.move();
        System.out.println(board.toString());
    }

}
