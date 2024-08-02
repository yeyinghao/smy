package com.luman.smy.client.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class Paging implements Serializable {

	private Long pageSize;

	private Long pageIndex;

}
