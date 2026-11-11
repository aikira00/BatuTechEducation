package edu.avo.banca;

import edu.avo.tcpiolibrary.MessageConsumer;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ReaderProtocolManager implements MessageConsumer {

    private static final String SEP = "°";

    private CommandConsumer app;

    public ReaderProtocolManager(CommandConsumer app) {
        this.app = app;
    }

    // "Logout" arriva anche dal MessageReader quando il client si disconnette
    @Override
    public void consumeMessage(String message) {
        String[] array = message.split(SEP, -1);
        try {
            switch (array[0]) {
                case "Login" -> {
                    if (array.length == 3) {
                        app.performLogin(array[1], array[2]);
                    } else {
                        app.performParametersError();
                    }
                }
                case "Saldo" -> {
                    if (array.length == 2) {
                        app.performSaldo(Integer.parseInt(array[1]));
                    } else {
                        app.performParametersError();
                    }
                }
                case "EstrattoConto" -> {
                    if (array.length == 3) {
                        app.performEstrattoConto(Integer.parseInt(array[1]), Integer.parseInt(array[2]));
                    } else {
                        app.performParametersError();
                    }
                }
                case "Versamento" -> {
                    if (array.length == 3) {
                        app.performVersamento(Integer.parseInt(array[1]), parseImporto(array[2]));
                    } else {
                        app.performParametersError();
                    }
                }
                case "Prelievo" -> {
                    if (array.length == 3) {
                        app.performPrelievo(Integer.parseInt(array[1]), parseImporto(array[2]));
                    } else {
                        app.performParametersError();
                    }
                }
                case "Bonifico" -> {
                    if (array.length == 4) {
                        app.performBonifico(Integer.parseInt(array[1]), parseImporto(array[2]), array[3]);
                    } else {
                        app.performParametersError();
                    }
                }
                case "Ricerca" -> {
                    if (array.length == 4) {
                        app.performRicerca(Integer.parseInt(array[1]), LocalDate.parse(array[2]), LocalDate.parse(array[3]));
                    } else {
                        app.performParametersError();
                    }
                }
                case "Logout" -> {
                    if (array.length == 1) {
                        app.performClose();
                    } else {
                        app.performParametersError();
                    }
                }
                default -> {
                    app.performUnknownCommand();
                }
            }
        } catch (NumberFormatException ex) {
            app.performNumberFormatError();
        } catch (DateTimeParseException ex) {
            app.performDateFormatError();
        } catch (RuntimeException ex) {
            // errore del db (es. importo oltre DECIMAL(6,2)): il protocollo non lo prevede, si chiude
            ex.printStackTrace();
            app.performClose();
        }
    }

    // Double.parseDouble accetta anche "NaN" e "Infinity"
    private double parseImporto(String text) {
        double importo = Double.parseDouble(text);
        if (!Double.isFinite(importo)) {
            throw new NumberFormatException(text);
        }
        return importo;
    }
}
