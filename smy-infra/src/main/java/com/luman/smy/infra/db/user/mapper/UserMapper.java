package com.luman.smy.infra.db.user.mapper;

import com.luman.smy.infra.db.user.dataobject.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * User Mapper
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2021/1/8
 */
@Repository
public interface UserMapper extends BaseMapper<UserDO> {
}
