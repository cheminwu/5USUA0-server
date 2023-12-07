package tue.student.iot.group18.mqttclient;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.UUID;

public class Client {


    private static IMqttClient client = null;

    public static IMqttClient getClient(){

        try {
            if(client == null || !client.isConnected()){
                String id = UUID.randomUUID().toString();
                client = new MqttClient("tcp://192.236.146.36:1883",id);

                MqttConnectOptions options = new MqttConnectOptions();
                options.setAutomaticReconnect(true);
                options.setCleanSession(true);
                options.setConnectionTimeout(10);
                options.setUserName("guest_mqtt");
                options.setPassword("gym2go18".toCharArray());
                client.connect(options);
            }
            return client;
        } catch (MqttException e) {
            e.printStackTrace();
        }


        return null;

    }
}
