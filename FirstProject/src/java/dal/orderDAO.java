package dal;

import java.util.Vector;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import model.Orders;

public class orderDAO extends DBContext {

    public Vector<Orders> getAllOrder(String sql) {
        Vector<Orders> vector = new Vector<>();
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Orders o = new Orders(rs.getInt(1), rs.getDate(2), rs.getDouble(3), rs.getString(4));
                vector.add(o);
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return vector;
    }

    public Orders searchOrder(int orderID) {
        String sql = "select * from tblOrders\n"
                + "where orderID = ?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, orderID);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                Orders o = new Orders(rs.getInt(1),
                        rs.getDate(2),
                        rs.getDouble(3),
                        rs.getString(4));
                return o;
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return null;
    }

    public int insertOrder(Orders o) {
        String sql = "INSERT INTO [dbo].[tblOrders]\n"
                + "           ([orderDate]\n"
                + "           ,[total]\n"
                + "           ,[userID])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        int n = 0;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setDate(1, o.getOrderDate());
            ptm.setDouble(2, o.getTotal());
            ptm.setString(3, o.getUserID());
            n = ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return n;
    }

    public int updateOrder(Orders o) {
        String sql = "UPDATE [dbo].[tblOrders]\n"
                + "   SET [orderDate] = ?\n"
                + "      ,[total] = ?\n"
                + "      ,[userID] = ?\n"
                + " WHERE [orderID] = ?";
        int n = 0;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setDate(1, o.getOrderDate());
            ptm.setDouble(2, o.getTotal());
            ptm.setString(3, o.getUserID());
            ptm.setInt(4, o.getOrderID());
            n = ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return n;
    }

    public int deleteOrder(int orderID) {
        int n = 0;
        // Xoa cac ban ghi lien quan trong tblOrderDetails truoc de tranh loi Foreign Key
        String sqlDetails = "DELETE FROM [dbo].[tblOrderDetails] WHERE [orderID] = ?";
        String sqlOrder = "DELETE FROM [dbo].[tblOrders] WHERE [orderID] = ?";
        try {
            PreparedStatement ptmDetails = connection.prepareStatement(sqlDetails);
            ptmDetails.setInt(1, orderID);
            ptmDetails.executeUpdate();

            PreparedStatement ptmOrder = connection.prepareStatement(sqlOrder);
            ptmOrder.setInt(1, orderID);
            n = ptmOrder.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        orderDAO dao = new orderDAO();
        String sql = "select * from tblOrders";
        Vector<Orders> vector = dao.getAllOrder(sql);
        for (Orders o : vector) {
            System.out.println(o);
        }
        Orders oSearch = dao.searchOrder(3);
        if (oSearch == null) {
            System.err.println("Not found!");
        } else {
            System.out.println("Order can tim: " + oSearch);
            oSearch.setTotal(550.00);
            dao.updateOrder(oSearch);
        }
//        Orders o = new Orders(new Date(2020 - 1900, 8, 14),
//                490.00,
//                "U006");
//        int n = dao.insertOrder(o);
        oSearch.setTotal(250.25);
        int n = dao.updateOrder(oSearch);
        dao.deleteOrder(10);
    }
}
