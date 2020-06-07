package chess.gui;
//imports
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
    /*Attributes*/

    //Bord to handle the ChessPieces
    private Board board;

    //New 2Dimensional ChessField
    private final Chessfield[][] fields = new Chessfield[8][8];

    //Different types of Outlining for selected/Offered Chess Pieces
    private final LineBorder selected = new LineBorder(Color.RED, 5);
    private final LineBorder offered = new LineBorder(Color.GREEN, 5);
    private final LineBorder unselected = new LineBorder(null);

    //new List of possible Moves
    private List<Field> currentPossibleMoves;

    //Attributes for the undo-method
    private int undoCounter = 0;
    private final Field[] lastMove = new Field[2];
    private Field originalField;
    ChessPiece beatenChessPiece = null;

    //active Player
    private ChessPiece.Color playerStatus;

    //Attribute for the move-method
    private boolean fieldsOffered = false;

    //Standard Folder to load the Pieces from
    private Folder skin = Folder.FOLDER1;

    //New ProgressBar object
    private final ProgressBar progressBar;

    //New KI object
    private IntelligentKI ki;

    //Enumeration of the different Folders to load the Skins from
    enum Folder {
        FOLDER1("Schachfiguren 1"), FOLDER2("Schachfiguren 2"), FOLDER3("Schachfiguren 3");
        public final String name;

        Folder(String name) {
            this.name = name;
        }
    }

    //Constructor for GUI without KI
    public GUI() {
        super("Chess");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        progressBar = new ProgressBar(getX(), getY(), 700);
        ki = null;
        setBoard(new Board());
        setupField();
        updateBoard();
    }

    //Constructor for GUI with KI
    public GUI(ChessPiece.Color colorOfKI) {
        super("Chess");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        ki = new IntelligentKI(colorOfKI);
        progressBar = new ProgressBar(getX(), getY(), 700);
        setBoard(new Board());
        setupField(colorOfKI.otherColor());
        updateBoard();
    }

    /*Get and set- methods*/

    //KI
    public IntelligentKI getKi() {
        return ki;
    }
    public void setKi(IntelligentKI ki) {
        this.ki = ki;
    }

    //Skin
    public Folder getSkin() {
        return skin;
    }

    public void setSkin(Folder skin) {
        this.skin = skin;
    }

    //PlayerStatus
    public void setPlayerStatus(ChessPiece.Color playerStatus) {
        this.playerStatus = playerStatus;
    }

    public void setBoard(Board newBoard) {
        board = newBoard;
        if (ki != null) {
            ki.setBoard(newBoard);
        }
    }

    //Progressbar
    public ProgressBar getProgressBar() {
        return progressBar;
    }

    //Board
    public Board getBoard() {
        return board;
    }

    //Undo counter
    public void setUndoCounter(int counter) {
        undoCounter = counter;
    }

    //setupField-method without KI: calls SetupField with the value White; White is always at the Bottom
    public void setupField() {
        setupField(ChessPiece.Color.WHITE);
    }

    //setUpField-method with KI: calls another SetupField method with attributes depending on the Color that is given.
    //the Field must be constructed different whether the KI shall play as White or as Black, the KI always needs to be on Top.
    public void setupField(ChessPiece.Color color) {
        playerStatus = color.otherColor();
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
        if (color.equals(ChessPiece.Color.WHITE)) {
            processMove(new Field(0, 0), new Field(0, 0));
        }
        updateBoard();
    }

    //setup-method, Builds the Field and adds the ActionListeners
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

    //sets all Borders to null -> sets all Fields unselected
    public void clearAllBorders() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                fields[i][j].setBorder(unselected);
            }
        }
    }

    //Checks the Top and Bottom row for an Pawn, and opens a new Window when there is one
    //Doesnt work properly //TODO
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


    //IDEA: mark all Pieces of the current Player so that it is clear who's turn it is.
    //doesnt Work
//    public void highlightCurrentPlayer() {
//        for (int row = 0; row < 8; row++) {
//            for (int column = 0; column < 8; column++) {
//                ChessPiece current = board.getChessPiece(new Field(row, column));
//                if (current != null && current.getColor().equals(playerStatus)) {
//                    fields[row][column].setBorder(highlighted);
//                }
//            }
//        }
//    }


    //Updates the Board after an Move, calls another Method with the current Skin
    public void updateBoard() {
        updateBoard(skin);
    }

    //Updates every Field of the ChessField with the current Skin, calls GetURLFromChessPiece-method to get the URLs
    //updates the ProgressBar
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

    //reacts to the Click on a ChessField:
    //      if there isn't an ChessPiece, it just selects the Field
    //      if there is an ChessPiece on that Field, it calls the highlights-method
    //      if the Field is Highlighted, it calls the Move-method
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
                        }
                        fields[row][column].setBorder(selected);
                        processSelection(row, column);
                        return;
                    }
                }
            }
        }
    }

    //Manages that you can only move your own ChessPieces
    //highlights the Field you clicked on in red, when there is a ChessPiece on it, offerFields-method is called
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

    //offers the possible Moves of the ChessPiece you clicked on in green
    public void offerFields(ArrayList<Field> possibleMoves) {
        for (Field possibleField : possibleMoves) {
            fields[possibleField.row][possibleField.column].setBorder(offered);
        }
        currentPossibleMoves = possibleMoves;
    }

    //saves the Move to maybe undo it, then moves the Figure in the Board and updates the GUI.
    //checks after every Move whether it is Checkmate
    public void processMove(Field from, Field to) {
        lastMove[0] = from;
        lastMove[1] = to;
        beatenChessPiece = board.getChessPiece(to);
        board.move(from, to);
        updateBoard();
        checkForTransfiguration();
        if(ki != null) {
            ki.move();
            updateBoard();
        } else {
            switchPlayer();
        }
        fieldsOffered = false;
        undoCounter = 1;
        boolean whiteCheckmate = board.isCheckmate(ChessPiece.Color.WHITE);
        if (whiteCheckmate){
            new GameEnd("Checkmate! Black Wins!");
        }
        if (board.isCheckmate(ChessPiece.Color.BLACK)){
            new GameEnd("Checkmate! White Wins!");
        }
        if (board.isStalemate(ChessPiece.Color.WHITE) || board.isStalemate(ChessPiece.Color.BLACK)){
            new GameEnd("Stalemate!");
        }
    }

    //uses the saved Move form the move-method to undo the last Move
    //playing against a KI, the Function only undoes your move, not the one of the KI//TODO
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

    //switches the current Player
    public void switchPlayer() {
        playerStatus = playerStatus.otherColor();
    }

    //specifies the URL of the ChessPieces
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

    //for testing
//    public static void main(String[] args) {
//        new GUI(ChessPiece.Color.BLACK);
//    }
}
