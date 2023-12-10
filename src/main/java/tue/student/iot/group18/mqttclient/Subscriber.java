package tue.student.iot.group18.mqttclient;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tue.student.iot.group18.gym2go.Util;
import tue.student.iot.group18.service.ED25519;
import tue.student.iot.group18.service.MD5;

import javax.annotation.PostConstruct;

@Service
public class Subscriber {

    @Autowired
    MD5 md5;

    @Autowired
    Publisher publisher;

    @PostConstruct
    public void subscribe() {
        try{
            IMqttClient subscriber = Client.getClient();
            subscriber.subscribe("history", (topic, msg) -> {
                System.out.println(msg);
            });
            subscriber.subscribe("start", (topic, msg) -> {
                System.out.println(msg);

                String key = md5.getKey();

                long currentSeconds = Math.round(((double)Util.currentTime())/1000);

                System.out.println("post key: " + key);
                System.out.println("post time: " + currentSeconds);
                publisher.publish("key", key);
                publisher.publish("time", currentSeconds + "*");
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
