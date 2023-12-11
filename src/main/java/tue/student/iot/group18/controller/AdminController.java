package tue.student.iot.group18.controller;

import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tue.student.iot.group18.module.LockerInfo;
import tue.student.iot.group18.module.Request;
import tue.student.iot.group18.module.UserInfo;
import tue.student.iot.group18.service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping(value = "admin")
public class AdminController {
    AdminController(){
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
    LockerInfoService lockerInfoService;


    final Map response_0 = new HashMap();


    @CrossOrigin(methods = RequestMethod.GET)
    @GetMapping(
            value = "/request_list.get",
            produces = "application/json")
    @ResponseBody
    public String request_list(
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Integer serialnumber,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Integer lockerId,
            @RequestParam(required = false) Integer flag) {

        Map param = new HashMap();
        param.put("serialnumber", serialnumber);
        param.put("firstname", firstName);
        param.put("lastname", lastName);
        param.put("locker_id", lockerId);
        param.put("flag", flag);


        PageHelper.offsetPage(offset, limit);
        List<Request> requests = requestService.list(param);
        int count = requestService.count(param);
        Map response = new HashMap();
        response.put("total", count);
        response.put("rows", requests);
        return JSON.toJSONString(response);
    }


    @CrossOrigin(methods = RequestMethod.GET)
    @GetMapping(
            value = "/user_list.get",
            produces = "application/json")
    @ResponseBody
    public String user_list(
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) String serialnumber,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {

        Map param = new HashMap();
        param.put("serialNumber", serialnumber);
        param.put("firstName", firstName);
        param.put("lastName", lastName);

        PageHelper.offsetPage(offset, limit);
        List<UserInfo> users = userInfoService.list(param);

        Integer count = userInfoService.count(param);

        Map response = new HashMap();
        response.put("total", count);
        response.put("rows", users);
        return JSON.toJSONString(response);
    }


    @CrossOrigin(methods = RequestMethod.GET)
    @GetMapping(
            value = "/locker_list.get",
            produces = "application/json")
    @ResponseBody
    public String locker_list(
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) String item) {

        Map param = new HashMap();
        param.put("item", item);

        PageHelper.offsetPage(offset, limit);
        List<LockerInfo> lockers = lockerInfoService.list(param);

        Integer count = lockerInfoService.count(param);

        Map response = new HashMap();
        response.put("total", count);
        response.put("rows", lockers);
        return JSON.toJSONString(response);
    }
}
