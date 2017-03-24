package com.missions.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.missions.dto.Mission;

public interface MissionRepository extends MongoRepository<Mission, String>{

}
