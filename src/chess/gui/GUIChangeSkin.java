package chess.gui;
//imports
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GUIChangeSkin extends JFrame {

    //Constructor
    public GUIChangeSkin(GUI gui, GUIControls guiControls) {
        super("Chess Skins");
        setVisible(false);
        setSize(500, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        showSkins(gui, guiControls);
    }

    //shows all Skins
    public void showSkins(GUI gui, GUIControls guiControls) {

        //Declaring and Initializing a new Panel with a GridLayout
        JPanel skinPanelFrame = new JPanel();
        skinPanelFrame.setLayout(new GridLayout(0, 3, 30, 20));
        skinPanelFrame.setBorder(new LineBorder(Color.WHITE, 20));
        skinPanelFrame.setBackground(Color.WHITE);

        //setting Up a new ChessField-Button, and load the king-Image of the FIRST Skin
        Chessfield skin1 = new Chessfield(null);
        skin1.setSize(100, 100);
        skin1.setButtonIconTo("Schachfiguren 1/King_Black.png");
        skin1.setBackground(Color.lightGray);
        skin1.setBorder(null);

        //problem:it needs to signalized to the Legend that the Skin has been changed
        // so the Button calls an method located in UGIControls that calls an method that updates the Legend
        skin1.addActionListener(e -> {
            gui.setSkin(GUI.Folder.FOLDER1);
            gui.updateBoard();
            guiControls.setNewLegend(gui, true);
            guiControls.setLegendCount(1);
        });
        skinPanelFrame.add(skin1);

        //setting up a new ChessField-Button, and load the king-Image of the SECOND skin
        Chessfield skin2 = new Chessfield(null);
        skin2.setSize(100, 100);
        skin2.setButtonIconTo("Schachfiguren 2/King_Black.png");
        skin2.setBackground(Color.lightGray);
        skin2.setBorder(null);

        //problem:it needs to signalized to the Legend that the Skin has been changed
        // so the Button calls an method located in UGIControls that calls an method that updates the Legend
        skin2.addActionListener(e -> {
            gui.setSkin(GUI.Folder.FOLDER2);
            gui.updateBoard();
            guiControls.setNewLegend(gui, true);
            guiControls.setLegendCount(1);
        });
        skinPanelFrame.add(skin2);

        //setting up a new ChessField-Button, and load the king-Image of the THIRD skin
        Chessfield skin3 = new Chessfield(null);
        skin3.setSize(100, 100);
        skin3.setButtonIconTo("Schachfiguren 3/King_Black.png");
        skin3.setBackground(Color.lightGray);
        skin3.setBorder(null);

        //problem:it needs to signalized to the Legend that the Skin has been changed
        // so the Button calls an method located in UGIControls that calls an method that updates the Legend
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
}
