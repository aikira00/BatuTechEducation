/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.esempiodbserver;

import edu.avo.esempiodbserver.bo.Category;
import edu.avo.esempiodbserver.bo.Product;
import edu.avo.esempiodbserver.bo.so.CategorySearchObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MULTI01
 */
public class MySqlServer {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    public MySqlServer(Connection connection) {
        this.connection = connection;
    }
    
    public MySqlServer(String connectionString) throws SQLException{
        connection=DriverManager.getConnection(connectionString);
    }
    
    public List<Category> getCategories() throws SQLException{
        statement=connection.prepareStatement("select * from categories");
        List<Category> list=new ArrayList<>();
        result=statement.executeQuery();
        while(result.next()){            
            list.add(new Category(result.getInt("id"),result.getString("description")));
        }
        return list;
    }
    
    public Category getCategory(int id) throws SQLException{
        statement=connection.prepareStatement("select * from categories where id=?");
        statement.setInt(1, id);
        result=statement.executeQuery();
        Category category=null;
        if (result.next()){
            category=new Category(result.getInt("id"),result.getString("description"));
        }
        return category;
    }
    
    public int deleteCategory(int id) throws SQLException{
        statement=connection.prepareStatement("delete from categories where id=?");
        statement.setInt(1, id);
        return statement.executeUpdate();
    }
    
    public int insertCategory(Category category) throws SQLException{
        statement=connection.prepareStatement("insert into categories (description) values (?)");
        statement.setString(1, category.getDescription());
        return statement.executeUpdate();
    }
    
    public int updateProduct(Product product) throws SQLException{
        statement=connection.prepareStatement("update products set description=?, price=?, id_category=? where id=?");
        statement.setString(1,product.getDescription());
        statement.setFloat(2, product.getPrice());
        statement.setInt(3, product.getCategory().getId());
        statement.setInt(4, product.getId());
        return statement.executeUpdate();
    }
    
    public List<Category> searchCategories(CategorySearchObject object){
        return null;
    }
    
}
