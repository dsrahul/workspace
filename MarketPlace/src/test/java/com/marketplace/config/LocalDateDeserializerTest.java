package com.marketplace.config;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

@RunWith(MockitoJUnitRunner.class)
public class LocalDateDeserializerTest {

	@InjectMocks
	private LocalDateDeserializer deserializer;
	
	@Mock
	private JsonParser jsonParser;
	
	@Mock
	private DeserializationContext ctxt;
	@Test
	public void test() throws Exception {
		when(jsonParser.getValueAsString()).thenReturn("2017-02-22");
		LocalDate deserialize = deserializer.deserialize(jsonParser, ctxt);
		assertThat(deserialize, notNullValue());
		assertThat(deserialize, is(LocalDate.of(2017, 2, 22)));
	}

}
