package com.agold;

import java.util.Collection;

public interface CaninePicService {
	/**
	 * Calls CaninePicRepository.findByBreedOrderByFavCountDesc(String breed)
	 */
	Collection<CaninePic> getByBreed(String breed);
	
	/**
	 * Calls CaninePicRepository.getAllGroupsByBreed()
	 */
	Collection<CaninePic> getAllGroupByBreed();
	
	/**
	 * Returns a Canine pic for a given CaninePicId
	 * @param CaninePicId
	 * @return CaninePic object
	 */
	CaninePic getCaninePic(Long CaninePicId);
	
	/**
	 * Updates the picture's favorite count.
	 * @param CaninePicId
	 * @param voteVal
	 * @return new favorite count
	 */
	Long updateFavCount(Long CaninePicId, int voteVal);
	
	/**
	 * Updates the picture's favorite count when the user voted 
	 * on the picture already
	 * @param CaninePicId
	 * @param voteVal
	 * @param oldVote
	 * @return new favorite count
	 */
	Long updateFavCount(Long CaninePicId, int voteVal, int oldVote);
}
