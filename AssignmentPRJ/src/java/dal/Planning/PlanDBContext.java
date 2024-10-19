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
import model.Planning.GeneralPlan;

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
}
