package com.agold;

import javax.persistence.*;

/**
 * Model for Canine pictures
 * @author Alex
 *
 */
@Entity
@Table(name = "CaninePic")
public class CaninePic {
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * Canine breed in the picture
	 */
	@Column(name = "breed")
	public String breed;
	
	/**
	 * URL of the Canine picture
	 */
	@Column(name = "url")
	public String url;
	
	/**
	 * Description of the Canine picture
	 */
	@Column(name = "description")
	public String description;
	
	/**
	 * Number of favorites a picute received
	 */
	@Column(name = "favCount")
	public Long favCount;
	
	CaninePic(){
		
	}
	
	public CaninePic(String breed, String url){
		this.breed = breed;
		this.url = url;
		this.favCount = Long.valueOf(0);
		this.description = "None";
	}
	
	public Long getId(){
		return id;
	}
	
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getFavCount() {
		return favCount;
	}
	public void setFavCount(Long favCount) {
		this.favCount = favCount;
	}
}
