/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package edu.avo.eserciziojson;

import edu.avo.eserciziojson.bo.Category;
import edu.avo.eserciziojson.parser.CategoryProxy;
import edu.avo.eserciziojson.parser.ProxyObjectConverter;
import edu.avo.eserciziojson.parser.Parser;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author palma
 */
public class EsercizioJson {

    public static void main(String[] args) {
        Category c = new Category(2, "description");
        Category c1 = new Category(1, "test");
        List<CategoryProxy> list = List.of(ProxyObjectConverter.getCategoryProxy(c), ProxyObjectConverter.getCategoryProxy(c1));
        String json = Parser.toJson(list);
        System.out.println(json);
        List<CategoryProxy> l = List.of(Parser.fromJsonGenerics(json, CategoryProxy[].class));
        System.out.println(l);
        l = (List<CategoryProxy>) Parser.fromJson(json, List.class);
        List<CategoryProxy> newList = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            newList.add((CategoryProxy) Parser.fromJson(Parser.toJson(list.get(i)), CategoryProxy.class));
        }
        System.out.println(newList);
    }

}
