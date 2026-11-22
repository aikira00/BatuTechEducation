package elfRacePart2;

public class Gift {
    private int number;
    private Letter letter;
    private String state;

    public Gift(int number, Letter letter) {
        this.number = number;
        this.letter = letter;
        this.state = "da costruire";   // il primo lavoro, quando la letterina sarà letta
    }

    public int getNumber() {
        return number;
    }

    public String getState() {
        if (letter.isRead()) {
            return state;
        } else {
            return "in attesa";        // lo chiede alla letterina, non se lo segna
        }
    }

    // l'elfo passa da qui: il regalo fa leggere la sua letterina e riferisce il testo
    public String readLetter() {
        letter.read();
        return letter.getText();
    }

    public boolean isReady() {
        return state.equals("pronto");
    }

    // un passo avanti; in attesa non si muove, pronto resta pronto
    public void advance() {
        if (letter.isRead()) {
            if (state.equals("da costruire")) {
                state = "da incartare";
            } else if (state.equals("da incartare")) {
                state = "pronto";
            }
        }
    }
}
