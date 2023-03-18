package com.delores.security.security;

import com.delores.base.utils.EncryptUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author William
 * @date 5/13/21 1:41 AM
 * @description
 */
@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    public CustomPasswordEncoder() {
        this(-1);
    }
    public CustomPasswordEncoder(int strength) {
    }

    @Override
    public String encode(CharSequence charSequence) {
        return EncryptUtil.encrypt(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String encodedPassword) {
        return encodedPassword.equals(EncryptUtil.encrypt(charSequence.toString()));
    }
}
