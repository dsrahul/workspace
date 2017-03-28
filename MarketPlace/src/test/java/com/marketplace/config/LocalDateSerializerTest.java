package com.marketplace.config;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

@RunWith(MockitoJUnitRunner.class)
public class LocalDateSerializerTest {

	@InjectMocks
	private LocalDateSerializer serializer;

	@Mock
	private JsonGenerator jsonGenerator;

	@Mock
	private SerializerProvider serializers;

	@Test
	public void test() throws Exception {

		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				assertThat(args[0], notNullValue());
				assertThat(args[0], is("2017-02-01"));
				return null;
			}
		}).when(jsonGenerator).writeString(anyString());
		serializer.serialize(LocalDate.of(2017, 2, 1), jsonGenerator, null);
		assertTrue(true);
	}

}
