package com.luman.smy.client.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListModel<T> extends DTO {
	private Collection<T> pageInfo;
}
