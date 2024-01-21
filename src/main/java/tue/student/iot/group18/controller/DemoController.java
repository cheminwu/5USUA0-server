package tue.student.iot.group18.controller;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tue.student.iot.group18.gym2go.Util;
import tue.student.iot.group18.module.*;
import tue.student.iot.group18.service.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
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


    @Autowired
    LocationService locationService;

    @Autowired
    LockerInfoService lockerInfoService;

    final Map response_0 = new HashMap();


    Map translate = new HashMap(){
        {
            put("Football", "Voetbal");
            put("Basketball", "Basketbal");
            put("Volleyball", "Volleybal");
            put("Hockey equipment", "Hockeyuitrusting");
            put("Frisbee", "Frisbee");
            put("Walking sticks", "Wandelstokken");
            put("Dog toys", "Hondenspeelgoed");

            put("renting", "huren");
            put("returned", "teruggekeerd");
        }
    };


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

        System.out.println(info);

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
            @RequestParam Integer user_id, @RequestParam Integer dutch) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        String responseStr = "";
        Map param = new HashMap();
        param.put("userId", user_id);
        param.put("unlocktimeIsNotNull", "0");

        List<Request> requests = requestService.list(param);
        for(int i = 0;i< requests.size();i++){
            Request request = requests.get(i);
            if(dutch == 0){
                responseStr += request.getItem() + ", " + request.getLocker_id() + ", " + (request.getFlag() == 0 ? "renting": "returned") + ", " + format.format(request.getUnlocktime()) + "\t";
            }else{
                responseStr += translate.get(request.getItem()) + ", " + request.getLocker_id() + ", " + translate.get((request.getFlag() == 0 ? "renting": "returned")) + ", " + format.format(request.getUnlocktime()) + "\t";
            }

        }
        return responseStr.substring(0, responseStr.length() - 1);
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
            UserInfo userInfo = users.get(0);
            return userInfo.getUser_id().toString();
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


    @GetMapping(
            value = "/locker_list.get",
            produces = "text/plain")
    @ResponseBody
    public String locker_list(
            @RequestParam Integer user_id,
            @RequestParam Integer flag,
            @RequestParam Integer site,
            @RequestParam Integer dutch) {
        String responseStr =  "";
        Map param = new HashMap();
        if(flag == 0){
        } else {
            param.put("user", user_id);
            param.put("Location", site);
        }
        List<LockerInfo> lockers =  lockerInfoService.list(param);
        for(int i = 0;i< lockers.size();i++){
            LockerInfo locker = lockers.get(i);

            if(dutch == 0){
                responseStr += locker.getId() + ", " + locker.getStatus() + ", " + locker.getItem() + "\t";
            }else{
                responseStr += locker.getId() + ", " + locker.getStatus() + ", " + translate.get(locker.getItem()) + "\t";
            }
        }
        if(!"".equals(responseStr)){
            return responseStr.substring(0, responseStr.length() - 1);
        }else{
            return "";
        }
    }



    @GetMapping(
            value = "/location.get",
            produces = "text/plain")
    @ResponseBody
    public String location(
            @RequestParam Integer id, @RequestParam Integer dutch) {
        String responseStr =  "";

        Location location = locationService.get(id);
        String items = "";
        for(int i = 0;i< location.getLockers().size();i++){
            LockerInfo locker = location.getLockers().get(i);
            if(dutch==0){
                items += locker.getItem() + ", ";
            }else{
                items += translate.get(locker.getItem()) + ", ";
            }
        }
        if(!"".equals(items)){
            items = items.substring(0, items.length() - 2);
        }

        responseStr += location.getAddress() + "| " + items;
        return responseStr;
    }
}
