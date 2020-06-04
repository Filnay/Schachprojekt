package chess.GUI;

import chess.Board;
import chess.Field;
import chess.chesspiece.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GUI extends JFrame {
    private final Board board = new Board();

    private final Chessfield[][] fields = new Chessfield[8][8];

    private final LineBorder selected = new LineBorder(Color.RED, 2);
    private final LineBorder offered = new LineBorder(Color.GREEN, 2);
    private final LineBorder unselected = new LineBorder(null);

    private List<Field> currentPossibleMoves;
    private Field originalField;
    private boolean fieldsOffered = false;

    private ChessPiece.Color playerStatus;


    public GUI() {
        super("Chess!");
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setupField();
        updateBoard();
    }

    private void setupField() {
        setupField(Color.WHITE);
    }


    private void setupField(Color color) {
        Container contents = getContentPane();
        contents.setLayout(new GridLayout(8, 8));

        ButtonHandler buttonHandler = new ButtonHandler();
        if (color.equals(Color.WHITE)) {
            for (int row = 7; row >= 0; row--) {
                setupF(row, contents, buttonHandler);
            }
        } else if (color.equals(Color.BLACK)) {
            for (int row = 0; row < 8; row++) {
                setupF(row, contents, buttonHandler);
            }
        }
        clearAllBorders();
        playerStatus = ChessPiece.Color.WHITE;
    }

    private void setupF(int row, Container contents, ActionListener buttonHandler) {
        for (int column = 0; column < 8; column++) {
            fields[row][column] = new Chessfield(null);
            if ((row + column + 1) % 2 != 0) {
                fields[row][column].setBackground(Color.DARK_GRAY);
            } else {
                fields[row][column].setBackground(Color.WHITE);
            }
            contents.add(fields[row][column]);
            fields[row][column].addActionListener(buttonHandler);
        }
    }


    public void clearAllBorders() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                fields[i][j].setBorder(unselected);
            }
        }
    }


    public void updateBoard() {
        for (int row = 7; row >= 0; row--) {
            for (int column = 0; column < 8; column++) {
                ChessPiece current = board.getChessPiece(new Field(row, column));
                if (current != null) {
                    String url = getURLFromChessPiece(current);
                    fields[row][column].setIconTo(url);
                } else {
                    fields[row][column].setIcon(null);
                }
            }
        }
    }


    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            for (int row = 0; row < 8; row++) {
                for (int column = 0; column < 8; column++) {
                    if (source == fields[row][column]) {
                        clearAllBorders();
                        if (fieldsOffered) {
                            for (Field possibleMoves : currentPossibleMoves) {
                                if (source.equals(fields[possibleMoves.row][possibleMoves.column])) {
                                    processMove(originalField, possibleMoves);
                                }
                            }
                            fields[row][column].setBorder(selected);
                            processSelection(row, column);
                            return;
                        } else {
                            fields[row][column].setBorder(selected);
                            processSelection(row, column);
                            return;
                        }
                    }
                }
            }
        }
    }


    private void processSelection(int row, int column) {
        clearAllBorders();
        fields[row][column].setBorder(selected);

        if (board.getChessPiece(new Field(row, column)) == null) {
            fieldsOffered = false;
            return;
        }

        ChessPiece selectedChessPiece = board.getChessPiece(new Field(row, column));
        ChessPiece.Color selectedChessPieceColor = selectedChessPiece.getColor();

        if (playerStatus == ChessPiece.Color.WHITE && playerStatus == selectedChessPieceColor) {
            ArrayList<Field> fieldList = (ArrayList<Field>) board.getMoves(row, column);
            offerFields(fieldList);
            originalField = new Field(row, column);
            fieldsOffered = true;

        }
        if (playerStatus == ChessPiece.Color.BLACK && playerStatus == selectedChessPieceColor) {
            ArrayList<Field> fieldList = (ArrayList<Field>) board.getMoves(row, column);
            offerFields(fieldList);
            originalField = new Field(row, column);
            fieldsOffered = true;
        }
    }


    public void offerFields(ArrayList<Field> possibleMoves) {
        for (Field possibleField : possibleMoves) {
            fields[possibleField.row][possibleField.column].setBorder(offered);
        }
        currentPossibleMoves = possibleMoves;
    }


    public void processMove(Field from, Field to) {
        board.move(from, to);
        updateBoard();
        if (playerStatus == ChessPiece.Color.WHITE) {
            playerStatus = ChessPiece.Color.BLACK;
        } else {
            playerStatus = ChessPiece.Color.WHITE;
        }
        fieldsOffered = false;
    }

    private String getURLFromChessPiece(ChessPiece chesspiece) {
        String URL = "";

        if (chesspiece instanceof Pawn) {
            if (chesspiece.getColor() == ChessPiece.Color.WHITE) {
                URL = "Pawn_White.png";
            } else {
                URL = "Pawn_Black.png";
            }
        } else if (chesspiece instanceof King) {
            if (chesspiece.getColor() == ChessPiece.Color.WHITE) {
                URL = "King_White.png";
            } else {
                URL = "King_Black.png";
            }
        } else if (chesspiece instanceof Queen) {
            if (chesspiece.getColor() == ChessPiece.Color.WHITE) {
                URL = "Queen_White.png";
            } else {
                URL = "Queen_Black.png";
            }
        } else if (chesspiece instanceof Bishop) {
            if (chesspiece.getColor() == ChessPiece.Color.WHITE) {
                URL = "Bishop_White.png";
            } else {
                URL = "Bishop_Black.png";
            }
        } else if (chesspiece instanceof Knight) {
            if (chesspiece.getColor() == ChessPiece.Color.WHITE) {
                URL = "Knight_White.png";
            } else {
                URL = "Knight_Black.png";
            }
        } else if (chesspiece instanceof Rook) {
            if (chesspiece.getColor() == ChessPiece.Color.WHITE) {
                URL = "Rook_White.png";
            } else {
                URL = "Rook_Black.png";
            }
        }

        return URL;
    }


    public static void main(String[] args) {
        new GUI();
    }
}
