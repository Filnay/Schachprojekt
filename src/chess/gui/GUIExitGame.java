package chess.gui;
//imports
import javax.swing.*;
import java.awt.*;

public class GUIExitGame extends JFrame {

    //Constructor
    public GUIExitGame() {
        super("Are you sure?");
        setLocationRelativeTo(null);
        setSize(300, 200);
        setVisible(false);
        setLocationRelativeTo(null);

        //Declaring and Initializing a new Panel wit a GridBagLayout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //set Constraints for the GridBagLayout
        constraints.fill = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10,20, 10, 20);
        constraints.weightx = GridBagConstraints.CENTER;


        //Label, whether you want to exit the game
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        JLabel verificationText = new JLabel("Do you really want to exit the Game?");
        verificationText.setBounds(10, 10, 100, 30);
        verificationText.setVisible(true);
        panel.add(verificationText, constraints);

        //Button with yes, exits the Program
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        JButton yes = new JButton("Yes");
//        yes.setBorder(null);
        yes.setBackground(Color.lightGray);
        yes.addActionListener(e -> System.exit(0));
        yes.setVisible(true);
        panel.add(yes, constraints);

        //Button with no, sets the Window invisible
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.CENTER;
        JButton no = new JButton("No");
        no.setBackground(Color.LIGHT_GRAY);
//        no.setBorder(null);
        no.addActionListener(e -> setVisible(false));
        panel.add(no, constraints);
        add(panel);

    }
}
