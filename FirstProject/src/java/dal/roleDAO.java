package dal;

import java.util.Vector;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Roles;

public class roleDAO extends DBContext {

    public Vector<Roles> getAllRole(String sql) {
        Vector<Roles> vector = new Vector<>();
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Roles r = new Roles(rs.getInt(1), rs.getString(2));
                vector.add(r);
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return vector;
    }

    public Roles searchRole(int roleID) {
        String sql = "select * from tblRoles\n"
                + "where roleID = ?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, roleID);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                Roles r = new Roles(rs.getInt(1), rs.getString(2));
                return r;
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return null;
    }

    public int insertRole(Roles r) {
        String sql = "INSERT INTO [dbo].[tblRoles]\n"
                + "           ([roleName])\n"
                + "     VALUES\n"
                + "           (?)";
        int n = 0;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, r.getRoleName());
            n = ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return n;
    }

    public int updateRole(Roles r) {
        String sql = "UPDATE [dbo].[tblRoles]\n"
                + "   SET [roleName] = ?\n"
                + " WHERE [roleID] = ?";
        int n = 0;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, r.getRoleName());
            ptm.setInt(2, r.getRoleID());
            n = ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return n;
    }

    public int deleteRole(int roleID) {
        int n = 0;
        String sql1 = "DELETE FROM [dbo].[tblRoles]\n"
                + "      WHERE [roleID] = ?";
        // Kiem tra roleID da duoc su dung boi User nao chua
        // 1. Neu da duoc gan cho User thi roleID nam trong tblUsers
        // -> Khong the xoa role
        String sql2 = "SELECT * FROM [dbo].[tblUsers] WHERE [roleID] = ?";
        // 2. Neu chua co User nao su dung role nay
        // -> Xoa role khoi bang tblRoles
        try {
            PreparedStatement ptm = connection.prepareStatement(sql2);
            ptm.setInt(1, roleID);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                System.out.println("RoleID " + roleID + " dang duoc su dung trong tblUsers, khong the xoa!");
                return 0;
            } else {
                PreparedStatement ptm1 = connection.prepareStatement(sql1);
                ptm1.setInt(1, roleID);
                n = ptm1.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.getSQLState();
        }
        return n;
    }

    public static void main(String[] args) {
        roleDAO dao = new roleDAO();
        String sql = "select * from tblRoles";
        Vector<Roles> vector = dao.getAllRole(sql);
        for (Roles r : vector) {
            System.out.println(r);
        }
        Roles rSearch = dao.searchRole(3);
        if (rSearch == null) {
            System.err.println("Not found!");
        } else {
            System.out.println("Role can tim: " + rSearch);
            rSearch.setRoleName("Tap dich updated");
            dao.updateRole(rSearch);
        }
//        Roles r = new Roles("Tap dich");
//        int n = dao.insertRole(r);
        rSearch.setRoleName("Tong chu");
        int n = dao.updateRole(rSearch);
        dao.deleteRole(7);
    }
}
