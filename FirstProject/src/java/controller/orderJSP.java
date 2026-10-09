package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import dal.orderDAO;
import model.Orders;

@WebServlet(name = "orderJSP", urlPatterns = {"/orderJSP"})
public class orderJSP extends HttpServlet {

    String sql = "select * from tblOrders";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Vector<Orders> vector;
        orderDAO dao = new orderDAO();

        String service = request.getParameter("service");
        if (service == null) {
            service = "listAllOrder";
        }

        if (service.equals("listAllOrder")) {
            String submit = request.getParameter("submit");

            if (submit == null) {
                vector = dao.getAllOrder(sql);

            } else {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                vector = dao.getAllOrder("select * from tblOrders\n"
                        + "where orderID like N'%" + orderID + "%'");
            }
            
            request.setAttribute("vector", vector);
            request.setAttribute("tableTitle", "List of Orders");
            request.setAttribute("pageTitle", "Order management");
            
            request.getRequestDispatcher("jsp/orderlist.jsp").forward(request, response);
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
