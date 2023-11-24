package tue.student.iot.group18.service;

import org.springframework.stereotype.Service;
import tue.student.iot.group18.dao.*;
import tue.student.iot.group18.module.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class DemoService {

    @Resource
    DemoMapper demoMapper;

    public void save(Demo demo){
        demoMapper.save(demo);
    }

    public Demo get(String code){
        return demoMapper.get(code);
    }

    public void update(Demo demo){
        demoMapper.update(demo);
    }

    public List<Demo> list(Map map){
        return demoMapper.find(map);
    }
}
