package tue.student.iot.group18.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SHAPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {

        return DigestUtils.sha256Hex(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {


        String a = DigestUtils.sha256Hex(charSequence.toString());
        String b = s;
        return a.equals(b);
    }
}
