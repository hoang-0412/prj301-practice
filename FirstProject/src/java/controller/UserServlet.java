package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import dal.userDAO;
import model.Users;

@WebServlet(name = "UserServlet", urlPatterns = {"/user"})
public class UserServlet extends HttpServlet {

    String sql = "select * from tblUsers";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        userDAO dao = new userDAO();
        Vector<Users> vector;
        String service = request.getParameter("service");

        if (service == null) {
            service = "listAllUser";
        }

        if (service.equals("deleteUser")) {
            String uId = request.getParameter("userID");
            dao.deleteUser(uId);
            response.sendRedirect("user");
        }

        if (service.equals("addUser")) {
            String userID = request.getParameter("userID"),
                    fullName = request.getParameter("fullName"),
                    password = request.getParameter("password");

            int roleID = Integer.parseInt(request.getParameter("roleID"));

            String address = request.getParameter("address"),
                    phone = request.getParameter("phone"),
                    email = request.getParameter("email");

            int activate = Integer.parseInt(request.getParameter("activate"));

            Users u = new Users(userID, fullName, password, roleID, address, phone, email, activate);
            int n = dao.insertUser(u);
            response.sendRedirect("user");
        }

        if (service.equals("listAllUser")) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet UserServlet</title>");
                out.println("</head>");
                out.println("<body>");

                out.println("<p><a href=\"addUser.html\">Insert a new User</a></p>");

                out.println("<form action=\"user\">\n"
                        + "            Search By User Name: <input type=\"text\" name=\"fullName\" id=\"txtName\">\n"
                        + "            <input type=\"submit\" name=\"submit\" id=\"txtSubmit\" value=\"Search\">\n"
                        + "            <input type=\"reset\" value=\"Reset\">\n"
                        + "            <input type=\"hidden\" name=\"service\" value=\"listAllUser\">\n"
                        + "        </form>");

                out.println("<table border=\"1\">\n"
                        + "                <thead>\n"
                        + "                    <tr>\n"
                        + "                        <th>userID</th>\n"
                        + "                        <th>fullName</th>\n"
                        + "                        <th>password</th>\n"
                        + "                        <th>roleID</th>\n"
                        + "                        <th>address</th>\n"
                        + "                        <th>phone</th>\n"
                        + "                        <th>email</th>\n"
                        + "                        <th>activate</th>\n"
                        + "                        <th>delete</th>\n"
                        + "                        <th>update</th>\n"
                        + "                    </tr>\n"
                        + "                </thead>");

                String submit = request.getParameter("submit");
                if (submit == null) {
                    vector = dao.getAllUser(sql);

                } else {
                    String uName = request.getParameter("fullName");
                    vector = dao.getAllUser("select * from tblUsers\n"
                            + "where fullName like N'%" + uName + "%'");
                }

                for (Users u : vector) {
                    out.println("<tbody>\n"
                            + "                    <tr>\n"
                            + "                        <td>" + u.getUserID() + "</td>\n"
                            + "                        <td>" + u.getFullName() + "</td>\n"
                            + "                        <td>" + u.getPassword() + "</td>\n"
                            + "                        <td>" + u.getRoleID() + "</td>\n"
                            + "                        <td>" + u.getAddress() + "</td>\n"
                            + "                        <td>" + u.getPhone() + "</td>\n"
                            + "                        <td>" + u.getEmail() + "</td>\n"
                            + "                        <td>" + u.getActivate() + "</td>\n"
                            + "                        <td><a href=\"user?service=deleteUser&userID=" + u.getUserID() + "\">delete</a></td>\n"
                            + "                        <td><a href=\"user?service=updateUser&userID=" + u.getUserID() + "\">update</a></td>\n"
                            + "                    </tr>\n"
                            + "                </tbody>"
                    );
                }

                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
