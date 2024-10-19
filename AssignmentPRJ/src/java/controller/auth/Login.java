/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.auth;

import dal.Planning.UserDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.auth.User;

/**
 *
 * @author Duong Minh Quyen
 */
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param_user = req.getParameter("username");//user input
        String param_pass = req.getParameter("password");
        
        UserDBContext udb = new UserDBContext();
        User account = udb.get(param_user, param_pass);
        
        if(account != null){         
            //Chen duong link cua tung phong ban vao day
            req.getSession().setAttribute("account", account);        
        } else {
            //Chen duong linh dang nhap lai vao day
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.html").forward(req, resp);
    }
    
}
