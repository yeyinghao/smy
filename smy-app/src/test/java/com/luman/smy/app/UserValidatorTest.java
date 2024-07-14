package com.luman.smy.app;

import com.luman.smy.client.user.dto.UserRegisterCmd;
import org.junit.jupiter.api.Test;

public class UserValidatorTest {

    @Test
    public void testValidation() {
        new UserRegisterCmd("amos", "");
    }
}
