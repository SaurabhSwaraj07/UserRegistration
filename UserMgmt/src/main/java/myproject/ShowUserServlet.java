package myproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/showdata")
public class ShowUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        pw.println("<html>");
        pw.println("<head>");
        pw.println("<title>User Data</title>");
        pw.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
        pw.println("<style>");
        pw.println(".table-hover tbody tr:hover { background-color: #E5E4E2; }");
        pw.println("</style>");
        pw.println("</head>");
        pw.println("<body>");

        pw.println("<div class=\"container\" style=\"margin-top: 20px;\">");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermgmt", "root", "password");
            PreparedStatement ps = con.prepareStatement("select id,name,email,mobile,dob,city,gender from user");
            ResultSet rs = ps.executeQuery();

            pw.println("<table class=\"table table-hover\">");

            pw.println("<thead class=\"thead-dark\">");
            pw.println("<tr>");
            pw.println("<th>ID</th>");
            pw.println("<th>Name</th>");
            pw.println("<th>E-mail</th>");
            pw.println("<th>Mobile</th>");
            pw.println("<th>Date of Birth</th>");
            pw.println("<th>City</th>");
            pw.println("<th>Gender</th>");
            pw.println("<th>Edit</th>");
            pw.println("<th>Delete</th>");
            pw.println("</tr>");
            pw.println("</thead>");

            pw.println("<tbody>");

            while (rs.next()) {
                pw.println("<tr>");
                pw.println("<td>" + rs.getInt(1) + "</td>");
                pw.println("<td>" + rs.getString(2) + "</td>");
                pw.println("<td>" + rs.getString(3) + "</td>");
                pw.println("<td>" + rs.getString(4) + "</td>");
                pw.println("<td>" + rs.getString(5) + "</td>");
                pw.println("<td>" + rs.getString(6) + "</td>");
                pw.println("<td>" + rs.getString(7) + "</td>");
                pw.println("<td><a href='editurl?id="+rs.getInt(1)+"' class='btn btn-outline-secondary mt-3'>Edit</a></td>");
                pw.println("<td><a href='deleteurl?id="+rs.getInt(1)+"' class='btn btn-outline-danger mt-3'>Delete</a></td>");
                pw.println("</tr>");
            }

            pw.println("</tbody>");
            pw.println("</table>");

            pw.println("<a href='index.jsp' class='btn btn-outline-success mt-3 btn-lg'>Home</a>");
            pw.println("</div>");

        } catch (Exception e) {
            e.printStackTrace();
        }

        pw.println("</body>");
        pw.println("</html>");

        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
