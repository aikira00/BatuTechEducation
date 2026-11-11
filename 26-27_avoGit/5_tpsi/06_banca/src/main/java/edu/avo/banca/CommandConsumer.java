package edu.avo.banca;

import java.time.LocalDate;

public interface CommandConsumer {

    void performLogin(String username, String password);

    void performSaldo(int token);

    void performEstrattoConto(int token, int n);

    void performVersamento(int token, double importo);

    void performPrelievo(int token, double importo);

    void performBonifico(int token, double importo, String conto);

    void performRicerca(int token, LocalDate inizio, LocalDate fine);

    void performClose();

    void performUnknownCommand();

    void performParametersError();

    void performNumberFormatError();

    void performDateFormatError();
}
