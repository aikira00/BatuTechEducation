package edu.avo.data;

import edu.avo.tcpiolibrary.MessageWriter;
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

    public void daysToKnow(String date, int daysNumber){
        writer.sendMessage(DAYS_TO_KNOW + "#" + date + "#" + daysNumber);
    }

    public void daysBetween(String firstDate, String secondDate, int daysNumber){
        writer.sendMessage(DAYS_BETWEEN + "#" + firstDate + "#" + secondDate + "#" + daysNumber);
    }

    public void subDays(String firstDate, String secondDate, int daysNumber){
        writer.sendMessage(SUB_DAYS + "#" + firstDate + "#" + daysNumber + "#" + secondDate);
    }

    public void addDays(String firstDate, String secondDate, int daysNumber){
        writer.sendMessage(ADD_DAYS + "#" + firstDate + "#" + daysNumber + "#" + secondDate);
    }

    public void dayOfWeek(String date, String dateName){
        writer.sendMessage(ADD_DAYS + "#" + firstDate + "#" + daysNumber + "#" + secondDate);
    }

}
