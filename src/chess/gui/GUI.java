package chess.gui;

import chess.Board;
import chess.Field;
import chess.chesspiece.*;
import chess.kI.IntelligentKI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {
    private Board board = new Board();

    private final Chessfield[][] fields = new Chessfield[8][8];

    private final LineBorder selected = new LineBorder(Color.RED, 5);
    private final LineBorder offered = new LineBorder(Color.GREEN, 5);
    private final LineBorder unselected = new LineBorder(null);

    private List<Field> currentPossibleMoves;
    private Field originalField;
    private boolean fieldsOffered = false;

    private ChessPiece.Color playerStatus;

    private int undoCounter = 0;

    private Folder skin = Folder.FOLDER1;
    ChessPiece beatenChessPiece = null;


    private final ProgressBar progressBar;


    private final Field[] lastMove = new Field[2];


    private IntelligentKI ki;

    enum Folder {
        FOLDER1("Schachfiguren 1"), FOLDER2("Schachfiguren 2"), FOLDER3("Schachfiguren 3");
        public final String name;

        Folder(String name) {
            this.name = name;
        }
    }

    public GUI() {
        super("Chess");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        progressBar = new ProgressBar(getX(), getY(), 700, board);
        ki = null;
        setupField();
    }

    public GUI(ChessPiece.Color colorOfKI) {
        super("Chess");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        this.ki = new IntelligentKI(board, colorOfKI);
        progressBar = new ProgressBar(getX(), getY(), 700, board);
        setupField(colorOfKI);
        playerStatus = colorOfKI.otherColor();
        updateBoard();
    }


    public IntelligentKI getKi() {
        return ki;
    }

    public void setKi(IntelligentKI ki) {
        this.ki = ki;
    }

    public void setPlayerStatus(ChessPiece.Color playerStatus) {
        this.playerStatus = playerStatus;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public Board getBoard() {
        return board;
    }

    public Folder getSkin() {
        return skin;
    }

    public void setSkin(Folder skin) {
        this.skin = skin;
    }

    public void setUndoCounter(int counter) {
        undoCounter = counter;
    }

    public void setupField() {
        setupField(ChessPiece.Color.WHITE);
    }


    public void setupField(ChessPiece.Color color) {
        Container contents = getContentPane();
        contents.setLayout(new GridLayout(8, 8));

        ButtonHandler buttonHandler = new ButtonHandler();
        if (color.equals(ChessPiece.Color.BLACK)) {
            for (int row = 7; row >= 0; row--) {
                setupF(row, contents, buttonHandler);
            }
        } else if (color.equals(ChessPiece.Color.WHITE)) {
            for (int row = 0; row < 8; row++) {
                setupF(row, contents, buttonHandler);
            }
        }
        clearAllBorders();
        playerStatus = color;
        if (playerStatus.equals(ChessPiece.Color.WHITE)) {
            processMove(new Field(0, 0), new Field(0, 0));
        }
        updateBoard();
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
        updateBoard(skin);
    }


    private void updateBoard(Folder folder) {
        for (int row = 7; row >= 0; row--) {
            for (int column = 0; column < 8; column++) {
                ChessPiece current = board.getChessPiece(new Field(row, column));
                if (current != null) {
                    String url = getURLFromChessPiece(current);
                    fields[row][column].setButtonIconTo(folder.name + "/" + url);
                } else {
                    fields[row][column].setIcon(null);
                }
            }
        }
        progressBar.updateScoreBar(board);
    }


    public void setBoard(Board newBoard) {
        board = newBoard;
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
        lastMove[0] = from;
        lastMove[1] = to;
        beatenChessPiece = board.getChessPiece(to);
        board.move(from, to);
        checkForTransfiguration();
        updateBoard();
        if(ki != null) {
            ki.move();
            updateBoard();
        } else {
            switchPlayer();
        }
        fieldsOffered = false;
        undoCounter = 1;
        if (board.isCheckmate(ChessPiece.Color.WHITE)){
            new GameEnd("Black Wins!", this);
        }
        if (board.isCheckmate(ChessPiece.Color.BLACK)){
            new GameEnd("White Wins!", this);
        }
        if (board.isStalemate(ChessPiece.Color.WHITE) || board.isStalemate(ChessPiece.Color.BLACK)){
            new GameEnd("Stalemate!", this);
        }
    }

    public void checkForTransfiguration() {
        for (int row = 0; row < 7; row++) {
            ChessPiece current = board.getChessPiece(new Field(row, 0));
            if (current != null && current.getClass().getName().equals("Pawn")) {
                new TransfigurePawn(current.getColor(), this, new Field(row, 0));
            }
        }
        for (int row = 0; row < 7; row++) {
            ChessPiece current = board.getChessPiece(new Field(7, row));
            if (current != null && current.getClass().getName().equals("Pawn")) {
                new TransfigurePawn(current.getColor(), this, new Field(row, 7));
            }
        }
    }

    public void undoMove() {
        if (undoCounter == 1) {
            board.move(lastMove[1], lastMove[0]);
            board.putChessPieceOn(lastMove[1].row, lastMove[1].column, beatenChessPiece);
            if (ki == null) {
                switchPlayer();
            }
            updateBoard();
            undoCounter = 0;
        }
    }

    public void switchPlayer() {
        if (playerStatus == ChessPiece.Color.WHITE) {
            playerStatus = ChessPiece.Color.BLACK;
        } else {
            playerStatus = ChessPiece.Color.WHITE;
        }
    }

    public String getURLFromChessPiece(ChessPiece chesspiece) {
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
        new GUI(ChessPiece.Color.WHITE);
    }
}
