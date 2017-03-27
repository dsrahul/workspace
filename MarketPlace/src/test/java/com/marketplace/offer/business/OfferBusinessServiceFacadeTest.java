package com.marketplace.offer.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.marketplace.offer.dto.OfferDTO;
import com.marketplace.offer.repository.OfferRepository;

@RunWith(SpringRunner.class)
public class OfferBusinessServiceFacadeTest {

	private final SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	
	@InjectMocks
	private OfferBusinessServiceFacade offerBusinessService;
	
	@Mock
	private OfferRepository offerRepository;

	@Test
	public void testFindActiveOffersSuccess() throws Exception {			
		
		Date validFromDate = dateformat.parse("2017-01-30");
		Date validToDate = dateformat.parse("2017-02-20");		
		List<OfferDTO> intermediate = Arrays.asList(
				new OfferDTO(1L, "Title1", "Description", 1L, 1L, 1L, validFromDate, validToDate, "N"),
				new OfferDTO(2L, "Title2", "Description", 1L, 1L, 1L, validFromDate, validToDate, "N"));
		List<OfferDTO> expected = Arrays.asList(
				new OfferDTO(1L, "Title1", "Description", 1L, 1L, 1L, validFromDate, validToDate, "N"));
		when(offerRepository.findByMerchantId(1L)).thenReturn(intermediate);
		
		List<OfferDTO> actual = offerBusinessService.getActiveOffersForMerchantId(1L, 1L);
		
		assertThat(actual).isNotNull();
		assertThat(actual.get(0)).isEqualToComparingFieldByField(expected.get(0));
		assertThat(actual).extracting("id").contains(1L);
	}

	@Test
	public void testFindActiveOffersFailure() throws Exception {			
		
		Date validFromDate = dateformat.parse("2017-01-30");
		Date validToDate = dateformat.parse("2017-02-20");		
		List<OfferDTO> intermediate = Arrays.asList(
				new OfferDTO(1L, "Title1", "Description", 1L, 1L, 1L, validFromDate, validToDate, "Y"),
				new OfferDTO(2L, "Title2", "Description", 1L, 1L, 1L, validFromDate, validToDate, "Y"));
		when(offerRepository.findByMerchantId(1L)).thenReturn(intermediate);
		
		List<OfferDTO> actual = offerBusinessService.getActiveOffersForMerchantId(1L, 1L);
		
		assertThat(actual).isNotNull();
		assertThat(actual).hasSize(0);
		
	}

	@Test
	public void testFindActiveOffersException() throws Exception {			
		
		List<OfferDTO> actual = offerBusinessService.getActiveOffersForMerchantId(null, null);
		
		assertThat(actual).isNotNull();
		assertThat(actual).hasSize(0);
		
	}

}
