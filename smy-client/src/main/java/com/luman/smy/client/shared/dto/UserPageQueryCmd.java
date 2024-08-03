package com.luman.smy.client.shared.dto;

import com.luman.smy.client.dto.PageQuery;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * 新增用户请求
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2021/1/8
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPageQueryCmd extends PageQuery {

	@Length(min = 3, max = 50, message = "长度不能小于3且不能大于50")
	@NotBlank(message = "不能为空")
	private String name;
}
