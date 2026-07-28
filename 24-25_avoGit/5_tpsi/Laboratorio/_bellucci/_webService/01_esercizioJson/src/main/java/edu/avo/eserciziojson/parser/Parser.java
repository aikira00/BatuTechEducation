/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziojson.parser;

import jakarta.json.*;
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
 */
public class Parser {
    Jsonb jsonb;
    Map<String, Object> map;
    JsonWriterFactory writerFactory ;
    //StringReader sr;
    //JsonReader reader;
    public Parser(){
        jsonb = JsonbBuilder.create();
        map = new HashMap<>();
        map.put(JsonGenerator.PRETTY_PRINTING, true);
        writerFactory = Json.createWriterFactory(map);
    }

    /**
     * Converts the given object into a JSON representation and parses it into a JsonObject.
     *
     * @param obj the object to be converted and parsed into a JsonObject
     * @return a JsonObject representation derived from the given object
     */
    public JsonObject objectToJsonObject(Object obj){
        String jsonString = jsonb.toJson(obj);
        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jobj = reader.readObject();
        return jobj;
    }

    /**
     * Converts a given JsonObject into its string representation.
     *
     * @param jobj the JsonObject to be converted into a string representation
     * @return the string representation of the provided JsonObject
     */
    public String jsonObjectToString(JsonObject jobj){
        StringWriter sw = new StringWriter();
        JsonWriter jsonWriter = writerFactory.createWriter(sw);
        jsonWriter.writeObject(jobj);// json object model
        jsonWriter.close(); //close the stream
       return sw.toString();
    }

    /**
     * Parses the given JSON string and converts it into a Java object.
     *
     * @param json the JSON string to be parsed
     * @return a Java object representing the deserialized JSON
     */
    public <T> T jsonToObj(String json, Class<T> runtimeClass){
        return jsonb.fromJson(json, runtimeClass);
    }

}
