/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.Planning;

import controller.auth.BaseRBACController;
import dal.Planning.PlanDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Planning.DetailProPlan;
import model.Planning.Plan;
import model.Planning.WorkAssignmentEmp;
import model.auth.User;

/**
 *
 * @author Duong Minh Quyen
 */
public class PlanListController extends BaseRBACController {

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        PlanDBContext plandb = new PlanDBContext();
        ArrayList<Plan> plans = plandb.listBasePlan();
        req.setAttribute("plans", plans);
        req.getRequestDispatcher("../view/plan/list.jsp").forward(req, resp);
    }

}
