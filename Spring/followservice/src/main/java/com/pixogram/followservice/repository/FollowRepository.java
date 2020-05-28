package com.pixogram.followservice.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pixogram.followservice.entity.Follow;

@Repository
@Transactional
public class FollowRepository{
	@PersistenceContext
	private EntityManager entityManager;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public boolean checkFollowing(Integer mineId, Integer otherId) {
		// select * from follow where userId=otherId and followerId=mineId
		TypedQuery<Follow> query = 
				this.entityManager.createQuery("select f from Follow f where f.userId=:otherId and f.followerId=:mineId", Follow.class);
		query.setParameter("mineId", mineId);
		query.setParameter("otherId", otherId);
		
		return query.getResultList().size() > 0;
		
		
	}
	
	
	public boolean follow(Follow follow) {
		this.entityManager.persist(follow);
		return true;
	}
	
	public boolean unFollow(Integer mineId, Integer otherId) {
		logger.info("mine id : "+mineId);
		logger.info("other id : "+otherId);
		
		Follow follow =this.entityManager.find(Follow.class,new Follow(otherId,mineId));
		this.entityManager.remove(follow);
		return false;
	}
	
}
