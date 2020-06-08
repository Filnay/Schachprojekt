package chess;

import chess.chesspiece.*;


import java.util.ArrayList;
import java.util.List;

//Informationsklasse über den Aufbau des Brettes, damit man nicht immer eine findChessPiee Methode benötigt
public class ChessPiecesOnBoard {
    List<Field> blackPawns = new ArrayList<>();
    List<Field> blackKnights = new ArrayList<>();
    List<Field> blackBishops = new ArrayList<>();
    List<Field> blackQueens = new ArrayList<>();
    List<Field> blackRooks = new ArrayList<>();
    List<Field> blackKings = new ArrayList<>();

    List<Field> whitePawns = new ArrayList<>();
    List<Field> whiteKnights = new ArrayList<>();
    List<Field> whiteBishops = new ArrayList<>();
    List<Field> whiteRooks = new ArrayList<>();
    List<Field> whiteQueens = new ArrayList<>();
    List<Field> whiteKings = new ArrayList<>();

    public List<Field> getBlackPawns() {
        return blackPawns;
    }

    public List<Field> getBlackKnights() {
        return blackKnights;
    }

    public List<Field> getBlackBishops() {
        return blackBishops;
    }

    public List<Field> getBlackQueens() {
        return blackQueens;
    }

    public List<Field> getBlackRooks() {
        return blackRooks;
    }

    public List<Field> getBlackKings() {
        return blackKings;
    }

    public List<Field> getWhitePawns() {
        return whitePawns;
    }

    public List<Field> getWhiteKnights() {
        return whiteKnights;
    }

    public List<Field> getWhiteBishops() {
        return whiteBishops;
    }

    public List<Field> getWhiteRooks() {
        return whiteRooks;
    }

    public List<Field> getWhiteQueens() {
        return whiteQueens;
    }

    public List<Field> getWhiteKings() {
        return whiteKings;
    }

    public void findChessPiece(Board board){
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Field currentField = new Field(row, column);
                ChessPiece currentChessPiece = board.getChessPiece(currentField);
                if (currentChessPiece != null){
                    if (currentChessPiece instanceof Pawn && currentChessPiece.getColor().equals(ChessPiece.Color.BLACK)){
                        blackPawns.add(currentField);
                    } else if (currentChessPiece instanceof Knight && currentChessPiece.getColor().equals(ChessPiece.Color.BLACK)){
                        blackKnights.add(currentField);
                    } else if (currentChessPiece instanceof Bishop && currentChessPiece.getColor().equals(ChessPiece.Color.BLACK)){
                        blackBishops.add(currentField);
                    } else if (currentChessPiece instanceof Rook && currentChessPiece.getColor().equals(ChessPiece.Color.BLACK)){
                        blackRooks.add(currentField);
                    } else if (currentChessPiece instanceof Queen && currentChessPiece.getColor().equals(ChessPiece.Color.BLACK)){
                        blackQueens.add(currentField);
                    } else if (currentChessPiece instanceof King && currentChessPiece.getColor().equals(ChessPiece.Color.BLACK)){
                        blackKings.add(currentField);
                    }
                     else if (currentChessPiece instanceof Pawn && currentChessPiece.getColor().equals(ChessPiece.Color.WHITE)){
                        whitePawns.add(currentField);
                    } else if (currentChessPiece instanceof Knight && currentChessPiece.getColor().equals(ChessPiece.Color.WHITE)){
                        whiteKnights.add(currentField);
                    } else if (currentChessPiece instanceof Bishop && currentChessPiece.getColor().equals(ChessPiece.Color.WHITE)){
                        whiteBishops.add(currentField);
                    } else if (currentChessPiece instanceof Rook && currentChessPiece.getColor().equals(ChessPiece.Color.WHITE)){
                        whiteRooks.add(currentField);
                    } else if (currentChessPiece instanceof Queen && currentChessPiece.getColor().equals(ChessPiece.Color.WHITE)){
                        whiteQueens.add(currentField);
                    } else if (currentChessPiece instanceof King && currentChessPiece.getColor().equals(ChessPiece.Color.WHITE)) {
                        whiteKings.add(currentField);
                    }
                }
            }
        }
    }
}
