package elfRacePart2;

public class Gift {
    private int number;
    private String state;

    public Gift(int number) {
        this.number = number;
        this.state = "da leggere";   // la letterina arriva per prima
    }

    public int getNumber() {
        return number;
    }

    public String getState() {
        return state;
    }

    public boolean isReady() {
        return state.equals("pronto");
    }

    // un passo avanti; pronto resta pronto
    public void advance() {
        if (state.equals("da leggere")) {
            state = "da costruire";
        } else if (state.equals("da costruire")) {
            state = "da incartare";
        } else if (state.equals("da incartare")) {
            state = "pronto";
        }
    }
}
