package com.qianliusi.accountbook.calculator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qianliusi on 2016/7/20.
 */
public class PersonItem {
	private String name;
	private Double factor;
	private Map<Date, Double> items = new HashMap<>();

	public Double getTotal(){
		Double total = 0d;
		for(Double value : items.values()) {
			total += value;
		}
		return total;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getFactor() {
		return factor;
	}

	public void setFactor(Double factor) {
		this.factor = factor;
	}

	public Map<Date, Double> getItems() {
		return items;
	}

	public void setItems(Map<Date, Double> items) {
		this.items = items;
	}
}
