package com.marketplace.util.date;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class DateTimeManager implements IDateTimeManager {
	
	@Override
	public LocalDate getCurrentLocalDate() {
		return LocalDate.now();
	}

}
