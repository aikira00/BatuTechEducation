/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.authapp;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Path("/")
public class AuthService {

    
    private static Map<String, String> db;
    private static List<String>tokens=new ArrayList<>();
    private static Random random;
    public AuthService() {
        db = new HashMap<>();
        random=new Random();
        db.put("user1", "user1");
        db.put("user2", "user2");
        db.put("user3", "user3");
    }

    @POST
    public Response authResult(@FormParam("username") String username, @FormParam("password")String password) {

        Response response=Response.status(Response.Status.UNAUTHORIZED).build();
        if (db.containsKey(username)) {
            String pass = db.get(username);
            if (pass.equalsIgnoreCase(password)) {
                int token=random.nextInt(1000000);
                while(tokens.contains(""+token)){
                    token=random.nextInt(1000000);
                }
                tokens.add(""+token);
                response=Response.ok(""+token).build();
            }
        }
        return  response;
    }
    
    @GET
    public Response tokenResult(@HeaderParam("Authorization") String token) {
        Response response=Response.status(Response.Status.UNAUTHORIZED).build();
        if (tokens.contains(token.substring(7))) {            
            response=Response.ok().build();
        }
        return  response;
    }
}
