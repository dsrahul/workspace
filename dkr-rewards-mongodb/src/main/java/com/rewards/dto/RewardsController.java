package com.rewards.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.controller.RewardsRepository;

@RestController
@RequestMapping("/reward")
public class RewardsController {
	
	@Autowired
	private RewardsRepository rewardsRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public Reward create(@RequestBody Reward mission) {
		return rewardsRepository.save(mission);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{missionId}")
	public Reward get(@PathVariable String missionId) {
		return rewardsRepository.findOne(missionId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/")
	public List<Reward> getAll(){
		return rewardsRepository.findAll();
	}
	
}
