package TIC_TAC_TOE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements MouseListener {

    private WinResult winResult = null;

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

        // Gewinnlinie zeichnen
        if (winResult != null){
            g2d.setColor(Color.red); // Farbe des Stiftes ändern
            g2d.setStroke(new BasicStroke(10)); // Dicke des Stiftes in 10 Pixel geändert
            g2d.drawLine(
                    // Hälfte der x-Koordinate
                    (int) (winResult.getField1().getX() + winResult.getField1().getWidth()/2),
                    // Hälfte der y-Koordinate
                    (int) (winResult.getField1().getY() + winResult.getField1().getHeight()/2),
                    (int) (winResult.getField2().getX() + winResult.getField2().getWidth()/2),
                    (int) (winResult.getField2().getY() + winResult.getField2().getHeight()/2)
            );

            // rote Linie verschwindet
            winResult = null;
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
        WinResult tempWinResult = TicTacToe.instance.getWinResult();
        if (tempWinResult.isWon()){
            winResult = tempWinResult;
            repaint();
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

    // Der Rest der Methoden werden nicht verwendet
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
