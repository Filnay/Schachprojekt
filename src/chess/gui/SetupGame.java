package chess.gui;

import com.sun.source.tree.WhileLoopTree;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetupGame extends JFrame {

    public SetupGame() {
        super("Chess Setup");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setupGUI();
    }

    public void setupGUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 5, 10, 10));
        panel.setBorder(new LineBorder(Color.WHITE, 30));
        panel.setBackground(Color.WHITE);



        JLabel switchMode = new JLabel("<html><body>Do you want to play<br> against KI or other Player?</body></html>");
        switchMode.setVisible(true);
        panel.add(switchMode);


        ButtonGroup mode = new ButtonGroup();

        JRadioButton kI = new JRadioButton("KI");
        kI.setBackground(Color.WHITE);
        kI.setVisible(true);
        mode.add(kI);
        panel.add(kI);

        JRadioButton otherPlayer = new JRadioButton("Other Player");
        otherPlayer.setBackground(Color.WHITE);
        otherPlayer.setVisible(true);
        mode.add(otherPlayer);
        panel.add(otherPlayer);


        JPanel spacer1 = new JPanel();
        spacer1.setBackground(Color.WHITE);
        panel.add(spacer1);



        JLabel switchColor = new JLabel("<html><body>Do you want to play<br>as Black or as White?</body></html>");
        switchColor.setVisible(true);
        panel.add(switchColor);

        ButtonGroup color = new ButtonGroup();


        JRadioButton White = new JRadioButton("White");
        White.setBackground(Color.WHITE);
        White.setVisible(true);
        color.add(White);
        panel.add(White);

        JRadioButton Black = new JRadioButton("Black");
        Black.setBackground(Color.WHITE);
        Black.setVisible(true);
        color.add(Black);
        panel.add(Black);


        JPanel spacer2 = new JPanel();
        spacer2.setBackground(Color.WHITE);
        panel.add(spacer2);

        JPanel spacer3 = new JPanel();
        spacer3.setBackground(Color.WHITE);
        panel.add(spacer3);

        JPanel spacer4 = new JPanel();
        spacer4.setBackground(Color.WHITE);
        panel.add(spacer4);

        JPanel spacer5 = new JPanel();
        spacer5.setBackground(Color.WHITE);
        panel.add(spacer5);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);

        JButton startGame = new JButton("Start Game!");
        startGame.setBackground(Color.lightGray);

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color;
                boolean gameMode;

//                if (Black.isSelected()) {
//                    color = Color.BLACK;
//                }else {
//                    color = Color.WHITE;
//                }

                gameMode = kI.isSelected();
                new GUIControls();
            }
        });

        startGame.setVisible(true);

        buttonPanel.add(startGame);
        panel.add(buttonPanel);

        panel.setVisible(true);
        add(panel);
    }

    public static void main(String[] args) {
        new SetupGame();
    }
}
