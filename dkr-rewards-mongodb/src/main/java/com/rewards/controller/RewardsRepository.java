package com.rewards.controller;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rewards.dto.Reward;

public interface RewardsRepository extends MongoRepository<Reward, String>{

}
