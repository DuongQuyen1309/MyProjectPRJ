/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.Planning;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Planning.Product;
import java.sql.*;
/**
 *
 * @author Duong Minh Quyen
 */
public class ProductDBContext extends DBContext<Product>{
    public ArrayList<Product> list() {
        String sql = "SELECT [prid]\n"
                + "      ,[prname]\n"
                + "  FROM [Product]";
        ArrayList<Product> products = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Product p = new Product();
                p.setPrid(rs.getInt("prid"));
                p.setPrname(rs.getString("prname"));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return products;
    }
}
