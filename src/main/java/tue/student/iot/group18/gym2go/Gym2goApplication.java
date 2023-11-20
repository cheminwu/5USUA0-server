package tue.student.iot.group18.gym2go;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@ComponentScan(basePackages = {
        "tue.student.iot.group18.service",
        "tue.student.iot.group18.controller",
})
@MapperScan("tue.student.iot.group18.dao")
@SpringBootApplication
public class Gym2goApplication {

    public static void main(String[] args) {
        SpringApplication.run(Gym2goApplication.class, args);
    }

}
