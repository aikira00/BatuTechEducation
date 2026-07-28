/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package edu.avo.clientappdist;

import edu.avo.opservice.OpService;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author palma
 */
public class ClientAppDist {

    public static void main(String[] args) {
        String urlString = "http://localhost:8080/Auth/api";
        String username = JOptionPane.showInputDialog("Inserire username");
        String password = JOptionPane.showInputDialog("Inserire password");
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(urlString);
            Form form = new Form();
            form.param("username", username);
            form.param("password", password);
            Response response = target.request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
            if (response.getStatus() == HttpURLConnection.HTTP_OK) {
                String token = response.readEntity(String.class);
                urlString = "http://localhost:7070/List/api/operations";
                client = ClientBuilder.newClient();
                target = client.target(urlString);
                response = target.request(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .get();
                if (response.getStatus() == HttpURLConnection.HTTP_OK) {
                    List<OpService> services = response.readEntity(new GenericType<List<OpService>>() {
                    });
                    String[] names = new String[services.size()];
                    for (int i = 0; i < services.size(); i++) {
                        names[i] = services.get(i).getName();
                    }
                    JComboBox<String> box = new JComboBox<>(names);
                    int confirm = JOptionPane.showConfirmDialog(null, box, "Operazioni disponibili", JOptionPane.OK_CANCEL_OPTION);
                    if (confirm == JOptionPane.OK_OPTION) {
                        int a = Integer.parseInt(JOptionPane.showInputDialog("Inserire primo numero"));
                        int b = Integer.parseInt(JOptionPane.showInputDialog("Inserire secondo  numero"));
                        OpService op = services.get(box.getSelectedIndex());
                        urlString = op.getUrl();
                        if (op.getMethod().equals("get")) {
                            urlString += "?" + op.getP1() + "=" + a + "&" + op.getP2() + "=" + b;
                            client = ClientBuilder.newClient();
                            target = client.target(urlString);
                            response = target.request(MediaType.APPLICATION_JSON)
                                    .header("Authorization", "Bearer " + token)
                                    .get();
                            if (response.getStatus() == HttpURLConnection.HTTP_OK) {
                                String result = response.readEntity(String.class);
                                client.close();
                                JOptionPane.showMessageDialog(null, a + " " + op.getSymbol() + " " + b + " = " + result.split(":")[1].substring(0, result.split(":")[1].length() - 1));
                            } else {
                                JOptionPane.showMessageDialog(null, "Fallito get verso " + urlString);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Fallito get verso " + urlString);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Credenziali non corrette");
            }
            client.close();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }
}
