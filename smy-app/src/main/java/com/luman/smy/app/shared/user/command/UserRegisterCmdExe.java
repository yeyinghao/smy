package com.luman.smy.app.shared.user.command;

import com.luman.smy.domain.user.UserEntity;
import com.luman.smy.domain.user.gateway.UserGateway;
import com.luman.smy.app.shared.user.assembler.UserAssembler;
import com.luman.smy.client.shared.dto.UserRegisterCmd;
import com.luman.smy.client.shared.dto.data.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UserAddCmdExe
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2021/1/10
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
