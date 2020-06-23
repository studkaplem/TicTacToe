package TIC_TAC_TOE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements MouseListener {

    public GamePanel(){
        setBackground(Color.WHITE);
        requestFocus(); // Fokusiert, anklickbar
        addMouseListener(this); // zeigt was er bei Mausklick machen soll

    }

    // Zeichenmethode
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        // Unserer eigene Zeichnungen
        Graphics2D g2d = (Graphics2D) g; // Umwandlung in 2d

        // Felderzeichnen
        for (Field field: TicTacToe.instance.getFields()) {
            field.draw(g2d);
        }
    }


    private void checkField(int x, int y) {
        // Der Bereich wo unsere Maus zielen und untersuchen soll
        Rectangle cursorHitbox = new Rectangle(x, y, 1, 1); // genau der Pixel wo die Maus hinklickt
        for (Field field:TicTacToe.instance.getFields()) {
            // Field ist Nachfolger von Rectangel
            if (cursorHitbox.intersects(field)){ // intersects = schneidet
                if (field.getValue() == FieldValue.EMPTY){
                    TicTacToe.instance.nextPlayerTurn(); // wechselt die Player
                    field.setValue(TicTacToe.instance.getCurrentPlayer()); // aktuelle Player
                    repaint(); // zwingt die Komponente neuaufzuzeichnen
                }
                break;
            }
        }

    }

    private void checkWin(){
        if (TicTacToe.instance.isWon()){
            JOptionPane.showMessageDialog(
                    this,
                    "Spieler "+TicTacToe.instance.getCurrentPlayer().name()+" hat gewonnen",
                    "GAME OVER!",
                    JOptionPane.INFORMATION_MESSAGE
            );
            TicTacToe.instance.initGame(); // Neustart
            repaint();
        }else if (TicTacToe.instance.isUndecided()){
            JOptionPane.showMessageDialog(
                    this,
                    "Unentschieden! Es gibt keinen Sieger!",
                    "GAME OVER!",
                    JOptionPane.INFORMATION_MESSAGE
            );
            TicTacToe.instance.initGame(); // Neustart
            repaint(); //zeigt an, damit wir sehen dass Neustart ist
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        checkField(e.getX(), e.getY());

        checkWin();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
