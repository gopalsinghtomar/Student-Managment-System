package com.gopal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        try {

            // Form se ID lena
            int id = Integer.parseInt(request.getParameter("id"));

            // Driver Load
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database Connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentdb",
                    "root",
                    "Rajput@1234");

            // SQL Query
            String sql = "DELETE FROM student WHERE id=" + id;

            // Statement Object
            Statement stmt = con.createStatement();

            // Execute Query
            int result = stmt.executeUpdate(sql);

            if (result > 0) {

                out.println("<h2>Student Deleted Successfully</h2>");

            } else {

                out.println("<h2>Student Not Found</h2>");

            }

            out.println("<br>");

            out.println("<a href='delete-student.html'>Delete Another Student</a>");

            out.println("<br><br>");

            out.println("<a href='index.html'>Back to Home</a>");

            stmt.close();
            con.close();

        } catch (Exception e) {

            out.println("<h2>Exception : "
                    + e.getMessage() + "</h2>");

        }

        out.close();
    }
}