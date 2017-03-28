package com.marketplace.offer.business;

import java.time.LocalDate;
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
		List<OfferDTO> lOfOffers = null;
		if (offerId == null) {
			lOfOffers = offerRepository.findByMerchantIdAndDeleted(merchantId, "N");
		} else {
			lOfOffers = offerRepository.findByMerchantIdAndIdAndDeleted(merchantId, offerId, "N");
		}

		LocalDate currentLocalDate = dateTimeManager.getCurrentLocalDate();
		List<OfferDTO> collect = lOfOffers.stream()
				.filter(ele -> {					
					LocalDate validTo = ele.getValidTo();
					if (validTo == null) return true;
					LocalDate validFrom = ele.getValidFrom();
					boolean fromDateValid = validFrom.isEqual(currentLocalDate);
					if (fromDateValid) return true;
					boolean toDateValid = validTo.isEqual(currentLocalDate);
					if (toDateValid) return true;
					boolean isInBetween = currentLocalDate.isAfter(validFrom)
												&& currentLocalDate.isBefore(validTo);
					return isInBetween;
				})
				.collect(Collectors.toList());
		return collect;
	}

}
