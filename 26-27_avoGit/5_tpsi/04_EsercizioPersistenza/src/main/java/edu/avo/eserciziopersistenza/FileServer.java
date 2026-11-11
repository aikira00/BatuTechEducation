/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziopersistenza;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MULTI01
 */
public class FileServer implements Persistence {

    String db;

    public FileServer(String db) {
        this.db = db;
    }

    @Override
    public boolean loginOk(String username, String password) {
        BufferedReader in = null;
        try {
            boolean ok = false;
            in = new BufferedReader(new FileReader(db + "/users.txt"));
            String line;
            while ((line = in.readLine()) != null && !ok) {
                String[] array = line.split("@");
                if (array[0].equalsIgnoreCase(username) && array[1].equals(password)) {
                    ok = true;
                }
            }
            return ok;
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public boolean saveText(String text, String username) {
        PrintWriter out = null;
        try {
            boolean ok = true;
            File file = new File(db + "/" + username + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new PrintWriter(new FileWriter(file, true));
            out.println(text);
            out.close();
            return ok;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            out.close();
        }
    }

    @Override
    public List<String> getTextList(String username) {
        List<String> list = new ArrayList<>();
        File file = new File(db + "/" + username + ".txt");

        if (file.exists()) {
            BufferedReader in = null;
            try {
                in = new BufferedReader(new FileReader(file));
                String line;
                while ((line = in.readLine()) != null) {
                    list.add(line);
                }
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } finally {
                try {
                    in.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return list;
    }
}
