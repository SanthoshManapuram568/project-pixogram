package com.pixogram.commentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pixogram.commentservice.entity.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer>{

}
