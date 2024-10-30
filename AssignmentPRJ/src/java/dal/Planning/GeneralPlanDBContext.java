/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.Planning;

import dal.DBContext;
import java.util.ArrayList;
import model.Planning.GeneralPlan;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Planning.Plan;
import model.Planning.Product;

/**
 *
 * @author Duong Minh Quyen
 */
public class GeneralPlanDBContext extends DBContext<GeneralPlan> {

    public ArrayList<GeneralPlan> listGP(int planID) {
        ArrayList<GeneralPlan> gplans = new ArrayList<>();
        PreparedStatement stm = null;
        String sql = "select gp.gpid, gp.pid, gp.prid,\n"
                + "gp.quantity, gp.estimatedeffort, p.prname\n"
                + "from GeneralPlan gp join Product p on gp.prid = p.prid\n"
                + "where gp.pid=?";

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, planID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                GeneralPlan g = new GeneralPlan();
                g.setGpid(rs.getInt("gpid"));

                Plan p = new Plan();
                p.setPid(rs.getInt("pid"));
                g.setPlan(p);

                Product pro = new Product();
                pro.setPrid(rs.getInt("prid"));
                pro.setPrname(rs.getNString("prname"));
                g.setProduct(pro);
                g.setQuantity(rs.getInt("quantity"));
                g.setEstimatedeffort(rs.getFloat("estimatedeffort"));

                gplans.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GeneralPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(GeneralPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gplans;
    }

}
