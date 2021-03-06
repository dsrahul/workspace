package com.marketplace.offer.service;

import java.util.List;

import com.marketplace.offer.dto.OfferDTO;
import com.marketplace.offer.exception.OfferNotUpdatedException;

public interface IOfferService {

	OfferDTO addOffer(OfferDTO anOfferDTO);

	OfferDTO updateOffer(OfferDTO anOfferDTO);

	List<OfferDTO> findOffersByMerchantId(Long merchantId);

	List<OfferDTO> bulkAdd(List<OfferDTO> lOfOfferDTOs);

	List<OfferDTO> findMerchantOffersByOfferId(Long merchantId, Long offerId);

	void deleteOfferByIdAndMerchantId(Long merchantId, Long offerId) throws OfferNotUpdatedException;

}
