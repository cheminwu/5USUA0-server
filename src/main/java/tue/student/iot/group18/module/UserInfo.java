package tue.student.iot.group18.module;

public class UserInfo {
    private Integer User_id;
    private String First_name;
    private String Last_Name;
    private String Email;
    private String Serial_number;
    private String password_SHA;


    public Integer getUser_id() {
        return User_id;
    }

    public void setUser_id(Integer user_id) {
        User_id = user_id;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSerial_number() {
        return Serial_number;
    }

    public void setSerial_number(String serial_number) {
        Serial_number = serial_number;
    }

    public String getPassword_SHA() {
        return password_SHA;
    }

    public void setPassword_SHA(String password_SHA) {
        this.password_SHA = password_SHA;
    }
}