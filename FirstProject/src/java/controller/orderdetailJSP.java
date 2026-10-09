package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import dal.orderdetailDAO;
import model.OrderDetails;

@WebServlet(name = "orderdetailJSP", urlPatterns = {"/orderdetailJSP"})
public class orderdetailJSP extends HttpServlet {

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

        if (service.equals("listAllOrderDetail")) {
            String submit = request.getParameter("submit");

            if (submit == null) {
                vector = dao.getAllOrderDetail(sql);

            } else {
                int detailID = Integer.parseInt(request.getParameter("detailID"));
                vector = dao.getAllOrderDetail("select * from tblOrderDetails\n"
                        + "where detailID like N'%" + detailID + "%'");
            }
            
            request.setAttribute("vector", vector);
            request.setAttribute("tableTitle", "List of order detail");
            request.setAttribute("pageTitle", "Order detail management");
            
            request.getRequestDispatcher("jsp/orderdetaillist.jsp").forward(request, response);
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
