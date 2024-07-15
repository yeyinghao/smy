package com.luman.smy.app.facade;

import com.luman.smy.client.facade.api.UserFacade;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(interfaceClass = UserFacade.class, version = "1.0.0")
public class UserFacadeImpl implements UserFacade {
}
