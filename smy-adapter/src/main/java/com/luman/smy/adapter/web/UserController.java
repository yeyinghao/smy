package com.luman.smy.adapter.web;

import cn.dev33.satoken.annotation.SaIgnore;
import com.luman.smy.client.dto.PageModel;
import com.luman.smy.client.dto.Response;
import com.luman.smy.client.shared.api.UserManager;
import com.luman.smy.client.shared.dto.UserPageQueryCmd;
import com.luman.smy.client.shared.dto.UserRegisterCmd;
import com.luman.smy.client.shared.dto.data.UserVO;
import com.luman.smy.infra.common.helper.RHelper;
import com.luman.smy.infra.common.log.rest.RestLog;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户控制器
 *
 * @author yeyinghao
 * @date 2024/08/19
 */
@RestController
@RequestMapping("user")
@RestLog
@RequiredArgsConstructor
public class UserController {

	/**
	 * 用户服务
	 */
	private final UserManager userService;

	/**
	 * 注册
	 *
	 * @param cmd cmd
	 * @return {@link Response }<{@link UserVO }>
	 */
	@SaIgnore
	@PostMapping(value = "/register")
	public Response<UserVO> register(@RequestBody UserRegisterCmd cmd) {
		return RHelper.success(userService.register(cmd));
	}

	/**
	 * 列表
	 *
	 * @return {@link Response }<{@link List }<{@link UserVO }>>
	 */
	@SaIgnore
	@PostMapping(value = "/list")
	public Response<List<UserVO>> list() {
		return RHelper.success(userService.list());
	}

	/**
	 * 页面
	 *
	 * @param cmd cmd
	 * @return {@link Response }<{@link PageModel }<{@link UserVO }>>
	 */
	@SaIgnore
	@PostMapping(value = "/page")
	public Response<PageModel<UserVO>> page(@RequestBody UserPageQueryCmd cmd) {
		return RHelper.success(userService.page(cmd));
	}

}

