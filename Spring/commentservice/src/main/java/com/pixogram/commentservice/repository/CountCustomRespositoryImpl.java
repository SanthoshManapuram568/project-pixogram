package com.pixogram.commentservice.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.pixogram.commentservice.model.CommentsNumberModel;



@Repository
public class CountCustomRespositoryImpl implements CountCustomRespository {

	@Autowired
	private EntityManager entityManager;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public CommentsNumberModel findCountById(Integer id) {
		TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(*) FROM Comments c WHERE c.mediaId=:id",Long.class);
		query.setParameter("id", id);
		Long count = (long) query.getSingleResult();
		logger.info("in count of custom long :"+count);
		Integer numberOfComments = Math.toIntExact(count);
		logger.info("in count of custom int :"+numberOfComments);
		CommentsNumberModel data = new CommentsNumberModel(numberOfComments);
		return data;
	}

}
