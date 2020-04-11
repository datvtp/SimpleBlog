package datvtp.daos;

import datvtp.dtos.Tbl_ArticleDTO;
import datvtp.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class Tbl_ArticleDAO implements Serializable {

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

    public int getNumberOfArticle(String searchTitle, int statusId) throws SQLException, NamingException {
        int number = 0;
        try {
            conn = DBUtils.getConnection();
            if (statusId == 0) {
                String sql = "SELECT COUNT(*) as Total FROM tbl_Article WHERE Title LIKE ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + searchTitle + "%");
            } else {
                String sql = "SELECT COUNT(*) as Total FROM tbl_Article WHERE Title LIKE ? AND StatusID = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + searchTitle + "%");
                ps.setInt(2, statusId);
            }
            rs = ps.executeQuery();

            if (rs.next()) {
                number = rs.getInt("Total");
            }
        } finally {
            closeConnection();
        }
        return number;
    }

    public List<Tbl_ArticleDTO> getListArticleByTitle(String searchTitle, int statusId, int offsetRecord, int next) throws SQLException, NamingException {
        List<Tbl_ArticleDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (statusId == 0) {
                String sql = "SELECT ArticleID, Title, ShortDescription, ArticleContent, Author, TimeGenerated, StatusID FROM tbl_Article \n"
                        + "	WHERE Title LIKE ? \n"
                        + "	ORDER BY TimeGenerated DESC \n"
                        + "	OFFSET ? ROWS \n"
                        + "	FETCH NEXT ? ROWS ONLY";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + searchTitle + "%");
                ps.setInt(2, offsetRecord);
                ps.setInt(3, next);
            } else {
                String sql = "SELECT ArticleID, Title, ShortDescription, ArticleContent, Author, TimeGenerated, StatusID FROM tbl_Article \n"
                        + "	WHERE Title LIKE ? AND StatusID = ? \n"
                        + "	ORDER BY TimeGenerated DESC \n"
                        + "	OFFSET ? ROWS \n"
                        + "	FETCH NEXT ? ROWS ONLY";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + searchTitle + "%");
                ps.setInt(2, statusId);
                ps.setInt(3, offsetRecord);
                ps.setInt(4, next);
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                int articleId = rs.getInt("ArticleID");
                String title = rs.getString("Title");
                String shortDescription = rs.getString("ShortDescription");
                String content = rs.getString("ArticleContent");
                String author = rs.getString("Author");
                String timeGenerated = rs.getString("TimeGenerated");
                int status = rs.getInt("StatusID");
                list.add(new Tbl_ArticleDTO(articleId, title, shortDescription, content, author, timeGenerated, status));
            }
        } finally {
            closeConnection();
        }

        return list;
    }

    public Tbl_ArticleDTO findArticleById(int articleId) throws SQLException, NamingException {
        Tbl_ArticleDTO articleDTO = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT ArticleID, Title, ShortDescription, ArticleContent, Author, TimeGenerated FROM tbl_Article "
                    + "WHERE ArticleID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, articleId);
            rs = ps.executeQuery();

            if (rs.next()) {
                String title = rs.getString("Title");
                String shortDescription = rs.getString("ShortDescription");
                String articleContent = rs.getString("ArticleContent");
                String author = rs.getString("Author");
                String timeGenerated = rs.getString("TimeGenerated");

                articleDTO = new Tbl_ArticleDTO(articleId, title, shortDescription, articleContent, author, timeGenerated);
            }
        } finally {
            closeConnection();
        }
        return articleDTO;
    }

    public boolean insert(String title, String shortDescription, String articleContent, String author, String timeGenerated, int statusId, String email) throws SQLException, NamingException {
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO tbl_Article(Title, ShortDescription, ArticleContent, Author, TimeGenerated, StatusID, Email) VALUES (?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, shortDescription);
            ps.setString(3, articleContent);
            ps.setString(4, author);
            ps.setString(5, timeGenerated);
            ps.setInt(6, statusId);
            ps.setString(7, email);

            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean update(int articleId, int statusId) throws SQLException, NamingException {
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tbl_Article SET StatusID = ? WHERE ArticleID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, statusId);
            ps.setInt(2, articleId);

            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

}
