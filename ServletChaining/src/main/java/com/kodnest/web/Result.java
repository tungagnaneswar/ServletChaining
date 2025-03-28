package com.kodnest.web;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Result extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static Connection con;
    static PreparedStatement pstmt;
    static ResultSet rs;
    static PrintWriter pw;
    
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/university";
    static final String USER = "root";
    static final String PASSWORD = "SVECIT50";
    static final String SQL = "SELECT * FROM stu_result WHERE ROLLNO = ?";

    @Override
    public void init() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) {
        try {
            String rollNo = req.getParameter("rollno");
            int ROLLNO = Integer.parseInt(rollNo);

            pstmt.setInt(1, ROLLNO);
            rs = pstmt.executeQuery();
            pw = res.getWriter();

            if (rs.next()) {
            	int rollno = rs.getInt("ROLLNO");
            	String name = rs.getString("STUDENT_NAME");
            	int m1 = rs.getInt("JAVA");
            	int m2 = rs.getInt("DBMS");
            	int m3 = rs.getInt("BIGDATA");
            	int m4 = rs.getInt("MYSQL");
            	
            	HttpSession session = req.getSession(true);
            	session.setAttribute("marks1", m1);
            	session.setAttribute("marks2", m2);
            	session.setAttribute("marks3", m3);
            	session.setAttribute("marks4", m4);
            	
            	ServletContext sc = req.getServletContext();
            	RequestDispatcher rd = sc.getRequestDispatcher("/PrintPercentage");
            	
            	
            	pw.println("<!DOCTYPE html>");
            	pw.println("<html>");
            	pw.println("<head>");
            	pw.println("<title>Result</title>");
            	pw.println("<style>");
            	pw.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; text-align: center; }");
            	pw.println("h1 { color: #333; margin-top: 20px; }");
            	pw.println("form { background: white; padding: 20px; width: 40%; margin: auto; border-radius: 10px; box-shadow: 2px 2px 10px rgba(0,0,0,0.1); }");
            	pw.println("label { font-weight: bold; }");
            	pw.println("input[type='text'] { padding: 8px; width: 80%; margin: 10px 0; border: 1px solid #ccc; border-radius: 5px; }");
            	pw.println("input[type='submit'] { padding: 10px 20px; background-color: #007bff; color: white; border: none; border-radius: 5px; cursor: pointer; }");
            	pw.println("input[type='submit']:hover { background-color: #0056b3; }");
            	pw.println("table { width: 50%; margin: 20px auto; border-collapse: collapse; background: white; box-shadow: 2px 2px 10px rgba(0,0,0,0.1); }");
            	pw.println("th, td { padding: 10px; border: 1px solid #ddd; text-align: center; }");
            	pw.println("th { background-color: #007bff; color: white; }");
            	pw.println("th:last-child { background-color: red; color: white; }");
            	pw.println("h4 { color: #888; margin-top: 20px; }");
            	pw.println("</style>");
            	pw.println("</head>");
            	pw.println("<body>");
            	pw.println("<h1>JNTUA Results</h1>");
            	pw.println("<form action=\"http://localhost:8082/ServletChaining/Result\" method=\"post\">");
            	pw.println("<label for=\"rollno\">Enter HallTicket Number:</label>");
            	pw.println("<input type=\"text\" autocomplete=\"off\" id=\"rollno\" name=\"rollno\" placeholder=\"Enter Roll No\" required />");
            	pw.println("<input type=\"submit\" value=\"Search\" />");
            	pw.println("</form>");
            	pw.println("<h2>HallTicket Number: " + rollno + "</h2>");
            	pw.println("<h2>Student Name: " + name + "</h2>");
            	pw.println("<table>");
            	// heading 
            	pw.println("<tr>");
            	pw.println("<th>JAVA</th><th>DBMS</th><th>BIGDATA</th><th>MySQL</th>");
            	if(m1 >= 25 && m2 >= 25 && m3 >= 25 && m4 >= 25) {
            		pw.println("<th>Percentage</th>");
            	} else {
            		pw.println("<th>Status</th>");
            	}
            	pw.println("</th>");
            	pw.println("</tr>");
            	// 
            	pw.println("<tr>");
            	pw.println("<td>" + m1 + "</td><td>" + m2 + "</td><td>" + m3 + "</td><td>" + m4 + "</td>");
            	pw.println("<td>"); 
            	if(m1 >= 25 && m2 >= 25 && m3 >= 25 && m4 >= 25) {
            		rd.include(req, res);
            	} else {
            		pw.println("Fail");
            	}
            	pw.println("</td>");
            	pw.println("</tr>");
            	pw.println("</table>");
            	pw.println("<h4><b>Result Disclaimer</b>: Data and information are for informational purposes only. The final results will be given by the respective Controller of Examinations.</h4>");
            	pw.println("</body>");
            	pw.println("</html>");
            	
            	
            	
            	// Dispatch req to printpercentage servlet
            	//ServletContext sc = req.getServletContext();
            	//RequestDispatcher rd = sc.getRequestDispatcher("/PrintPercentage");
            	//rd.forward(req, res);
            	//rd.include(req, res);
            	//req.getServletContext().getRequestDispatcher("/PrintPercentage").include(req, res);
            	
            } else {
                res.sendRedirect("http://localhost:8082/ServletChaining/error.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}