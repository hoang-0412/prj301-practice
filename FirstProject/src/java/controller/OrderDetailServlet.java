package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrderDetails;
import java.util.Vector;
import dal.orderdetailDAO;

@WebServlet(name = "OrderDetailServlet", urlPatterns = {"/orderdetail"})
public class OrderDetailServlet extends HttpServlet {

    String sql = "select * from tblOrderDetails";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        orderdetailDAO dao = new orderdetailDAO();
        Vector<OrderDetails> vector;

        String service = request.getParameter("service");
        if (service == null) {
            service = "listAllOrderDetail";
        }

        if (service.equals("deleteOrderDetail")) {
            int detailID = Integer.parseInt(request.getParameter("detailID"));
            dao.deleteOrderDetail(detailID);
            response.sendRedirect("orderdetail");
        }

        if (service.equals("addOrderDetail")) {
            int detailID = Integer.parseInt(request.getParameter("detailID"));
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity")),
                    orderID = Integer.parseInt(request.getParameter("orderID")),
                    productID = Integer.parseInt(request.getParameter("productID"));

            OrderDetails od = new OrderDetails(detailID, price, quantity, orderID, productID);
            int n = dao.insertOrderDetail(od);
            response.sendRedirect("order");
        }

        if (service.equals("listAllOrderDetail")) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet OrderDetailServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<p><a href=\"addOrderDetail.html\">Insert a new Order Detail</a></p>");

                out.println("<form action=\"orderdetail\">\n"
                        + "            Search By detailID: <input type=\"text\" name=\"detailID\" id=\"txtId\">\n"
                        + "            <input type=\"submit\" name=\"submit\" id=\"txtSubmit\" value=\"Search\">\n"
                        + "            <input type=\"reset\" value=\"Reset\">\n"
                        + "            <input type=\"hidden\" name=\"service\" value=\"listAllOrderDetail\">\n"
                        + "        </form>");

                out.println("<table border=\"1\">\n"
                        + "            <thead>\n"
                        + "                <tr>\n"
                        + "                    <th>detailID</th>\n"
                        + "                    <th>price</th>\n"
                        + "                    <th>quantity</th>\n"
                        + "                    <th>orderID</th>\n"
                        + "                    <th>productID</th>\n"
                        + "                    <th>delete</th>\n"
                        + "                    <th>update</th>\n"
                        + "                </tr>\n"
                        + "            </thead>");

                String submit = request.getParameter("submit");
                if (submit == null) {
                    vector = dao.getAllOrderDetail(sql);

                } else {
                    int detailID = Integer.parseInt(request.getParameter("detailID"));
                    vector = dao.getAllOrderDetail("select * from tblOrderDetails\n"
                            + "where detailID like N'%" + detailID + "%'");
                }

                for (OrderDetails od : vector) {
                    out.print("<tbody>\n"
                            + "                <tr>\n"
                            + "                    <td>" + od.getDetailID() + "</td>\n"
                            + "                    <td>" + od.getPrice() + "</td>\n"
                            + "                    <td>" + od.getQuantity() + "</td>\n"
                            + "                    <td>" + od.getOrderID() + "</td>\n"
                            + "                    <td>" + od.getProductID() + "</td>\n"
                            + "                    <td><a href=\"orderdetail?service=deleteOrderDetail&orderDetailId=" + od.getDetailID() + "\">delete</a></td>\n"
                            + "                    <td><a href=\"orderdetail?service=updateOrderDetail&orderDetailId=" + od.getDetailID() + "\">update</a></td>\n"
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
