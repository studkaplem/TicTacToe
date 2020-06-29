package TIC_TAC_TOE;

public class WinResult {
    private boolean isWon;
    private Field field1;
    private Field field2;

    public WinResult(boolean isWon, Field field1, Field field2) {
        this.isWon = isWon;
        this.field1 = field1;
        this.field2 = field2;
    }

    public void setWon(boolean won) {
        isWon = won;
    }

    public void setField1(Field field1) {
        this.field1 = field1;
    }

    public void setField2(Field field2) {
        this.field2 = field2;
    }

    public boolean isWon() {
        return isWon;
    }

    public Field getField1() {
        return field1;
    }

    public Field getField2() {
        return field2;
    }
}
