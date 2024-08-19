package com.luman.smy.start;

import com.luman.smy.client.shared.api.UserManager;
import com.luman.smy.client.shared.dto.UserRegisterCmd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.UUID;

/**
 * UserServiceTest
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2021/1/9
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor
@SpringBootTest
public class UserServiceTest {

    private final UserManager userService;

    private static final String username = "AMOS_" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    private static final String password = "666666";
    private static Long id = null;

    @BeforeEach
    public void setUp() {
        log.info("start username is [" + username + "]");
    }

    @Test
    public void user_1_Register() {
        //1.prepare
        UserRegisterCmd registerCmd = new UserRegisterCmd(username, password);
        registerCmd.setName("amos.wang");
        registerCmd.setPhoneNo("18907378888");
        registerCmd.setGender(1);
        registerCmd.setBirthday(LocalDate.of(2000, 1, 1));
        registerCmd.setDescription("https://amos.wang/");

        //2.execute
//        UserVO register = userService.register(registerCmd);

        //3.assert
//        Assertions.assertTrue(Objects.nonNull(register.getId()));
//
//        id = register.getId();
    }

}
