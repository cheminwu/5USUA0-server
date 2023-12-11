package tue.student.iot.group18.controller;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tue.student.iot.group18.gym2go.Util;
import tue.student.iot.group18.module.Demo;
import tue.student.iot.group18.module.Request;
import tue.student.iot.group18.module.UserInfo;
import tue.student.iot.group18.service.DemoService;
import tue.student.iot.group18.service.MD5;
import tue.student.iot.group18.service.RequestService;
import tue.student.iot.group18.service.UserInfoService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "demo")
public class DemoController {
    DemoController(){
        response_0.put("code", 0);
        response_0.put("message", "command was delivered");
    }

    @Autowired
    DemoService demoService;

    @Autowired
    MD5 md5;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    RequestService requestService;

    final Map response_0 = new HashMap();


    @PostMapping(
            value = "/save.do",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public String save(@RequestBody Map params) {
        Object code = params.get("code");
        Object flag = params.get("flag");
        demoService.save(new Demo((String)code, (String)flag));
        System.out.println("command was delivered");
        return JSON.toJSONString(response_0);
    }

    @PostMapping(
            value = "/update.do",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public String update(@RequestBody Map params) {
        Object code = params.get("code");
        Object flag = params.get("flag");
        demoService.update(new Demo((String)code, (String)flag));
        System.out.println("command was delivered");
        return JSON.toJSONString(response_0);
    }

    @GetMapping(
            value = "/flag.get",
            produces = "text/plain")
    @ResponseBody
    public String get(
            @RequestParam String code) {

        Demo demo = demoService.get(code.trim());
        Map response = new HashMap();
        response.put("code", 0);
        response.put("data", demo);
        return JSON.toJSONString(response);
    }



    @GetMapping(
            value = "/qr_request.get",
            produces = "text/plain")
    @ResponseBody
    public String qr(
            @RequestParam Integer user_id, @RequestParam Integer locker_id, @RequestParam Integer flag) {

        long currentSeconds = Math.round(((double)Util.currentTime())/1000);

//        System.out.println(currentTime);
//        System.out.println(currentSeconds);
//        System.out.println(ret);
        Request request = new Request();
        request.setUser_id(user_id);
        request.setLocker_id(locker_id);
        request.setFlag(flag);
//        request.setQrcode(ret);
        request.setDatetime(new Date());

        requestService.save(request);

        String info = request.getId() + "," + locker_id.toString() + "," + flag + "," + currentSeconds;
        String sign = md5.encrypt(info);



        String message[] = new String[6];
        message[0] = "G2G";
        message[1] = String.valueOf(request.getId());
        message[2] = String.valueOf(locker_id);
        message[3] = String.valueOf(flag);
        message[4] = String.valueOf(currentSeconds);
        message[5] = sign;

        String ret = String.join(",",message) + "*";

        Request request2 = new Request();
        request2.setId(request.getId());
        request2.setQrcode(ret);
        requestService.update(request2);

        return String.join(",",message) + "*";

    }


    @GetMapping(
            value = "/user_history_request.get",
            produces = "text/plain")
    @ResponseBody
    public String userHistory(
            @RequestParam Integer user_id) {

        String returnStr = "football,001,renting,2023-11-11 08:30,-|football,001,returned,2023-11-01 17:32,2023-11-08 13:32|football,001,returned,2023-10-01 17:32,2023-10-08 13:32";

        return returnStr;

    }



    @PostMapping(
            value = "/unlock.post",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public String unlock(@RequestBody Map params) {
        Object id = params.get("transactionId");
        Object state = params.get("state");

        Request request = new Request();
        request.setId((Integer)id);
        request.setState((Integer)state);
        request.setUnlocktime(new Date());

        requestService.update(request);
        return "0";
    }


    @PostMapping(
            value = "/user_login.post",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public String userLogin(@RequestBody Map params) {
        Object serialNumber = params.get("serialNumber");
        Object passwordSHA = params.get("passwordSHA");
        Map map = new HashMap<>();
        map.put("SerialNumber", (String)serialNumber);
        map.put("passwordSHA", (String)passwordSHA);
        List<UserInfo> users = userInfoService.list(map);
        if(users != null && users.size() > 0){
            return "0";
        }else{
            return "-1";
        }
    }


    @PostMapping(
            value = "/user_register.post",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public String userRegister(@RequestBody Map params) {
        Object serialNumber = params.get("serialNumber");
        Object firstName = params.get("firstName");
        Object lastName = params.get("lastName");
        Object email = params.get("email");
        Object passwordSHA = params.get("passwordSHA");

        Map map = new HashMap<>();
        map.put("SerialNumber", (String)serialNumber);

        List<UserInfo> users = userInfoService.list(map);
        if(users != null && users.size() > 0){
            return "-1";
        }else{


            UserInfo userInfo = new UserInfo();
            userInfo.setFirst_name((String)firstName);
            userInfo.setLast_Name((String)lastName);
            userInfo.setEmail((String)email);
            userInfo.setSerial_number((String)serialNumber);
            userInfo.setPassword_SHA((String)passwordSHA);
            userInfoService.save(userInfo);
            System.out.println("info was delivered");
            return "0";
        }
    }



    @PostMapping(
            value = "/defect_report.post",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public String defectReport(@RequestBody Map params) {
        Object code = params.get("code");
        Object flag = params.get("flag");
        demoService.save(new Demo((String)code, (String)flag));
        System.out.println("report was delivered");
        return JSON.toJSONString(response_0);
    }
}
