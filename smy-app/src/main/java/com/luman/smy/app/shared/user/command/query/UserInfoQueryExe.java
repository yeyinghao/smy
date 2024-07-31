package com.luman.smy.app.shared.user.command.query;

import com.luman.smy.app.shared.user.assembler.UserAssembler;
import com.luman.smy.client.dto.Response;
import com.luman.smy.client.shared.dto.data.UserVO;
import com.luman.smy.domain.user.UserEntity;
import com.luman.smy.domain.user.gateway.UserGateway;
import com.luman.smy.infra.common.helper.RHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 用户信息查询
 *
 * @author daoyuan
 * @date 2021/2/14 23:27
 */
@Component
public class UserInfoQueryExe {

    @Autowired
    private UserGateway userGateway;

    public Response<UserVO> execute(Long id) {
        UserEntity userEntity = userGateway.findById(id);
        if (Objects.isNull(userEntity)) {
        }

        return RHelper.of(UserAssembler.toValueObject(userEntity));
    }

}
