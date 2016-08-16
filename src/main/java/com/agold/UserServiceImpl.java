package com.agold;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	/**
	 * Populates the UserRepository with a few users
	 */
	@PostConstruct
	@Transactional
	public void populate(){
		User u = new User(Long.valueOf(1234));
		userRepo.saveAndFlush(u);
		
		u = new User(Long.valueOf(122));
		userRepo.saveAndFlush(u);
		
		u = new User(Long.valueOf(42));
		userRepo.saveAndFlush(u);
	}
	
	@Override
	@Transactional(readOnly=true)
	public boolean isDupVote(Long userId, Long dogPicId){
		User u = userRepo.findOne(userId);
		return u.hasVoted(dogPicId);
	}

	@Override
	@Transactional(readOnly=true)
	public int getOldVote(Long userId, Long dogPicId){
		User u = userRepo.findOne(userId);
		return u.getVoteVal(dogPicId);
	}

	@Override
	@Transactional
	public void update(Long userId, Long dogPicId, int voteVal){
		User u = userRepo.findOne(userId);
		u.addVote(dogPicId, voteVal);
		userRepo.saveAndFlush(u);
	}

}
