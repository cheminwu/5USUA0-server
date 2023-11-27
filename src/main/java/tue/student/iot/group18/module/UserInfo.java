package tue.student.iot.group18.module;

public class UserInfo {
    private Integer userId;
    private String FirstName;
    private String LastName;
    private String Email;
    private String SerialNumber;

    public UserInfo(String firstName, String lastName, String email, String serialNumber) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        this.SerialNumber = serialNumber;
    }

    public Integer getUserId () {
        return userId;
    }

    public String getFirstName() {
        return FirstName;
    }
 
    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return Email;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }
}