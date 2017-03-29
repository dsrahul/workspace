package com.marketplace.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marketplace.offer.dto.MerchantDTO;

@Repository
public interface MerchantRepository extends JpaRepository<MerchantDTO, Long> {

}
