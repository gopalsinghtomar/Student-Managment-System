package com.gopal;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet  {

    protected void doPost(HttpServletRequest request,HttpServletResponse response)
        throws ServletException , IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {

            int id =Integer.parseInt(request.getParameter("id"));
            String name=request.getParameter("name");
            String email=request.getParameter("email");
            String course=request.getParameter("course");
            String mobile=request.getParameter("mobile");

            //driver load
            Class.forName("com.mysql.cj.jdbc.Driver");
            //database connection
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","Rajput@1234");

             //sql query
            String sql="update student set name='"+ name
                    +"',email='"+ email
                    +"',course='"+ course
                    +"',mobile= '"+ mobile
                    +"' where id=" + id;

            Statement stmt =con.createStatement();
            int result =stmt.executeUpdate(sql);

            if (result > 0) {

                out.println("<h2>Student Updated Successfully</h2>");

            } else {

                out.println("<h2>Student Not Found</h2>");

            }
         out.println ("<a href='update-student.htm'> Update another student <br></a>");
                 out.println ("<a href='index.html'> GO TO HOME PAGE</a>");
            stmt.close();
            con.close();

        } catch (Exception e) {

            out.println("<h2>Exception : " + e.getMessage() + "</h2>");

        }

        out.close();
        }
    }

