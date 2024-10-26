/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.Planning;

import dal.DBContext;
import java.util.ArrayList;
import model.Planning.Shift;
import java.sql.*;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Duong Minh Quyen
 */
public class ShiftDBContext extends DBContext<Shift> {

    public ArrayList<Shift> listShift() {
        ArrayList<Shift> shifts = new ArrayList<>();
        PreparedStatement stm = null;
        String sql = "SELECT [sid]\n"
                + "      ,[start]\n"
                + "      ,[end]\n"
                + "      ,[sname]\n"
                + "  FROM [Shift]";
        try {
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Shift s = new Shift();
                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setStart(rs.getTime("start").toLocalTime());
                s.setEnd(rs.getTime("end").toLocalTime());
                shifts.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShiftDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            stm.close();
//            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ShiftDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return shifts;
    }
}
