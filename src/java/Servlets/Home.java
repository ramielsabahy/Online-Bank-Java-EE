package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Home extends HttpServlet {
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Users u = new Users();
        double amo = Double.parseDouble(request.getParameter("amo"));
        String sql = "update information set withdraw ='"+amo+"' where email = '";
        
        String n=null;
        String b = null;
        String deposit = null;
        String withdraw = null;
        String trans = null;
        String mail=null;
        String pass = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
            Statement st = (Statement) con.createStatement();
            ResultSet result = st.executeQuery(sql);
            while (result.next()){
                n=result.getString(1);
                b=result.getString(2);
                deposit=result.getString(3);
                withdraw=result.getString(4);
                trans=result.getString(5);
                mail=result.getString(6);
                pass=result.getString(7);
            }
        
        response.sendRedirect("Home.html");
    } catch(SQLException ex){
        out.println(ex.getMessage());
    } catch(ClassNotFoundException ex){
        out.println(ex.getMessage());
    }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
}
