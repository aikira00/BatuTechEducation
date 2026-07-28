package edu.avo.exampleJsonBJsonP;

import jakarta.json.*;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.io.StringReader;
import java.io.StringWriter;

/**primo esempio, abbiamo usato solo JSON-B per serializzare e deserializzare in modo semplice.
Qui usiamo JSON-P per navigare e modificare un JSON esistente rappresentato da un oggetto JSONOBJECT,
 aggiungendo/rinominando campi.
Usiamo sempre JSON-B  per convertire il nuovo JSON in un oggetto Java.*/

public class exampleJsonP {
    public static void main(String[] args) {
        // JSON iniziale (stringa JSON esistente)
        String originalJson = """
            {
                "title": "Effective Java",
                "author": "Joshua Bloch",
                "year": 2018
            }
            """;
        System.out.println("JSON originale:");
        System.out.println(originalJson);

        // Navigazione e modifica del JSON con JSON-P
        // JSON originale viene letto in un oggetto JsonObject.
        StringReader stringReader = new StringReader(originalJson);
        JsonReader jsonReader = Json.createReader(stringReader);
        JsonObject originalJsonObject = jsonReader.readObject();
        jsonReader.close();

        // Creazione di un nuovo JsonObjectBuilder con modifiche
        // Jakarta: JsonObject sono immutabili! devo lavorare su una copia
        // come le stringhe
        // Creazione di un nuovo JsonObject modificato
        JsonObjectBuilder builder = Json.createObjectBuilder(originalJsonObject);

        // Se manca il campo 'description', aggiungilo
        if (!originalJsonObject.containsKey("description")) {
            builder.add("description", "A must-read book for Java developers");
        }

        // Costruire il nuovo JSON
        JsonObject modifiedJsonObject = builder.build();

        // Scrittura del nuovo JSON in una stringa
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = Json.createWriter(stringWriter);
        jsonWriter.writeObject(modifiedJsonObject);
        jsonWriter.close();

        String modifiedJson = stringWriter.toString();
        System.out.println("\nJSON modificato:");
        System.out.println(modifiedJson);

        //Deserializzazione del nuovo JSON in un oggetto Java con JSON-B
        Jsonb jsonb = JsonbBuilder.create();
        Book bookNotModified = jsonb.fromJson(originalJson, Book.class);
        System.out.println("\nOggetto Java creato dal JSON NON modificato:");
        System.out.println("Title: " + bookNotModified.getTitle());
        BookWDescription bookModified = jsonb.fromJson(modifiedJson, BookWDescription.class);
        System.out.println("\nOggetto Java creato dal JSON modificato:");
        System.out.println("Title: " + bookModified.getTitle());
        //System.out.println("Author: " + bookModified.author);
        //System.out.println("Year: " + bookModified.year);
        //System.out.println("Description: " + bookModified.description);
    }
}
