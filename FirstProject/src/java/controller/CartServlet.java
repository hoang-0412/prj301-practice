package controller;

import dal.CartDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Vector;
import model.Cart;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        CartDAO dao = new CartDAO();
        String service = request.getParameter("service");
        if (service == null) {
            service = "showCart";
        }
        if (service.equals("removeAll")) {
//            session.invalidate();
            Enumeration enu = session.getAttributeNames();
            while (enu.hasMoreElements()) {
                String key = (String) enu.nextElement();
                if (!key.equals("email")) {
                    session.removeAttribute(key);
                }
            }
            response.sendRedirect("cart");
        }
        if (service.equals("removeItem")) {
            int pId = Integer.parseInt(request.getParameter("productID"));
            session.removeAttribute(pId + "");
            response.sendRedirect("cart");
        }
        if (service.equals("showCart")) {
            Vector<Cart> vector = new Vector<>();

            Enumeration enu = session.getAttributeNames();
            while (enu.hasMoreElements()) {
                String key = (String) enu.nextElement();
                if (!key.equals("email")) {
                    Object obj = session.getAttribute(key);
                    if (obj instanceof Cart) {
                        vector.add((Cart) obj);
                    }
                }
            }
            request.setAttribute("vector", vector);
            request.getRequestDispatcher("jsp/cart.jsp").forward(request, response);
        }
        if (service.equals("add2Cart")) {
            int pId = Integer.parseInt(request.getParameter("productID"));
            Cart cart = dao.getCart(pId);
            if (cart != null) {
                //Kiem tra xem product co trong Cart chua
                if (session.getAttribute(pId + "") == null) {//First time shopping
                    cart.setQuantity(1);
                    session.setAttribute(pId + "", cart);
                } else {// >= second time to shopping
                    cart = (Cart) session.getAttribute(pId + "");
                    cart.setQuantity(cart.getQuantity() + 1);
                    session.setAttribute(pId + "", cart);
                }
            }
            response.sendRedirect("cart");
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
