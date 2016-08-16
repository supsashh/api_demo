package com.agold;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main REST Controller for the project
 * @author Alex
 *
 */
@RestController
@RequestMapping("/Caninepics")
public class CaninePicController {
	
	@Autowired
	private CaninePicService CaninePicServ;
	@Autowired
	private UserService uServ;

	/**
	 * List all of the available Canine pictures by breed
	 * @return JSON of all the CaninePic objects grouped by breed
	 * and ordered by greatest number of favorites.
	 */
	@RequestMapping(value ="/", method = RequestMethod.GET)
	public @ResponseBody Collection<CaninePic> getAllPicsGroupByBreed(){
		return CaninePicServ.getAllGroupByBreed();
	}
	
	/**
	 * List al of the available Canine pictures of a particular breed
	 * @param breed
	 * @return JSON of all the CaninePic objects of a particular breed
	 * and ordered by greatest number of favorites.
	 */
	@RequestMapping(value ="/breedpics/{breed}", method = RequestMethod.GET)
	public @ResponseBody Collection<CaninePic> getPicsByBreed(@PathVariable String breed){
		return this.CaninePicServ.getByBreed(breed);
	}
	
	/**
	 * The details associated with a Canine picture
	 * @param CaninePicId
	 * @return CaninePic JSON
	 */
	@RequestMapping(value="/{CaninePicId}", method = RequestMethod.GET)
	public @ResponseBody CaninePic getCaninePic(@PathVariable Long CaninePicId){
		return this.CaninePicServ.getCaninePic(CaninePicId);
	}
	
	/**
	 * POST Request to vote up and down a Canine picture, in a reddit-esque 
	 * style.
	 * @param userId
	 * @param CaninePicId
	 * @param voteVal either 1, 0, -1
	 * @return The picture's new favorite count.
	 */
	@RequestMapping(value="/{userId}/{CaninePicId}", method = RequestMethod.POST)
	public ResponseEntity<String> votePic(@PathVariable Long userId, @PathVariable Long CaninePicId, @RequestBody int voteVal) {
		Long favCount;
		
		/*
		 * If the user has not voted before, add new value to total
		 */
		if(!this.uServ.isDupVote(userId, CaninePicId)){
			favCount = this.CaninePicServ.updateFavCount(CaninePicId, voteVal);
		}
		/*
		 * Else, take old vote value into account
		 */
		else{
			favCount = this.CaninePicServ.updateFavCount(CaninePicId, voteVal, this.uServ.getOldVote(userId, CaninePicId));
		}
		/*
		 * Acknowledge that the user voted on this picture
		 */
		this.uServ.update(userId, CaninePicId,voteVal);
		
		/*
		 * Respond with the new favorite count.
		 */
		return new ResponseEntity<String>(favCount.toString(),HttpStatus.OK);
	}
	
}
