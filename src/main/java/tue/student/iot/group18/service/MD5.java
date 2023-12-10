package tue.student.iot.group18.service;


import org.springframework.stereotype.Service;
import tue.student.iot.group18.gym2go.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class MD5 {
    private String key;

    public String getKey(){
        if(key == null || "".equals(key)){
            key = Util.getRandomString(16);
        }
        System.out.println(key);
        return key;
    }


    public String encrypt(String message){
        MessageDigest md = null;
        try {

            md = MessageDigest.getInstance("MD5");
            byte[] result = md.digest((message + getKey()).getBytes());
            return Util.byteArrayToHexString(result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
