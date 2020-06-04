package chess.GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUIControls extends JFrame {
    JButton reset = new JButton("Reset");
    JButton switchSides = new JButton("Switch Sides");
    JButton changeSkin = new JButton("ChangeSkin");
    JButton undo = new JButton("Undo");

    public GUIControls() {
        super("Chess Controls!");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GUI chessGUI = new GUI();
        int guiX = chessGUI.getX();
        int guiWidth = chessGUI.getWidth();
        int guiY = chessGUI.getY();
        setLocation(guiX + guiWidth, guiY);
        setSize(300, 700);
        setResizable(false);
        setupControls();
    }

    public void setupControls(){
        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(new LineBorder(Color.WHITE, 30));
        GridLayout controlPanelLayout = new GridLayout(4, 1, 20, 20);
        controlPanel.setLayout(controlPanelLayout);
        controlPanel.setBackground(Color.WHITE);

        reset.setBackground(Color.lightGray);
        reset.setBorder(null);
        //reset.addActionListener(ActionEvent e);
        controlPanel.add(reset);

        switchSides.setBackground(Color.lightGray);
        switchSides.setBorder(null);
        controlPanel.add(switchSides);

        changeSkin.setBackground(Color.lightGray);
        changeSkin.setBorder(null);
        controlPanel.add(changeSkin);

        undo.setBackground(Color.lightGray);
        undo.setBorder(null);
        controlPanel.add(undo);

        controlPanel.setVisible(true);
        add(controlPanel);
    }

    public static void main(String[] args) {
        new GUIControls();
    }
}
