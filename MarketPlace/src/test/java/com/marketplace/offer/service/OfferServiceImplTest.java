package com.marketplace.offer.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.marketplace.offer.dto.OfferDTO;
import com.marketplace.offer.repository.OfferRepository;
@RunWith(MockitoJUnitRunner.class)
public class OfferServiceImplTest {

	@InjectMocks
	private OfferServiceImpl service = new OfferServiceImpl();
	
	@Mock
	private OfferRepository offerRepository;
	
	@Before
	public void setUp() throws Exception {
	}

	@Ignore
	@Test(expected=AssertionError.class)
	public void testAddOfferAssertionFailure() {
		when(service.addOffer(null)).thenThrow(new AssertionError());		
	}

	@Test
	public void testAddOfferSuccess() throws Exception {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

		Date validFromDate = dateformat.parse("2017-01-30");
		Date validToDate = dateformat.parse("2017-02-20");
		OfferDTO offer = new OfferDTO( "Title", "Description", 1L, 1L, 1L, validFromDate, validToDate);
		OfferDTO expected = new OfferDTO(1L, "Title", "Description", 1L, 1L, 1L, validFromDate, validToDate);
		when(offerRepository.save(offer)).thenReturn(expected);
		
		OfferDTO actual = service.addOffer(offer);
		assertThat(actual, is(expected));
		verify(offerRepository, times(1)).save(offer);
		verifyNoMoreInteractions(offerRepository);
		
		//when(offerRepository.findAll()).thenReturn(Arrays.asList(new OfferDTO(1L, "Title", "Description", 1L, 1L, validFromDate, validToDate)));
	}
	
	@Test
	public void testDeleteByOfferID() throws Exception {
		service.deleteOfferByIdAndMerchantId(1L, 1L);
		assertTrue("No exception thrown", true);
		verify(offerRepository, times(1)).deleteByMerchantIdAndId(1L, 1L);
		verifyNoMoreInteractions(offerRepository);
	}
	@Ignore
	@Test(expected=RuntimeException.class)	
	public void testDeleteByOfferIDThrowException() throws Exception {
		doThrow(new RuntimeException()).when(offerRepository).delete(2L);		
		service.deleteOfferByIdAndMerchantId(2L, 2L);
		verify(offerRepository, times(1)).deleteByMerchantIdAndId(2L, 2L);
		verifyNoMoreInteractions(offerRepository);
	}

}
