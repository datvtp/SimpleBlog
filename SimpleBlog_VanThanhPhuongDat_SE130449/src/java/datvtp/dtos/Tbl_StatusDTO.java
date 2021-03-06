package datvtp.dtos;

import java.io.Serializable;

public class Tbl_StatusDTO implements Serializable {

    private int statusId;
    private String statusName;

    public Tbl_StatusDTO() {
    }

    public Tbl_StatusDTO(int statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}
