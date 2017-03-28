package com.marketplace.config;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.marketplace.offer.dto.DTOTest;

public class StandardAPIErrorTest extends DTOTest<StandardAPIError> {

	@Override
	protected List<StandardAPIError> getlOfInstance() {
		return Arrays.asList(new StandardAPIError(), new StandardAPIError(HttpStatus.ACCEPTED, new Date(), "message", "error"));
	}

}
