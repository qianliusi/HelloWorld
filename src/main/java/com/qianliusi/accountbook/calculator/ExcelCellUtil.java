package com.qianliusi.accountbook.calculator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by qls on 2015/12/2.
 */
public class ExcelCellUtil {
    //元转成分
    private static Long double2Long(double d){
        return Math.round(d * 100);
    }
    //获取金额
    public static Long getCellNumericValue(Row row, int index, FormulaEvaluator evaluator){
        Cell cell = row.getCell(index, Row.RETURN_BLANK_AS_NULL);
        if(null == cell){
            return 0L;
        }
        if(Cell.CELL_TYPE_FORMULA == cell.getCellType()){
            return double2Long(evaluator.evaluate(cell).getNumberValue());
        }
        if(Cell.CELL_TYPE_NUMERIC == cell.getCellType()){
            return double2Long(cell.getNumericCellValue());
        }
        return 0L;
    }
    public static Double getCellDoubleValue(Row row, int index, FormulaEvaluator evaluator){
        Cell cell = row.getCell(index, Row.RETURN_BLANK_AS_NULL);
        if(null == cell){
            return 0d;
        }
        if(Cell.CELL_TYPE_FORMULA == cell.getCellType()){
            return evaluator.evaluate(cell).getNumberValue();
        }
        if(Cell.CELL_TYPE_NUMERIC == cell.getCellType()){
            return cell.getNumericCellValue();
        }
        return 0d;
    }
    //获取字符串
    public static String getCellStringValue(Row row, int index, FormulaEvaluator evaluator, String format){
        Cell cell = row.getCell(index, Row.RETURN_BLANK_AS_NULL);
        if(null == cell){
            return null;
        }
        if(Cell.CELL_TYPE_FORMULA == cell.getCellType()){
            return evaluator.evaluate(cell).getStringValue();
        }
        if(Cell.CELL_TYPE_STRING == cell.getCellType()){
            return cell.getStringCellValue();
        }
        if(Cell.CELL_TYPE_NUMERIC == cell.getCellType()){
            if (DateUtil.isCellDateFormatted(cell)) {
                Date dateCellValue = cell.getDateCellValue();
                if(null == dateCellValue){
                    return null;
                }
                return new SimpleDateFormat(format).format(dateCellValue);
            } else {
                return String.valueOf(cell.getNumericCellValue());
            }
        }
        return null;
    }
    public static Date getCellDateValue(Row row, int index){
        Cell cell = row.getCell(index, Row.RETURN_BLANK_AS_NULL);
        return getCellDateValue(cell);
    }
	public static Date getCellDateValue(Cell cell){
		if(null == cell){
			return null;
		}
		if(Cell.CELL_TYPE_NUMERIC == cell.getCellType()){
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue();
			}
		}
		return null;
	}
    public static String getCellStringValue(Row row, int index, FormulaEvaluator evaluator){
        return getCellStringValue(row,index,evaluator,"yyyy-MM-dd");
    }
}
