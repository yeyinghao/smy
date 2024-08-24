package com.luman.smy.app.shared.user.command;

import com.luman.smy.domain.user.UserEntity;
import com.luman.smy.domain.user.gateway.UserGateway;
import com.luman.smy.app.shared.user.assembler.UserAssembler;
import com.luman.smy.client.shared.dto.UserRegisterCmd;
import com.luman.smy.client.shared.dto.data.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 用户注册CMD exe
 *
 * @author yeyinghao
 * @date 2024/08/25
 */
@Component
public class UserRegisterCmdExe {

    @Autowired
    private UserGateway userGateway;

    public UserVO execute(UserRegisterCmd cmd) {
        // check 用户名是否重复
//        if (userGateway.checkByUsername(null, cmd.getUsername())) {
//            throw new ThinkBizException(ErrorCode.B_USER_USERNAME_REPEAT);
//        }

        UserEntity userEntity = userGateway.save(UserAssembler.toEntity(cmd));

        return UserAssembler.toValueObject(userEntity);
    }

}
