package dal;

import java.util.Vector;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Categories;

public class categoryDAO extends DBContext {

    public Vector<Categories> getAllCategory(String sql) {
        Vector<Categories> vector = new Vector<>();
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Categories c = new Categories(rs.getString(1), rs.getString(2), rs.getString(3));
                vector.add(c);
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return vector;
    }

    public Categories searchCategory(String categoryID) {
        String sql = "select * from tblCategories\n"
                + "where categoryID = ?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, categoryID);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                Categories c = new Categories(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3));
                return c;
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return null;
    }

    public int insertCategory(Categories c) {
        String sql = "INSERT INTO [dbo].[tblCategories]\n"
                + "           ([categoryID]\n"
                + "           ,[categoryName]\n"
                + "           ,[describe])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        int n = 0;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, c.getCategoryID());
            ptm.setString(2, c.getCategoryName());
            ptm.setString(3, c.getDescribe());
            n = ptm.executeUpdate();
        } catch (SQLException ex) {
        }
        return n;
    }

    public int updateCategory(Categories c) {
        String sql = "UPDATE [dbo].[tblCategories]\n"
                + "   SET [categoryName] = ?\n"
                + "      ,[describe] = ?\n"
                + " WHERE [categoryID] = ?";
        int n = 0;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, c.getCategoryName());
            ptm.setString(2, c.getDescribe());
            ptm.setString(3, c.getCategoryID());
            n = ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return n;
    }

    public int deleteCategory(String categoryID) {
        int n = 0;
        String sql1 = "DELETE FROM [dbo].[tblCategories]\n"
                + "      WHERE [categoryID] = ?";
        // Kiem tra categoryID da co san pham nao chua
        // 1. Neu da co san pham thi categoryID nam trong tblProducts
        // -> Khong the xoa category
        String sql2 = "SELECT * FROM [dbo].[tblProducts] WHERE [categoryID] = ?";
        // 2. Neu chua co san pham nao thuoc category nay
        // -> Xoa category khoi bang tblCategories
        try {
            PreparedStatement ptm = connection.prepareStatement(sql2);
            ptm.setString(1, categoryID);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                System.out.println("CategoryID " + categoryID + " dang duoc su dung trong tblProducts, khong the xoa!");
                return 0;
            } else {
                PreparedStatement ptm1 = connection.prepareStatement(sql1);
                ptm1.setString(1, categoryID);
                n = ptm1.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        categoryDAO dao = new categoryDAO();
        String sql = "select * from tblCategories";
        Vector<Categories> vector = dao.getAllCategory(sql);
        for (Categories c : vector) {
            System.out.println(c);
        }
        Categories cSearch = dao.searchCategory("C005");
        if (cSearch == null) {
            System.err.println("Not found!");
        } else {
            System.out.println("Category can tim: " + cSearch);
        }
//        Categories c = new Categories("C006", "Test", "data test code");
//        int n = dao.insertCategory(c);
        cSearch.setCategoryName("FASHION");
        int n = dao.updateCategory(cSearch);
        dao.deleteCategory("C006");
    }
}
