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

@WebServlet("/editurl")
public class EditScreenServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        pw.println("<html>");
        pw.println("<head>");
        pw.println("<title>Edit User Data</title>");
        pw.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
        pw.println("<style>");
        pw.println("body { background-color: #e9ecef; }");
        pw.println(".form-container { max-width: 600px; margin: 10px auto; padding: 20px; border: 1px solid #ccc; border-radius: 10px; background-color: #ffffff; }");
        pw.println(".form-title { text-align: center; margin-bottom: 20px; }");
        pw.println(".form-select { width: 100%; padding: 10px; }");
        pw.println(".btn { border-radius: 10px; font-size: 16px; padding: 10px 20px; margin: 5px; min-width: 150px; height: 50px; }");
        pw.println(".btn-container { text-align: center; margin-top: 20px; }");
        pw.println(".btn:hover { opacity: 0.8; }");
        pw.println("</style>");
        pw.println("</head>");
        pw.println("<body>");

        pw.println("<div class=\"container form-container\">");
        pw.println("<h2 class=\"form-title\">Edit User Data</h2>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermgmt", "root", "password");
            PreparedStatement ps = con.prepareStatement("select name, email, mobile, dob, city, gender from user where id=?");
            int id = Integer.parseInt(req.getParameter("id"));
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();
            pw.println("<form action='edit?id=" + id + "' method='post'>");

            pw.println("<div class='mb-3'>");
            pw.println("<label for='name' class='form-label'>Name</label>");
            pw.println("<input type='text' class='form-control' id='name' required name='name1' value='" + rs.getString("name") + "'>");
            pw.println("</div>");

            pw.println("<div class='mb-3'>");
            pw.println("<label for='email' class='form-label'>Email address</label>");
            pw.println("<input type='email' class='form-control' id='email' required name='email1' value='" + rs.getString("email") + "'>");
            pw.println("</div>");

            pw.println("<div class='mb-3'>");
            pw.println("<label for='mobile' class='form-label'>Mobile</label>");
            pw.println("<input type='text' class='form-control' id='mobile' required name='mobile1' value='" + rs.getString("mobile") + "'>");
            pw.println("</div>");

            pw.println("<div class='mb-3'>");
            pw.println("<label for='dob' class='form-label'>Date of Birth</label>");
            pw.println("<input type='date' class='form-control' id='dob' required name='dob1' value='" + rs.getString("dob") + "'>");
            pw.println("</div>");

            pw.println("<div class='mb-3'>");
            pw.println("<label for='city' class='form-label'>City</label>");
            pw.println("<select class='form-select' id='city' required name='city1'>");
            pw.println("<option value='' disabled>Select your city</option>");
            pw.println("<option value='Bangalore'" + (rs.getString("city").equals("Bangalore") ? " selected" : "") + ">Bangalore</option>");
            pw.println("<option value='Patna'" + (rs.getString("city").equals("Patna") ? " selected" : "") + ">Patna</option>");
            pw.println("<option value='Varanasi'" + (rs.getString("city").equals("Varanasi") ? " selected" : "") + ">Varanasi</option>");
            pw.println("<option value='Darbhanga'" + (rs.getString("city").equals("Darbhanga") ? " selected" : "") + ">Darbhanga</option>");
            pw.println("</select>");
            pw.println("</div>");

            pw.println("<div class='mb-3'>");
            pw.println("<label class='form-label'>Gender</label><br>");
            pw.println("<div class='form-check form-check-inline'>");
            pw.println("<input class='form-check-input' type='radio' id='male' name='gender1' value='Male'" + (rs.getString("gender").equals("male") ? " checked" : "") + ">");
            pw.println("<label class='form-check-label' for='male'>Male</label>");
            pw.println("</div>");
            pw.println("<div class='form-check form-check-inline'>");
            pw.println("<input class='form-check-input' type='radio' id='female' name='gender1' value='Female'" + (rs.getString("gender").equals("female") ? " checked" : "") + ">");
            pw.println("<label class='form-check-label' for='female'>Female</label>");
            pw.println("</div>");
            pw.println("</div>");

            pw.println("<div class='btn-container d-flex justify-content-center'>");
            pw.println("<button type='submit' class='btn btn-outline-primary mx-2'>Save Changes</button>");
            pw.println("<button type='button' class='btn btn-outline-secondary mx-2' onclick='this.form.reset()'>Cancel</button>");
            pw.println("<a href='index.jsp' class='btn mx-2 btn-outline-success'>Home</a>");
            pw.println("</div>");
            
            
            

            pw.println("</form>");

        } catch (Exception e) {
            e.printStackTrace();
        }

        pw.println("</div>");
        pw.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js'></script>");
        pw.println("</body>");
        pw.println("</html>");

        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
