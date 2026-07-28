package edu.avo.demotomcatrest;

//import org.json.JSONArray;
//import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class REST5C {

   /* public static String getJson(HttpURLConnection connection) throws IOException {
        // Lettura della risposta
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Stampa del JSON ricevuto
        System.out.println("Risposta JSON: " + response.toString());
        return response.toString();
    }
    public static void main(String[] args) {
        String urlString = "https://randomuser.me/api/"; // URL del servizio REST
        //primo Json che inizia con JsonObject results che è un array
        try {
            // Creazione dell'oggetto URL
            URL url = new URL(urlString);

            // Apertura della connessione
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET"); // Metodo HTTP GET

            // Controllo del codice di risposta
            int responseCode = connection.getResponseCode();
            System.out.println("Codice di risposta: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                System.out.println("#######################PRIMO SERVIZIO REST CHE INIZIA CON {\"results:\"[{...}]}");
                //recupero json
                String responseJson = getJson(connection);
                //parsificazione Json con org.json
                //https://www.javadoc.io/doc/org.json/json/20170516/org/json/JSONObject.html
                //https://www.javadoc.io/doc/org.json/json/20170516/org/json/JSONArray.html
                JSONObject resultsObj = new JSONObject(responseJson);
                JSONArray resultsArray = resultsObj.getJSONArray("results");
                //for classico libreria non supporta foreach altrimenti Iterator
                //qui solo un elemento ciclo non avrebbe molto senso
                //vedere in particolare opt<Methods> che permettono di gestire chiavi mancanti e valori nulli
                //con possibile valore default
                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject resObj = resultsArray.getJSONObject(i);
                    String gender = resObj.optString("gender", ""); //es. con valore default
                    JSONObject objName = resObj.optJSONObject("name"); //chiave name JsonObject complesso
                    String name = objName.optString("title", "");
                    name = name.concat(" " + objName.optString("first", ""));
                    name = name.concat(" " + objName.optString("last", ""));
                    JSONObject objStreet = resObj.optJSONObject("location").optJSONObject("street"); //chiave location e street JsonObject complessi
                    String street = objStreet.optString("number", "");
                    street = street.concat(", " + objStreet.optString("name", ""));
                    String city = resObj.optString("city", "");
                    String email = resObj.optString("email", "");
                    //etc... prendere solo qualche campo
                    System.out.println("Recuperate info da json " + gender + " " + name + " " + street + " " + city + " " + email);
                    //qui preparare connessione db, preparedStatement etc. QUERY a DB "INSERT INTO users (gender, name, street, city, email) VALUES (?, ?, ?, ?, ?)";
                }
            } else {
                System.out.println("Errore Risposta: " + responseCode + "per REST " + urlString);
            }
            //secondo JSON che inizia con un array
            urlString = "https://api.restful-api.dev/objects"; // URL del servizio REST
            url = new URL(urlString);
            // Apertura della connessione
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET"); // Metodo HTTP GET

            // Controllo del codice di risposta
            responseCode = connection.getResponseCode();
            System.out.println("Codice di risposta: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                //recupero json
                System.out.println("#######################SECONDO SERVIZIO REST CHE INIZIA CON [{...}]");
                String responseJson = getJson(connection);
                JSONArray resultsArray = new JSONArray(responseJson);
                System.out.println(resultsArray);
                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject resObj = resultsArray.getJSONObject(i);
                    String id = resObj.getString("id");
                    String name = resObj.getString("name");

                    JSONObject objData = resObj.optJSONObject("data", null);
                    String strData = "";
                    //qui boh decidee se prendere data per un campo varchar lungo o solo qualche campo dentro data
                    if(objData != null){
                        String color = objData.optString("color");
                        if(color!=null)
                            strData += color + " ";

                        String capacity  = objData.optString("capacity");
                        if(capacity!= null)
                            strData += capacity + " ";

                        String screenSize = objData.optString("screenSize");
                        if(screenSize!= null){
                            strData += screenSize + " ";
                        }
                        //etc... altri campi se si vuole
                    }

                    System.out.println("Recuperate info da json el " + i + " id: " + id + " name:  " + name + " strData: " + strData );
                    //qui insert query DB etc preparedStatement con set per ?? campo etc.
                }
            }
        }
         catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
