package com.pixogram.userservice.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
@Transactional
@Repository
public class UserNameRepository {
	@PersistenceContext
	private EntityManager em;
}
