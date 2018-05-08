package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Try extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        if(request.getParameter("depo")!=null){
            RequestDispatcher dis = request.getRequestDispatcher("/Deposit");
            dis.include(request, response);
        }
        else if(request.getParameter("with")!=null){
            RequestDispatcher dis = request.getRequestDispatcher("/Withdraw");
            dis.include(request, response);
        }
        else if(request.getParameter("trans")!=null){
            RequestDispatcher dis = request.getRequestDispatcher("/Transfer");
            dis.include(request, response);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


}
