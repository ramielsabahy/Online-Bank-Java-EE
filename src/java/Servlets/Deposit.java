package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Deposit extends HttpServlet {
    
    protected synchronized void doDeposit(HttpServletRequest request,HttpServletResponse response) throws IOException{
    
    PrintWriter out = response.getWriter();
        Date d = new Date();
        String n=null;
        String b = null;
        double deposit = 0;
        double withdraw = 0;
        double trans = 0;
        String mail=null;
        String pass = null;
       try{ 
            HttpSession session =request.getSession();
            Users u = (Users) session.getAttribute("serving");
            if(request.getParameter("depo")!=""){
                if(Double.parseDouble(request.getParameter("depo"))>0)
                u.setDeposit(Double.parseDouble(request.getParameter("depo")));}
            else{
                u.setDeposit(0);
            }
            double depo = u.getDeposit();
            
            String name=null;
            double balance =0;
            double last = u.getBalance();
            double all = depo+last;
            String sql1 = "select fname,balance,deposit,withdraw,transfer,email,pass from information where email = '"+u.getName()+"'";
            String updateBalance = "update information set balance ='"+(all)+"' where email ='"+u.getName()+"'";
            String updateDeposit = "update information set deposit ='"+depo+"' where email ='"+u.getName()+"'";
            String insertIntoHistory = "insert into history (withdraw,deposit,transfer,time,mail) values ("+0+","+depo+","+0+",'"+d+"','"+u.getName()+"')";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
            Statement st = (Statement) con.createStatement();
            st.executeUpdate(updateBalance);
            st.executeUpdate(updateDeposit);
            st.executeUpdate(insertIntoHistory);
            ResultSet res = st.executeQuery(sql1);
            while(res.next()){
                name =res.getString("fname");
                balance = Double.parseDouble(res.getString("balance"));
                deposit = Double.parseDouble(res.getString("deposit"));
                withdraw = Double.parseDouble(res.getString("withdraw"));
                trans = Double.parseDouble(res.getString("transfer"));
            }
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Home</title>");
        out.println("<link rel=\"stylesheet\" href=\"font-awesome-4.4.0/font-awesome-4.4.0/css/font-awesome.min.css\">");
        out.println("<link rel=\"stylesheet\" href=\"bootstrap-3.3.5-dist/css/bootstrap.min.css\">");
        out.println("<link rel=\"stylesheet\" href=\"Css/Home_style.css\">");
        out.println("<link rel=\"icon\" href=\"Images/Ghost-Recon-Future-Soldier-2-icon.png\" type=\"image/ico\">");
        out.println("</head>");
        out.println("<nav class=\"navbar navbar-default class1\" role=\"navigation\">");
        out.println("<div class=\"container-fluid\">");
        out.println("<div class=\"navbar-header\">");
        out.println("<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\">");
        out.println("<span class=\"sr-only\">Toggle navigation</span>");
        out.println("<span class=\"icon-bar\"></span>");
        out.println("<span class=\"icon-bar\"></span>");
        out.println("<span class=\"icon-bar\"></span>");
        out.println("</button>");
        out.println("<li style=\"list-style:none;margin-top:15px;display:inline-block;\" id=\"id1\">Welcome </li>");
        out.println(name.toUpperCase());
        out.println("</div>");
        out.println("<div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">");
        out.println("<ul class=\"nav navbar-nav navbar-right right\">");
        out.println("<li id=\"id5\">");
        out.println("<form action=\"Logout\" method=\"get\">");
        out.println("<h5 style=\"margin-top: 18px;padding-left: 50px;margin-left:50px;\">Current Balance is:"+all+"<button style=\"margin-left:200px\" value=\"signout\" class=\"btn btn-default\">Sign out</button></h5></form>");
        out.println("</li>");
        out.println("</ul></div></div>");
        out.println("</nav>");
        out.println("<div class=\"imag\">");
        out.println("</div>");
        out.println("<div class=\"container a\">");
        out.println("<div style=\"width: 100%;height: 50px;\"><h3 style=\"float: left;margin-left: 2%;\">Dear "+name+"</h3></div>");
        out.println("<p>In order to provide Detailed information for your login operations we produced a feature\n" +
"        to let you know the last operation you did.\n" +
"        the last activity,transaction,deposit or withdraw<br>your privacy and security is our periority so make sure that your\n" +
"        information will be kept safe and secure</p>");
        out.println("<input type=\"text\" name=\"inf\" style=\"display:none\">");
        out.println("<table border=\"1\" >");
        out.println("<tbody>");
        out.println("<tr>");
        out.println("<td id=\"id3\">Last Account activity</td>");
        out.println("<td id=\"id4\">Thu 12-10-2015 2:30 PM</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td id=\"id3\">Deposits</td>");
        out.println("<td id=\"id4\">"+deposit+" EGP</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td id=\"id3\">Withdrawls</td>");
        out.println("<td id=\"id4\">"+withdraw+" EGP</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td id=\"id3\">Transfered</td>");
        out.println("<td id=\"id4\">"+trans+" EGP</td>");
        out.println("</tr>");
        out.println("</tbody>");
        out.println("</table>");
        out.println("<form method=\"get\" action=\"History\">");
        out.println("<input type=\"submit\" style=\"  margin-top: 60px;margin-left: 39%;\" value=\"Check Account activity\">");
        out.println("</form>");
        out.println("<div class=\"control\">");
        out.println("<h3>Transactions Area</h3>");
        out.println("<hr>");
        out.println("<div id=\"carousel-example-generic\" class=\"carousel slide\" data-ride=\"carousel\">");
        out.println("<ol class=\"carousel-indicators\">");
        out.println("<li data-target=\"#carousel-example-generic\" data-slide-to=\"0\" class=\"active\"></li>");
        out.println("<li data-target=\"#carousel-example-generic\" data-slide-to=\"1\"></li>");
        out.println("<li data-target=\"#carousel-example-generic\" data-slide-to=\"2\"></li>");
        out.println("</ol>");
        out.println("<div class=\"carousel-inner\">");
        out.println("<div class=\"item active\">");
        out.println("<img src=\"Images/banner.jpg\" alt=\"...\">");
        out.println("<div class=\"carousel-caption\">");
        out.println("<div class=\"con\" style=\"margin-bottom:30px;\">");
        out.println("<h3>Deposit Area</h3>");
        out.println("<form method=\"get\" action=\"Try\">");
        out.println("<input type=\"text\" class=\"form-control\" name=\"depo\" placeholder=\"Amount to withdraw\"><br>");
        out.println("<button type=\"submit\" class=\"btn btn-default\" style=\"width:100px;height:40px\">Submit</button>");
        out.println("</form>");
        out.println("</div>");
        out.println("</div></div>");
        out.println("<div class=\"item\">");
        out.println("<img src=\"Images/banner.jpg\" alt=\"...\">");
        out.println("<div class=\"carousel-caption\">");
        out.println("<div class=\"con\" style=\"margin-bottom:30px;\">");
        out.println("<h3>Withdraw Area</h3>");
        out.println("<form method=\"get\" action=\"Try\">");
        out.println("<input type=\"text\" class=\"form-control\" name=\"with\" placeholder=\"Amount to Deposit\"><br>");
        out.println("<button type=\"submit\" class=\"btn btn-default\" style=\"width:100px;height:40px\">Submit</button>");
        out.println("</form>");
        out.println("</div>");
        out.println("</div></div>");
        out.println("<div class=\"item\">");
        out.println("<img src=\"Images/banner.jpg\" alt=\"...\">");
        out.println("<div class=\"carousel-caption\">");
        out.println("<div class=\"con\" style=\"margin-bottom:30px;\">");
        out.println("<h3>Transfer Area</h3>");
        out.println("<form method=\"get\" action=\"Try\">");
        out.println("<input type=\"text\" class=\"form-control\" name=\"to\" placeholder=\"Transfere to\"><br>");
        out.println("<input type=\"text\" class=\"form-control\" name=\"trans\" placeholder=\"Amount to Transfer\"><br>");
        out.println("<button type=\"submit\" class=\"btn btn-default\" style=\"width:100px;height:40px\">Submit</button>");
        out.println("</form></div></div></div></div></div></div></div>");
        out.println("<script src=\"JScript/jqeury.js\"></script>");
        out.println("<script src=\"bootstrap-3.3.5-dist/js/bootstrap.min.js\"></script>");
        out.println("</html>");
        u.setBalance(all);
        
       }
       catch(Exception ex){
           out.println("Invalid input "+ex.getMessage());
       }
    
}

    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doDeposit(request, response);
    }
    
}
