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

@WebServlet("/edit")
public class EditRecordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        pw.println("<html>");
        pw.println("<head>");
        pw.println("<title>Edit User Data</title>");
        pw.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
        pw.println("<style>");
        pw.println(".message-container { position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); text-align: center; padding: 20px; border: 1px solid #ccc; border-radius: 10px; background-color: #ffffff; }");
        pw.println("</style>");
        pw.println("</head>");
        pw.println("<body>");

        pw.println("<div class=\"container\" style=\"margin-top: 20px;\">");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermgmt", "root", "password");
            PreparedStatement ps = con.prepareStatement("update user set name=?, email=?, mobile=?, dob=?, city=?, gender=?  where id=?");
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name1");
            String email = req.getParameter("email1");
            String mobile = req.getParameter("mobile1");
            String dob = req.getParameter("dob1");
            String city = req.getParameter("city1");
            String gender = req.getParameter("gender1");

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobile);
            ps.setString(4, dob);
            ps.setString(5, city);
            ps.setString(6, gender);
            ps.setInt(7, id);

            int count = ps.executeUpdate();

            if (count > 0) {
                pw.println("<div class='message-container'>");
                pw.println("<h2>Edited Successfully</h2>");
                pw.println("<a href='showdata' class='btn btn-outline-warning mt-3'>Show User Data</a>");
                pw.println("<a href='index.jsp' class='btn btn-outline-success mt-3'>Home</a>");
                pw.println("</div>");
            } else {
                pw.println("<h2>Error - Data Not Edited</h2>");
                pw.println("<a href='index.jsp' class='btn btn-primary mt-3'>Home</a>");
            }

            pw.println("</div>");

        } catch (Exception e) {
            e.printStackTrace();
        }

        pw.println("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"></script>");
        pw.println("</body>");
        pw.println("</html>");

        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
