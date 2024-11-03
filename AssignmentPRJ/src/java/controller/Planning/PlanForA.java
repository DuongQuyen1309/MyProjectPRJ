/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.Planning;

import controller.auth.BaseRBACController;
import dal.Planning.PlanDBContext;
import jakarta.servlet.ServletException;
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
public class PlanForA extends BaseRBACController {

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        PlanDBContext pdb = new PlanDBContext();
        ArrayList<Plan> listplan = pdb.listPlanFromWS("WSA");
        req.setAttribute("listplan", listplan);
        req.getRequestDispatcher("../view/managerws/listforA.jsp").forward(req, resp);
    }

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        PlanDBContext pdb = new PlanDBContext();
        ArrayList<Plan> listplan = pdb.listPlanFromWS("WSA");
        req.setAttribute("listplan", listplan);
        req.getRequestDispatcher("../view/managerws/listforA.jsp").forward(req, resp);
    }
    
}
