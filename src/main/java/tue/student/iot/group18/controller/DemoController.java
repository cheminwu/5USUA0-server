package tue.student.iot.group18.controller;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tue.student.iot.group18.gym2go.Util;
import tue.student.iot.group18.module.Demo;
import tue.student.iot.group18.service.DemoService;

import java.util.HashMap;
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

        String returnStr = user_id.toString() + ", " + locker_id.toString() + ", " + flag.toString() + ", " + Util.currentTime();

        return Util.getMD5Str(returnStr);

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
            value = "/user_login.post",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public String userLogin(@RequestBody Map params) {
        Object code = params.get("code");
        Object flag = params.get("flag");
        demoService.save(new Demo((String)code, (String)flag));
        System.out.println("info was delivered");
        return JSON.toJSONString(response_0);
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
