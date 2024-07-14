package com.luman.smy.infra.db.user.mapper;

import com.luman.smy.infra.db.user.dataobject.UserInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * User Info Mapper
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2021/10/30
 */
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfoDO> {
}
