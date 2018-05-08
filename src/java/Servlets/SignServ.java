package Servlets;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "NServ2", urlPatterns = {"/Serv2"})
public class SignServ extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String pass = request.getParameter("pass");
        String cpass = request.getParameter("cpass");
        String mail = request.getParameter("mail");
        String card = request.getParameter("card");
        String phone = request.getParameter("phone");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String code = request.getParameter("code");
        Connection con = null;
        Statement st = null;
        if(pass.equals(cpass)&&mail!="select mail from sign where mail ='"+mail+"'"){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
            String sql = "insert into information values ('"+fname+"','"+lname+"','"+pass+"','"+cpass+"','"+mail+"','"+card+"','"+phone+"','"+country+"','"+city+"','"+state+"','"+code+"','"+0+"','"+0+"','"+0+"','"+0+"','"+0+"')";
            st = (Statement)con.createStatement();
            int i = st.executeUpdate(sql);
            if(i>0){
                response.sendRedirect("SuccessfullRegister.html");
            }
            else
                out.println("Un");
            
        } catch (ClassNotFoundException ex) {
            ex.getMessage();
        } catch (SQLException ex) {
            ex.getMessage();
        }
        }
        else
            out.println("Pass Didn't match");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
