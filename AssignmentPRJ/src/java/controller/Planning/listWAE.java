/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.Planning;

import controller.auth.BaseRBACController;
import dal.Planning.DetailProPlanDBContext;
import dal.Planning.EmployeeDBContext;
import dal.Planning.GeneralPlanDBContext;
import dal.Planning.PlanDBContext;
import dal.Planning.ShiftDBContext;
import dal.Planning.WorkAssignmentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import model.Planning.DetailProPlan;
import model.Planning.GeneralPlan;
import model.Planning.Plan;
import model.Planning.Shift;
import model.Planning.WorkAssignmentEmp;
import model.auth.User;
import model.humanresource.Employee;

/**
 *
 * @author Duong Minh Quyen
 */
public class listWAE extends BaseRBACController {

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        EmployeeDBContext edb = new EmployeeDBContext();
        ShiftDBContext shiftdb = new ShiftDBContext();
        DetailProPlanDBContext dppdb = new DetailProPlanDBContext();
        SyntheticProcessing sp = new SyntheticProcessing();
        PlanDBContext plandb = new PlanDBContext();
        WorkAssignmentDBContext waedb = new WorkAssignmentDBContext();

        ArrayList<Shift> shifts = new ArrayList<>();
        ArrayList<DetailProPlan> dpplist = new ArrayList<>();
        ArrayList<Plan> planWithGP = new ArrayList<>();
        ArrayList<WorkAssignmentEmp> waelist = new ArrayList<>();

        GeneralPlanDBContext gpdb = new GeneralPlanDBContext();

        ArrayList<GeneralPlan> gplist = new ArrayList<>();
        gplist = gpdb.listGP(id);

        planWithGP = plandb.listGeneralPlanfromPlan(id);
        Date startDate = planWithGP.get(0).getStart();
        Date endDate = planWithGP.get(0).getEnd();
        ArrayList<Date> daterange = sp.getDatesBetween(startDate, endDate);

        ArrayList<Employee> emps = edb.listEmployee(id);

        dpplist = dppdb.listDetailPPlanFromPlan(id);
        shifts = shiftdb.listShift();
        waelist = waedb.list();

        req.setAttribute("planid", id);
        req.setAttribute("waelist", waelist);
        req.setAttribute("emps", emps);
        req.setAttribute("daterange", daterange);
        req.setAttribute("planWithGP", planWithGP);
        req.setAttribute("shifts", shifts);
        req.setAttribute("dpplist", dpplist);
        req.setAttribute("gplist", gplist);
        req.getRequestDispatcher("../view/plan/listwae.jsp").forward(req, resp);
    }
    
}
