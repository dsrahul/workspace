package com.marketplace.offer.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.marketplace.offer.dto.OfferDTO;
import com.marketplace.offer.exception.OfferNotUpdatedException;
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

	@Test
	public void testAddOfferSuccess() throws Exception {
		
		LocalDate validFromDate = LocalDate.of(2017, 1, 30); //dateformat.parse("2017-01-30");
		LocalDate validToDate = LocalDate.of(2017, 2, 20); //dateformat.parse("2017-02-20");
		OfferDTO offer = new OfferDTO( "Title", "Description", 1L, 1L, 1L, validFromDate, validToDate, "Y");
		OfferDTO expected = new OfferDTO(1L, "Title", "Description", 1L, 1L, 1L, validFromDate, validToDate, "Y");
		when(offerRepository.save(offer)).thenReturn(expected);
		
		OfferDTO actual = service.addOffer(offer);
		assertThat(actual, is(expected));
		verify(offerRepository, times(1)).save(offer);
		verifyNoMoreInteractions(offerRepository);
		
		//when(offerRepository.findAll()).thenReturn(Arrays.asList(new OfferDTO(1L, "Title", "Description", 1L, 1L, validFromDate, validToDate)));
	}	
	
	@Test
	public void testDeleteByOfferID() throws Exception {
		doReturn(1).when(offerRepository).deleteOffer(3L, 1L);
		service.deleteOfferByIdAndMerchantId(3L, 1L);
		assertTrue("No exception thrown", true);
		verify(offerRepository, times(1)).deleteOffer(3L, 1L);
		verifyNoMoreInteractions(offerRepository);
	}

	@Test
	public void testDeleteByOfferIDThrowException() throws Exception {
		doReturn(0).when(offerRepository).deleteOffer(2L, 2L);		
		try {
			service.deleteOfferByIdAndMerchantId(2L, 2L);
		} catch (Exception e) {
			assertThat(e, IsInstanceOf.instanceOf(OfferNotUpdatedException.class));
		}
		verify(offerRepository, times(1)).deleteOffer(2L, 2L);
		verifyNoMoreInteractions(offerRepository);
	}

}
