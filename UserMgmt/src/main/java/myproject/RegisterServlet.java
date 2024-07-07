package myproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        pw.println("<html>");
        pw.println("<head>");
        pw.println("<title>User Registration</title>");
        pw.println("<link rel=\"stylesheet\" href=\"css/bootstrap.css\">");
        pw.println("<style>");
        pw.println("body { background-color: #f0f0f0; }"); // Decent background color
        pw.println(".center-message { display: flex; justify-content: center; align-items: center; height: 100vh; text-align: center; }");
        pw.println(".custom-alert { padding: 20px; border-radius: 10px; background-color: #ffffff; box-shadow: 0 4px 8px rgba(0,0,0,0.1); }"); // Increased padding and added box-shadow
        pw.println("</style>");
        pw.println("</head>");
        pw.println("<body>");

        String name = req.getParameter("name1");
        String email = req.getParameter("email1");
        String mobile = req.getParameter("mobile1");
        String dob = req.getParameter("dob1");
        String city = req.getParameter("city1");
        String gender = req.getParameter("gender1");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermgmt", "root", "password");
            PreparedStatement ps = con.prepareStatement("insert into user(name,email,mobile,dob,city,gender) values(?,?,?,?,?,?)");

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobile);
            ps.setString(4, dob);
            ps.setString(5, city);
            ps.setString(6, gender);

            int count = ps.executeUpdate();

            pw.println("<div class=\"container center-message\">");
            pw.println("<div class=\"alert alert-info custom-alert\" role=\"alert\">");
            if (count > 0) {
                pw.print("<h2>Registration Successful</h2>");
            } else {
                pw.print("<h2>Registration Unsuccessful</h2>");
            }

            pw.println("<div class='mt-3'>");
            pw.println("<a href='index.jsp' class='btn btn-outline-success btn-lg mx-2'>Home</a>");
            pw.println("<a href='showdata' class='btn btn-outline-warning btn-lg mx-2'>Show Users</a>");
            pw.println("</div>");

            pw.println("</div>");
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
