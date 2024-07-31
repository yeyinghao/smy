package com.luman.smy.app.shared.user.service;

import com.luman.smy.app.shared.user.command.UserRegisterCmdExe;
import com.luman.smy.app.shared.user.command.query.UserInfoQueryExe;
import com.luman.smy.client.dto.Response;
import com.luman.smy.client.shared.api.UserManager;
import com.luman.smy.client.shared.dto.UserRegisterCmd;
import com.luman.smy.client.shared.dto.data.UserVO;
import com.luman.smy.infra.common.template.RTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户相关
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2021/1/8
 */
@Service
public class UserManagerImpl implements UserManager {

	/**
	 * xxxExe 避免 Service 膨胀利器
	 */
	@Autowired
	private UserRegisterCmdExe userRegisterCmdExe;

	@Autowired
	private UserInfoQueryExe userInfoQueryExe;

	@Override
	public Response<UserVO> register(UserRegisterCmd cmd) {
		return RTemplate.excute(() -> {
			return userRegisterCmdExe.execute(cmd);
		}, cmd);
	}


}
