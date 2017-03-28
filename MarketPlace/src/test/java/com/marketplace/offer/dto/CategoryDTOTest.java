package com.marketplace.offer.dto;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;

public class CategoryDTOTest extends DTOTest<CategoryDTO> {

	@Before
	public void setUp() throws Exception {
	}

	protected CategoryDTO getInstance() {
		// TODO Auto-generated method stub
		return new CategoryDTO();
	}

	@Override
	protected List<CategoryDTO> getlOfInstance() {
		List<CategoryDTO> asList = Arrays.asList(getInstance(), new CategoryDTO(1L, "name", "descirption"));
		return asList;
	}
	
	
}