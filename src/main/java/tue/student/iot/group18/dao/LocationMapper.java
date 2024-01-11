package tue.student.iot.group18.dao;

import org.apache.ibatis.annotations.Mapper;
import tue.student.iot.group18.module.Location;

import java.util.List;
import java.util.Map;

@Mapper
public interface LocationMapper {


    Location get(Integer id);
}
