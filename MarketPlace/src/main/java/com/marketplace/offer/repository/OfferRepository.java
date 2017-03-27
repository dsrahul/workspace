package com.marketplace.offer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marketplace.offer.dto.OfferDTO;

@Repository
public interface OfferRepository extends JpaRepository<OfferDTO, Long> {

	
	List<OfferDTO> findByMerchantIdAndId(Long merchantId, Long id);

	List<OfferDTO> findByMerchantId(Long merchantId);

	@Modifying(clearAutomatically = true)
	@Query("update offer TOFF set TOFF.deleted = 'Y' where TOFF.id = :offerId and TOFF.merchantId = :merchantId")
	int deleteOffer(@Param("merchantId") Long merchantId, @Param("offerId") Long offerId);

	List<OfferDTO> findByMerchantIdAndDeleted(Long merchantId, String deleted);

	List<OfferDTO> findByMerchantIdAndIdAndDeleted(Long merchantId, Long offerId, String string);

}
