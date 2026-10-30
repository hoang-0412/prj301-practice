package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import model.Cart;
import model.Products;

public class CartDAO extends DBContext {

    public Cart getCart(int productId) {
        String sql = "Select productID, productName,price,quantity\n"
                + "From tblProducts\n"
                + "Where productID=?";
        Cart cart = null;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, productId);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                cart = new Cart(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        0);
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return cart;
    }

}
