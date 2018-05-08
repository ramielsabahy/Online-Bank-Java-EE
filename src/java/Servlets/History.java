package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class History extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Users u =(Users) session.getAttribute("serving");
        int a =0;
        double [] deposit=new double [100];
        double [] withdraw =new double [100];
        double [] transfer =new double [100];
        String [] time =new String [100];
        String executeIt = "select withdraw,deposit,transfer,time from history where mail ='"+u.getName()+"'";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
            Statement st = (Statement) con.createStatement();
            ResultSet result =st.executeQuery(executeIt);
            while(result.next()){
                withdraw[a]=Double.parseDouble(result.getString(1));
                deposit[a]=Double.parseDouble(result.getString(2));
                transfer[a]=Double.parseDouble(result.getString(3));
                time[a]=result.getString(4);
                a++;
            }
            
        out.println("<html>\n" +
"    <head>\n" +
"        <title>History</title>\n" +
"        <link rel=\"stylesheet\" href=\"font-awesome-4.4.0/font-awesome-4.4.0/css/font-awesome.min.css\">\n" +
"        <link rel=\"stylesheet\" href=\"bootstrap-3.3.5-dist/css/bootstrap.min.css\">\n" +
"        <link rel=\"stylesheet\" href=\"Css/project_Css.css\">\n" +
"        <link rel=\"icon\" href=\"Images/Ghost-Recon-Future-Soldier-2-icon.png\" type=\"image/ico\">\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    </head>");
        out.println("<body>\n" +
"        <nav class=\"navbar navbar-default class1\" role=\"navigation\">\n" +
"  <div class=\"container-fluid\">\n" +
"    <!-- Brand and toggle get grouped for better mobile display -->\n" +
"    <div class=\"navbar-header\">\n" +
"      <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\">\n" +
"        <span class=\"sr-only\">Toggle navigation</span>\n" +
"        <span class=\"icon-bar\"></span>\n" +
"        <span class=\"icon-bar\"></span>\n" +
"        <span class=\"icon-bar\"></span>\n" +
"      </button>\n" +
"      <li style=\"list-style:none;margin-top:15px;color:white;width:100%\"\">Welcome "+u.getEmail().toUpperCase()+"</li>\n" +
"    </div>\n" +
"\n" +
"    <!-- Collect the nav links, forms, and other content for toggling -->\n" +
"    <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n" +
"      \n" +
"      <ul class=\"nav navbar-nav navbar-right right\">\n" +
"      <li  id=\"id5\">\n" +
"<form action=\"Logout\" method=\"get\">          <h5 style=\"margin-top: 18px;padding-left: 50px;margin-left:50px;color:white\">Current Balance is:"+u.getBalance()+"<button style=\"margin-left:200px\" value=\"signout\" class=\"btn btn-default\">Sign out</button></h5></form>\n" +
"        </li>\n" +
"        \n" +
"        \n" +
"      </ul>\n" +
"    </div><!-- /.navbar-collapse -->\n" +
"  </div><!-- /.container-fluid -->\n" +
"        </nav><br><br><br>");
        out.println("<div style=\"height:1300px;\" class=\"container\">");
        out.println("<table border=\"3px\" width=\"1000px\" height=\"auto\" style=\"margin-top: 50px;margin-left:5%\">");
        out.println("<tr>");
        out.println("<td style=\"text-align: center;width: 20%\">Withdrawals</td>");
        out.println("<td style=\"text-align: center;width: 20%\">Deposits</td>");
        out.println("<td style=\"text-align: center;width: 20%\">Transfers</td>");
        out.println("<td style=\"text-align: center;width: 35%\">Date</td>");
        out.println("</tr>");
        for(int i=0;i<a;i++){
            out.println("<tr>");
        out.println("<td style=\"text-align: center;width: 20%\">"+withdraw[i]+"</td>");
        out.println("<td style=\"text-align: center;width: 20%\">"+deposit[i]+"</td>");
        out.println("<td style=\"text-align: center;width: 20%\">"+transfer[i]+"</td>");
        out.println("<td style=\"text-align: center;width: 35%\">"+time[i]+"</td>");
        out.println("</tr>");
        }
        out.println("</table>\n" +
"        </div>");
        out.println("<script src=\"JScript/jquery-1.11.3.min.js\"></script>\n" +
"	<script src=\"JScript/bootstrap.min.js\"></script>\n" +
"        <script src=\"JScript/project_JS.js\"></script>\n" +
"    </body>\n" +
"</html>");
        }catch (ClassNotFoundException ex) {
            out.println(ex.getMessage());
        } catch (SQLException ex) {
            out.println(ex.getMessage());
        }catch(NullPointerException ex){
            out.println(ex.getMessage());
        }
        
    }   
    
}
