package datvtp.dtos;

import java.io.Serializable;

public class Tbl_CommentErrorObject implements Serializable {

    private String commentContentError;

    public String getCommentContentError() {
        return commentContentError;
    }

    public void setCommentContentError(String commentContentError) {
        this.commentContentError = commentContentError;
    }

}
