package com.luman.smy.app.shared.user.service;

import com.luman.smy.app.shared.user.command.UserRegisterCmdExe;
import com.luman.smy.app.shared.user.command.query.UserInfoQueryExe;
import com.luman.smy.client.dto.PageModel;
import com.luman.smy.client.shared.api.UserManager;
import com.luman.smy.client.shared.dto.UserPageQueryCmd;
import com.luman.smy.client.shared.dto.UserRegisterCmd;
import com.luman.smy.client.shared.dto.data.UserVO;
import com.luman.smy.infra.common.constant.LoggerConstant;
import com.luman.smy.infra.common.helper.PageHelper;
import com.luman.smy.infra.common.log.log.Logged;
import com.luman.smy.infra.domain.user.mapper.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 用户Manager实现
 *
 * @author yeyinghao
 * @date 2024/08/25
 */
@Logged(topic = LoggerConstant.FACADE_LOG)
@Service
@RequiredArgsConstructor
public class UserManagerImpl implements UserManager {

	/**
	 * xxxExe 避免 Service 膨胀利器
	 */
	private final UserRegisterCmdExe userRegisterCmdExe;

	private final UserInfoQueryExe userInfoQueryExe;

	@Override
	public UserVO register(UserRegisterCmd cmd) {
		return userRegisterCmdExe.execute(cmd);
	}

	@Override
	public List<UserVO> list() {
		return userInfoQueryExe.list().stream().map(this::convertUser).toList();
	}

	private UserVO convertUser(User user) {
		UserVO userVO = new UserVO();
		userVO.setUsername(user.getUsername());
		userVO.setName(user.getName());
		return userVO;
	}

	@Override
	public PageModel<UserVO> page(UserPageQueryCmd cmd) {
		return PageHelper.buildPage(userInfoQueryExe.page(cmd).convert(this::convertUser));
	}
}
