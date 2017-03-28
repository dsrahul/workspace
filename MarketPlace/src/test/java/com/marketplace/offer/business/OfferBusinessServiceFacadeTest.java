package com.marketplace.offer.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.marketplace.offer.dto.OfferDTO;
import com.marketplace.offer.repository.OfferRepository;
import com.marketplace.util.date.DateTimeManager;

@RunWith(SpringRunner.class)
public class OfferBusinessServiceFacadeTest {
	
	@InjectMocks
	private OfferBusinessServiceFacade offerBusinessService;

	@Mock
	private OfferRepository offerRepository;
	@Mock
	private DateTimeManager dateTimeManager;

	@Test
	public void testFindActiveOffersNullInput() throws Exception {			
		
		List<OfferDTO> actual = offerBusinessService.getActiveOffersForMerchantId(null, null);
		
		assertThat(actual).isNotNull();
		assertThat(actual).hasSize(0);
		
	}

	@Test
	public void testFindActiveOffersSuccessForMerchIdAndOfferId() throws Exception {			
		
		LocalDate validFromDate = LocalDate.of(2017, 1, 30); //dateformat.parse("2017-01-30");
		LocalDate validToDate = LocalDate.of(2017, 2, 20); //dateformat.parse("2017-02-20");		
		List<OfferDTO> intermediate = Arrays.asList(
				new OfferDTO(1L, "Title1", "Description", 1L, 1L, 1L, validFromDate, validToDate, "N"),
				new OfferDTO(2L, "Title2", "Description", 1L, 1L, 1L, validFromDate, validToDate, "N"));
		List<OfferDTO> expected = Arrays.asList(
				new OfferDTO(1L, "Title1", "Description", 1L, 1L, 1L, validFromDate, validToDate, "N"));
		when(offerRepository.findByMerchantIdAndIdAndDeleted(1L, 1L, "N")).thenReturn(intermediate);
		when(dateTimeManager.getCurrentLocalDate()).thenReturn(LocalDate.of(2017, 2, 1));
		
		List<OfferDTO> actual = offerBusinessService.getActiveOffersForMerchantId(1L, 1L);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.get(0)).isEqualToComparingFieldByField(expected.get(0));
		assertThat(actual).extracting("id").contains(1L);
		assertThat(actual).extracting("deleted").contains("N");
	}
	

	@Test
	public void testFindActiveOffersNoResultsForMerchIdAndOfferId() throws Exception {
		when(offerRepository.findByMerchantIdAndIdAndDeleted(1L, 1L, "N")).thenReturn(ListUtils.EMPTY_LIST);
		
		List<OfferDTO> actual = offerBusinessService.getActiveOffersForMerchantId(1L, 1L);
		
		assertThat(actual).isNotNull();
		assertThat(actual).hasSize(0);
		
	}

	@Test
	public void testFindActiveOffersSuccessForMerchIdOnly() throws Exception {	
		
		LocalDate validFromDate = LocalDate.of(2017, 1, 30); //dateformat.parse("2017-01-30");
		LocalDate validToDate = LocalDate.of(2017, 2, 20); //dateformat.parse("2017-02-20");
		List<OfferDTO> intermediate = Arrays.asList(
				new OfferDTO(1L, "Title1", "Description", 1L, 1L, 1L, validFromDate, validToDate, "N"),
				new OfferDTO(2L, "Title2", "Description", 1L, 1L, 1L, validFromDate, validToDate, "N"));
		when(dateTimeManager.getCurrentLocalDate()).thenReturn(LocalDate.of(2017, 2, 1));
		when(offerRepository.findByMerchantIdAndDeleted(1L, "N")).thenReturn(intermediate);
		
		List<OfferDTO> actual = offerBusinessService.getActiveOffersForMerchantId(1L, null);
		
		assertThat(actual).isNotNull();
		assertThat(actual).hasSize(2);
	}

	@Test
	public void testFindActiveOffersValidFromSuccessForMerchIdOnly() throws Exception {	

		LocalDate validFromDate1 = LocalDate.of(2017, 2, 1); //dateformat.parse("2017-01-30");
		LocalDate validFromDate2 = LocalDate.of(2017, 2, 3); //dateformat.parse("2017-01-30");
		LocalDate validToDate = LocalDate.of(2017, 2, 20); //dateformat.parse("2017-02-20");
		List<OfferDTO> intermediate = Arrays.asList(
				new OfferDTO(1L, "Title1", "Description", 1L, 1L, 1L, validFromDate1, validToDate, "N"),
				new OfferDTO(2L, "Title2", "Description", 1L, 1L, 1L, validFromDate2, validToDate, "N"));
		when(dateTimeManager.getCurrentLocalDate()).thenReturn(LocalDate.of(2017, 2, 1));
		when(offerRepository.findByMerchantIdAndDeleted(1L, "N")).thenReturn(intermediate);
		
		List<OfferDTO> actual = offerBusinessService.getActiveOffersForMerchantId(1L, null);
		
		assertThat(actual).isNotNull();
		assertThat(actual).hasSize(1);
	}

	@Test
	public void testFindActiveOffersValidToSuccessForMerchIdOnly() throws Exception {	

		LocalDate validFromDate = LocalDate.of(2017, 1, 1);
		LocalDate validToDate1 = LocalDate.of(2017, 2, 1);
		LocalDate validToDate2 = LocalDate.of(2017, 1, 31);
		OfferDTO expected = new OfferDTO(1L, "Title1", "Description", 1L, 2L, 3L, validFromDate, validToDate1, "N");
		List<OfferDTO> intermediate = Arrays.asList(
				expected,
				new OfferDTO(2L, "Title2", "Description", 1L, 1L, 1L, validFromDate, validToDate2, "N"));
		when(dateTimeManager.getCurrentLocalDate()).thenReturn(LocalDate.of(2017, 2, 1));
		when(offerRepository.findByMerchantIdAndDeleted(1L, "N")).thenReturn(intermediate);
		
		List<OfferDTO> actual = offerBusinessService.getActiveOffersForMerchantId(1L, null);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual).hasSize(1);
		assertThat(actual.get(0)).isEqualToComparingFieldByField(expected);
		assertThat(actual).extracting("id").contains(1L);
		assertThat(actual).extracting("deleted").contains("N");
	}

	@Test
	public void testFindActiveOffersValidToNullSuccessForMerchIdOnly() throws Exception {	

		LocalDate validFromDate = LocalDate.of(2017, 1, 1);
		LocalDate validToDate2 = LocalDate.of(2017, 1, 31);
		OfferDTO expected = new OfferDTO(1L, "Title1", "Description", 1L, 2L, 3L, validFromDate, null, "N");
		List<OfferDTO> intermediate = Arrays.asList(
				expected,
				new OfferDTO(2L, "Title2", "Description", 1L, 1L, 1L, validFromDate, validToDate2, "N"));
		when(dateTimeManager.getCurrentLocalDate()).thenReturn(LocalDate.of(2017, 2, 1));
		when(offerRepository.findByMerchantIdAndDeleted(1L, "N")).thenReturn(intermediate);
		
		List<OfferDTO> actual = offerBusinessService.getActiveOffersForMerchantId(1L, null);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual).hasSize(1);
		assertThat(actual.get(0)).isEqualToComparingFieldByField(expected);
		assertThat(actual).extracting("id").contains(1L);
		assertThat(actual).extracting("deleted").contains("N");
	}
	

	@Test
	public void testFindActiveOffersNoResultsForMerchIdOnly() throws Exception {
		when(dateTimeManager.getCurrentLocalDate()).thenReturn(LocalDate.of(2017, 2, 1));
		when(offerRepository.findByMerchantIdAndDeleted(1L, "N")).thenReturn(ListUtils.EMPTY_LIST);
		
		List<OfferDTO> actual = offerBusinessService.getActiveOffersForMerchantId(1L, null);
		
		assertThat(actual).isNotNull();
		assertThat(actual).hasSize(0);
		
	}

	@Test
	public void testFindActiveOffersFailureBasedonDate() throws Exception {
		
		LocalDate validFromDate = LocalDate.of(2017, 1, 30); //dateformat.parse("2017-01-30");
		LocalDate validToDate = LocalDate.of(2017, 2, 20); //dateformat.parse("2017-02-20");
		List<OfferDTO> intermediate = Arrays.asList(
				new OfferDTO(1L, "Title1", "Description", 1L, 1L, 1L, validFromDate, validToDate, "Y"),
				new OfferDTO(2L, "Title2", "Description", 1L, 1L, 1L, validFromDate, validToDate, "Y"));
		when(offerRepository.findByMerchantId(1L)).thenReturn(intermediate);
		
		List<OfferDTO> actual = offerBusinessService.getActiveOffersForMerchantId(1L, 1L);
		
		assertThat(actual).isNotNull();
		assertThat(actual).hasSize(0);
		
	}

}
