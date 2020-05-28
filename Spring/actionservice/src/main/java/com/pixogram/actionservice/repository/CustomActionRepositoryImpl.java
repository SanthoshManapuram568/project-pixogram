package com.pixogram.actionservice.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pixogram.actionservice.model.CountOfActionsModel;

@Repository
public class CustomActionRepositoryImpl implements CustomActionRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PersistenceContext
	private EntityManager entityManager;


	
	@Override
	public CountOfActionsModel getLikesAndDislikes(Integer id) {
												 					
		TypedQuery<Long> likesQuery = entityManager.createQuery("SELECT COUNT(*) FROM Action a WHERE a.mediaId=:id AND a.status=true",Long.class);
		
		likesQuery.setParameter("id", id);
		
		TypedQuery<Long> disLikesQuery = entityManager.createQuery("SELECT COUNT(*) FROM Action a WHERE a.mediaId=:id AND a.status=false",Long.class);
		
		disLikesQuery.setParameter("id", id);
		
		Long count = (long) likesQuery.getSingleResult();
		logger.info(" likes count :" +count);
		Long count1 = (long) disLikesQuery.getSingleResult();
		logger.info("dis likes count :" +count1);
		Integer likes = Math.toIntExact(count);
		Integer disLikes  = Math.toIntExact(count1);
		CountOfActionsModel result = new CountOfActionsModel(likes, disLikes);
		return result;
	}
	
	
}
