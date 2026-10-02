package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.categoryDAO;
import java.util.Vector;
import model.Categories;

@WebServlet(name = "CategoryServlet", urlPatterns = {"/category"})
public class CategoryServlet extends HttpServlet {

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

        if (service.equals("deleteCategory")) {
            String cId = request.getParameter("categoryID");
            dao.deleteCategory(cId);
            response.sendRedirect("category");
        }

        if (service.equals("addCategory")) {
            String categoryID = request.getParameter("categoryID"),
                    categoryName = request.getParameter("categoryName"),
                    describe = request.getParameter("describe");
            Categories c = new Categories(categoryID, categoryName, describe);
            int n = dao.insertCategory(c);
            response.sendRedirect("category");      
        }
        if (service.equals("listAllCategory")) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet CategoryServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<p><a href=\"addCategory.html\">Insert a new Category</a></p>");
                out.println("<form action=\"category\">\n"
                        + "            Search By Category Name: <input type=\"text\" name=\"categoryName\" id=\"txtName\">\n"
                        + "            <input type=\"submit\" name=\"submit\" id=\"txtSubmit\" value=\"Search\">\n"
                        + "            <input type=\"reset\" value=\"Reset\">\n"
                        + "            <input type=\"hidden\" name=\"service\" value=\"listAllCategory\">\n"
                        + "        </form>");

                out.println("<table border=\"1\">\n"
                        + "            <thead>\n"
                        + "                <tr>\n"
                        + "                    <th>categoryID</th>\n"
                        + "                    <th>categoryName</th>\n"
                        + "                    <th>describe</th>\n"
                        + "                    <th>delete</th>\n"
                        + "                    <th>update</th>\n"
                        + "                </tr>\n"
                        + "            </thead>");

                String submit = request.getParameter("submit");
                if (submit == null) {
                    //Show all category
                    vector = dao.getAllCategory(sql);
                } else {
                    //Search category
                    String cName = request.getParameter("categoryName");
                    vector = dao.getAllCategory("Select * From tblCategories\n"
                            + "Where categoryName like N'%" + cName + "%'");
                }

                for (Categories c : vector) {
                    out.println("<tbody>\n"
                            + "                <tr>\n"
                            + "                    <td>" + c.getCategoryID() + "</td>\n"
                            + "                    <td>" + c.getCategoryName() + "</td>\n"
                            + "                    <td>" + c.getDescribe() + "</td>\n"
                            + "                    <td><a href=\"category?service=deleteCategory&categoryID=" + c.getCategoryID() + "\">delete</a></td>\n"
                            + "                    <td><a href=\"category?service=deleteCategory&categoryID=" + c.getCategoryID() + "\">delete</a></td>\n"
                            + "                </tr>\n"
                            + "            </tbody>");
                }
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
