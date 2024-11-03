/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.Planning;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import model.humanresource.Employee;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Duong Minh Quyen
 */
public class EmployeeDBContext extends DBContext<Employee> {

    public ArrayList<Employee> listEmployee(int planID) {
        ArrayList<Employee> emps = new ArrayList<>();
        PreparedStatement stm = null;
        String sql = "select p.pid, p.pname, p.[start], p.[end],\n"
                + "d.did, d.dname, d.[type], e.eid, e.ename, e.salarylevel, e.position\n"
                + "from [Plan] p join Department d on p.did = d.did\n"
                + "join Employee e on e.did = d.did\n"
                + "where p.pid = ? and e.position = 'Employee'";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, planID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setEid(rs.getInt("eid"));
                e.setEname(rs.getString("ename"));
                e.setSalarylevel(rs.getString("salarylevel"));
                emps.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emps;
    }
}
