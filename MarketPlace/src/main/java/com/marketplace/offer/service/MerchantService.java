package com.marketplace.offer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marketplace.offer.dto.MerchantDTO;
import com.marketplace.offer.repository.MerchantRepository;

@Component
public class MerchantService implements IMerchantService {

	@Autowired
	private MerchantRepository merchantRepository;
	
	@Override
	public List<MerchantDTO> getAllMerchants() {
		
		return merchantRepository.findAll();
	}

}
