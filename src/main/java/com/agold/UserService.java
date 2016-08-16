package com.agold;

public interface UserService {
	/**
	 * Checks if the user has already voted on the picture before
	 * @param userId
	 * @param dogPicId
	 * @return
	 */
	boolean isDupVote(Long userId, Long dogPicId);
	
	/**
	 * Gets the user's old vote on the picture
	 * @param userId
	 * @param dogPicId
	 * @return
	 */
	int getOldVote(Long userId, Long dogPicId);
	
	/**
	 * Updates the user's votedPics HashMap 
	 * @param userId
	 * @param dogPicId
	 * @param voteVal
	 */
	void update(Long userId, Long dogPicId, int voteVal);
}
