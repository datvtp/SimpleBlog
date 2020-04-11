package datvtp.daos;

import datvtp.dtos.Tbl_CommentDTO;
import datvtp.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class Tbl_CommentDAO implements Serializable {

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

    public List<Tbl_CommentDTO> getListCommentByArticleId(int articleId) throws SQLException, NamingException {
        List<Tbl_CommentDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT CommentID, CommentContent, Author, TimeGenerated FROM tbl_Comment "
                    + "WHERE ArticleID = ? ORDER BY TimeGenerated DESC";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, articleId);
            rs = ps.executeQuery();

            while (rs.next()) {
                int commentId = rs.getInt("CommentID");
                String commentContent = rs.getString("CommentContent");
                String author = rs.getString("Author");
                String timeGenerated = rs.getString("TimeGenerated");
                list.add(new Tbl_CommentDTO(commentId, commentContent, author, timeGenerated));
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public boolean insert(String commentContent, String author, String timeGenerated, int articleId, String email) throws SQLException, NamingException {
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO tbl_Comment(CommentContent, Author, TimeGenerated, ArticleID, Email) VALUES (?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, commentContent);
            ps.setString(2, author);
            ps.setString(3, timeGenerated);
            ps.setInt(4, articleId);
            ps.setString(5, email);
            
            result = ps.executeUpdate() > 0;
            
        } finally {
            closeConnection();
        }
        return result;
    }
}
