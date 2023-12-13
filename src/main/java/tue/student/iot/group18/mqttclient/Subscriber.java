package tue.student.iot.group18.mqttclient;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tue.student.iot.group18.gym2go.Util;
import tue.student.iot.group18.module.Request;
import tue.student.iot.group18.service.ED25519;
import tue.student.iot.group18.service.MD5;
import tue.student.iot.group18.service.RequestService;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class Subscriber {

    @Autowired
    MD5 md5;

    @Autowired
    Publisher publisher;


    @Autowired
    RequestService requestService;
    @PostConstruct
    public void subscribe() {
        try{
            IMqttClient subscriber = Client.getClient();
            subscriber.subscribe("history", (topic, msg) -> {
                System.out.println(msg);

                JsonObject jsonObject = JsonParser.parseString(msg.toString())
                        .getAsJsonObject();

                Integer requestId = jsonObject.get("requestId").getAsInt();
                String type = jsonObject.get("type").getAsString();
                if("unlock".equals(type)){
                    Request request = new Request();
                    request.setId(requestId);
                    request.setState(1);
                    request.setUnlocktime(new Date());
                    requestService.update(request);
                }

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
