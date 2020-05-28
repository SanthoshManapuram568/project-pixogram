package com.pixogram.blockservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity 
@Table(name = "blockeduser")
public class BlockedUser {
	private Integer userId;
	private Integer blockedUserId;
}
