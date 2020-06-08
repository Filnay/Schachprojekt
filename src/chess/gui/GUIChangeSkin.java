package chess.gui;
import chess.chesspiece.ChessPiece;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GUIChangeSkin extends JFrame {

    /*
    Opens a window with all available Skins. Changes the Skin int the selected one when clicked.
    Also updates the Legend with the new Skin.
    */
    
    
    //Constructor
    public GUIChangeSkin(GUI gui, GUIControls guiControls) {
        super("Chess Skins");
        setVisible(false);
        setSize(500, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        showSkins(gui, guiControls);
    }
    
    public void showSkins(GUI gui, GUIControls guiControls) {

        JPanel skinPanelFrame = new JPanel();
        skinPanelFrame.setLayout(new GridLayout(0, 3, 30, 20));
        skinPanelFrame.setBorder(new LineBorder(Color.WHITE, 20));
        skinPanelFrame.setBackground(Color.WHITE);

        ChessField skin1 = new ChessField(null);
        skin1.setSize(100, 100);
        skin1.setButtonIconTo(GUI.Folder.FOLDER1.name + "/King_Black.png");
        skin1.setBackground(Color.lightGray);
        skin1.setBorder(null);

        //problem:it needs to signalized to the Legend that the Skin has been changed,
        //so the Button calls an method located in GUIControls that calls an method that updates the Legend.
        skin1.addActionListener(e -> {
            gui.setSkin(GUI.Folder.FOLDER1);
            gui.updateBoard();
            guiControls.setNewLegend(gui, true);
            guiControls.setLegendCount(1);
        });
        skinPanelFrame.add(skin1);

        ChessField skin2 = new ChessField(null);
        skin2.setSize(100, 100);
        skin2.setButtonIconTo(GUI.Folder.FOLDER2.name + "/King_Black.png");
        skin2.setBackground(Color.lightGray);
        skin2.setBorder(null);

        skin2.addActionListener(e -> {
            gui.setSkin(GUI.Folder.FOLDER2);
            gui.updateBoard();
            guiControls.setNewLegend(gui, true);
            guiControls.setLegendCount(1);
        });
        skinPanelFrame.add(skin2);

        ChessField skin3 = new ChessField(null);
        skin3.setSize(100, 100);
        skin3.setButtonIconTo(GUI.Folder.FOLDER3.name + "/King_Black.png");
        skin3.setBackground(Color.lightGray);
        skin3.setBorder(null);

        skin3.addActionListener(e -> {
            gui.setSkin(GUI.Folder.FOLDER3);
            gui.updateBoard();
            guiControls.setNewLegend(gui, true);
            guiControls.setLegendCount(1);
        });
        skinPanelFrame.add(skin3);

        add(skinPanelFrame);
        setVisible(false);
    }
    
    //No Main/Testing methode, because you need to hand over an GUI and the GUIControls, but the GUiControls create a new GUi,
    // so you have two and get an Error.
    
    public static void main(String[] args) {
        new GUIChangeSkin(new GUI(), new GUIControls(ChessPiece.Color.WHITE));
    }
}
