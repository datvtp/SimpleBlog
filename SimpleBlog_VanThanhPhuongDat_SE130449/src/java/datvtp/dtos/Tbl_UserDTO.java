package datvtp.dtos;

import java.io.Serializable;

public class Tbl_UserDTO implements Serializable {

    private String email;
    private String name;
    private String password;
    private int roleId;
    private int statusId;

    public Tbl_UserDTO() {
    }

    public Tbl_UserDTO(String email, String name, String password, int roleId, int statusId) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.roleId = roleId;
        this.statusId = statusId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

}
