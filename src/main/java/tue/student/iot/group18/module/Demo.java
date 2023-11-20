package tue.student.iot.group18.module;

public class Demo {
    private Integer id;
    private String code;
    private String flag;

    public Demo(String code, String flag) {
        this.code = code;
        this.flag = flag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
