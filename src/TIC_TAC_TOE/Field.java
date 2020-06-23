package TIC_TAC_TOE;

import java.awt.*;

public class Field extends Rectangle {

    private FieldValue value;


    public Field(int x, int y, int width, int height){
        super(x,y,width,height); // Rectangle hat schon diese Attribute
        value = FieldValue.EMPTY; //speichert standartmäßig EMPTY als value
    }

    public void draw(Graphics2D g2d){
        // Rahmen zeichnen
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x,y,width,height); // zeichnet Rechteck

        // Symbol zeichnen
        if (value == FieldValue.O){
            // x und y Koordinaten werden von oben nach unten gezählt
            g2d.drawOval(x + 5, y + 5, width - 10,height - 10);
        } else if (value == FieldValue.X){

            // P1(x1/y2) P2(x2/y1)
            g2d.drawLine(x + 5, y + 5, x + width - 5, y + height - 5);
            g2d.drawLine(x+width-5, y+5, x+5, y + height - 5);

        }
    }

    // Setter/ Getter
    public FieldValue getValue(){
        return value;
    }
    public void setValue(FieldValue newValue){
        value = newValue;
    }
}
