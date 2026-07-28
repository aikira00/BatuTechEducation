/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.mainc;

/**
 *
 * @author palma
 */
public class ReaderProtocolManager {

    private App app;
    private MyReader reader;

    public ReaderProtocolManager(App app, MyReader reader) {
        this.app = app;
        this.reader = reader;
    }

    public void start() {
        String read;
        boolean done = app.read();
        while (!done) {
            read = reader.readLine();
            String[] array = read.split(" ");
            app.printResult(Integer.parseInt(array[1]), Integer.parseInt(array[2]), array[0], Integer.parseInt(array[3]));
            done = app.read();
        }
    }
}
