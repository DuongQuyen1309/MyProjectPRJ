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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Planning.GeneralPlan;
import model.Planning.Plan;
import model.Planning.Product;
import model.Planning.Shift;
import model.humanresource.Employee;
import java.sql.*;
import model.Planning.DetailProPlan;
import model.Planning.WorkAssignmentEmp;
import model.auth.User;

/**
 *
 * @author Duong Minh Quyen
 */
public class WorkAssignmentCreate extends BaseRBACController {


    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("planid"));
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

        for (int i = 0; i < daterange.size(); i++) {

            Date date = daterange.get(i);
            for (int j = 0; j < shifts.size(); j++) {

                Shift sid = shifts.get(j);
                for (int m = 0; m < gplist.size(); m++) {

                    GeneralPlan gpid = gplist.get(m);
                    for (int n = 0; n < emps.size(); n++) {

                        Employee eid = emps.get(n);
                        
                        String temp = req.getParameter("orderquantity" + date+"a" + sid.getSid() +"a"+ gpid.getGpid() +"a"+ eid.getEid());   
                        
                        if (temp != null && dppdb.getDPPID(gpid.getGpid(), sid.getSid(), date) != null) {
                            int dppid = dppdb.getDPPID(gpid.getGpid(), sid.getSid(), date).getDppid();
                           
                            if (temp != null && !temp.trim().isEmpty()) {
                                
//                                resp.getWriter().print("eid "+ eid);
//                                resp.getWriter().print("orderq"+ temp);
//                                resp.getWriter().print("dppid "+dppid);
                                 
                                int orderquantity = Integer.parseInt(temp);
                                if (waedb.get(gpid.getGpid(), sid.getSid(), date, eid.getEid()) == null) {
                                    waedb.insert(dppid, eid.getEid(), orderquantity);
                                    //insert
//                                    resp.getWriter().println("insert");
                                }
                                if (waedb.get(gpid.getGpid(), sid.getSid(), date, eid.getEid()) != null 
                                        && waedb.checkWAEInAttendance(gpid.getGpid(), sid.getSid(), date, eid.getEid()) == false) {
                                    waedb.update(dppid, eid.getEid(), orderquantity);
                                    //update
//                                    resp.getWriter().println("update");
                                }
                                if (waedb.get(gpid.getGpid(), sid.getSid(), date, eid.getEid()) != null 
                                        && waedb.checkWAEInAttendance(gpid.getGpid(), sid.getSid(), date, eid.getEid()) == true) {
//                                    resp.getWriter().println("th3");
                                }
                            }
                        }
                    }
                }
            }
        }
        req.getRequestDispatcher("../managerA").forward(req, resp);
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
        req.getRequestDispatcher("../view/plan/workassignmentcreate.jsp").forward(req, resp);
    }

}
