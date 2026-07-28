/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.mains;

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

        String message = reader.readLine();
        while (message != null && !message.equals("QUIT")) {
            String[] array = message.split(" ");
            int op1 = Integer.parseInt(array[1]);
            int op2 = Integer.parseInt(array[2]);
            switch (array[0]) {
                case "ADD" -> {
                    app.add(op1, op2);
                }
                case "SUB" -> {
                    app.sub(op1, op2);
                }
                case "MUL" -> {
                    app.mul(op1, op2);
                }
                case "DIV" -> {
                    app.div(op1, op2);
                }
            }
            message = reader.readLine();
        }
    }
}
