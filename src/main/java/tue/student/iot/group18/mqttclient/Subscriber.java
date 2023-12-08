package tue.student.iot.group18.mqttclient;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tue.student.iot.group18.service.ED25519;

import javax.annotation.PostConstruct;

@Service
public class Subscriber {

    @Autowired
    ED25519 ed25519;

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

                String hexPublicKey = ed25519.getHexPublicKey();
                System.out.println("post public key: " + hexPublicKey);
                publisher.publish("publicKey", hexPublicKey);
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
