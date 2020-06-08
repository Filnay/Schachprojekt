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
	
	
	/*
	Creates the real GUI. the GUI itself creates the ProgressBar. Interface to Finns Board and KI.
 	*/
	
	
	/*Attributes*/
	private Board board;
	private final LineBorder selected = new LineBorder(Color.RED, 5);
	private final LineBorder offered = new LineBorder(Color.GREEN, 5);
	private final LineBorder unselected = new LineBorder(null);
	private final ChessField[][] fields = new ChessField[8][8];
	private final ProgressBar progressBar;
	private final IntelligentKI ki;
	private final Field[] lastMove = new Field[2];
	private boolean fieldsOffered = false;
	private int undoCounter = 0;
	private ChessPiece beatenChessPiece = null;
	private ChessPiece.Color playerStatus;
	private List<Field> currentPossibleMoves;
	private Field originalField;
	private Folder skin = Folder.FOLDER1;
	private TransfigurePawn transfigurePawn;
	
	enum Folder {
		FOLDER1("Schachfiguren1"), FOLDER2("Schachfiguren2"), FOLDER3("Schachfiguren3");
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
	
	public void setSkin(Folder skin) {
		this.skin = skin;
	}
	public void setPlayerStatus(ChessPiece.Color playerStatus) {
		this.playerStatus = playerStatus;
	}
	public void setBoard(Board newBoard) {
		board = newBoard;
		if (ki != null) {
			ki.setBoard(newBoard);
		}
	}
	public void setUndoCounter(int counter) {
		undoCounter = counter;
	}
	public IntelligentKI getKi() {
		return ki;
	}
	public Folder getSkin() {
		return skin;
	}
	public Board getBoard() {
		return board;
	}
	public ProgressBar getProgressBar() {
		return progressBar;
	}
	
	
	//setupField-method without KI, white is always at the bottom
	public void setupField() {
		setupField(ChessPiece.Color.WHITE);
	}
	
	//setUpField-method with KI: calls another SetupField method with attributes depending on the Color the Ki plays.
	//the Field must be constructed different whether the KI shall play as White or as Black, because the KI always needs to be on Top.
	public void setupField(ChessPiece.Color color) {
		playerStatus = color.otherColor();
		Container contents = getContentPane();
		contents.setLayout(new GridLayout(8, 8));
		ButtonHandler buttonHandler = new ButtonHandler();
		
		if (color.equals(ChessPiece.Color.WHITE)) {
			for (int row = 7; row >= 0; row--) {
				for (int column = 7; column >= 0; column--) {
					setupF(row, column, contents, buttonHandler);
				}
			}
		} else if (color.equals(ChessPiece.Color.BLACK)) {
			for (int row = 0; row < 8; row++) {
				for (int column = 0; column < 8; column++) {
					setupF(row, column, contents, buttonHandler);
				}
			}
		}
		
		clearAllBorders();
		if (color.equals(ChessPiece.Color.BLACK)) {
			ki.move();
		}
		updateBoard();
	}
	
	private void setupF(int row, int column, Container contents, ActionListener buttonHandler) {
		column = 7 - column;
		fields[row][column] = new ChessField(null);
		if ((row + column + 1) % 2 != 0) {
			fields[row][column].setBackground(Color.DARK_GRAY);
		} else {
			fields[row][column].setBackground(Color.WHITE);
		}
		contents.add(fields[row][column]);
		fields[row][column].addActionListener(buttonHandler);
	}
	
	//sets all Borders to null -> sets all Fields unselected
	public void clearAllBorders() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				fields[i][j].setBorder(unselected);
			}
		}
	}
	
	//Checks whether there is a Pawn to be Transfigured and opens a new Window to Transfigure the Pawn int another ChessPiece.
	public void checkForTransfiguration() {
		for (int column = 0; column < 7; column++) {
			ChessPiece currentBlack = board.getChessPiece(new Field(0, column));
			if (currentBlack instanceof Pawn) {
				transfigurePawn = new TransfigurePawn(currentBlack.getColor(), this, new Field(0, column));
			}
			ChessPiece currentWhite = board.getChessPiece(new Field(7, column));
			if (currentWhite instanceof Pawn) {
				transfigurePawn = new TransfigurePawn(currentWhite.getColor(), this, new Field(7, column));
			}
		}
	}

	public void updateBoard() {
		updateBoard(skin);
	}
	
	//Updates every Field of the ChessField with the current Skin
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
	//      if the Field is highlighted, it calls the Move-method
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
	//checks whether there is a Pawn to transfigured.
	//checks after every Move whether it is Checkmate.
	public void processMove(Field from, Field to) {
		lastMove[0] = from;
		lastMove[1] = to;
		beatenChessPiece = board.getChessPiece(to);
		board.move(from, to);
		checkForTransfiguration();
		updateBoard();
		
		if (ki != null) {
			ki.move();
			updateBoard();
		} else {
			switchPlayer();
		}
		
		fieldsOffered = false;
		undoCounter = 1;
		if (board.isCheckmate(ChessPiece.Color.WHITE)) {
			new GameEnd("Checkmate! Black Wins!");
		}
		if (board.isCheckmate(ChessPiece.Color.BLACK)) {
			new GameEnd("Checkmate! White Wins!");
		}
		if ((board.isStalemate(ChessPiece.Color.WHITE) && playerStatus.equals(ChessPiece.Color.WHITE)) || (board.isStalemate(ChessPiece.Color.BLACK) && playerStatus.equals(ChessPiece.Color.BLACK))) {
			new GameEnd("Stalemate!");
		}
	}
	
	//uses the saved Move form the move-method to undo the last Move
	//Playing against an KI, you cant undo your Move
	//also undoes your Transfiguration
	public void undoMove() {
		if (undoCounter == 1) {
			if (transfigurePawn != null && transfigurePawn.getTransfigured()) {
				transfigurePawn.transfigureBackToPawn();
				transfigurePawn.setTransfigured(false);
			}
			board.move(lastMove[1], lastMove[0]);
			board.putChessPieceOn(lastMove[1].row, lastMove[1].column, beatenChessPiece);
			checkForTransfiguration();
			if (ki == null) {
				switchPlayer();
			}
			updateBoard();
			undoCounter = 0;
		}
	}
	
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
	    public static void main(String[] args) {
		//Play Against yourself:
//	        GUI gui = new GUI();
//	        gui.switchPlayer();
	    //Play against KI:
			GUI gui = new GUI(ChessPiece.Color.WHITE);
			gui.switchPlayer();
	    }
}
