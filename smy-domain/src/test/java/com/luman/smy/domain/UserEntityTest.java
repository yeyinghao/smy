package com.luman.smy.domain;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

public class UserEntityTest {

    @Test
    public void passwordTest() {
        String password = "TestPassword@12345";
        System.out.println("加密: " + DigestUtils.sha3_256Hex(password));
        System.out.println("加密2: " + DigestUtils.sha3_256Hex(password));
    }
}
