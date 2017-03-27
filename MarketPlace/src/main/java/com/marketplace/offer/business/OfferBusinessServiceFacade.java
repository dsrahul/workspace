package com.marketplace.offer.business;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marketplace.offer.dto.OfferDTO;
import com.marketplace.offer.repository.OfferRepository;
import com.marketplace.util.date.IDateTimeManager;

@Component
public class OfferBusinessServiceFacade implements IOfferBusinessService {

	private Logger log = LoggerFactory.getLogger(OfferBusinessServiceFacade.class);
	@Autowired
	private OfferRepository offerRepository;
	@Autowired
	private IDateTimeManager dateTimeManager;
	
	@Override
	public List<OfferDTO> getActiveOffersForMerchantId(Long merchantId, final Long offerId) {
		List<OfferDTO> findByMerchantId = null;
		if (offerId == null) {
			findByMerchantId = offerRepository.findByMerchantIdAndDeleted(merchantId, "N");
		} else {
			findByMerchantId = offerRepository.findByMerchantIdAndIdAndDeleted(merchantId, offerId, "N");
		}
		
		log.debug("{}", findByMerchantId);

		LocalDate currentLocalDate = dateTimeManager.getCurrentLocalDate();
		List<OfferDTO> collect = findByMerchantId.stream().filter(ele -> {
			boolean equal = ele.getValidFrom().isEqual(currentLocalDate);
			boolean equal2 = ele.getValidTo().isEqual(currentLocalDate);
			boolean b = currentLocalDate.isAfter(ele.getValidFrom())
					&& currentLocalDate.isBefore(ele.getValidTo());
			return equal
					|| equal2 
					|| b;
		}).collect(Collectors.toList());
		return collect;
	}

}
