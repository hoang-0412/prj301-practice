package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.Roles;
import dal.roleDAO;

@WebServlet(name = "RoleServlet", urlPatterns = {"/role"})
public class RoleServlet extends HttpServlet {

    String sql = "select * from tblRoles";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        roleDAO dao = new roleDAO();
        Vector<Roles> vector;

        String service = request.getParameter("service");
        if (service == null) {
            service = "listAllRole";
        }

        if (service.equals("deleteRole")) {
            int rId = Integer.parseInt(request.getParameter("roleID"));
            dao.deleteRole(rId);
            response.sendRedirect("role");
        }

        if (service.equals("addRole")) {
            int roleID = Integer.parseInt(request.getParameter("roleID"));
            String roleName = request.getParameter("roleName");

            Roles r = new Roles(roleID, roleName);
            int n = dao.insertRole(r);
            response.sendRedirect("role");
        }

        if (service.equals("listAllRole")) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet RoleServlet</title>");
                out.println("</head>");
                out.println("<body>");

                out.println("<p><a href=\"addRole.html\">Insert a new Role</a></p>");

                out.println("<form action=\"role\">\n"
                        + "            Search by Role Name: <input type=\"text\" name=\"roleName\" id=\"txtName\">\n"
                        + "            <input type=\"submit\" name=\"submit\" id=\"txtSubmit\" value=\"Search\">\n"
                        + "            <input type=\"reset\" value=\"Reset\">\n"
                        + "            <input type=\"hidden\" name=\"service\" value=\"listAllRole\">\n"
                        + "        </form>");

                out.println("<table border=\"1\">\n"
                        + "            <thead>\n"
                        + "                <tr>\n"
                        + "                    <th>roleID</th>\n"
                        + "                    <th>roleName</th>\n"
                        + "                    <th>delete</th>\n"
                        + "                    <th>update</th>\n"
                        + "                </tr>\n"
                        + "            </thead>");

                String submit = request.getParameter("submit");
                if (submit == null) {
                    vector = dao.getAllRole(sql);

                } else {
                    String rName = request.getParameter("roleName");
                    vector = dao.getAllRole("select * from tblRoles\n"
                            + "where roleName like N'%" + rName + "%'");
                }

                for (Roles r : vector) {
                    out.println("<tbody>\n"
                            + "                <tr>\n"
                            + "                    <td>" + r.getRoleID() + "</td>\n"
                            + "                    <td>" + r.getRoleName() + "</td>\n"
                            + "                    <td><a href=\"role?service=deleteRole&roleId=" + r.getRoleID() + "\">delete</a></td>\n"
                            + "                    <td><a href=\"role?service=updateRole&roleId=" + r.getRoleID() + "\">update</a></td>\n"
                            + "                </tr>\n"
                            + "            </tbody>");
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
