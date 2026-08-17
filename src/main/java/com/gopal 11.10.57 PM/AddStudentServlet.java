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
@WebServlet("/addstudent")
public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request , HttpServletResponse response)
            throws ServletException , IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
try{
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String course=request.getParameter("course");
        String mobile=request.getParameter("mobile");
  //driver load
    Class.forName("com.mysql.cj.jdbc.Driver");
    //data base connection
    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","Rajput@1234");

     //sql query
    String sql="insert into student(name,email,course,mobile) values('"+name+"','"+email+"','"+course+"','"+mobile+"')";

    //noraml statment
    Statement stmt=con.createStatement();

    //execute query
    int result=stmt.executeUpdate(sql);

    if (result > 0) {
        out.println("<h2>Student Added Successfully</h2>");
    } else {
        out.println("<h2>Problem</h2>");
    }
    out.println("<a href='add-student.html'>Add Another Student</a>");
    out.println("<a href='index.html'>GO TO HOME PAGE</a>");
    stmt.close();
    con.close();

} catch (Exception e) {
    out.println("<h2>Exception : " + e.getMessage() + "</h2>");
}

        out.close();
    }
}
