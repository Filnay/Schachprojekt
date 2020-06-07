package chess.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUIChangeSkin extends JFrame {

    public GUIChangeSkin(GUI gui, GUILegend guiLegend, GUIControls guiControls) {
        super("Chess Skins");
        setVisible(false);
        setSize(500, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        showSkins(gui, guiLegend, guiControls);
    }

    public void showSkins(GUI gui, GUILegend guiLegend, GUIControls guiControls) {
        JPanel skinPanelFrame = new JPanel();

        skinPanelFrame.setLayout(new GridLayout(0, 3, 30, 20));
        skinPanelFrame.setBorder(new LineBorder(Color.WHITE, 20));
        skinPanelFrame.setBackground(Color.WHITE);

        JPanel skinPanel1 = new JPanel();
        skinPanel1.setSize(this.getWidth() / 3, this.getHeight()/ 3);

        Chessfield skin1 = new Chessfield(null);
        skin1.setSize(100, 100);
        skin1.setButtonIconTo("Schachfiguren 1/King_Black.png");
        skin1.setBackground(Color.lightGray);
        skin1.setBorder(null);

        skin1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.setSkin(GUI.Folder.FOLDER1);
                gui.updateBoard();
                guiControls.setNewLegend(gui);
            }
        });

        skinPanel1.add(skin1);
        skinPanelFrame.add(skinPanel1);

        JPanel skinPanel2 = new JPanel();
        skinPanel2.setSize(100, 100);

        Chessfield skin2 = new Chessfield(null);
        skin2.setSize(100, 100);
        skin2.setButtonIconTo("Schachfiguren 2/King_Black.png");
        skin2.setBackground(Color.lightGray);
        skin2.setBorder(null);

        skin2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.setSkin(GUI.Folder.FOLDER2);
                gui.updateBoard();
                guiControls.setNewLegend(gui);
            }
        });
        skinPanel2.add(skin2);
        skinPanelFrame.add(skinPanel2);

        JPanel skinPanel3 = new JPanel();
        skinPanel3.setSize(100, 100);

        Chessfield skin3 = new Chessfield(null);
        skin3.setSize(100, 100);
        skin3.setButtonIconTo("Schachfiguren 3/King_Black.png");
        skin3.setBackground(Color.lightGray);
        skin3.setBorder(null);

        skin3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.setSkin(GUI.Folder.FOLDER3);
                gui.updateBoard();
                guiControls.setNewLegend(gui);
            }
        });

        skinPanel3.add(skin3);
        skinPanelFrame.add(skinPanel3);

        add(skinPanelFrame);
        setVisible(false);
    }

    public static void main(String[] args) {
//        new GUIChangeSkin(new GUI());
    }
}
