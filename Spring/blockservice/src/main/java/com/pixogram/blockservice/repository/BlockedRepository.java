package com.pixogram.blockservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pixogram.blockservice.entity.BlockedUser;

public interface BlockedRepository extends JpaRepository<BlockedUser, Integer> {

}
