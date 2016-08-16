package com.agold;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CaninePicServiceImpl implements CaninePicService {

	@Autowired
	private CaninePicRepository CaninePicRepo;
	
	/**
	 * Populates the CaninePic repository with files
	 * from resources/data
	 * TODO Programmatically load from each file
	 * based on the BreedEnum. 
	 * @throws URISyntaxException
	 */
	@PostConstruct
	@Transactional
	public void populate() throws URISyntaxException{
		String fileName = "/data/labrador.txt";
	
		try (Stream<String> stream = Files.lines(Paths.get(getClass().getResource(fileName).toURI()))) {
			// Java 8 lambda expression
			stream.forEach(s -> CaninePicRepo.saveAndFlush(new CaninePic(BreedEnum.labrador.toString(),s)));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fileName = "/data/pug.txt";
		
		try (Stream<String> stream = Files.lines(Paths.get(getClass().getResource(fileName).toURI()))) {

			stream.forEach(s -> CaninePicRepo.saveAndFlush(new CaninePic(BreedEnum.pug.toString(),s)));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fileName = "/data/retriever.txt";
		
		try (Stream<String> stream = Files.lines(Paths.get(getClass().getResource(fileName).toURI()))) {
			
			stream.forEach(s -> CaninePicRepo.saveAndFlush(new CaninePic(BreedEnum.retriever.toString(),s)));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fileName = "/data/yorkie.txt";
		
		try (Stream<String> stream = Files.lines(Paths.get(getClass().getResource(fileName).toURI()))) {
			
			stream.forEach(s -> CaninePicRepo.saveAndFlush(new CaninePic(BreedEnum.yorkie.toString(),s)));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public Collection<CaninePic> getByBreed(String breed){
		return CaninePicRepo.findByBreedOrderByFavCountDesc(breed);
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<CaninePic> getAllGroupByBreed(){
		return CaninePicRepo.getAllGroupByBreed();
	}

	@Override
	@Transactional(readOnly=true)
	public CaninePic getCaninePic(Long CaninePicId){
		CaninePic dp = CaninePicRepo.findOne(CaninePicId);
		return dp;
	}

	@Override
	@Transactional
	public Long updateFavCount(Long CaninePicId, int voteVal){
		CaninePic dp = CaninePicRepo.findOne(CaninePicId);
		long favCount = dp.getFavCount() + Long.valueOf(voteVal);
		System.out.println("new favCount: "+ favCount);
		dp.setFavCount(favCount);
		CaninePicRepo.saveAndFlush(dp);
		return dp.getFavCount();
	}

	@Override
	@Transactional
	public Long updateFavCount(Long CaninePicId, int voteVal, int oldVote) {
		CaninePic dp = CaninePicRepo.findOne(CaninePicId);
		long favCount = dp.getFavCount() + Long.valueOf(voteVal) - Long.valueOf(oldVote);
		System.out.println("new favCount: "+ favCount);
		dp.setFavCount(favCount);
		CaninePicRepo.saveAndFlush(dp);
		return dp.getFavCount();
	}

}
