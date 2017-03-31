package com.qianliusi.accountbook.calculator.com.qianliusi.practice.chapter1;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by qianliusi on 2017/3/31.
 */
public class TimesTable {
	//private final Logger logger = LogManager.getLogger(TimesTable.class);
	private static final Logger logger = Logger.getLogger(TimesTable.class);

	private int level = 9;

	public TimesTable(int level) {
		this.level = level;
	}
	public void print() {
		for(int i = 1; i <= level; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j = 1; j <= i; j++) {
				String item = i + "x" + j + "=" + i * j + ",";
				sb.append(item);
			}
			sb.deleteCharAt(sb.length() - 1);
			logger.info(sb);
		}
	}
}