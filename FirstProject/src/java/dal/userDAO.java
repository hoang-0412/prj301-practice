package dal;

import java.util.Vector;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Users;

public class userDAO extends DBContext {

    public Vector<Users> getAllUser(String sql) {
        Vector<Users> vector = new Vector<>();
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Users u = new Users(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8)
                );
                vector.add(u);
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return vector;
    }

    public Users searchUser(String userID) {
        String sql = "select * from tblUsers\n"
                + "where userID = ?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, userID);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                Users u = new Users(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8));
                return u;
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return null;
    }

    public int insertUser(Users u) {
        String sql = "INSERT INTO [dbo].[tblUsers]\n"
                + "           ([userID]\n"
                + "           ,[fullName]\n"
                + "           ,[password]\n"
                + "           ,[roleID]\n"
                + "           ,[address]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[activate])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?)";
        int n = 0;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, u.getUserID());
            ptm.setString(2, u.getFullName());
            ptm.setString(3, u.getPassword());
            ptm.setInt(4, u.getRoleID());
            ptm.setString(5, u.getAddress());
            ptm.setString(6, u.getPhone());
            ptm.setString(7, u.getEmail());
            ptm.setInt(8, u.getActivate());
            n = ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return n;
    }

    public int updateUser(Users u) {
        String sql = "UPDATE [dbo].[tblUsers]\n"
                + "   SET [fullName] = ?\n"
                + "      ,[password] = ?\n"
                + "      ,[roleID] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[activate] = ?\n"
                + " WHERE [userID] = ?";
        int n = 0;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, u.getFullName());
            ptm.setString(2, u.getPassword());
            ptm.setInt(3, u.getRoleID());
            ptm.setString(4, u.getAddress());
            ptm.setString(5, u.getPhone());
            ptm.setString(6, u.getEmail());
            ptm.setInt(7, u.getActivate());
            ptm.setString(8, u.getUserID());
            n = ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return n;
    }

    public void changeStatus(String userID, int newStatus) {
        String sql = "UPDATE [dbo].[tblUsers]\n"
                + "   SET [activate] = ?\n"
                + " WHERE [userID] = ?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, newStatus);
            ptm.setString(2, userID);
            ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }

    public int deleteUser(String userID) {
        int n = 0;
        String sql1 = "DELETE FROM [dbo].[tblUsers]\n"
                + "      WHERE [userID] = ?";
        // Kiem tra userID da tung dat hang chua
        // 1. Neu da tung dat hang thi userID nam trong tblOrders
        // -> Khong xoa user, change activate = 0
        String sql2 = "SELECT * FROM [dbo].[tblOrders] WHERE [userID] = ?";
        // 2. Neu chua tung dat hang thi userID khong nam trong tblOrders
        // -> Xoa user khoi bang tblUsers
        try {
            PreparedStatement ptm = connection.prepareStatement(sql2);
            ptm.setString(1, userID);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                // 1. Neu da tung dat hang thi userID nam trong tblOrders
                // -> Khong xoa user, change activate = 0
                changeStatus(userID, 0);
            } else {
                // 2. Neu chua tung dat hang thi xoa khoi bang tblUsers
                PreparedStatement ptm1 = connection.prepareStatement(sql1);
                ptm1.setString(1, userID);
                n = ptm1.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        userDAO dao = new userDAO();
        String sql = "select * from tblUsers";
        Vector<Users> vector = dao.getAllUser(sql);
        for (Users u : vector) {
            System.out.println(u);
        }
        Users uSearch = dao.searchUser("U001");
        if (uSearch == null) {
            System.err.println("Not found!");
        } else {
            System.out.println("User can tim: " + uSearch);
        }
//        Users u = new Users("U006", "Trinh Huy Hoang", "password10", 2, "FPTU", "0965551664", "hoangth04122005@gmail.com", 1);
//        int n = dao.insertUser(u);
        uSearch.setFullName("Trinh Huy Hoang Updated");
        dao.updateUser(uSearch);
        dao.deleteUser("U006");
    }
}
