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
import model.auth.Feature;
import model.auth.Role;
import model.auth.User;

/**
 *
 * @author Duong Minh Quyen
 */
public class UserDBContext extends DBContext<User>{
    public ArrayList<Role> getRoles(String username) {
        PreparedStatement stm = null;
        ArrayList<Role> roles = new ArrayList<>();

        try {
            String sql = "SELECT r.rid,r.rname,f.fid,f.fname,f.[url] FROM [User] u \n"
                    + "	INNER JOIN UserRole ur ON u.username = ur.username\n"
                    + "	INNER JOIN [Role] r ON r.rid = ur.rid\n"
                    + "	INNER JOIN RoleFeature rf ON rf.rid = r.rid\n"
                    + "	INNER JOIN Feature f ON f.fid = rf.fid\n"
                    + "WHERE u.username = ? ";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            
            Role crole = new Role();
            crole.setRid(-1);
            
            while(rs.next()){
                int rid = rs.getInt("rid");
                if(rid != crole.getRid()){
                    crole = new Role();
                    crole.setRid(rid);
                    crole.setRname(rs.getNString("rname"));
                    roles.add(crole);
                }
                Feature f = new Feature();
                f.setFid(rs.getInt("fid"));
                f.setFname(rs.getString("fname"));
                f.setUrl(rs.getString("url"));
                
                f.setRoles(roles);                
                crole.getFeatures().add(f);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return roles;
    }
    public User get(String username, String password) {
        User user = null;
        PreparedStatement stm = null;

        try {
            String sql = "SELECT [username],[password],[displayname] FROM [User]\n"
                    + "WHERE [username] = ? AND [password] = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setDisplayname(rs.getString("displayname"));
                user.setUsername(username);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }
}
