/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.Planning;

import dal.DBContext;
import java.util.ArrayList;
import model.Planning.DetailProPlan;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Duong Minh Quyen
 */
public class DetailProPlanDBContext extends DBContext<DetailProPlan> {

    public DetailProPlan get(int gpid, int sid, Date date) {

        PreparedStatement stm = null;
        String sql = "select gpid, [sid], [date]\n"
                + "from DetailProPlan\n"
                + "where gpid = ? and [sid] = ? and [date] = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, gpid);
            stm.setInt(2, sid);
            stm.setDate(3, date);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                DetailProPlan dpp = new DetailProPlan();
                dpp.setGpid(rs.getInt("gpid"));
                dpp.setSid(rs.getInt("sid"));
                dpp.setDate(rs.getDate("date"));
                return dpp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailProPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//            stm.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(DetailProPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return null;
    }

    public boolean checkActualAmountWAE(int gpid, int sid, Date date) { // neu muon sua so luong bat ke da lam thi them mot quantity nhung ko nho hon cai actual
        boolean check = false;
        PreparedStatement stm = null;
        String sql = "select a.actualquantity\n"
                + "from DetailProPlan dpp left join WorkAssignmentEmp wae on dpp.dppid=wae.dppid\n"
                + "left join Attendance a on wae.waeid = a.waeid\n"
                + "where dpp.gpid=? and dpp.[sid]=? and dpp.[date]=?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, gpid);
            stm.setInt(2, sid);
            stm.setDate(3, date);
            ResultSet rs = stm.executeQuery();
            int count = 0;
            while (rs.next()) {
                Integer t = rs.getObject("actualquantity", Integer.class);
                if (t != null) {
                    count = count + 1;
                }
            }
            if (count != 0) {
                check = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailProPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return check;
    }

    public void insert(int gpid, int sid, Date date, int quantity) {
        PreparedStatement stm = null;
        String sql = "INSERT INTO [DetailProPlan]\n"
                + "           ([gpid]\n"
                + "           ,[date]\n"
                + "           ,[sid]\n"
                + "           ,[quantity])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, gpid);
            stm.setDate(2, new java.sql.Date(date.getTime()));
            stm.setInt(3, sid);
            stm.setInt(4, quantity);
            int rowsInserted = stm.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Insert successful, rows affected: " + rowsInserted);
            } else {
                System.out.println("Insert failed, no rows affected.");
            }
//            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DetailProPlanDBContext.class.getName()).log(Level.SEVERE, "Failed to insert DetailProPlan: gpid=" + gpid + ", sid=" + sid + ", date=" + date + ", quantity=" + quantity, ex);
        }
//        try {
//            stm.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(DetailProPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void update(int gpid, int sid, Date date, int quantity) {
        PreparedStatement stm = null;
        String sql = "UPDATE [DetailProPlan]\n"
                + "   SET [quantity] = ?\n"
                + " WHERE [gpid] = ? and [date] = ? and [sid] = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, quantity);
            stm.setInt(2, gpid);
            stm.setDate(3, date);
            stm.setInt(4, sid);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DetailProPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//            stm.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(DetailProPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    public ArrayList<DetailProPlan> listDetailPPlanFromPlan(int planID) {
        ArrayList<DetailProPlan> dpplans = new ArrayList<>();
        PreparedStatement stm = null;
        String sql = "select p.pid, p.pname, p.[start],p.[end], gp.gpid,\n"
                + "gp.prid, dpp.dppid, dpp.[date], dpp.[sid], dpp.quantity\n"
                + "from [Plan] p join GeneralPlan gp on p.pid = gp.pid\n"
                + "join DetailProPlan dpp on dpp.gpid = gp.gpid\n"
                + "where p.pid=?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, planID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DetailProPlan dpp = new DetailProPlan();
                dpp.setDppid(rs.getInt("dppid"));
                dpp.setGpid(rs.getInt("gpid"));
                dpp.setDate(rs.getDate("date"));
                dpp.setSid(rs.getInt("sid"));
                dpp.setQuantity(rs.getInt("quantity"));
                dpplans.add(dpp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailProPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            stm.close();
//            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DetailProPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dpplans;
    }

    public ArrayList<DetailProPlan> listFullAttDPPFronPlan(int planID) {
        ArrayList<DetailProPlan> dpplist = new ArrayList<>();
        return dpplist;
    }

    public int getDPPID(int gpid, int sid, Date date) {
        PreparedStatement stm = null;
        String sql = "select dpp.dppid\n"
                + "from DetailProPlan dpp \n"
                + "where dpp.gpid=? and dpp.[sid]=? and dpp.[date]=?";
        try {
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                int dppid = rs.getInt("dppid");
                return dppid;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailProPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
