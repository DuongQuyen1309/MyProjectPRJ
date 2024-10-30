/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.Planning;

import controller.auth.BaseRBACController;
import dal.Planning.DepartmentDBContext;
import dal.Planning.GeneralPlanDBContext;
import dal.Planning.PlanDBContext;
import dal.Planning.ProductDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import model.Planning.Department;
import model.Planning.GeneralPlan;
import model.Planning.Plan;
import model.Planning.Product;
import model.auth.User;

/**
 *
 * @author Duong Minh Quyen
 */
public class PlanUpdateController extends BaseRBACController {

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        Plan plan = new Plan();
        plan.setPid(Integer.parseInt(req.getParameter("id")));
        plan.setPname(req.getParameter("name"));
        plan.setStart(Date.valueOf(req.getParameter("from")));
        plan.setEnd(Date.valueOf(req.getParameter("to")));
        plan.setStatus(req.getParameter("status"));
        plan.setCreatedby(account);
        
        Department d = new Department();
        d.setDid(req.getParameter("did"));
        
        plan.setDept(d);
        
        String[] prids = req.getParameterValues("prid");
         for (String prid : prids) {
             resp.getWriter().println(prid);
         }
        for (String prid : prids) {
            Product p = new Product();
            p.setPrid(Integer.parseInt(prid));
            
            GeneralPlan gplan = new GeneralPlan();
            gplan.setProduct(p);
            String raw_quantity = req.getParameter("quantity"+p.getPrid());
            String raw_effort = req.getParameter("effort"+p.getPrid());
            gplan.setQuantity(raw_quantity!=null && raw_quantity.length()>0?Integer.parseInt(raw_quantity):0);
            gplan.setEstimatedeffort(raw_effort!=null && raw_effort.length()>0?Float.parseFloat(raw_effort):0);
            
            resp.getWriter().println(gplan.getQuantity());
            resp.getWriter().println(gplan.getEstimatedeffort());
            if(gplan.getQuantity()>0&& gplan.getEstimatedeffort()>0)
                plan.getGeneralplan().add(gplan);
        }
        
        if(plan.getGeneralplan().size() >0)
        {
            PlanDBContext db = new PlanDBContext();
            db.update(plan);
            resp.sendRedirect("list");
        }
        else
        {
            resp.sendRedirect("create");
        }
    }

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        PlanDBContext plandb = new PlanDBContext();
        ProductDBContext productdb = new ProductDBContext();
        GeneralPlanDBContext gpdb = new GeneralPlanDBContext();
        DepartmentDBContext ddb = new DepartmentDBContext();
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<GeneralPlan> gplans = new ArrayList<>();
        ArrayList<Department> depts = new ArrayList<>();

        Plan plan = plandb.get(id);
        if (plan != null) {
            products = productdb.list();
            gplans = gpdb.listGP(id);
            depts = ddb.get("Production");

            req.setAttribute("plan", plan);
            req.setAttribute("products", products);
            req.setAttribute("gplans", gplans);
            req.setAttribute("depts", depts);

            req.getRequestDispatcher("../view/plan/updateplan.jsp").forward(req, resp);
        }
        else{
            resp.sendError(404, "employee does not exist!");
        }
    }

}
