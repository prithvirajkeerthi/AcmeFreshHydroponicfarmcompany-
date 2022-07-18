package com.acmefresh.service;


import java.time.LocalDateTime;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.acmefresh.exception.UserException;
import com.acmefresh.model.User;
import com.acmefresh.model.UserSession;
import com.acmefresh.repository.SessionDao;
import com.acmefresh.repository.UserDaoInterface;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDaoInterface uDao;
	
	@Autowired
	private SessionDao sDao;


	@Override
	public User saveUser(User user) throws UserException{
		
		Long mobile = user.getMobile();
		User existingUser = uDao.findByMobile(mobile);
		if(String.valueOf(mobile).length()==10) {
			if(existingUser==null) {
				return uDao.save(user);
			}
			else {
				throw new UserException("User already exists with Mobile No. : "+mobile);
			}
		}
		else {
			throw new UserException("Mobile number must be 10 digits");
		}
		
		
	}
	
	@Override
	public UserSession saveLogin(User user) throws UserException{
		
		Long mobile = user.getMobile();
		String password = user.getPassword();
		User existingUser = uDao.findByMobile(mobile);
		if(existingUser==null) {
			throw new UserException("Invalid user");
		}
		else {
			String existingPassword = existingUser.getPassword();
			if(password.equals(existingPassword)) {
				String key = getRandomString(6);
				LocalDateTime localDateTime = LocalDateTime.now();
				UserSession userSession = new UserSession(existingUser.getUserId(),key,localDateTime);
				sDao.save(userSession);
				return userSession;
			}
			else {
				throw new UserException("Wrong password");
			}
		}
		
	}

	private String getRandomString(int n) {
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"0123456789"+"abcdefghijklmnopqrstuvwxyz"+"0123456789";
		String randomString = "";
		
		for(int i=0; i<n; i++) {
			int index = (int) ((Math.random()*str.length()));
			randomString = randomString + str.charAt(index);
		}
		return randomString;
	}
	
	@Override
	public User updateUser(User user, String key)throws UserException{
		
		Optional<UserSession> opt = sDao.findByUuid(key);
		
		if(opt.isPresent()) {
			UserSession existingSession = opt.get();
			Integer existingUserId = existingSession.getUserId();
			Integer userId = user.getUserId();
			if(existingUserId==userId) {
				return uDao.save(user);
			}
			else {
				throw new UserException("Invalid User");
			}
		}
		else {
			throw new UserException("Invalid User");
		}
	}
	
	@Override
	public String deleteSession(String key) throws UserException{
		
		Optional<UserSession> opt = sDao.findByUuid(key);
		if(opt.isPresent()) {
			UserSession session = opt.get();
			sDao.deleteById(session.getId());
			return "Logged out successfully";
		}
		else {
			throw new UserException("Invalid User");
		}
	}
}
