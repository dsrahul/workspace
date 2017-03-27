package com.marketplace.offer.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;

import com.marketplace.config.StandardAPIError;
import com.marketplace.offer.dto.OfferDTO;
import com.marketplace.offer.repository.OfferRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data-test-h2.sql")
@TestPropertySource(locations="classpath:application-test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OfferControllerIntegrationTest {	
	
	private static final Logger log = LoggerFactory.getLogger(OfferControllerIntegrationTest.class);

	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private OfferRepository OfferRepository;
	
	@Before
	public void setUp() throws Exception {		
		
	}

	@Test
	public void someTest() {
		assertTrue(true);		
	}
	@Test
	public void testFindOffers() throws Exception {		
		ResponseEntity<OfferDTO[]> response = restTemplate.getForEntity("/merchants/3/offers/1", OfferDTO[].class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response, notNullValue());
		OfferDTO[] body = response.getBody();
		assertThat(body, notNullValue());
		assertThat(body.length, is(1));        
	}


	@Test
	public void testFindOffersNoResult() throws Exception {	
		log.info("testFindOffersNoResult");		
		ResponseEntity<OfferDTO[]> response = restTemplate.getForEntity("/merchants/3/offers/2", OfferDTO[].class);
        assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));
        assertThat(response, notNullValue());
		OfferDTO[] body = response.getBody();
		assertThat(body).isNull();
        
	}
	
	@Ignore
	@Test
	public void testAddOfferNotAuthorised() throws IOException {
		ClientHttpResponse clientHttpResponse = restTemplate.withBasicAuth("fakeuser", "fakepassword").execute("/merchants/1/offers", HttpMethod.POST, new RequestCallback() {
			
			@Override
			public void doWithRequest(ClientHttpRequest request) throws IOException {
			}
		}, new ResponseExtractor<ClientHttpResponse>() {

			@Override
			public ClientHttpResponse extractData(ClientHttpResponse response) throws IOException {
				//log.info("======================="+response.getStatusCode().toString());
				return response;
			}
		});		
		assertThat(clientHttpResponse, notNullValue());
		//TODO assertThat(clientHttpResponse.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
		
	}
	
	
	@Test
	public void testAddOfferSuccess() throws Exception {
	
		log.info("testAddOfferSuccess");
		
		LocalDate validFromDate = LocalDate.of(2017, 1, 30); //dateformat.parse("2017-01-30");
		LocalDate validToDate = LocalDate.of(2017, 2, 20); //dateformat.parse("2017-02-20");
		List<OfferDTO> lOfOffers = Arrays.asList(
				new OfferDTO( "Title1", "Description", 100057L, 1L, 200057L, validFromDate, validToDate, "Y"),
				new OfferDTO( "Title2", "Description", 100057L, 1L, 200057L, validFromDate, validToDate, "Y"));
		ResponseEntity<OfferDTO[]> response = restTemplate.withBasicAuth("user", "password").postForEntity("/merchants/1/offers", lOfOffers, OfferDTO[].class);
		assertThat(response, notNullValue());
		OfferDTO[] body = response.getBody();
		assertNull(body);
		
	}

	@Test
	public void testAddOfferFailure() throws Exception {
	
		log.info("testAddOfferFailure");
		
		LocalDate validFromDate = LocalDate.of(2017, 1, 30); //dateformat.parse("2017-01-30");
		LocalDate validToDate = LocalDate.of(2017, 2, 20); //dateformat.parse("2017-02-20");
		List<OfferDTO> lOfOffers = Arrays.asList(
				new OfferDTO( "Title1", "Description", 1L, 1L, 1L, validFromDate, validToDate, "Y"),
				new OfferDTO( "Title2", "Description", 100057L, 2L, 1L, validFromDate, validToDate, "Y"));
		ResponseEntity<StandardAPIError> response = restTemplate.withBasicAuth("user", "password").postForEntity("/merchants/1/offers", lOfOffers, StandardAPIError.class);
		assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	}

	@Test
	public void testFindAllOffers() throws Exception {	
		log.info("testFindAllOffers");		
		ResponseEntity<OfferDTO[]> response = restTemplate.getForEntity("/merchants/3/offers", OfferDTO[].class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response, notNullValue());
		OfferDTO[] body = response.getBody();
		assertThat(body, notNullValue());
		assertThat(body.length, is(1));        
	}


	@Test
	public void testFindAllOffersNoResult() throws Exception {	
		log.info("testFindAllOffersNoResult");
		OfferRepository.deleteAll();
		ResponseEntity<OfferDTO[]> response = restTemplate.getForEntity("/merchants/21/offers", OfferDTO[].class);
        assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));
        assertThat(response, notNullValue());
		OfferDTO[] body = response.getBody();
		assertThat(body).isNull();
        
	}


	@Test
	public void testDeleteOfferFailure() throws Exception {
	
		log.info("testDeleteOffer");
		ResponseEntity<String> response = restTemplate.exchange("/merchants/21/offers/1", HttpMethod.DELETE, null, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_MODIFIED));
        assertThat(response, notNullValue());
		assertThat(response.getBody()).isNull();		
	}


	@Test
	public void testDeleteOfferSuccess() throws Exception {
	
		log.info("testDeleteOffer");
		ResponseEntity<String> response = restTemplate.exchange("/merchants/3/offers/1", HttpMethod.DELETE, null, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response, notNullValue());
		assertThat(response.getBody()).isNull();	

		ResponseEntity<OfferDTO[]> afterDelete = restTemplate.getForEntity("/merchants/3/offers/1", OfferDTO[].class);
        assertThat(afterDelete.getStatusCode(), is(HttpStatus.NO_CONTENT));
        assertThat(afterDelete, notNullValue());
        assertThat(afterDelete.getBody()).isNull();
        
	}
	
	@Test
	public void testLocalDate() {
		
		ResponseEntity<LocalDate> exchange = restTemplate.exchange("/time", HttpMethod.GET, null, LocalDate.class);
	}
	
	
}
