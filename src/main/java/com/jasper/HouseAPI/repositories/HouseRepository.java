package com.jasper.HouseAPI.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jasper.HouseAPI.domain.House;

@Repository
public interface HouseRepository extends CrudRepository<House, Long> {
	
	@Override
	Iterable<House> findAllById(Iterable<Long> iterable);

}
