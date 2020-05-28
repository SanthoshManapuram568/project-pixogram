package com.pixogram.actionservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActionDataModel {
	private Integer id;
	private Integer mediaId;
	private Integer userId;
	private Boolean status;
}
