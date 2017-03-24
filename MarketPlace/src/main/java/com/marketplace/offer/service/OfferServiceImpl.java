package com.marketplace.offer.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marketplace.offer.dto.OfferDTO;
import com.marketplace.offer.repository.OfferRepository;

@Component
public class OfferServiceImpl implements IOfferService {
	private static final Logger log = LoggerFactory.getLogger(OfferServiceImpl.class);
			

	@Autowired
	private OfferRepository offerRepository;
	
	/**
	 * Service method to add a single Offer
	 */
	@Override
	public OfferDTO addOffer(final OfferDTO anOfferDTO) {
		//assert anOfferDTO != null;
		return offerRepository.save(anOfferDTO);
	}

	/**
	 * Service method to find a list of Offers
	 */
	@Override
	public List<OfferDTO> findOffersByMerchantId(Long merchantId) {
		return offerRepository.findByMerchantId(merchantId);
	}

	/**
	 * 
	 * Service method to add a list of Offers
	 */
	@Override
	public List<OfferDTO> bulkAdd(List<OfferDTO> lOfOfferDTOs) {		
		return offerRepository.save(lOfOfferDTOs);
	}

	@Override
	public List<OfferDTO> findMerchantOffersByOfferId(final Long merchantId, Long offerId) {
		return offerRepository.findByMerchantIdAndId(merchantId, offerId);
	}


	/**
	 * Service method to delete an Offer
	 * A Runtime exception is thrown by the repository if the expected offer does not exists
	 */
	@Override
	public void deleteOfferByIdAndMerchantId(Long merchantId, Long offerId) {		
		offerRepository.deleteByMerchantIdAndId(merchantId, offerId);		
	}
	

}
