package tue.student.iot.group18.module;

import java.util.List;

public class Location {
    private Integer id;
    private String address;


    private List<LockerInfo> lockers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<LockerInfo> getLockers() {
        return lockers;
    }

    public void setLockers(List<LockerInfo> lockers) {
        this.lockers = lockers;
    }
}
