package tue.student.iot.group18.service;

import org.springframework.stereotype.Service;

import tue.student.iot.group18.dao.LocationMapper;

import tue.student.iot.group18.dao.LockerInfoMapper;
import tue.student.iot.group18.module.Location;
import tue.student.iot.group18.module.LockerInfo;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LocationService {

    @Resource
    LocationMapper locationMapper;

    @Resource
    LockerInfoMapper lockerInfoMapper;

    public Location get(Integer id){
        Location location = locationMapper.get(id);
        Map param = new HashMap();
        param.put("Location", id);
        List<LockerInfo> lockers = lockerInfoMapper.find(param);
        location.setLockers(lockers);
        return location;

    }
}
