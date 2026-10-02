package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.productDAO;
import java.sql.Date;
import java.util.Vector;
import model.Products;

@WebServlet(name = "ProductServlet", urlPatterns = {"/productSVL"})
public class ProductServlet extends HttpServlet {

    String sql = "select * from tblProducts";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        productDAO dao = new productDAO();
        Vector<Products> vector;
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAllProduct";
        }
        if (service.equals("deleteProduct")) {
            int pId = Integer.parseInt(request.getParameter("productID"));
            dao.deleteProduct(pId);
            response.sendRedirect("productSVL");
        }
        if (service.equals("addProduct")) {
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
            response.sendRedirect("productSVL");
        }
        if (service.equals("listAllProduct")) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ProductServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<p><a href=\"addProduct.html\">Insert a new Product</a></p>");
                out.println("<form action=\"productSVL\">\n"
                        + "            Search By Product Name: <input type=\"text\" name=\"productName\" id=\"txtName\">\n"
                        + "            <input type=\"submit\" name=\"submit\" id=\"txtSubmit\" value=\"Search\">\n"
                        + "            <input type=\"reset\" value=\"Reset\">\n"
                        + "            <input type=\"hidden\" name=\"service\" value=\"listAllProduct\">\n"
                        + "        </form>");
                out.println("<table border=\"1\">\n"
                        + "            <thead>\n"
                        + "                <tr>                    \n"
                        + "                    <th>productID</th>\n"
                        + "                    <th>productName</th>\n"
                        + "                    <th>image</th>\n"
                        + "                    <th>price</th>\n"
                        + "                    <th>quantity</th>\n"
                        + "                    <th>categoryID</th>\n"
                        + "                    <th>importDate</th>\n"
                        + "                    <th>usingDate</th>\n"
                        + "                    <th>status</th>\n"
                        + "                    <th>delete</th>\n"
                        + "                    <th>update</th>\n"
                        + "                </tr>\n"
                        + "            </thead>");
                String submit = request.getParameter("submit");
                if (submit == null) {
                    //show all products
                    vector = dao.getAllProduct(sql);
                } else {
                    //search product
                    String pName = request.getParameter("productName");
                    vector = dao.getAllProduct("Select * From tblProducts\n"
                            + "Where productName like N'%" + pName + "%'");
                }
                for (Products p : vector) {
                    out.println(" <tbody>\n"
                            + "                <tr>\n"
                            + "                    <td>" + p.getProductID() + "</td>\n"
                            + "                    <td>" + p.getProductName() + "</td>\n"
                            + "                    <td>" + p.getImage() + "</td>\n"
                            + "                    <td>" + p.getPrice() + "</td>\n"
                            + "                    <td>" + p.getQuantity() + "</td>\n"
                            + "                    <td>" + p.getCategoryID() + "</td>\n"
                            + "                    <td>" + p.getImportDate() + "</td>\n"
                            + "                    <td>" + p.getUsingDate() + "</td>\n"
                            + "                    <td>" + p.getStatus() + "</td>\n"
                            + "                    <td><a href=\"productSVL?service=deleteProduct&productID=" + p.getProductID() + "\">delete</a></td>\n"
                            + "                    <td><a href=\"productSVL?service=updateProduct&productID=" + p.getProductID() + "\">update</a></td>\n"
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
