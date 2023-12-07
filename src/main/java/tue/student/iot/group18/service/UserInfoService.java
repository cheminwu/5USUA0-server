package tue.student.iot.group18.service;

import org.springframework.stereotype.Service;
import tue.student.iot.group18.dao.DemoMapper;
import tue.student.iot.group18.dao.UserInfoMapper;
import tue.student.iot.group18.module.Demo;
import tue.student.iot.group18.module.UserInfo;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoService {

    @Resource
    UserInfoMapper userInfoMapper;

    public void save(UserInfo userInfo){
        userInfoMapper.save(userInfo);
    }

    public UserInfo get(String serialNumber){
        return userInfoMapper.get(serialNumber);
    }

    public void update(UserInfo userInfo){
        userInfoMapper.update(userInfo);
    }

    public List<UserInfo> list(Map map){
        return userInfoMapper.find(map);
    }
}
