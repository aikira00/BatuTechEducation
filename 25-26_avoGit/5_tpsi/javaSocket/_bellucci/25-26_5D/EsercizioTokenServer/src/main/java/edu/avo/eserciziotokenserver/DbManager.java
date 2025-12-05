/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziotokenserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author palma
 */
public class DbManager {

    private String dbTexts;
    private final Map<String, String> dbUsers;
    private PrintWriter out;
    private BufferedReader in;

    public DbManager(Map<String, String> dbUsers) {
        this.dbUsers = dbUsers;
    }

    public void setDbName(String dbTexts) {
        this.dbTexts = dbTexts;
        File file = new File(dbTexts);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    boolean okLogin(String username, String password) {

        return dbUsers.containsKey(username) 
                && dbUsers.get(username).equals(password);
    }

    void saveText(String text) {
        try {
            out = new PrintWriter(new FileWriter(dbTexts, true),true);
            out.println(text);
            out.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    List<String> getList() {
        List<String> list = new ArrayList<>();
        String line;
        try {
            in = new BufferedReader(new FileReader(dbTexts));
            while ((line = in.readLine()) != null) {
                list.add(line);
            }
            in.close();
            return list;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
