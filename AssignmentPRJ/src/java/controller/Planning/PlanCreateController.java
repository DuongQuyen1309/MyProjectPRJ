/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.Planning;

import controller.auth.BaseRBACController;
import dal.Planning.DepartmentDBContext;
import dal.Planning.PlanDBContext;
import dal.Planning.ProductDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import model.Planning.Department;
import model.Planning.GeneralPlan;
import model.Planning.Plan;
import model.Planning.Product;
import model.auth.User;

/**
 *
 * @author Duong Minh Quyen
 */
public class PlanCreateController extends BaseRBACController {

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        Plan plan = new Plan();
        plan.setPname(req.getParameter("name"));
        plan.setStart(Date.valueOf(req.getParameter("from")));
        plan.setEnd(Date.valueOf(req.getParameter("to")));
        plan.setStatus(req.getParameter("status"));
        plan.setCreatedby(account);
        
        Department d = new Department();
        d.setDid(req.getParameter("did"));
        
        plan.setDept(d);
        
        String[] pids = req.getParameterValues("pid");
        for (String pid : pids) {
            Product p = new Product();
            p.setPrid(Integer.parseInt(pid));
            
            GeneralPlan gplan = new GeneralPlan();
            gplan.setProduct(p);
            String raw_quantity = req.getParameter("quantity"+pid);
            String raw_effort = req.getParameter("effort"+pid);
            gplan.setQuantity(raw_quantity!=null && raw_quantity.length()>0?Integer.parseInt(raw_quantity):0);
            gplan.setEstimatedeffort(raw_effort!=null && raw_effort.length()>0?Float.parseFloat(raw_effort):0);
            
            if(gplan.getQuantity()>0&& gplan.getEstimatedeffort()>0)
                plan.getGeneralplan().add(gplan);
        }
        
        if(plan.getGeneralplan().size() >0)
        {
            PlanDBContext db = new PlanDBContext();
            db.insert(plan);
            resp.getWriter().println("your plan has been added!");
        }
        else
        {
            resp.getWriter().println("your plan does not have any headers! it is not allowed!");
        }
        
    }

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        DepartmentDBContext dbDept = new DepartmentDBContext();
        ProductDBContext dbProduct = new ProductDBContext();
        
        req.setAttribute("depts", dbDept.get("Production"));
        req.setAttribute("products", dbProduct.list());
        
        req.getRequestDispatcher("../view/plan/create.jsp").forward(req, resp);
    }
    
}
