package datvtp.dtos;

import java.io.Serializable;

public class Tbl_UserErrorObject implements Serializable {

    private String emailError;
    private String passwordError;
    private String confirmError;
    private String nameError;
    private String codeEmailError;

    public Tbl_UserErrorObject() {
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getCodeEmailError() {
        return codeEmailError;
    }

    public void setCodeEmailError(String codeEmailError) {
        this.codeEmailError = codeEmailError;
    }

}
