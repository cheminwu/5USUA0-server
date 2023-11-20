package tue.student.iot.group18.dao;

import org.apache.ibatis.annotations.Mapper;
import tue.student.iot.group18.module.Demo;

import java.util.List;
import java.util.Map;

@Mapper
public interface DemoMapper {

    void save(Demo demo);

    Demo get(String code);

    void update(Demo demo);

    List<Demo> find(Map demo);
}
