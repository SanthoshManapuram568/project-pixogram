package com.pixogram.mediaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pixogram.mediaservice.entity.Media;

@Repository
public interface MediaServiceRepository extends JpaRepository<Media, Integer> {
	public List<Media> findByUserId(Integer userId);
}
