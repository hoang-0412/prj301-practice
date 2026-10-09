package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import dal.roleDAO;
import model.Roles;

@WebServlet(name = "roleJSP", urlPatterns = {"/roleJSP"})
public class roleJSP extends HttpServlet {

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

        if (service.equals("listAllRole")) {
            String submit = request.getParameter("submit");

            if (submit == null) {
                vector = dao.getAllRole(sql);

            } else {
                String roleName = request.getParameter("roleName");
                vector = dao.getAllRole("select * from tblRoles\n"
                        + "where roleName like N'%" + roleName + "%'");
            }
            
            request.setAttribute("vector", vector);
            request.setAttribute("tableTitle", "List all role");
            request.setAttribute("pageTitle", "Role management");
            
            request.getRequestDispatcher("jsp/rolelist.jsp").forward(request, response);
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
