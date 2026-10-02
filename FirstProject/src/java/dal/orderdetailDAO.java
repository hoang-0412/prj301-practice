package dal;

import java.util.Vector;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OrderDetails;

public class orderdetailDAO extends DBContext {

    public Vector<OrderDetails> getAllOrderDetail(String sql) {
        Vector<OrderDetails> vector = new Vector<>();
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                OrderDetails od = new OrderDetails(rs.getInt(1),
                        rs.getDouble(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5));
                vector.add(od);
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return vector;
    }

    public OrderDetails searchOrderDetail(int detailID) {
        String sql = "select * from tblOrderDetails\n"
                + "where detailID = ?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, detailID);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                OrderDetails od = new OrderDetails(rs.getInt(1),
                        rs.getDouble(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5));
                return od;
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return null;
    }

    public int insertOrderDetail(OrderDetails od) {
        String sql = "INSERT INTO [dbo].[tblOrderDetails]\n"
                + "           ([price]\n"
                + "           ,[quantity]\n"
                + "           ,[orderID]\n"
                + "           ,[productID])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        int n = 0;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setDouble(1, od.getPrice());
            ptm.setInt(2, od.getQuantity());
            ptm.setInt(3, od.getOrderID());
            ptm.setInt(4, od.getProductID());
            n = ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return n;
    }

    public int updateOrderDetail(OrderDetails od) {
        String sql = "UPDATE [dbo].[tblOrderDetails]\n"
                + "   SET [price] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[orderID] = ?\n"
                + "      ,[productID] = ?\n"
                + " WHERE [detailID] = ?";
        int n = 0;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setDouble(1, od.getPrice());
            ptm.setInt(2, od.getQuantity());
            ptm.setInt(3, od.getOrderID());
            ptm.setInt(4, od.getProductID());
            ptm.setInt(5, od.getDetailID());
            n = ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return n;
    }

    public int deleteOrderDetail(int detailID) {
        String sql = "DELETE FROM [dbo].[tblOrderDetails] WHERE [detailID] = ?";
        int n = 0;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, detailID);
            n = ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        orderdetailDAO dao = new orderdetailDAO();
        String sql = "select * from tblOrderDetails";
        Vector<OrderDetails> vector = dao.getAllOrderDetail(sql);
        for (OrderDetails od : vector) {
            System.out.println(od);
        }
        OrderDetails odSearch = dao.searchOrderDetail(3);
        if (odSearch == null) {
            System.err.println("Not found!");
        } else {
            System.out.println("OrderDetail can tim: " + odSearch);
            odSearch.setQuantity(10);
            dao.updateOrderDetail(odSearch);
        }
//        OrderDetails od = new OrderDetails(9900.00, 5, 5, 4);
//        int n = dao.insertOrderDetail(od);
        odSearch.setPrice(23.23);
        int n = dao.updateOrderDetail(odSearch);
        dao.deleteOrderDetail(10);
    }
}
