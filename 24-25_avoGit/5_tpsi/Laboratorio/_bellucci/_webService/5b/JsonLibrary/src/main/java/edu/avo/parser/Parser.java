/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.parser;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

/**
 *
 * @author palma
 */
public class Parser {

    public static Object fromJson(String json, Class clazz) {
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.fromJson(json, clazz);
    }

    public static String toJson(Object obj) {
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.toJson(obj);
    }
    
    

}
