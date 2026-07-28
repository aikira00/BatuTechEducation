/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.backend.resources;


import edu.avo.backend.ConnectionUtility;
import edu.avo.bolibrary.Category;
import edu.avo.mysqllibrary.Server;

import edu.avo.parser.Parser;
import edu.avo.parser.ProxyObjectConverter;
import edu.avo.parser.proxies.CategoryProxy;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.ws.rs.Consumes;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 *
 * @author palma
 */
@Path("categories")
public class Categories {

    @GET
    @Operation(
            summary = "Retrieve categories",
            description = "Returns the entire list, a single element identified by the ID, or a list of elements based on a LIKE search on the description."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful retrieval of categories",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid 'id' format"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json")
            )
    })
    public Response getCategories(@QueryParam("id") String id) throws SQLException, NamingException {
        Object result;
        Connection connection = ConnectionUtility.getConnection();
        Server server = new Server(connection);
        if(id==null){
            result = server.selectCategories();
        }else{
            result=server.selectCategory(Integer.parseInt(id));
        }
        connection.close();
        return Response.ok(result, MediaType.APPLICATION_JSON).build();

    }
    
    @DELETE
    @Operation(
            summary = "Delete a category",
            description = "Deletes a category based on the provided 'id' query parameter and returns the number of deleted records."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Category successfully deleted",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"deleted\": 1}")
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid 'id' format"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    public Response deleteCategory(@QueryParam("id") String id) throws SQLException, NamingException {
        Connection connection = ConnectionUtility.getConnection();
        Server server = new Server(connection);
        int num=server.deleteCategory(Integer.parseInt(id));
        String result = "{\"deleted\":"+num+"}";
        connection.close();
        return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Add a new category",
            description = "Creates a new category based on the provided JSON payload and returns the number of records added."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Category successfully added",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"added\": 1}")
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request payload"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    public Response addCategory(CategoryProxy cp) throws NamingException, SQLException{
        Connection connection = ConnectionUtility.getConnection();
        Server server = new Server(connection);
        Category category=ProxyObjectConverter.getCategory(cp);
        int num=server.insertCategory(category);
        String result = "{\"added\":"+num+"}";
        connection.close();
        return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }
    
    @PUT
    @Operation(
            summary = "Update an existing category",
            description = "Updates an existing category based on the provided JSON payload and returns the number of records updated."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Category successfully updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"updated\": 1}")
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request payload"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Category not found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCategory(CategoryProxy cp) throws NamingException, SQLException{
        Connection connection = ConnectionUtility.getConnection();
        Server server = new Server(connection);
        Category category=ProxyObjectConverter.getCategory(cp);
        int num=server.updateCategory(category);
        String result = "{\"updated\":"+num+"}";
        connection.close();
        return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }
}
