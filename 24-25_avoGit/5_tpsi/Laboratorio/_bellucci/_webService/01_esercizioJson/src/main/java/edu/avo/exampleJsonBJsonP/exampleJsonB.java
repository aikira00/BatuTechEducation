/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package edu.avo.exampleJsonBJsonP;

import edu.avo.eserciziojson.bo.Category;
import jakarta.json.*;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.stream.JsonGenerator;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class exampleJsonB {

    public static void main(String[] args) {
        // Creazione di un oggetto Category
        Book myBook = new Book("Il paradiso dei calzini", "Pippo", 1945);

        // Serializzazione (Oggetto -> JSON)
        Jsonb jsonb = JsonbBuilder.create();
        String json = jsonb.toJson(myBook);
        System.out.println("JSON serializzato:");
        System.out.println(json);

        // Deserializzazione (JSON -> Oggetto)
        Book deserializedBook = jsonb.fromJson(json, Book.class);
        System.out.println("\nOggetto deserializzato:");
        System.out.println(deserializedBook);
        System.out.println(deserializedBook.getTitle());
    }
}
