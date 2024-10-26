/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.Planning;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Planning.Plan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Planning.Attendance;
import model.Planning.Department;
import model.Planning.GeneralPlan;
import model.Planning.Plan;
import model.Planning.DetailProPlan;
import model.Planning.Product;
import model.Planning.WorkAssignmentEmp;
import model.auth.User;
import model.humanresource.Employee;

/**
 *
 * @author Duong Minh Quyen
 */
public class PlanDBContext extends DBContext<Plan> {

    public void insert(Plan model) {
        try {
            connection.setAutoCommit(false);
            String sql_insert_plan = "INSERT INTO [Plan]\n"
                    + "           ([pname]\n"
                    + "           ,[start]\n"
                    + "           ,[end]\n"
                    + "           ,[did]\n"
                    + "           ,[status]\n"
                    + "           ,[createdby])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm_insert_plan = connection.prepareStatement(sql_insert_plan);
            stm_insert_plan.setString(1, model.getPname());
            stm_insert_plan.setDate(2, model.getStart());
            stm_insert_plan.setDate(3, model.getEnd());
            stm_insert_plan.setString(4, model.getDept().getDid());
            stm_insert_plan.setString(5, model.getStatus());
            stm_insert_plan.setString(6, model.getCreatedby().getDisplayname());
            stm_insert_plan.executeUpdate();

            String sql_select_plan = "SELECT @@IDENTITY as pid";
            PreparedStatement stm_select_plan = connection.prepareStatement(sql_select_plan);
            ResultSet rs = stm_select_plan.executeQuery();
            if (rs.next()) {
                model.setPid(rs.getInt("pid"));
            }

            String sql_insert_header = "INSERT INTO [GeneralPlan]\n"
                    + "           ([pid]\n"
                    + "           ,[prid]\n"
                    + "           ,[quantity]\n"
                    + "           ,[estimatedeffort])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            for (GeneralPlan gplan : model.getGeneralplan()) {
                PreparedStatement stm_insert_header = connection.prepareStatement(sql_insert_header);
                stm_insert_header.setInt(1, model.getPid());
                stm_insert_header.setInt(2, gplan.getProduct().getPrid());
                stm_insert_header.setInt(3, gplan.getQuantity());
                stm_insert_header.setFloat(4, gplan.getEstimatedeffort());
                stm_insert_header.executeUpdate();
            }

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public ArrayList<Plan> listBasePlan() {
        ArrayList<Plan> ps = new ArrayList<>();
        PreparedStatement stm = null;
        String sql = "SELECT [pid]\n"
                + "      ,[pname]\n"
                + "      ,[start]\n"
                + "      ,[end]\n"
                + "      ,[did]\n"
                + "      ,[status]\n"
                + "      ,[createdby]\n"
                + "  FROM [Plan]";
        try {
            stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Plan p = new Plan();
                p.setPid(rs.getInt("pid"));
                p.setPname(rs.getNString("pname"));
                p.setStart(rs.getDate("start"));
                p.setEnd(rs.getDate("end"));

                Department d = new Department();
                d.setDid(rs.getString("did"));
                p.setDept(d);

                p.setStatus(rs.getString("status"));

                User u = new User();
                u.setDisplayname(rs.getString("createdby"));
                p.setCreatedby(u);

                ps.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            stm.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ps;
    }

    public ArrayList<Plan> listProductsInGeneralPlan(int planID) {
        ArrayList<Plan> information = new ArrayList<>();
        PreparedStatement stm = null;
        String sql = "SELECT p.pid, p.pname, p.[start], p.[end], p.[status], d.did, d.dname,\n"
                + "gp.gpid, gp.prid, gp.quantity as gp_quantity,pr.prname,\n"
                + "dpp.dppid, dpp.[date], dpp.[sid], dpp.quantity as dpp_quantity,\n"
                + "we.waeid,we.orderdquantity, att.actualquantity, att.alpha\n"
                + "FROM [Plan] p \n"
                + "LEFT JOIN Department d ON p.did = d.did\n"
                + "LEFT JOIN GeneralPlan gp ON p.pid = gp.pid\n"
                + "INNER JOIN Product pr on gp.prid = pr.prid\n"
                + "LEFT JOIN DetailProPlan dpp ON gp.gpid = dpp.gpid \n"
                + "LEFT JOIN WorkAssignmentEmp we ON dpp.dppid = we.dppid \n"
                + "LEFT JOIN Attendance att ON we.waeid = att.waeid\n"
                + "where p.pid = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, planID);
            ResultSet rs = stm.executeQuery();

            Plan p = new Plan();
            p.setPid(-1);
            GeneralPlan gp = new GeneralPlan();
            gp.setGpid(-1);
            DetailProPlan dpp = new DetailProPlan();
            dpp.setDppid(-1);
            while (rs.next()) {
                int pid = rs.getInt("pid");
                if (pid != p.getPid()) {
                    p = new Plan();
                    p.setPid(pid);
                    p.setPname(rs.getNString("pname"));
                    p.setStart(rs.getDate("start"));
                    p.setEnd(rs.getDate("end"));
                    p.setStatus(rs.getString("status"));

                    Department d = new Department();
                    d.setDid(rs.getString("did"));
                    d.setDname(rs.getString("dname"));
                    p.setDept(d);
                    p.setGeneralplan(new ArrayList<>());
                    information.add(p);
                }

                int gpid = rs.getInt("gpid");
                if (gpid != gp.getGpid()) {
                    gp = new GeneralPlan();
                    gp.setGpid(gpid);
                    gp.setPlan(p);

                    Product pro = new Product();
                    pro.setPrid(rs.getInt("prid"));
                    pro.setPrname(rs.getNString("prname"));
                    gp.setProduct(pro);

                    gp.setQuantity(rs.getInt("gp_quantity"));
                    gp.setDetailplanlist(new ArrayList<>());
                    p.getGeneralplan().add(gp);
                }

                int dppid = rs.getInt("dppid");
                if (dppid != dpp.getDppid()) {
                    dpp = new DetailProPlan();
                    dpp.setDppid(dppid);
                    dpp.setDate(rs.getDate("date"));
                    dpp.setSid(rs.getInt("sid"));
                    dpp.setQuantity(rs.getInt("dpp_quantity"));
                    gp.getDetailplanlist().add(dpp);
                    dpp.setWorklist(new ArrayList<>());
                }

                WorkAssignmentEmp wae = new WorkAssignmentEmp();
                wae.setOrderquantity(rs.getInt("orderdquantity"));
                wae.setWaeid(rs.getInt("waeid"));

                Attendance att = new Attendance();
                att.setActualquantity(rs.getInt("actualquantity"));
                att.setAlpha(rs.getFloat("alpha"));
                wae.setAttendedPerson(att);

                dpp.getWorklist().add(wae);
            }
            for (int i = 0; i < information.size(); i++) {
                for (int j = 0; j < information.get(i).getGeneralplan().size(); j++) {
                    int sumgeneralplan = 0;
                    for (int m = 0; m < information.get(i).getGeneralplan().get(j).getDetailplanlist().size(); m++) {
                        DetailProPlan tempdpp = information.get(i).getGeneralplan().get(j).getDetailplanlist().get(m);
                        for (int n = 0; n < tempdpp.getWorklist().size(); n++) {
                            int amount = tempdpp.getWorklist().get(n).getAttendedPerson().getActualquantity();
                            sumgeneralplan += amount;
                        }
                    }
                    information.get(i).getGeneralplan().get(j).setCompletedamount(sumgeneralplan);
                    int remaingeneralplan = information.get(i).getGeneralplan().get(j).getQuantity() - sumgeneralplan;
                    information.get(i).getGeneralplan().get(j).setRemainedamount(remaingeneralplan);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return information;
    }

    public ArrayList<Plan> listGeneralPlanfromPlan(int PlanID) {
        ArrayList<Plan> gplans = new ArrayList<>();
        PreparedStatement stm = null;
        String sql = "select p.pid, p.pname, p.[start], p.[end], gp.gpid, gp.prid, pr.prname, gp.quantity\n"
                + "from [Plan] p join GeneralPlan gp on p.pid = gp.pid\n"
                + "join Product pr on pr.prid = gp.prid\n"
                + "where p.pid = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, PlanID);
            ResultSet rs = stm.executeQuery();
            Plan p = new Plan();
            p.setPid(-1);
            while (rs.next()) {
                int pid = rs.getInt("pid");
                if (pid != p.getPid()) {
                    p = new Plan();
                    p.setPid(pid);
                    p.setPname(rs.getNString("pname"));
                    p.setStart(rs.getDate("start"));
                    p.setEnd(rs.getDate("end"));
                    p.setGeneralplan(new ArrayList<>());
                    gplans.add(p);
                }
                GeneralPlan gp = new GeneralPlan();
                gp.setGpid(rs.getInt("gpid"));

                Product pro = new Product();
                pro.setPrid(rs.getInt("prid"));
                pro.setPrname(rs.getNString("prname"));
                gp.setProduct(pro);
                gp.setQuantity(rs.getInt("quantity"));
                p.getGeneralplan().add(gp);
            } 
        }catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            stm.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gplans;
    }

    
}
