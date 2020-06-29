package TIC_TAC_TOE;

import java.util.ArrayList;

public class TicTacToe {

    private FieldValue currentPlayer;
    private ArrayList<Field> fields ;
    private int[] winningConstellations = new int[] {
            // Zeilen
            0, 1, 2,
            3, 4, 5,
            6, 7, 8,
            // Spalten
            0, 3, 6,
            1, 4, 7,
            2, 5, 8,
            // Diagonalen
            0, 4, 8,
            6, 4, 2
    };

    private GameWindow game;

    public static TicTacToe instance;

    public static void main(String[] args) {
        instance = new TicTacToe();
    }

    public TicTacToe(){
        game = new GameWindow(450,475);
        initGame();
    }



    public void initGame(){
        // Game Objekte
        // Player
        currentPlayer = FieldValue.X;
        nextPlayerTurn();
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

        game.setCurrenPlayerLabelTExt("Aktueller Spieler ist "+currentPlayer.name() +" !");

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

    // Code ist nicht effizient
    /*public boolean isWon(){

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


                *//*
                 Math.abs() rechnet Betrag aus
                 Ergebnis kann entweder 3 oder -3 sein
                 durch Math.abs() wird das Ergebnis automatisch positiv
                  *//*
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

    }*/

    // besserer Algorithmus
    public WinResult getWinResult(){
        // Alle Feld-Konstellationnen untersuchen blockweise daher i+3
        for (int i = 0; i < winningConstellations.length; i += 3){

            int a = winningConstellations[i];
            int b = winningConstellations[i+1];
            int c = winningConstellations[i+2];

            // Bedingung für Gewinn
            if (Math.abs(fields.get(a).getValue().getValue() + fields.get(b).getValue().getValue() + fields.get(c).getValue().getValue()) == 3){
                return new WinResult(true,fields.get(a),fields.get(c));
            }
        }
        // verloren
        return new WinResult(false, null, null);
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
        if (getWinResult().isWon()){
            return false;
        }
            return true;
    }

}
