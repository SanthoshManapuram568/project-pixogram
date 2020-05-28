package com.pixogram.blockservice.model;

import java.util.List;

import com.pixogram.blockservice.entity.BlockedUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlockData {
List<BlockedUser> blockUser;
}
