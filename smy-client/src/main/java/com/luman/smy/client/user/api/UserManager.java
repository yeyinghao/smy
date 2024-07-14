package com.luman.smy.client.user.api;

import com.luman.smy.client.user.dto.UserRegisterCmd;
import com.luman.smy.client.user.dto.data.UserVO;

/**
 * 用户相关
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2021/1/8
 */
public interface UserManager {

    /**
     * 注册用户
     *
     * @param cmd 用户注册请求
     * @return Response
     */
    UserVO register(UserRegisterCmd cmd);

}
