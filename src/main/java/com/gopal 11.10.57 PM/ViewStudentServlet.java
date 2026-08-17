package com.gopal;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.Statement;
 import java.sql.ResultSet;

 import jakarta.servlet.ServletException;
 import jakarta.servlet.http.HttpServlet;
 import jakarta.servlet.http.HttpServletRequest;
 import jakarta.servlet.http.HttpServletResponse;
 import jakarta.servlet.annotation.WebServlet;

 @WebServlet("/viewStudent")


public class ViewStudentServlet extends HttpServlet {

     protected void doGet(HttpServletRequest request , HttpServletResponse response)
         throws ServletException ,IOException {

         response.setContentType("text/html");
         PrintWriter out=response.getWriter();

         try {
                //driver load
              Class.forName("com.mysql.cj.jdbc.Driver");

              //database connection
             Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","Rajput@1234");

          String sql="select * from student";

          //noraml statment
             Statement stmt=con.createStatement();

             //execute query
             ResultSet rs=stmt.executeQuery(sql);

             //html
             out.println("STUDENT LIST <br> ");
             out.println("__________________________________<br>");

             while(rs.next()){

                 out.println("id: " + rs.getInt("id"));
                 out.println("<br>");
                 out.println("name: " + rs.getString("name"));
                 out.println("<br>");
                 out.println("email: " + rs.getString("email"));
                 out.println("<br>");
                 out.println("course: " + rs.getString("course"));
                 out.println("<br>");
                 out.println("mobile: " + rs.getString("mobile"));
                 out.println("<br>");
                 out.println("__________________________________<br>");

             }
             out.println("<a href='index.html'>GO TO HOME PAGE</a>");
                 rs.close();
                stmt.close();
                con.close();
         } catch (Exception e) {
             out.println("Expection is " + e.getMessage());
         }
            out.close();
     }
}
