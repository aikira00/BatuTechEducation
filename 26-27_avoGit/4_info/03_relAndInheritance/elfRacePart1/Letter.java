package elfRacePart1;

public class Letter {
    private String text;
    private boolean read;

    public Letter(String text) {
        this.text = text;
        this.read = false;   // nasce non letta
    }

    public String getText() {
        return text;
    }

    public boolean isRead() {
        return read;
    }

    public void read() {
        read = true;         // da letta non torna indietro
    }
}
