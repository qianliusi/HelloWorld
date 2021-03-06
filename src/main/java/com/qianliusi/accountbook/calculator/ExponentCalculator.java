package com.qianliusi.accountbook.calculator;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by qianliusi on 2016/12/20.
 */
public class ExponentCalculator implements Calculator{
	@Override
	public Double calculate(PersonItem personItem, Date endDate) {
		if(null == personItem || null == personItem.getItems()) {
			return 0D;
		}
		double count = 0;
		Map<Date, Double> items = personItem.getItems();
		for(Entry<Date, Double> dateDoubleEntry : items.entrySet()) {
			Double value = dateDoubleEntry.getValue();
			Date startDate = dateDoubleEntry.getKey();
			double rate = Math.pow(personItem.getFactor(), ((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000))) - 1;
			count += rate * value * 10000;
		}
		return count;
	}
}
