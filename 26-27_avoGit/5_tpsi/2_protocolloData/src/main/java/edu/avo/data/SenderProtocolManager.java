package edu.avo.data;

import edu.avo.tcpiolibrary.MessageWriter;

import java.time.LocalDate;

public class SenderProtocolManager {
    private MessageWriter writer;

    // costanti messaggi

    private final String DAYS_TO_KNOW = "DAYSTOKNOW";
    private final String DAYS_BETWEEN = "DAYSBETWEEN";
    private final String SUB_DAYS = "SUBDAYS";
    private final String ADD_DAYS = "ADDDAYS";
    private final String DAY_OF_WEEK = "DAYOFWEEK#";
    public SenderProtocolManager(MessageWriter writer){
        this.writer = writer;
    }

    public void daysToKnow(LocalDate date, int daysNumber){
        String d = date.toString();
        writer.sendMessage(DAYS_TO_KNOW + "#" + d + "#" + daysNumber);
    }

    //TO DO sistemare parametri il sender deve prendere risultati da Application e poi trasforma lui in stringa
    public void daysBetween(LocalDate firstDate, LocalDate secondDate, int daysNumber){
        writer.sendMessage(DAYS_BETWEEN + "#" + firstDate + "#" + secondDate + "#" + daysNumber);
    }

    public void subDays(LocalDate firstDate, LocalDate secondDate, int daysNumber){
        writer.sendMessage(SUB_DAYS + "#" + firstDate + "#" + daysNumber + "#" + secondDate);
    }

    public void addDays(LocalDate firstDate, LocalDate secondDate, int daysNumber){
        writer.sendMessage(ADD_DAYS + "#" + firstDate + "#" + daysNumber + "#" + secondDate);
    }

    public void dayOfWeek(LocalDate date, LocalDate dateName){
        writer.sendMessage(ADD_DAYS + "#" + firstDate + "#" + daysNumber + "#" + secondDate);
    }

}
