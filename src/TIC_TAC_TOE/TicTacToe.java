package TIC_TAC_TOE;

import java.util.ArrayList;

public class TicTacToe {

    private FieldValue currentPlayer;
    private ArrayList<Field> fields ;

    public static TicTacToe instance;

    public static void main(String[] args) {
        instance = new TicTacToe();
    }

    public TicTacToe(){
        new GameWindow(450,475);
        initGame();
    }



    public void initGame(){
        // Game Objekte
        // Player
        currentPlayer = FieldValue.X;
        // Fields
        int fieldsMarginLeft = 15;
        int fieldsMarginTop = 4;
        int fieldWidth = 411/3; // Gesamtbreite durch Anzahl der Felder teilen
        int fieldHeight = 411/3; // Gesamthöhe durch Anzahl der Felder teilen

        fields = new ArrayList<Field>();
        // Erste Zeile mit drei Spalten
        fields.add(new Field(fieldsMarginLeft + 0, fieldsMarginTop + 0,fieldWidth, fieldHeight ));
        fields.add(new Field(fieldsMarginLeft + 1*fieldWidth, fieldsMarginTop,fieldWidth,fieldHeight));
        fields.add(new Field(fieldsMarginLeft + 2*fieldWidth, fieldsMarginTop, fieldWidth, fieldHeight));

        // Zweite Zeile mit drei Spalten
        fields.add(new Field(fieldsMarginLeft + 0, fieldsMarginTop + 1*fieldHeight, fieldWidth, fieldHeight));
        fields.add(new Field(fieldsMarginLeft + 1*fieldWidth, fieldsMarginTop + 1*fieldHeight, fieldWidth, fieldHeight));
        fields.add(new Field(fieldsMarginLeft + 2*fieldWidth, fieldsMarginTop + 1*fieldHeight, fieldWidth, fieldHeight));

        // Dritte Zeile mit drei Spalten
        fields.add(new Field(fieldsMarginLeft + 0, fieldsMarginTop + 2*fieldHeight, fieldWidth, fieldHeight));
        fields.add(new Field(fieldsMarginLeft + 1*fieldWidth, fieldsMarginTop + 2*fieldHeight, fieldWidth, fieldHeight));
        fields.add(new Field(fieldsMarginLeft + 2*fieldWidth, fieldsMarginTop + 2*fieldHeight, fieldWidth, fieldHeight));
    }


    public FieldValue getCurrentPlayer(){
        return currentPlayer;
    }

    // Player sollen sich abwechseln
    public void nextPlayerTurn(){
        if (currentPlayer == FieldValue.X){
            currentPlayer = FieldValue.O;
        }else {
            currentPlayer = FieldValue.X;
        }
    }

    // Zugriff auf ArrayList gewährleisten mit GETTER
    public ArrayList<Field> getFields(){
        return fields;
    }

    public boolean isWon(){

        // Matrix mit 3 Zeilen und 3 Spalten
        int[][] matrix = new int[3][3];

        // erste Zeile erste Spalte; gibt den value an der 0. Zeile von Fields -> FieldsValue
        matrix[0][0] = fields.get(0).getValue().getValue();
        matrix[0][1] = fields.get(1).getValue().getValue();
        matrix[0][2] = fields.get(2).getValue().getValue();

        matrix[1][0] = fields.get(3).getValue().getValue();
        matrix[1][1] = fields.get(4).getValue().getValue();
        matrix[1][2] = fields.get(5).getValue().getValue();

        matrix[2][0] = fields.get(6).getValue().getValue();
        matrix[2][1] = fields.get(7).getValue().getValue();
        matrix[2][2] = fields.get(8).getValue().getValue();


                /*
                 Math.abs() rechnet betrag aus
                 Ergebnis kann entweder 3 oder -3 sein
                 durch Math.abs() wird das Ergebnis automatisch positiv
                  */
        return // Zeilen
                Math.abs(matrix[0][0] + matrix[0][1] + matrix[0][2]) == 3 ||
                Math.abs(matrix[1][0] + matrix[1][1] + matrix[1][2]) == 3 ||
                Math.abs(matrix[2][0] + matrix[2][1] + matrix[2][2]) == 3 ||
               // Spalten
                Math.abs(matrix[0][0] + matrix[1][0] + matrix[2][0]) == 3 ||
                Math.abs(matrix[0][1] + matrix[1][1] + matrix[2][1]) == 3 ||
                Math.abs(matrix[0][2] + matrix[1][2] + matrix[2][2]) == 3 ||
               // Diagonalen
                Math.abs(matrix[0][0] + matrix[1][1] + matrix[2][2]) == 3 ||
                Math.abs(matrix[0][2] + matrix[1][1] + matrix[2][0]) == 3;

    }

    public boolean isUndecided(){
        for (Field field:fields) {
            // Wenn es noch leere Felder gibts dann noch kein Unentschieden
            // da das Spiel weitergeht
            if (field.getValue() == FieldValue.EMPTY) {
                return false;
            }
        }
        // Wenn es einen Sieger gibts dann gibt es kein Unentschieden
        if (isWon()){
            return false;
        }
            return true;
    }

}
