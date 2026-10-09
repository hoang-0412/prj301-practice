package controller;

import dal.productDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.Products;

@WebServlet(name = "ProductJSP", urlPatterns = {"/productjsp"})
public class ProductJSP extends HttpServlet {

    String sql = "SELECT * FROM tblProducts";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        productDAO dao = new productDAO();
        Vector<Products> vector;

        String service = request.getParameter("service");
        if (service == null) {
            service = "listAllProduct";
        }

        if (service.equals("listAllProduct")) {
            String submit = request.getParameter("submit");
            //Step 1: Call Model
            if (submit == null) {
                //show all products
                vector = dao.getAllProduct(sql);

            } else {
                //search product
                String pName = request.getParameter("productName");
                vector = dao.getAllProduct("Select * From tblProducts\n"
                        + "Where productName like N'%" + pName + "%'");
            }

            //Step 2: Set Data to send
            request.setAttribute("vector", vector);
            request.setAttribute("tableTitle", "List of Products");
            request.setAttribute("pageTtile", "Product management");

            //Step 3: Select view to send data
            request.getRequestDispatcher("jsp/productlist.jsp").forward(request, response);
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
