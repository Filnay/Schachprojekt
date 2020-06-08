package chess.gui;

import chess.Board;
import chess.chesspiece.ChessPiece;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GUIControls extends JFrame {
	
	/*
	Sets up the game. Creates a control panel the change settings mid game. Creates all other Elements.
 	*/
	
	
	/*
	Attributes
	*/
	
	JButton undo = new JButton("Undo");
	JButton reset = new JButton("Reset");
	JButton toggleLegend = new JButton("Show Legend");
	JButton changeSkin = new JButton("ChangeSkin");
	JButton toggleScore = new JButton("Toggle Score");
	JButton close = new JButton("Exit Game");
	
	JPanel controlPanel = new JPanel();
	
	//Declares legend; only one that needs to be declared here because otherwise
	//the changing of the Skins wouldn't affect the legend/toggling wound't work
	GUILegend legends;
	private int legendCount;
	
	//Constructor
	public GUIControls(ChessPiece.Color colorOfKI) {
		super("Chess Controls");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setupControls(colorOfKI);
	}
	
	public void setLegendCount(int count) {
		legendCount = count;
	}
	
	public void setNewLegend(GUI chessGUI, boolean visibility) {
		legends.dispose();
		legends = new GUILegend(chessGUI);
		legends.setVisible(visibility);
	}
	
	public void setupControls(ChessPiece.Color colorOfKi) {
		GUI chessGUI;
		
		//calls a different GUI-Constructor, depending on whether you want to play against a KI or an other Player
		boolean isKI;
		if (colorOfKi != null) {
			isKI = true;
			chessGUI = new GUI(colorOfKi);
		} else {
			isKI = false;
			chessGUI = new GUI();
		}
		reset(chessGUI);
		
		setLocation(chessGUI.getX() + chessGUI.getWidth(), chessGUI.getY());
		setSize(300, chessGUI.getHeight());
		
		
		/*Declarations and Initializations*/
		
		legends = new GUILegend(chessGUI);
		GUIExitGame exit = new GUIExitGame();
		GUIChangeSkin changeChessPieceSkin = new GUIChangeSkin(chessGUI, this);
		
		controlPanel.setBorder(new LineBorder(Color.WHITE, 30));
		GridLayout controlPanelLayout = new GridLayout(6, 1, 20, 20);
		controlPanel.setLayout(controlPanelLayout);
		controlPanel.setBackground(Color.WHITE);
		
		
		/*Settings for the Buttons*/
		//All Buttons that open a new Window are Toggle Buttons, so they toggle the Windows
		
		undo.setBackground(Color.lightGray);
		undo.setBorder(null);
		if (isKI) {
			undo.setAlignmentX(Component.CENTER_ALIGNMENT);
			undo.setText("<html>You can't undo your move<br>while playing against a KI</html>");
		} else {
			undo.addActionListener(e -> chessGUI.undoMove());
		}
		controlPanel.add(undo);
		
		
		reset.setBackground(Color.lightGray);
		reset.setBorder(null);
		reset.addActionListener(e -> reset(chessGUI));
		controlPanel.add(reset);
		
		
		//Legend-Button, only one that replaces the old Legend with a new, and does not just simply make it visible/invisible.
		toggleLegend.setBackground(Color.lightGray);
		toggleLegend.setBorder(null);
		toggleLegend.addActionListener(e -> {
			if (legendCount == 0) {
				setNewLegend(chessGUI, true);
				legendCount = 1;
			} else if (legendCount == 1) {
				setNewLegend(chessGUI, false);
				legendCount = 0;
				
			}
		});
		controlPanel.add(toggleLegend);
		
		
		toggleScore.setBackground(Color.lightGray);
		toggleScore.setBorder(null);
		chessGUI.getProgressBar().setVisible(true);
		toggleScore.addActionListener(e -> {
			JFrame progressBar = chessGUI.getProgressBar();
			progressBar.setVisible(!progressBar.isVisible());
		});
		controlPanel.add(toggleScore);
		
		
		changeSkin.setBackground(Color.lightGray);
		changeSkin.setBorder(null);
		
		changeSkin.addActionListener(e -> changeChessPieceSkin.setVisible(!changeChessPieceSkin.isVisible()));
		controlPanel.add(changeSkin);
		
		
		close.setBackground(Color.lightGray);
		close.setBorder(null);
		close.addActionListener(e -> exit.setVisible(!exit.isVisible()));
		controlPanel.add(close);
		
		
		controlPanel.setVisible(true);
		add(controlPanel);
		setVisible(true);
	}
	
	private void reset(GUI chessGUI) {
		Board newBoard = new Board();
		chessGUI.setBoard(newBoard);
		chessGUI.setPlayerStatus(ChessPiece.Color.WHITE);
		chessGUI.updateBoard();
		chessGUI.setUndoCounter(0);
		if (chessGUI.getKi() != null) {
			if (chessGUI.getKi().getColor().equals(ChessPiece.Color.WHITE)) {
				chessGUI.getKi().move();
				chessGUI.setPlayerStatus(ChessPiece.Color.BLACK);
				chessGUI.updateBoard();
			}
		}
	}
	
	public static void main(String[] args) {
		ChessPiece.Color color = ChessPiece.Color.WHITE;
		if (args.length > 0 && args[0].toLowerCase().startsWith("b")) color = ChessPiece.Color.BLACK;
		new GUIControls(color);
	}
}
