package com.agold;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Data Access Layer for the CanineCaninePic model.
 * @author Alex
 *
 */
@Transactional
public interface CaninePicRepository extends JpaRepository<CaninePic, Long>{

	/**
	 * Find CaninePics of a particular breed, order by favorite count
	 * descending.
	 * @param breed
	 * @return Collection<CaninePic>
	 */
	Collection<CaninePic> findByBreedOrderByFavCountDesc(String breed);
	
	/**
	 * Select all s and order first by breed and then by
	 * favorite count descending.
	 * @return Collection<CaninePic>
	 */
	@Query("select d from CaninePic d order by d.breed, d.favCount desc")
	Collection<CaninePic> getAllGroupByBreed();
}
