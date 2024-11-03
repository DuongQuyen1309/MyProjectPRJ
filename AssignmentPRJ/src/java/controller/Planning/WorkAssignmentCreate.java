/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.Planning;

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

/**
 *
 * @author Duong Minh Quyen
 */
public class WorkAssignmentCreate extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        gplist = gpdb.listGP(1);

        planWithGP = plandb.listGeneralPlanfromPlan(1);
        Date startDate = planWithGP.get(0).getStart();
        Date endDate = planWithGP.get(0).getEnd();
        ArrayList<Date> daterange = sp.getDatesBetween(startDate, endDate);

        ArrayList<Employee> emps = edb.listEmployee(1);

        dpplist = dppdb.listDetailPPlanFromPlan(1);
        shifts = shiftdb.listShift();
        waelist = waedb.list();

        for (int i = 0; i < daterange.size(); i++) {

            Date date = daterange.get(i);
            for (int j = 0; j < shifts.size(); j++) {

                int sid = shifts.get(j).getSid();
                for (int m = 0; m < gplist.size(); m++) {

                    int gpid = gplist.get(m).getGpid();
                    for (int n = 0; n < emps.size(); n++) {

                        int eid = emps.get(n).getEid();
                        String temp = req.getParameter("orderquantity" + date + sid + gpid + eid);                       
                        if (dppdb.getDPPID(gpid, sid, date) != -1) {
                            int dppid = dppdb.getDPPID(gpid, sid, date);
                            if (temp != null && !temp.trim().isEmpty()) {
                                int orderquantity = Integer.parseInt(temp);
                                if (waedb.get(gpid, sid, date, eid) == null) {
                                    waedb.insert(dppid, eid, orderquantity);
                                    //insert
                                }
                                if (waedb.get(gpid, sid, date, eid) != null && waedb.checkWAEInAttendance(gpid, sid, date, eid) == false) {
                                    waedb.update(dppid, eid, orderquantity);
                                    //update
                                }
                                if (waedb.get(gpid, sid, date, eid) != null && waedb.checkWAEInAttendance(gpid, sid, date, eid) == true) {

                                }
                            }
                        }
                    }
                }
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        gplist = gpdb.listGP(1);

        planWithGP = plandb.listGeneralPlanfromPlan(1);
        Date startDate = planWithGP.get(0).getStart();
        Date endDate = planWithGP.get(0).getEnd();
        ArrayList<Date> daterange = sp.getDatesBetween(startDate, endDate);

        ArrayList<Employee> emps = edb.listEmployee(1);

        dpplist = dppdb.listDetailPPlanFromPlan(1);
        shifts = shiftdb.listShift();
        waelist = waedb.list();

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
