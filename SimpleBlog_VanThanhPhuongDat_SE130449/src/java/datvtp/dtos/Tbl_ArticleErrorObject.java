package datvtp.dtos;

import java.io.Serializable;

public class Tbl_ArticleErrorObject implements Serializable {

    String titleError;
    String shortDescriptionError;
    String contentError;

    public String getTitleError() {
        return titleError;
    }

    public void setTitleError(String titleError) {
        this.titleError = titleError;
    }

    public String getShortDescriptionError() {
        return shortDescriptionError;
    }

    public void setShortDescriptionError(String shortDescriptionError) {
        this.shortDescriptionError = shortDescriptionError;
    }

    public String getContentError() {
        return contentError;
    }

    public void setContentError(String contentError) {
        this.contentError = contentError;
    }
    
    

}
