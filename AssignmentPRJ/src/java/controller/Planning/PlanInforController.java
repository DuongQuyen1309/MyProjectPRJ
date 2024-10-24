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
import model.Planning.Plan;
import model.auth.User;

/**
 *
 * @author Duong Minh Quyen
 */
public class PlanInforController extends BaseRBACController{

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        PlanDBContext plandb = new PlanDBContext();
        ArrayList<Plan> vidu = plandb.listProductsInGeneralPlan(id);
        req.setAttribute("vidu", vidu);
        req.getRequestDispatcher("../view/plan/inforplan.jsp").forward(req, resp);
    }
    
}
