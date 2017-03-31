package com.qianliusi.accountbook.calculator.com.qianliusi.practice.chapter1;

/**
 * Created by qianliusi on 2017/3/31.
 */
public class MyString {
	private String value;
	private int length;

	public MyString(String value) {
		this.value = value;
		if(value != null) {
			this.length = value.getBytes().length;
		}
	}
	public MyString substring(int beginIndex, int endIndex){
		if(value == null) {
			throw new NullPointerException();
		}
		if (beginIndex < 0) {
			throw new StringIndexOutOfBoundsException(beginIndex);
		}
		if (endIndex > length) {
			throw new StringIndexOutOfBoundsException(endIndex);
		}
		int subLen = endIndex - beginIndex;
		if (subLen < 0) {
			throw new StringIndexOutOfBoundsException(subLen);
		}
		byte[] bytes = value.getBytes();
		if((beginIndex == 0) && (endIndex == length)) {
			return this;
		}
		byte[] subBytes = new byte[subLen];
		for(int i = beginIndex,j=0; i < endIndex; i++,j++) {
			subBytes[j] = bytes[i];
		}
		return new MyString(new String(subBytes));
	}

	@Override
	public String toString() {
		return "MyString{" + "value='" + value + '\'' + ", length=" + length + '}';
	}
}
