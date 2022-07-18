package com.acmefresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acmefresh.model.User;
@Repository
public interface UserDaoInterface extends JpaRepository<User, Integer>{
	
	public User findByMobile(Long mobile);
	
	public User getByUserId(Integer userId);

}
