package com.marketplace.offer.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.marketplace.offer.dto.OfferDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
//@DataJpaTest(showSql=true)
//@ActiveProfiles(value="test")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data-test-h2.sql")
public class OfferRepositoryIntegrationTest {
	
	private Logger log = LoggerFactory.getLogger(OfferRepositoryIntegrationTest.class);
	@Autowired
	private OfferRepository offerRepository;
	@Before
	public void setUp() throws Exception {			
		//offerRepository.deleteAll();
	}

	@Test
	public void testSaveOfferSuccess() throws Exception {
		
		LocalDate validFromDate = LocalDate.of(2017, 1, 30); //dateformat.parse("2017-01-30");
		LocalDate validToDate = LocalDate.of(2017, 2, 20); //dateformat.parse("2017-02-20");
		
		final OfferDTO offerDTO = new OfferDTO( "Title1", "Description", 100057L, 1L, 200057L, validFromDate, validToDate, "Y");
		final OfferDTO savedOffer = offerRepository.save(offerDTO);

		assertThat(savedOffer, notNullValue());
		assertThat(savedOffer.getId(), notNullValue());
		assertThat(savedOffer.getTitle(), is("Title1"));
		assertThat(savedOffer.getDescription(), is("Description"));
		assertThat(savedOffer.getTypeId(), is(100057L));
		assertThat(savedOffer.getMerchantId(), is(1L));
	}

	@Test
	public void testSaveOfferFailure() throws Exception {
		
		LocalDate validFromDate = LocalDate.of(2017, 1, 30); //dateformat.parse("2017-01-30");
		LocalDate validToDate = LocalDate.of(2017, 2, 20); //dateformat.parse("2017-02-20");
		
		final OfferDTO offerDTO = new OfferDTO( "Title1", "Description", 2L, 1L, 1L, validFromDate, validToDate, "Y");
		
		assertThatThrownBy(() -> offerRepository.save(offerDTO)).isInstanceOf(DataIntegrityViolationException.class);
	}	
	
	@Test
	@Transactional
	public void testDeleteOfferSuccess() {
		int deletecount = offerRepository.deleteOffer(3L, 1L);		
		assertThat(deletecount, is(1));
		OfferDTO deletedOffer = offerRepository.findOne(1L);
		assertThat(deletedOffer.getDeleted(), is("Y"));
	}	
	
	
	@Test
	@Transactional
	public void testDeleteOfferNotUpdated() {
		
		int deletecount = offerRepository.deleteOffer(3L, 21L);		
		assertThat(deletecount, is(0));
		List<OfferDTO> lOfOffers = offerRepository.findByMerchantIdAndId(3L, 1L);
		assertThat(lOfOffers.get(0).getDeleted(), is("N"));
	}
	
	
	@Test
	public void testFindOffers() {
		List<OfferDTO> actual = offerRepository.findByMerchantId(null);
		assertThat(actual).isNotNull();
		assertThat(actual).hasSize(0);
	}

}
