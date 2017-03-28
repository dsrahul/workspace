package com.marketplace.config;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.marketplace.MarketPlaceApplication;
@SpringBootTest
public class MarketPlaceApplicationTest {
	@Test(expected=IllegalArgumentException.class)
	public void test() {
		MarketPlaceApplication.main(null);
	}
	
	@Test
	public void testWorks() {
		
		MarketPlaceApplication.main(new String[]{""});
		assertTrue(true);
	}
}
