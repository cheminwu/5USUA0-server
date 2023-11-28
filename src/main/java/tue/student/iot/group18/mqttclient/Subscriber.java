package tue.student.iot.group18.mqttclient;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
public class Subscriber {

    @PostConstruct
    public void start() {
        try{
            String subscriberId = UUID.randomUUID().toString();
            IMqttClient subscriber = new MqttClient("tcp://192.236.146.36:1883",subscriberId);

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            options.setUserName("guest_mqtt");
            options.setPassword("gym2go18".toCharArray());
            subscriber.connect(options);


            subscriber.subscribe("public_key", (topic, msg) -> {
                System.out.println(msg);
            });
            subscriber.subscribe("history", (topic, msg) -> {
                System.out.println(msg);
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
