/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.Planning;

import dal.DBContext;
import java.util.ArrayList;
import model.Planning.WorkAssignmentEmp;
import model.humanresource.Employee;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Duong Minh Quyen
 */
public class WorkAssignmentDBContext extends DBContext<WorkAssignmentEmp> {

    public ArrayList<WorkAssignmentEmp> list() {
        ArrayList<WorkAssignmentEmp> lists = new ArrayList<>();
        PreparedStatement stm = null;
        String sql = "SELECT [waeid]\n"
                + "      ,[dppid]\n"
                + "      ,[eid]\n"
                + "      ,[orderdquantity]\n"
                + "  FROM [WorkAssignmentEmp]";
        try {
            stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                WorkAssignmentEmp w = new WorkAssignmentEmp();
                w.setWaeid(rs.getInt("waeid"));
                w.setDppid(rs.getInt("dppid"));

                Employee e = new Employee();
                e.setEid(rs.getInt("eid"));
                w.setEid(e);

                w.setOrderquantity(rs.getInt("orderdquantity"));
                lists.add(w);
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkAssignmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(WorkAssignmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lists;
    }

    public WorkAssignmentEmp get(int gpid, int sid, Date date, int eid) {
        PreparedStatement stm = null;
        String sql = "select d.dppid, w.eid, w.orderdquantity, w.waeid\n"
                + "from DetailProPlan d join WorkAssignmentEmp w on d.dppid = w.dppid\n"
                + "where d.gpid = ? and d.[date] = ? and d.[sid] = ? and w.eid = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, gpid);
            stm.setDate(2, date);
            stm.setInt(3, sid);

            stm.setInt(4, eid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                WorkAssignmentEmp w = new WorkAssignmentEmp();
                w.setWaeid(rs.getInt("waeid"));
                w.setDppid(rs.getInt("dppid"));

                Employee e = new Employee();
                e.setEid(rs.getInt("eid"));
                w.setEid(e);

                w.setOrderquantity(rs.getInt("orderdquantity"));
                return w;
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkAssignmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//            stm.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(WorkAssignmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return null;
    }

    public boolean checkWAEInAttendance(int gpid, int sid, Date date, int eid) {
        boolean check = false; //khong co
        PreparedStatement stm = null;
        String sql = "select a.actualquantity\n"
                + "from DetailProPlan dpp left join WorkAssignmentEmp wae on dpp.dppid=wae.dppid\n"
                + "left join Attendance a on wae.waeid = a.waeid\n"
                + "where dpp.gpid=? and dpp.[sid]=? and dpp.[date]=? and wae.eid = ?";

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, gpid);
            stm.setInt(2, sid);
            stm.setDate(3, date);
            stm.setInt(4, eid);
            ResultSet rs = stm.executeQuery();
            int count = 0;
            while (rs.next()) {
                Integer t = rs.getObject("actualquantity", Integer.class);
                if (t != null) {
                    count = count + 1;
                }
            }
            if (count != 0) {
                check = true; //co
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkAssignmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    public void insert(int dppid, int eid, int orderquantity) {
        PreparedStatement stm = null;
        String sql = "INSERT INTO [WorkAssignmentEmp]\n"
                + "           ([dppid]\n"
                + "           ,[eid]\n"
                + "           ,[orderdquantity])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, dppid);
            stm.setInt(2, eid);
            stm.setInt(3, orderquantity);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(WorkAssignmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//            stm.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(WorkAssignmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void update(int dppid, int eid, int orderquantity) {
        PreparedStatement stm = null;
        String sql = "UPDATE [WorkAssignmentEmp]\n"
                + "   SET [orderdquantity] = ?\n"
                + " WHERE [dppid] = ? and [eid] = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, orderquantity);
            stm.setInt(2, dppid);
            stm.setInt(3, eid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WorkAssignmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//            stm.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(WorkAssignmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
