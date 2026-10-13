package controller;

import dal.productDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Vector;
import model.Products;
import dal.categoryDAO;
import model.Categories;

@WebServlet(name = "ProductJSP", urlPatterns = {"/productjsp"})
public class ProductJSP extends HttpServlet {

    String sql = "SELECT * FROM tblProducts";

    String sql2 = "select * from tblCategories";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        productDAO dao = new productDAO();
        Vector<Products> vector;
        categoryDAO cDao = new categoryDAO();

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

        if (service.equals("addProduct")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                Vector<Categories> cVector = cDao.getAllCategory(sql2);
                for (Categories c : cVector) {
                    c.getCategoryID();
                    c.getCategoryName();
                }

                request.setAttribute("cVector", cVector);
                request.getRequestDispatcher("jsp/addProduct.jsp").forward(request, response);

            } else {
                String productName = request.getParameter("productName"),
                        image = request.getParameter("image");
                double price = Double.parseDouble(request.getParameter("price"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                String categoryID = request.getParameter("categoryID");
                Date importDate = Date.valueOf(request.getParameter("importDate")),
                        usingDate = Date.valueOf(request.getParameter("usingDate"));
                int status = Integer.parseInt(request.getParameter("status"));
                Products p = new Products(productName, image, price, quantity, categoryID, importDate, usingDate, status);
                int n = dao.insertProduct(p);
                response.sendRedirect("productjsp");
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
