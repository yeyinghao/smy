package com.luman.smy.app.user.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.luman.smy.client.user.api.UserManager;
import com.luman.smy.app.user.command.UserRegisterCmdExe;
import com.luman.smy.app.user.command.query.UserInfoQueryExe;
import com.luman.smy.client.user.dto.UserRegisterCmd;
import com.luman.smy.client.user.dto.data.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户相关
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2021/1/8
 */
@Service
@CatchAndLog
public class UserManagerImpl implements UserManager {

    /**
     * xxxExe 避免 Service 膨胀利器
     */
    @Autowired
    private UserRegisterCmdExe userRegisterCmdExe;
    @Autowired
    private UserInfoQueryExe userInfoQueryExe;

    @Override
    public UserVO register(UserRegisterCmd cmd) {
        return userRegisterCmdExe.execute(cmd);
    }


}
