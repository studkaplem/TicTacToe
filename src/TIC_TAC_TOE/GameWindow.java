package TIC_TAC_TOE;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GameWindow extends JFrame {
    protected GameWindow instance2;

    // weil wir es ausserhalb dieser Klasse benutzen möchten
    private JLabel currenPlayerLabel;

    public GameWindow(int width, int height){
        setTitle("TIC_TAC_TOE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, width, height);
        setLocationRelativeTo(null); // erscheint perfect in der Mitte
        setResizable(false); // Fenster kann nicht vergrößert werden
        setLayout(new BorderLayout());

        GamePanel gamePanel  = new GamePanel();
        // Komponennte im Fenster die ganze Inhalt beinhalten soll
        getContentPane().add(gamePanel, BorderLayout.CENTER);

        currenPlayerLabel = new JLabel("PlatzHalter!");
        // zentrieren
        currenPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // JLabel erscheint über Fenster
        getContentPane().add(currenPlayerLabel,BorderLayout.NORTH);

        setVisible(true);
    }

    public void setCurrenPlayerLabelTExt(String s) {
        currenPlayerLabel.setText(s);
    }
}
