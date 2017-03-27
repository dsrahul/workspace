package com.marketplace.offer.business;

import java.util.List;

import com.marketplace.offer.dto.OfferDTO;

public interface IOfferBusinessService {

	List<OfferDTO> getActiveOffersForMerchantId(Long merchantId, Long offerId);

	
	
}
