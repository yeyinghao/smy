package com.luman.smy.app.shared.user.command.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.luman.smy.app.shared.user.assembler.UserAssembler;
import com.luman.smy.client.dto.Response;
import com.luman.smy.client.shared.dto.UserPageQueryCmd;
import com.luman.smy.client.shared.dto.data.UserVO;
import com.luman.smy.domain.user.UserEntity;
import com.luman.smy.domain.user.gateway.UserGateway;
import com.luman.smy.infra.common.helper.RHelper;
import com.luman.smy.infra.db.user.model.User;
import com.luman.smy.infra.db.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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

    @Autowired
    private UserService userService;

    public Response<UserVO> execute(Long id) {
        UserEntity userEntity = userGateway.findById(id);
        if (Objects.isNull(userEntity)) {
        }

        return RHelper.success(UserAssembler.toValueObject(userEntity));
    }

    public List<User> list() {
        return userService.findAll();
    }


    public IPage<User>  page(UserPageQueryCmd cmd) {
        return userService.page(cmd.getPaging(), cmd.getName());
    }
}
