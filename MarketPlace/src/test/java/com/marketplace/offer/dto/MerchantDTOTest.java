package com.marketplace.offer.dto;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;

public class MerchantDTOTest extends DTOTest<MerchantDTO> {

	@Before
	public void setUp() throws Exception {
	}
	protected MerchantDTO getInstance() {
		// TODO Auto-generated method stub
		return new MerchantDTO();
	}

	@Override
	protected List<MerchantDTO> getlOfInstance() {
		List<MerchantDTO> asList = Arrays.asList(getInstance(), new MerchantDTO(1L, "code","name", "currency"));
		return asList;
	}

}
