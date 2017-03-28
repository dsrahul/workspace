package com.marketplace.offer.dto;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;

public class OfferTypeDTOTest extends DTOTest<OfferTypeDTO> {

	@Before
	public void setUp() throws Exception {
	}

	protected OfferTypeDTO getInstance() {
		return new OfferTypeDTO();
	}

	@Override
	protected List<OfferTypeDTO> getlOfInstance() {
		
		List<OfferTypeDTO> asList = Arrays.asList(getInstance(), 
				new OfferTypeDTO(1L, "type"));
		return asList;
	}

}
