/*
 * Demo "compilo una volta, eseguo ovunque" — versione Java.
 *
 * Stesso compito del hello.c, ma le informazioni non sono scritte dentro
 * il file: il programma le CHIEDE alla JVM mentre gira. Ecco perche' lo
 * stesso identico Hello.class, copiato su un'altra macchina, risponde
 * cose diverse.
 *
 * Nota per me: NON commentare adesso public/static/String[] args.
 * Alla domanda "prof ma cos'e' static?" -> "fidatevi, tra due lezioni".
 */

public class Hello {
    public static void main(String[] args) {
        System.out.println("Ciao 4A! Sono un programma in Java.");
        System.out.println("  architettura : " + System.getProperty("os.arch"));
        System.out.println("  sistema      : " + System.getProperty("os.name"));
        System.out.println("  JVM          : " + System.getProperty("java.version"));
        System.out.println("L'ho chiesto alla JVM ADESSO, mentre giro.");
    }
}
