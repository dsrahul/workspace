package com.marketplace.offer.business;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marketplace.offer.dto.OfferDTO;
import com.marketplace.offer.repository.OfferRepository;

@Component
public class OfferBusinessServiceFacade implements IOfferBusinessService {

	private Logger log = LoggerFactory.getLogger(OfferBusinessServiceFacade.class);
	@Autowired
	private OfferRepository offerRepository;
	
	@Override
	public List<OfferDTO> getActiveOffersForMerchantId(Long merchantId, final Long offerId) {
		List<OfferDTO> findByMerchantId = offerRepository.findByMerchantId(merchantId);
		
		log.debug("{}", findByMerchantId);
		if (offerId != null) {
			return findByMerchantId.stream().filter(ele -> {
				return ele.getId().longValue() == offerId.longValue() && ele.getDeleted().equals("N");
			}).collect(Collectors.toList());
		} else {
			return findByMerchantId.stream().filter(ele -> {
				return ele.getDeleted().equals("N");
			}).collect(Collectors.toList());
		}
	}

}
