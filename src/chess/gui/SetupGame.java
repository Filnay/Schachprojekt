package chess.gui;

import javax.swing.*;
import java.awt.*;

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
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10,20, 10, 20);
        constraints.weightx = GridBagConstraints.CENTER;

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;


        JLabel switchMode = new JLabel("<html><body>Do you want to play<br> against KI or other Player?</body></html>");
        switchMode.setVisible(true);
        panel.add(switchMode);

        ButtonGroup mode = new ButtonGroup();

        constraints.gridx = 4;
        constraints.gridwidth = 1;
        JRadioButton kI = new JRadioButton("KI");
        kI.setVisible(true);
        mode.add(kI);
        panel.add(kI);

        constraints.gridx = 5;
        JRadioButton otherPlayer = new JRadioButton("Other Player");
        otherPlayer.setVisible(true);
        mode.add(otherPlayer);
        panel.add(otherPlayer);


        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;


        JLabel switchColor = new JLabel("<html><body>Do you want to play<br>as Black or as White?</body></html>");
        switchColor.setVisible(true);
        panel.add(switchColor);

        ButtonGroup color = new ButtonGroup();

        constraints.gridx = 4;
        constraints.gridwidth = 1;
        JRadioButton White = new JRadioButton("White");
        White.setVisible(true);
        color.add(White);
        panel.add(White);

        constraints.gridx = 5;
        JRadioButton Black = new JRadioButton("Black");
        Black.setVisible(true);
        color.add(Black);
        panel.add(Black);
        
        
        panel.setVisible(true);
        add(panel);
    }

    public static void main(String[] args) {
        new SetupGame();
    }
}
