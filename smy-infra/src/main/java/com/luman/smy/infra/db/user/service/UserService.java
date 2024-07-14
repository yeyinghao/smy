package com.luman.smy.infra.db.user.service;

import com.luman.smy.infra.db.user.model.User;
import com.luman.smy.integration.dal.CoreService;
import com.luman.smy.infra.db.user.dataobject.UserDO;

public interface UserService extends CoreService<User, UserDO> {
}
