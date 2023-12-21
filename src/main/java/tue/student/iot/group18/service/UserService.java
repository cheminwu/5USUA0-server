package tue.student.iot.group18.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tue.student.iot.group18.dao.UserInfoMapper;
import tue.student.iot.group18.module.User;
import tue.student.iot.group18.module.UserInfo;

import javax.annotation.Resource;


@Service
public class UserService implements UserDetailsService {

    @Resource
    UserInfoMapper userInfoMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserInfo userInfo = userInfoMapper.get(s);
        if(userInfo == null){
            throw new UsernameNotFoundException(s);
        }
        if(!("ADMIN".equals(userInfo.getRole()) || "ROOT".equals(userInfo.getRole()))){
            throw new UsernameNotFoundException(s);
        }
        User user = new User();
        user.setUserInfo(userInfo);
        return user;
    }
}
