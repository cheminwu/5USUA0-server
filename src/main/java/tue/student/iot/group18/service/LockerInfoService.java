package tue.student.iot.group18.service;

import org.springframework.stereotype.Service;
import tue.student.iot.group18.dao.LockerInfoMapper;
import tue.student.iot.group18.module.LockerInfo;


import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

@Service
public class LockerInfoService {

    @Resource
    LockerInfoMapper lockerInfoMapper;

    public void save(LockerInfo lockerInfo){
        lockerInfoMapper.save(lockerInfo);
    }

    public LockerInfo get(Integer id){
        return lockerInfoMapper.get(id);
    }

    public void update(LockerInfo lockerInfo){
        lockerInfoMapper.update(lockerInfo);
    }

    public List<LockerInfo> list(Map map){
        return lockerInfoMapper.find(map);
    }

    public Integer count(Map map){
        return lockerInfoMapper.count(map);
    }
}
