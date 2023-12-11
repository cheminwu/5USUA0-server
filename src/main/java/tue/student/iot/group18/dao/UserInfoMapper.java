package tue.student.iot.group18.dao;

import org.apache.ibatis.annotations.Mapper;
import tue.student.iot.group18.module.Demo;
import tue.student.iot.group18.module.UserInfo;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserInfoMapper {

    void save(UserInfo userInfo);

    UserInfo get(String SerialNumber);

    void update(UserInfo userInfo);

    List<UserInfo> find(Map map);
    Integer count(Map map);
}
