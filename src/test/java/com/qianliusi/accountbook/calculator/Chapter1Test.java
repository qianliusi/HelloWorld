package com.qianliusi.accountbook.calculator;

import com.qianliusi.practice.chapter1.MyString;
import com.qianliusi.practice.chapter1.TimesTable;
import com.qianliusi.practice.chapter1.TrianglePrint;
import org.junit.Test;

/**
 * Created by qianliusi on 2017/3/31.
 */
public class Chapter1Test {
	@Test
	public void printTimesTable(){
		new TimesTable(9).print();
	}
	@Test
	public void printTriangle(){
		new TrianglePrint(4).print();
	}
	@Test
	public void testMyString(){
		String value = "中国abc";
		System.out.println(new MyString(value).substring(0, 1));
	}
}
