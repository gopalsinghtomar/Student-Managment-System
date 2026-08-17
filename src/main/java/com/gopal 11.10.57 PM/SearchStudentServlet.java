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


@WebServlet("/searchStudent")
public class SearchStudentServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request , HttpServletResponse response)
        throws ServletException , IOException {


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {

            int id=Integer.parseInt(request.getParameter("id"));

            //driver load
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Data base connection
            Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","Rajput@1234");

            //sql query
            String sql="select * from student where id=" + id;
             //statment object
            Statement stmt=con.createStatement();
            //execute query
            ResultSet rs=stmt.executeQuery(sql);



            //html
            out.println("Student Managment System <br><br>");
            out.println("---------Student Record---------<br><br>");
            if (rs.next()) {
                out.println("id: " +rs.getInt("ID")+"<br>");
                out.println("name: " +rs.getString("name")+"<br>");
                out.println("email: " +rs.getString("email")+"<br>");
                out.println("course: " +rs.getString("course")+"<br>");
                out.println("mobile: " +rs.getString("mobile")+"<br>");
                out.println("--------------------------------------------<br><br>");
            } else {
                out.println("<h2>Student Not Found</h2>");

            }

            out.println ("<a href='search-student.html'> search another student <br></a>");
            out.println ("<a href='index.html'> GO TO HOME PAGE</a>");

            rs.close();
            con.close();
            stmt.close();
        }  catch (Exception e) {
            out.println("<h2>Exception: "  +e.getMessage());
        }
        out.close();
    }
}
