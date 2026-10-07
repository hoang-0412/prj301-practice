package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.Orders;
import dal.orderDAO;
import java.sql.Date;

@WebServlet(name = "OrderServlet", urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {

    String sql = "select * from tblOrders";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        orderDAO dao = new orderDAO();
        Vector<Orders> vector;

        String service = request.getParameter("service");
        if (service == null) {
            service = "listAllOrder";
        }

        if (service.equals("deleteOrder")) {
            int oId = Integer.parseInt(request.getParameter("orderID"));
            dao.deleteOrder(oId);
            response.sendRedirect("order");
        }

        if (service.equals("addOrder")) {
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            Date orderDate = Date.valueOf(request.getParameter("orderDate"));
            double total = Double.parseDouble(request.getParameter("total"));
            String userID = request.getParameter("userID");

            Orders o = new Orders(orderID, orderDate, total, userID);
            int n = dao.insertOrder(o);
            response.sendRedirect("order");
        }

        if (service.equals("listAllOrder")) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet OrderServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<p><a href=\"addOrder.html\">Insert a new Order</a></p>");

                out.println("<form action=\"order\">\n"
                        + "            Search by Order ID: <input type=\"text\" name=\"orderID\" id=\"txtId\">\n"
                        + "            <input type=\"submit\" name=\"submit\" id=\"txtSubmit\" value=\"Search\">\n"
                        + "            <input type=\"reset\" value=\"Reset\">\n"
                        + "            <input type=\"hidden\" name=\"service\" value=\"listAllOrder\">\n"
                        + "        </form>");

                out.println("<table border=\"1\">\n"
                        + "            <thead>\n"
                        + "                <tr>\n"
                        + "                    <th>orderID</th>\n"
                        + "                    <th>orderDate</th>\n"
                        + "                    <th>total</th>\n"
                        + "                    <th>userID</th>\n"
                        + "                    <th>delete</th>\n"
                        + "                    <th>update</th>\n"
                        + "                </tr>\n"
                        + "            </thead>");

                String submit = request.getParameter("submit");
                if (submit == null) {
                    vector = dao.getAllOrder(sql);

                } else {
                    int orderID = Integer.parseInt(request.getParameter("orderID"));
                    vector = dao.getAllOrder("select * from tblOrders\n"
                            + "where orderID like N'%" + orderID + "%'");
                }

                for (Orders o : vector) {
                    out.println("<tbody>\n"
                            + "                <tr>\n"
                            + "                    <td>" + o.getOrderID() + "</td>\n"
                            + "                    <td>" + o.getOrderDate() + "</td>\n"
                            + "                    <td>" + o.getTotal() + "</td>\n"
                            + "                    <td>" + o.getUserID() + "</td>\n"
                            + "                    <td><a href=\"order?service=deleteOrder&orderId=" + o.getOrderID() + "\">delete</a></td>\n"
                            + "                    <td><a href=\"order?service=updateOrder&orderId=" + o.getOrderID() + "\">update</a></td>\n"
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
