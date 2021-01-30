package com.jasper.HouseAPI.repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.jasper.HouseAPI.domain.HouseResource;


/**
 * Repository class for <code> </code>
 * @author jinglunzhou
 *
 */

@Repository
public interface HouseResourceRepository extends CrudRepository<HouseResource, Long> {
	
	HouseResource findHouseResourceById(Long id);
	
	@Override
	Iterable<HouseResource> findAll();

}
