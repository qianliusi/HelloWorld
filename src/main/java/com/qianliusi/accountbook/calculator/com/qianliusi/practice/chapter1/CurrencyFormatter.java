package com.qianliusi.accountbook.calculator.com.qianliusi.practice.chapter1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qianliusi on 2017/3/31.
 */
public class CurrencyFormatter {
	private double value = 1006.333;
	private static final String[] formatter = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾", "佰", "仟", "万", "亿"};
	public String format(){
		int mod = (int)value % 10;
		int num = (int)value / 10;
		while(num > 0) {

		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(1006.333+"");
	}
	private List<Byte> extractDigit(){
		List<Byte> result = new ArrayList<>();
		byte minus = '-';
		byte decimalPoint = '.';
		String str = value + "";
		for(byte b : str.getBytes()) {
			if(minus != b && decimalPoint != b) {
				result.add(b);
			}
		}
		return result;
	}

}
