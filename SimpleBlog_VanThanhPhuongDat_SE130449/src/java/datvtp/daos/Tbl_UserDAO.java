package datvtp.daos;

import datvtp.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

public class Tbl_UserDAO implements Serializable {

    private String name;
    private int status;

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public int checkLogin(String email, String password) throws NamingException, SQLException {
        int role = 0;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT RoleID, Name, StatusID FROM tbl_User WHERE Email = ? AND Password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                this.status = rs.getInt("StatusID");
                this.name = rs.getString("Name");
                role = rs.getInt("RoleID");
            }
        } finally {
            closeConnection();
        }
        return role;
    }
    
    public String checkEmailExist(String emailCheck) throws SQLException, NamingException {
        String email = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT Email FROM tbl_User WHERE Email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, emailCheck);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                email = rs.getString("Email");
            }
        } finally {
            closeConnection();
        }
        return email;
    }

    public boolean insert(String email, String password, String name) throws SQLException, NamingException {
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO tbl_User(Email, Name, Password, RoleID, StatusID) VALUES (?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, name);
            ps.setString(3, password);
            ps.setInt(4, 2);
            ps.setInt(5, 1);

            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateStatusUser(String email, int statusId) throws NamingException, SQLException {
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tbl_User SET StatusID = ? WHERE EMAIL = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, statusId);
            ps.setString(2, email);

            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
}
