package com.qianliusi.accountbook.calculator;

import org.apache.poi.ss.usermodel.*;

import java.util.*;

/**
 * Created by qianliusi on 2016/12/20.
 */
public class NoteBook {
	private Workbook wb;
	private Calculator calculator;
	private Date endDate;

	public NoteBook(Workbook wb, Calculator calculator, Date endDate) {
		this.wb = wb;
		this.calculator = calculator;
		this.endDate = endDate;
	}

	/**
	 * 统计excel 竖向统计
	 * @param sheetIndex 第几个sheet 0
	 * @param rowOffset 第几行开始计算 2
	 * @param columnOffset 第几列开始计算 1
	 */
	public void countExcelY(int sheetIndex,int rowOffset,int columnOffset) {
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
		Sheet sheet = wb.getSheetAt(sheetIndex);
		Row nameRow = sheet.getRow(0);
		Row factorRow = sheet.getRow(1);
		List<PersonItem> personItems = new ArrayList<>();
		for(int i = columnOffset; i < nameRow.getLastCellNum(); i++) {
			String name = ExcelCellUtil.getCellStringValue(nameRow, i, evaluator);
			Double factor = ExcelCellUtil.getCellDoubleValue(factorRow, i, evaluator);
			if(null != name && factor > 0) {
				PersonItem personItem = new PersonItem();
				personItem.setName(name);
				personItem.setFactor(factor);
				personItems.add(personItem);
			}
		}
		for(int rowNum = rowOffset; rowNum <= sheet.getLastRowNum(); rowNum++) {
			Row row = sheet.getRow(rowNum);
			if(null == row) {
				break;
			}
			Date loanDate = ExcelCellUtil.getCellDateValue(row.getCell(0));
			if(null != loanDate){
				for(int i = columnOffset; i < row.getLastCellNum() && i < personItems.size() + columnOffset; i++) {
					Double loan = ExcelCellUtil.getCellDoubleValue(row, i, evaluator);
					PersonItem personItem = personItems.get(i - columnOffset);
					Map<Date, Double> items = personItem.getItems();
					items.put(loanDate, loan);
				}
			}
		}
		Row totalRow = sheet.getRow(sheet.getLastRowNum() - 1);
		for(int i = columnOffset; i < personItems.size() + columnOffset; i++) {
			Cell cell = totalRow.createCell(i);
			cell.setCellValue(personItems.get(i - columnOffset).getTotal());
		}
		Row countRow = sheet.getRow(sheet.getLastRowNum());
		for(int i = columnOffset; i < personItems.size() + columnOffset; i++) {
			Cell cell = countRow.createCell(i);
			cell.setCellValue(calculator.calculate(personItems.get(i - columnOffset), endDate));
		}
	}
	/**
	 * 统计excel 横向统计
	 * @param sheetIndex 第几个sheet 1
	 * @param rowOffset 第几行开始计算 1
	 * @param columnOffset 第几列开始计算 2
	 */
	public void countExcelX(int sheetIndex,int rowOffset,int columnOffset) {
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
		Sheet sheet = wb.getSheetAt(sheetIndex);
		Row firstRow = sheet.getRow(0);
		List<Date> loanDates = new ArrayList<>();
		for(int i = columnOffset; i < firstRow.getLastCellNum(); i++) {
			Date cellDateValue = ExcelCellUtil.getCellDateValue(firstRow.getCell(i));
			if(null != cellDateValue) {
				loanDates.add(cellDateValue);
			}
		}
		for(int rowNum = rowOffset; rowNum <= sheet.getLastRowNum(); rowNum++) {
			Row row = sheet.getRow(rowNum);
			if(null == row) {
				break;
			}
			PersonItem personItem = new PersonItem();
			String name = ExcelCellUtil.getCellStringValue(row, 0, evaluator);
			Double factor = ExcelCellUtil.getCellDoubleValue(row, 1, evaluator);
			personItem.setName(name);
			personItem.setFactor(factor);
			Map<Date, Double> loanMap = new HashMap<>();
			for(int i = columnOffset; i < row.getLastCellNum(); i++) {
				Double loan = ExcelCellUtil.getCellDoubleValue(row, i, evaluator);
				if(i - columnOffset < loanDates.size()) {
					loanMap.put(loanDates.get(i - columnOffset), loan);
				}
			}
			personItem.setItems(loanMap);
			Cell totalCell = row.createCell(loanDates.size() + columnOffset);
			totalCell.setCellValue(personItem.getTotal());
			Cell cell = row.createCell(loanDates.size() + columnOffset + 1);
			cell.setCellValue(calculator.calculate(personItem, endDate));
		}
	}


}
