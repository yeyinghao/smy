package com.luman.smy.domain.user.service;

import com.luman.smy.client.dto.PageModel;
import com.luman.smy.client.dto.Paging;
import com.luman.smy.domain.dal.CoreService;
import com.luman.smy.domain.user.model.User;

/**
 * 用户服务
 *
 * @author yeyinghao
 * @date 2024/08/26
 */
public interface UserService extends CoreService<User> {
	PageModel<User> page(Paging paging, String name);
}
