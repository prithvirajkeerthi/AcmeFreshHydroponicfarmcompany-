package com.acmefresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acmefresh.model.UserSession;

import java.util.Optional;

public interface SessionDao extends JpaRepository<UserSession, Integer> {

	public Optional<UserSession> findByUserId(Integer userId);
	public Optional<UserSession> findByUuid(String uuid);
	public String deleteByUuid(String uuid);
}
