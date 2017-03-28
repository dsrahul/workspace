package com.marketplace.offer.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;

public class OfferDTOTest extends DTOTest<OfferDTO> {

	@Before
	public void setUp() throws Exception {
	}
	protected OfferDTO getInstance() {
		// TODO Auto-generated method stub
		return new OfferDTO();
	}

	@Override
	protected List<OfferDTO> getlOfInstance() {
		
		LocalDate validFrom = LocalDate.of(2017, 1, 30);
		LocalDate validTo = LocalDate.of(2017, 2, 20);
		List<OfferDTO> asList = Arrays.asList(new OfferDTO(),
				new OfferDTO(1L, "title", "descption", 1L, 1L, 1L, validFrom, validTo, "Y"),
				new OfferDTO("title", "descption", 1L, 1L, 1L, validFrom, validTo, "Y"),
				new OfferDTO(1L, "title", "description", 1L, new OfferTypeDTO(1L, "type"), 1L,  new CategoryDTO(1L, "name", "descirption"), 1L, new MerchantDTO(1L, "code","name", "currency"), validFrom, validTo, "N"));
		return asList;
	}

}
