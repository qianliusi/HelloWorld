package com.qianliusi.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qls on 2015/12/9.
 */
public class POIUtil {
	public static Workbook initWorkbookByTemplate(List<List<String>> data, InputStream is, int offsetRow) throws Exception {
		return initWorkbook(data, null, is, offsetRow);
	}

	public static Workbook initWorkbookByData(List<List<String>> data, List<String> titles, int offsetRow) throws Exception {
		return initWorkbook(data, titles, null, offsetRow);
	}

	/**
	 * 可支持两种方式的xls输出
	 * 1.根据已有xls文件的输入流，初始化workbook，填充数据
	 * 2.新建workbook，根据传入数据和标题填充workbook
	 *
	 * @param data      需要填充的数据
	 * @param titles    标题
	 * @param is        模板的输入流
	 * @param offsetRow 行偏移量
	 * @return
	 * @throws IOException
	 */
	public static Workbook initWorkbook(List<List<String>> data, List<String> titles, InputStream is, int offsetRow) throws Exception {
		Workbook workbook;
		Sheet sheet;
		if(is == null) {
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet();
		} else {
			workbook = WorkbookFactory.create(is);
			sheet = workbook.getSheetAt(0);
		}
		if(data == null || data.size() == 0)
			return workbook;
		CellStyle cellStyle;
		Row templateRow = sheet.getRow(offsetRow);
		cellStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("微软雅黑");
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		for(int i = 0; i < data.size(); i++) {
			List<String> rowData = data.get(i);
			Row row;
			if(0 != i) {
				row = sheet.createRow(offsetRow + i);
			} else {
				row = templateRow;
			}
			if(i == 0 && (null != titles && titles.size() > 0)) {
				for(int j = 0; j < titles.size(); j++) {
					Cell cell = row.createCell(j);
					cell.setCellStyle(cellStyle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(rowData.get(j));
				}
				continue;
			}
			for(int i1 = 0; i1 < rowData.size(); i1++) {
				Cell cell;
				if(0 != i) {
					cell = row.createCell(i1);
				} else {
					cell = templateRow.getCell(i1, Row.RETURN_NULL_AND_BLANK);
				}
				if(null == cell) {
					cell = row.createCell(i1);
				}
				if(null != templateRow && null != templateRow.getCell(i1, Row.RETURN_NULL_AND_BLANK)) {
					Cell templateCell = templateRow.getCell(i1);
					cell.setCellStyle(templateCell.getCellStyle());
					cell.setCellType(templateCell.getCellType());
				} else {
					cell.setCellStyle(cellStyle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
				}
				cell.setCellValue(rowData.get(i1));
			}
		}
		return workbook;
	}

	public static Cell createTitleCell(Row row, Workbook workbook, int cellNumber, String cellValue) {
		Cell cell = row.createCell(cellNumber);
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cell.setCellStyle(cellStyle);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(cellValue);
		return cell;
	}

	public static <T> List<List<String>> getExportData(List<T> ts, String... fieldNames) {
		if(CollectionUtils.isEmpty(ts))
			return null;
		List<List<String>> data = new ArrayList<>();
		for(T t : ts) {
			List<String> strs = new ArrayList<>();
			for(String fn : fieldNames) {
				String value;
				try {
					String fieldType = PropertyUtils.getPropertyType(t, fn).toString();
					value = BeanUtils.getProperty(t, fn);
					if(value == null) {
						value = "0";
					}
				} catch(IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					value = fn;
				}
				strs.add(value);
			}
			data.add(strs);
		}
		return data;
	}
}
