/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package edu.avo.eserciziojson;

import edu.avo.eserciziojson.bo.Category;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonWriter;
import jakarta.json.JsonWriterFactory;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.stream.JsonGenerator;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author palma
 * Jakarta JSON Binding (JSON-B) è l’API che fornisce un modo standard per serializzare e deserializzare oggetti Java in JSON e viceversa.
 * 	•	È l’equivalente JSON di JAXB (usato per XML).
 * 	•	È simile a librerie di terze parti come Jackson o Gson, ma fa parte dello standard Jakarta EE.
 */

/** Jakarta JSON-B (JSON Binding) che Jakarta JSON-P (JSON Processing
 * invece di limitarmi alla stringa
 * il JSON viene elaborato utilizzando Jakarta JSON-P,
 * che permette di lavorare con un modello ad albero:*/

public class EsercizioJson {

    public static void main(String[] args) {
        //serialization from obj to json
        Jsonb jsonb = JsonbBuilder.create();
        String jsons = jsonb.toJson(new Category("pulce"));
        System.out.println(jsons);

        //deserialization from json to obj
        Category categoryEx = jsonb.fromJson(jsons, Category.class);
        System.out.println(categoryEx.getDescription());

        //object -> json
        Category c = new Category("description");

        //da oggetto a Json, jsonB top of the tree
        jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(c);

        //read json format, da Json a oggetto
        StringReader sr = new StringReader(jsonString);
        // Creates a JSON reader from a character stream.
        JsonReader reader = Json.createReader(sr);
        //JsonObject (JsonStructure), top of the tree we could navigate
        //from character stream to JSON obj
        //https://docs.oracle.com/javaee/7/api/javax/json/JsonReader.html#readObject--
        JsonObject jobj = reader.readObject();
        Map<String, Object> map = new HashMap<>();
        map.put(JsonGenerator.PRETTY_PRINTING, true);

        //writing an object model to a stream
        StringWriter sw = new StringWriter();
        JsonWriterFactory writerFactory = Json.createWriterFactory(map);
        JsonWriter jsonWriter = writerFactory.createWriter(sw);
        jsonWriter.writeObject(jobj);// json object model
        jsonWriter.close(); //close the stream
        System.out.println(sw.toString());

        //json->object
        jsonb = JsonbBuilder.create();
        //string json
        Category other = jsonb.fromJson(sw.toString(), Category.class);
        //Category other = jsonb.fromJson(jsonString, Category.class);
        System.out.println(other);

    }
}
