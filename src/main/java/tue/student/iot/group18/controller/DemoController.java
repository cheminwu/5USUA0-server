package tue.student.iot.group18.controller;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
}
