package com.jasper.HouseAPI.repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.jasper.HouseAPI.domain.HouseResource;


/**
 * Repository class for <code>HouseResource </code> domain objects
 * All method names are compliant with Spring Data naming conventions so this interface can easily be extended for Spring Data
 * @author jinglunzhou
 *
 */

@Repository
public interface HouseResourceRepository extends CrudRepository<HouseResource, Long> {
	
	/**
	 * Find one single House Resource by it's ID
	 * @param id The House Resouce's ID that user wants to find
	 * @return If exists, return the House Resource, otherwise, returns null
	 */
	HouseResource findHouseResourceById(Long id);
	
	/**
	 * List all house resources stored in database
	 */
	@Override
	Iterable<HouseResource> findAll();

}
