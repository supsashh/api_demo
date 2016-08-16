package com.agold;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;

/**
 * Model for User
 * @author Alex
 *
 */
@Entity
@Table(name="User")
public class User {
	@Id
	public Long id;

	/*
	 * Hashmap of the pictures the user voted on as well as whether
	 * they voted up or down.
	 */
	@ElementCollection
	private Map<Long,Integer> votedPics = new HashMap<Long,Integer>();
	
	User(){
	}
	
	public User(Long id){
		this.id = id;
	}
	
	public boolean hasVoted(Long CaninePicId) {
		return this.votedPics.containsKey(CaninePicId);
	}
	
	public void addVote(Long CaninePicId, int voteVal) {
		this.votedPics.put(CaninePicId, voteVal);
	}
	
	public int getVoteVal(Long CaninePicId){
		return this.votedPics.get(CaninePicId);
	}
}
