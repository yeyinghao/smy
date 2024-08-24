package com.luman.smy.client.shared.api;

import com.luman.smy.client.dto.ListModel;
import com.luman.smy.client.dto.PageModel;
import com.luman.smy.client.dto.Response;
import com.luman.smy.client.shared.dto.UserPageQueryCmd;
import com.luman.smy.client.shared.dto.UserRegisterCmd;
import com.luman.smy.client.shared.dto.data.UserVO;


/**
 * 用户Manager
 *
 * @author yeyinghao
 * @date 2024/08/25
 */
public interface UserManager {

	/**
	 * 注册用户
	 *
	 * @param cmd 用户注册请求
	 * @return {@link Response }<{@link UserVO }>
	 */
	Response<UserVO> register(UserRegisterCmd cmd);

	Response<ListModel<UserVO>> list();

	Response<PageModel<UserVO>> page(UserPageQueryCmd cmd);
}
