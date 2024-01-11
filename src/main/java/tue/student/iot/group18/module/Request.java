package tue.student.iot.group18.module;

import java.util.Date;

public class Request {
    private Integer id;
    private Integer user_id;
    private String serialnumber;

    private String item;
    private String firstname;
    private String lastname;
    private Integer locker_id;
    private Integer flag;
    private Date datetime;
    private String qrcode;

    private Integer state;
    private Date unlocktime;

//    public Request(Integer userId, Integer lockerId, Integer flag, Date datetime, String qrcode) {
//        this.userId = userId;
//        this.lockerId = lockerId;
//        this.flag = flag;
//        this.datetime = datetime;
//        this.qrcode = qrcode;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public Integer getLocker_id() {
        return locker_id;
    }

    public void setLocker_id(Integer locker_id) {
        this.locker_id = locker_id;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getUnlocktime() {
        return unlocktime;
    }

    public void setUnlocktime(Date unlocktime) {
        this.unlocktime = unlocktime;
    }
}
