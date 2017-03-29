package com.marketplace.offer.service;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marketplace.offer.business.IOfferBusinessService;
import com.marketplace.offer.dto.OfferDTO;
import com.marketplace.offer.exception.OfferNotUpdatedException;
import com.marketplace.offer.repository.OfferRepository;

@Component
public class OfferServiceImpl implements IOfferService {
	private static final Logger log = LoggerFactory.getLogger(OfferServiceImpl.class);

	@Autowired
	private IOfferBusinessService offerBusinessService;  

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
		return offerBusinessService.getActiveOffersForMerchantId(merchantId, null);
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
		return offerBusinessService.getActiveOffersForMerchantId(merchantId, offerId);
	}


	/**
	 * Service method to delete an Offer
	 * A Runtime exception is thrown by the repository if the expected offer does not exists
	 * @throws OfferNotUpdatedException 
	 */
	@Override
	public void deleteOfferByIdAndMerchantId(Long merchantId, Long offerId) throws OfferNotUpdatedException {		
		int deleteOffer = offerRepository.deleteOffer(merchantId, offerId);
		if (deleteOffer == 0) {
			throw new OfferNotUpdatedException("Offer was already deleted or does not exist");
		}
	}	


	@Override
	public OfferDTO updateOffer(OfferDTO anOfferDTO) {
		
		/*OfferDTO source = offerRepository.findOne(anOfferDTO.getId());
		
		String[] nullPropertyNames = getNullPropertyNames(anOfferDTO);
		
		BeanUtils.copyProperties(anOfferDTO, source, nullPropertyNames);*/
		
		return offerRepository.save(anOfferDTO);
	}

	public static String[] getNullPropertyNames(Object source) {
	    final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
	    return Stream.of(wrappedSource.getPropertyDescriptors())
	            .map(FeatureDescriptor::getName)
	            .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
	            .toArray(String[]::new);
	}
}
