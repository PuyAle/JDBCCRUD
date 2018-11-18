package JDBCCRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//The class that connects to the database and collects information in ArrayLists. The class implements interface. 
public class DAOProductJDBCConn implements DAOProduct {

    public static Scanner sc = new Scanner(System.in);

    final String url = "jdbc:mysql://localhost:3306/daotest?zeroDateTimeBehavior=convertToNull";
    final String user = "root";
    final String password = "root";

    private static Connection con;
    private PreparedStatement insert, findByName, findByPriceRange, findAll, delete, update;

    private static List<Product> listFromStatment(PreparedStatement ps) throws SQLException {

        ArrayList<Product> list = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price")));
        }

        return list;
    }
    
    public void printList(List<Product> a){
               a.forEach(System.out::println);
       
    }

    public DAOProductJDBCConn() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected");
            insert = con.prepareStatement("INSERT INTO product (name, price) values (?,?)");
            findByName = con.prepareStatement("Select * from product where name like ?");
            findByPriceRange = con.prepareStatement("Select * from product where price between ? and ?");
            findAll = con.prepareStatement("Select * from product");
            update = con.prepareStatement("update product set price = ? where id = ?");
            delete = con.prepareStatement("delete from product where id =?");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void create(String name, double price) {
        try {
            insert.setString(1, name);
            insert.setDouble(2, price);
            insert.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    @Override
    public List<Product> getByName(String name) {

        try {

            findByName.setString(1, name + "%");
            return listFromStatment(findByName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getByPriceRange(double min, double max) {
        try {
            findByPriceRange.setDouble(1, min);
            findByPriceRange.setDouble(2, max);
            return listFromStatment(findByPriceRange);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean updatePrice(int id, double newPrice) {
        try {
            update.setDouble(1, newPrice);
            update.setInt(2, id);
            if (update.executeUpdate() > 0) {
                return true;

            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAll() {
        try {
            return listFromStatment(findAll);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean remove(int id) {
        try {
            delete.setInt(1, id);
            if (delete.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
