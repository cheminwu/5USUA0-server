package tue.student.iot.group18.dao;

import org.apache.ibatis.annotations.Mapper;
import tue.student.iot.group18.module.LockerInfo;

import java.util.List;
import java.util.Map;

@Mapper
public interface LockerInfoMapper {

    void save(LockerInfo lockerInfo);

    LockerInfo get(Integer id);

    void update(LockerInfo lockerInfo);

    List<LockerInfo> find(Map map);
    Integer count(Map map);
}
