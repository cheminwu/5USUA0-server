package tue.student.iot.group18.dao;

import org.apache.ibatis.annotations.Mapper;
import tue.student.iot.group18.module.Request;

import java.util.List;
import java.util.Map;

@Mapper
public interface RequestMapper {

    void save(Request request);

    List<Request> find(Map map);

    Integer count(Map map);

    void update(Request request);


    Request get(Integer id);
}
