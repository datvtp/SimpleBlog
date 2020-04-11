package datvtp.dtos;

import java.io.Serializable;

public class Tbl_CommentDTO implements Serializable {

    private int commentId;
    private String commentContent;
    private String author;
    private String timeGenerated;
    private int articleId;
    private String email;

    public Tbl_CommentDTO() {
    }

    public Tbl_CommentDTO(int commentId, String commentContent, String author, String timeGenerated, int articleId, String email) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.author = author;
        this.timeGenerated = timeGenerated;
        this.articleId = articleId;
        this.email = email;
    }

    public Tbl_CommentDTO(int commentId, String commentContent, String author, String timeGenerated) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.author = author;
        this.timeGenerated = timeGenerated;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTimeGenerated() {
        return timeGenerated;
    }

    public void setTimeGenerated(String timeGenerated) {
        this.timeGenerated = timeGenerated;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
