package dal;

import java.util.Vector;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Products;

import java.sql.Date;

public class productDAO extends DBContext {

    public Vector<Products> getAllProduct(String sql) {
        Vector<Products> vector = new Vector<>();
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Products p = new Products(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getDate(8),
                        rs.getInt(9));
                vector.add(p);
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return vector;
    }

    public Products searchProduct(int productID) {
        String sql = "select * from tblProducts\n"
                + "where productID = ?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, productID);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                Products p = new Products(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getDate(8),
                        rs.getInt(9));
                return p;
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return null;
    }

    public int insertProduct(Products p) {
        String sql = "INSERT INTO [dbo].[tblProducts]\n"
                + "           ([productName]\n"
                + "           ,[image]\n"
                + "           ,[price]\n"
                + "           ,[quantity]\n"
                + "           ,[categoryID]\n"
                + "           ,[importDate]\n"
                + "           ,[usingDate]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?)";
        int n = 0;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, p.getProductName());
            ptm.setString(2, p.getImage());
            ptm.setDouble(3, p.getPrice());
            ptm.setInt(4, p.getQuantity());
            ptm.setString(5, p.getCategoryID());
            ptm.setDate(6, p.getImportDate());
            ptm.setDate(7, p.getUsingDate());
            ptm.setInt(8, p.getStatus());
            n = ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return n;
    }

    public int updateProduct(Products p) {
        String sql = "UPDATE [dbo].[tblProducts]\n"
                + "   SET [productName] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[categoryID] = ?\n"
                + "      ,[importDate] = ?\n"
                + "      ,[usingDate] = ?\n"
                + "      ,[status] = ?\n"
                + " WHERE productID = ?";
        int n = 0;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, p.getProductName());
            ptm.setString(2, p.getImage());
            ptm.setDouble(3, p.getPrice());
            ptm.setInt(4, p.getQuantity());
            ptm.setString(5, p.getCategoryID());
            ptm.setDate(6, p.getImportDate());
            ptm.setDate(7, p.getUsingDate());
            ptm.setInt(8, p.getStatus());
            ptm.setInt(9, p.getProductID());
            n = ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return n;
    }

    public void changeStatus(int productID, int newStatus) {
        String sql = "UPDATE tblProducts\n"
                + "SET status = ?\n"
                + "WHERE productID = ?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, newStatus);
            ptm.setInt(2, productID);
            ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }

    public int deleteProduct(int productID) {
        int n = 0;
        String sql1 = "DELETE FROM [dbo].[tblProducts]\n"
                + "      WHERE productID = ?";
        //Kiem tra productID da ban chua
        //1. Neu da tung ban thi productID nam trong OrderDetail
        //->Khong xoa product, change status = 0

        String sql2 = "select * from tblOrderDetails where productID = ?";
        //2. Neu chua ban thi prodcutID khong nam trong OrderDetail
        //->Xoa product khoi bang tblProducts

        try {
            PreparedStatement ptm = connection.prepareStatement(sql2);
            ptm.setInt(1, productID);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                //1. Neu da tung ban thi productID nam trong OrderDetail
                //->Khong xoa product, change status = 0
                changeStatus(productID, 0);

            } else {
                //2. Neu chua ban thi prodcutID khong nam trong OrderDetail
                //->Xoa product khoi bang tblProducts
                PreparedStatement ptm1 = connection.prepareStatement(sql1);
                ptm1.setInt(1, productID);
                n = ptm1.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        productDAO dao = new productDAO();
        String sql = "select * from tblProducts";
        Vector<Products> vector = dao.getAllProduct(sql);
        for (Products p : vector) {
            System.out.println(p);
        }
        Products pSearch = dao.searchProduct(3);
        if (pSearch == null) {
            System.err.println("Not found!");
        } else {
            System.out.println("Product can tim: " + pSearch);
        }
//        Products p = new Products("New product1",
//                "d://prj301/se2075",
//                100,
//                20,
//                "C003",
//                new Date(2016 - 1900, 8, 14),
//                new Date(2020 - 1900, 8, 14),
//                1);
//        int n = dao.insertProduct(p);
        pSearch.setProductName("Iphone 18 pro max");
        int n = dao.updateProduct(pSearch);
        dao.deleteProduct(8);
    }
}
