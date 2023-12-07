package tue.student.iot.group18.mqttclient;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
public class Publisher {

    public void publish(String topic, String content) {
        try{
            IMqttClient publisher = Client.getClient();
            MqttMessage message = new MqttMessage(content.getBytes());
            publisher.publish(topic, message);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @PostConstruct
    public void test() {
        try{
            IMqttClient publisher = Client.getClient();
            MqttMessage message = new MqttMessage("hello2".getBytes());
            publisher.publish("test", message);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
