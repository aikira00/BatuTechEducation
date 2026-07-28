/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.avo.mysqllibrary;



import edu.avo.bolibrary.Category;
import edu.avo.bolibrary.Product;
import edu.avo.bolibrary.Showroom;
import edu.avo.bolibrary.ShowroomProduct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author palma
 */
public class Server {

    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;

    public Server(Connection connection) {
        this.connection = connection;
    }

    public List<String> selectTables() {
        try {
            List<String> list = new ArrayList<>();
            statement = connection.prepareStatement("select table_name from information_schema.tables where table_schema='RestDb'");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public List<Category> selectCategories() {
        List<Category> list = new ArrayList();
        try {
            statement = connection.prepareStatement("select * from categories");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add((Category) (new Category(resultSet.getInt(1), resultSet.getString(2))));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }


    public Category selectCategory(int id) {
        Category c = null;
        try {
            statement = connection.prepareStatement("select * from categories where id=?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                c = (Category) (new Category(resultSet.getInt(1),
                        resultSet.getString(2)));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return c;
    }    

    public int deleteCategory(Category c) {
        return deleteCategory(c.getId());
    }

    public int deleteCategory(int id) {
        int n = 0;
        try {
            statement = connection.prepareStatement("delete from categories  where id=?");
            statement.setInt(1, id);
            n = statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return n;
    }

    public int updateCategory(Category c) {
        try {
            statement = connection.prepareStatement("update categories set description=?"
                    + " where id=?");
            statement.setString(1, c.getDescription());
            statement.setInt(2, c.getId());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public int insertCategory(Category c) {
        try {
            statement = connection.prepareStatement("insert into categories (description)"
                    + " values (?)");
            statement.setString(1, c.getDescription());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public List<Product> selectProducts() {
        List<Product> list = new ArrayList();
        try {
            statement = connection.prepareStatement("select * from products inner join categories on id_category=categories.id");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new Product(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getFloat(4),
                        new Category(resultSet.getInt(6), resultSet.getString(7))));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    public Product selectProduct(int id) {
        Product c = null;
        try {
            statement = connection.prepareStatement("select * from products, categories where products.id=? and id_category=categories.id");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                c = new Product (resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getFloat(4),new Category(resultSet.getInt(5),resultSet.getString(6)));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return c;
    }    

    public int deleteProduct(Product p) {
        return deleteProduct(p.getId());
    }

    public int deleteProduct(int id) {
        int n = 0;
        try {
            statement = connection.prepareStatement("delete from products  where id=?");
            statement.setInt(1, id);
            n = statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return n;
    }

    public int updateProduct(Product p) {
        try {
            statement = connection.prepareStatement("update products set description=?,  price=?,  id_category=?"
                    + " where id=?");
            statement.setString(1, p.getDescription());
            statement.setDouble(2, p.getPrice());
            statement.setInt(3, p.getCategory().getId());
            statement.setInt(4, p.getId());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public int insertProduct(Product p) {
        try {
            statement = connection.prepareStatement("insert into products (name,description,price, id_category)"
                    + " values (?,?,?,?)");
            statement.setString(1, p.getName());
            statement.setString(2, p.getDescription());
            statement.setDouble(3, p.getPrice());
            statement.setInt(4, p.getCategory().getId());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public List<Showroom> selectShowrooms() {
        List<Showroom> list = new ArrayList();
        try {
            statement = connection.prepareStatement("select * from showrooms");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new Showroom(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4),
                        resultSet.getString(5)));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    public Showroom selectShowroom(int id) {
        Showroom s = null;
        try {
            statement = connection.prepareStatement("select * from showrooms where id=?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                s = new Showroom (resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return s;
    }    

    public int deleteShowroom(Showroom s) {
        return deleteShowroom(s.getId());
    }

    public int deleteShowroom(int id) {
        int n = 0;
        try {
            statement = connection.prepareStatement("delete from showrooms  where id=?");
            statement.setInt(1, id);
            n = statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return n;
    }

    public int updateShowroom(Showroom s) {
        try {
            statement = connection.prepareStatement("update showrooms set address=?,  manager=? where id=?");
            statement.setString(1, s.getAddress());
            statement.setString(2, s.getManager());
            statement.setInt(3, s.getId());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public int insertShowroom(Showroom s) {
        try {
            statement = connection.prepareStatement("insert into showrooms (name,address,city, manager)"
                    + " values (?,?,?,?)");
            statement.setString(1, s.getName());
            statement.setString(2, s.getAddress());
            statement.setString(3, s.getCity());
            statement.setString(4, s.getManager());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public List<ShowroomProduct> selectShowroomsProducts() {
        List<ShowroomProduct> list = new ArrayList();
        try {
            statement = connection.prepareStatement("select * from showrooms, products,"
                    + " productsshowrooms, categories where "
                    + "showrooms.id=id_showroom and products.id=id_product and id_category=categories.id");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new ShowroomProduct(new Showroom(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4),
                        resultSet.getString(5)),new Product(resultSet.getInt(6),resultSet.getString(7),
                        resultSet.getString(8),resultSet.getFloat(9),new Category(resultSet.getInt(10),resultSet.getString(14)))));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    public List<Showroom> selectShowroomsByProduct(int id) {
        List<Showroom> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement("select * from showrooms, productsshowrooms where id_product =?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                list.add(new Showroom (resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }    

    public List<Product> selectProductsByShowroom(int id) {
        List<Product> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement("select * from products, categories, productsshowrooms "
                    + "where id_product=? and id_category=categories.id");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                list.add( new Product (resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getFloat(4),new Category(resultSet.getInt(5),resultSet.getString(6))));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }    
    public int deleteShowroomProduct(ShowroomProduct sp) {
        return deleteShowroomProduct(sp.getProduct().getId(),sp.getShowroom().getId());
    }

    public int deleteShowroomProduct(int idProduct,int idShowroom) {
        int n = 0;
        try {
            statement = connection.prepareStatement("delete from productsshowrooms  where id_product=? and id_showroom=?");
            statement.setInt(1, idProduct);
            statement.setInt(2, idShowroom);
            n = statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return n;
    }

    public int insertShowroomProduct(ShowroomProduct sp) {
        try {
            statement = connection.prepareStatement("insert into productsshowrooms (id_product, id_showroom)"
                    + " values (?,?)");
            statement.setInt(1, sp.getProduct().getId());
            statement.setInt(2, sp.getShowroom().getId());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
