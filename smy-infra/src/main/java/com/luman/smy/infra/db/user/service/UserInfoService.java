package com.luman.smy.infra.db.user.service;

import com.luman.smy.infra.db.user.model.UserInfo;
import com.luman.smy.infra.integration.dal.CoreService;
import com.luman.smy.infra.db.user.dataobject.UserInfoDO;

public interface UserInfoService extends CoreService<UserInfo, UserInfoDO> {
}
