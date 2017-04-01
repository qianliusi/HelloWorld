package com.qianliusi.practice.chapter1;

import org.apache.log4j.Logger;

/**
 * Created by qianliusi on 2017/3/31.
 */
public class TrianglePrint {
	private static final Logger logger = Logger.getLogger(TrianglePrint.class);
	private int level = 3;
	private String word = "*";

	public TrianglePrint(int level) {
		this.level = level;
	}
	public void print() {
		for(int i = 1; i <= level; i++) {
			int lastWordNum = 2 * level - 1;
			int currentWordNum = 2 * i - 1;
			int blankNum = (lastWordNum - currentWordNum) / 2;
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < blankNum; j++) {
				sb.append(" ");
			}
			for(int j = 0; j < currentWordNum; j++) {
				sb.append(word);
			}
			for(int j = 0; j < blankNum; j++) {
				sb.append(" ");
			}
			logger.info(sb);
		}
	}
}
