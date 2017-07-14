package com.qianliusi.accountbook.calculator;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by qianliusi on 2017/3/31.
 */
public class CalculatorTest {
	@Test
	public void check() throws Exception{
		String filePath = "D:/poi/账本.xlsx";
		InputStream inputStream = new FileInputStream(filePath);
		Workbook wb = WorkbookFactory.create(inputStream);
		Date date = DateUtil.parseYYYYMMDDDate("2017/07/15");
		NoteBook noteBook = new NoteBook(wb, new LinearCalculator(), date);
		noteBook.countExcelY(0,2,1);
		NoteBook noteBook2 = new NoteBook(wb, new ExponentCalculator(), date);
		noteBook2.countExcelY(1,2,1);
		FileOutputStream fileOut = new FileOutputStream(filePath);
		wb.write(fileOut);
		fileOut.close();
	}
}
