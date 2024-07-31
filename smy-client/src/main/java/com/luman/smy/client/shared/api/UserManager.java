package com.luman.smy.client.shared.api;

import com.luman.smy.client.dto.Response;
import com.luman.smy.client.shared.dto.UserRegisterCmd;
import com.luman.smy.client.shared.dto.data.UserVO;

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
	Response<UserVO> register(UserRegisterCmd cmd);

}
