/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.Planning;

import java.sql.Date;
import controller.auth.BaseRBACController;
import dal.Planning.DetailProPlanDBContext;
import dal.Planning.PlanDBContext;
import dal.Planning.ShiftDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Planning.DetailProPlan;
import model.Planning.GeneralPlan;
import model.Planning.Plan;
import model.Planning.Shift;
import model.auth.User;

/**
 *
 * @author Duong Minh Quyen
 */
public class DetailProPlanCreate extends BaseRBACController {

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("planid"));       
        PlanDBContext plandb = new PlanDBContext();
        ShiftDBContext shiftdb = new ShiftDBContext();
        DetailProPlanDBContext dppdb = new DetailProPlanDBContext();
        ArrayList<Shift> shifts = new ArrayList<>();
        ArrayList<Plan> planWithGP = new ArrayList<>();

        planWithGP = plandb.listGeneralPlanfromPlan(id);
        shifts = shiftdb.listShift();

        Date startDate = planWithGP.get(0).getStart();
        Date endDate = planWithGP.get(0).getEnd();

        SyntheticProcessing sp = new SyntheticProcessing();
        ArrayList<Date> daterange = sp.getDatesBetween(startDate, endDate);
        ArrayList<DetailProPlan> dpplans = dppdb.listDetailPPlanFromPlan(id);

        for (int i = 0; i < planWithGP.size(); i++) {
            for (int j = 0; j < planWithGP.get(i).getGeneralplan().size(); j++) {
                
                GeneralPlan gp = planWithGP.get(i).getGeneralplan().get(j);
                for (int m = 0 ; m < shifts.size();m++){
                    
                    Shift s = shifts.get(m);
                    for(int n = 0; n < daterange.size();n++){
                        
                        Date d = daterange.get(n);
                        String temp = req.getParameter(""+gp.getGpid()+"a"+s.getSid()+"a"+d);
                        
                        if(temp != null  && !temp.trim().isEmpty()){
                            int quantity = Integer.parseInt(temp);
                            
                            if(dppdb.get(gp.getGpid(), s.getSid(), d) ==  null){
                                dppdb.insert(gp.getGpid(), s.getSid(), d, quantity);
                            }
                            if(dppdb.get(gp.getGpid(), s.getSid(), d) !=  null 
                                    && dppdb.checkActualAmountWAE(gp.getGpid(), s.getSid(), d) == false){
                                
                                dppdb.update(gp.getGpid(), s.getSid(), d, quantity);
                            }
                            if(dppdb.get(gp.getGpid(), s.getSid(), d) !=  null 
                                    && dppdb.checkActualAmountWAE(gp.getGpid(), s.getSid(), d) == true){
                            }
                        }                                              
                    }
                }
            }
        }
    }

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
       int id = Integer.parseInt(req.getParameter("id"));
        
        PlanDBContext plandb = new PlanDBContext();
        ShiftDBContext shiftdb = new ShiftDBContext();
        DetailProPlanDBContext dppdb = new DetailProPlanDBContext();
        ArrayList<Shift> shifts = new ArrayList<>();
        ArrayList<Plan> planWithGP = new ArrayList<>();

        planWithGP = plandb.listGeneralPlanfromPlan(id);
        shifts = shiftdb.listShift();

        Date startDate = planWithGP.get(0).getStart();
        Date endDate = planWithGP.get(0).getEnd();

        SyntheticProcessing sp = new SyntheticProcessing();
        ArrayList<Date> daterange = sp.getDatesBetween(startDate, endDate);
        ArrayList<DetailProPlan> dpplans = dppdb.listDetailPPlanFromPlan(id);
        
        req.setAttribute("planid", id);
        req.setAttribute("shifts", shifts);
        req.setAttribute("daterange", daterange);
        req.setAttribute("dpplans", dpplans);
        req.setAttribute("planWithGP", planWithGP);
        req.getRequestDispatcher("../../view/plan/createDetailPlan.jsp").forward(req, resp);
    }

}
