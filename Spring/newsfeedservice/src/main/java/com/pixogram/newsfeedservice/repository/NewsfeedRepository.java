package com.pixogram.newsfeedservice.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pixogram.newsfeedservice.entity.NewsFeed;
import com.pixogram.newsfeedservice.model.NewsFeedModel;

@Repository
public interface NewsfeedRepository extends JpaRepository<NewsFeed,Integer>{

	List<NewsFeed> findByUserId(Integer id);
}
