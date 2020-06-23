package TIC_TAC_TOE;

public enum FieldValue {

    X(1), O(-1), EMPTY(0);

    int value;

    private FieldValue(int value){
        this.value = value;
    }

    int getValue(){
        return value;
    }
}
