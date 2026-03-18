package JvTh05;

public class Spell extends Thread {

    private String wizardName;
    private String spellName;
    private boolean successo;
   private int succeededSpells;
   private int numberOfSpells;

    public Spell(String wizardName, String spellName, int numberOfSpells) {
        this.wizardName = wizardName;
        this.spellName = spellName;
        this.numberOfSpells = numberOfSpells;
        this.succeededSpells = 0;
    }
    public void run() {
        System.out.println(wizardName + " inizia a lanciare incantesimi ...");
        for (int i = 0; i < numberOfSpells; i++) {
            System.out.println(wizardName + " lancia " + spellName + "...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean success = Math.random() > 0.4;
            if (success) // 80% di successo
                succeededSpells++;
            System.out.println(wizardName + " ha " +
                    (successo ? "successo" : "fallito") + " con " + spellName + "!");
        }
        System.out.println(wizardName + " ha completato " + numberOfSpells + " incantesimi!");
    }
    public int getSuccededSpells() {
        return succeededSpells;
    }

    public int getNumberOfSpells() {
        return numberOfSpells;
    }

    public int getFailedSpells() {
        return numberOfSpells - succeededSpells;
    }
}
