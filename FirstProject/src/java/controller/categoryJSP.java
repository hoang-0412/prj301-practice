package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.Categories;
import dal.categoryDAO;

@WebServlet(name = "categoryJSP", urlPatterns = {"/categoryJSP"})
public class categoryJSP extends HttpServlet {

    String sql = "select * from tblCategories";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        categoryDAO dao = new categoryDAO();
        Vector<Categories> vector;

        String service = request.getParameter("service");
        if (service == null) {
            service = "listAllCategory";
        }

        if (service.equals("listAllCategory")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                vector = dao.getAllCategory(sql);

            } else {
                String categoryName = request.getParameter("categoryName");
                vector = dao.getAllCategory("select * from tblCategories\n"
                        + "where categoryName like N'%" + categoryName + "%'");
            }

            request.setAttribute("vector", vector);
            request.setAttribute("tableTitle", "list of categories");
            request.setAttribute("pageTitle", "Category Management");

            request.getRequestDispatcher("jsp/categorylist.jsp").forward(request, response);
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
