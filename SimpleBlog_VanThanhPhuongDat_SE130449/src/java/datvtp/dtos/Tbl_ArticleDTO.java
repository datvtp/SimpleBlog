package datvtp.dtos;

import java.io.Serializable;

public class Tbl_ArticleDTO implements Serializable {

    private int articleId;
    private String title;
    private String shortDescription;
    private String articleContent;
    private String author;
    private String timeGenerated;
    private int statusId;
    private String email;

    public Tbl_ArticleDTO() {
    }

    public Tbl_ArticleDTO(int articleId, String title, String shortDescription, String articleContent, String author, String timeGenerated) {
        this.articleId = articleId;
        this.title = title;
        this.shortDescription = shortDescription;
        this.articleContent = articleContent;
        this.author = author;
        this.timeGenerated = timeGenerated;
    }

    public Tbl_ArticleDTO(int articleId, String title, String shortDescription, String articleContent, String author, String timeGenerated, int statusId) {
        this.articleId = articleId;
        this.title = title;
        this.shortDescription = shortDescription;
        this.articleContent = articleContent;
        this.author = author;
        this.timeGenerated = timeGenerated;
        this.statusId = statusId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
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

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
