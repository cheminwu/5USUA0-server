package tue.student.iot.group18.service;

import org.springframework.stereotype.Service;
import tue.student.iot.group18.dao.RequestMapper;
import tue.student.iot.group18.module.Demo;
import tue.student.iot.group18.module.Request;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RequestService {

    @Resource
    RequestMapper requestMapper;

    public void save(Request request){
        requestMapper.save(request);
    }

    public List<Request> list(Map map){
        return requestMapper.find(map);
    }

    public Integer count(Map map){
        return requestMapper.count(map);
    }

    public void update(Request request){
        requestMapper.update(request);
    }

    public Request get(Integer id){
        return requestMapper.get(id);
    }


}
