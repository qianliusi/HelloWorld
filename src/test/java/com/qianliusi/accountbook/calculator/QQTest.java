package com.qianliusi.accountbook.calculator;

import com.manyi.json.FasterJsonTool;
import com.qianliusi.qq.group.QQMember;
import com.qianliusi.qq.group.SearchGroupMemberResult;
import com.qianliusi.utils.POIUtil;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by qianliusi on 2017/3/31.
 */
public class QQTest {
	@Test
	public void check() throws Exception {
		SearchGroupMemberResult result = FasterJsonTool.readValue(FileUtils.readFileToString(new File("D:/poi/lejucaifu.json")),
																  SearchGroupMemberResult.class);
		List<QQMember> mems = result.getMems();
		String[] columns = new String[]{"uin", "nick", "card", "gender", "qage", "join_timeStr", "last_speak_timeStr"};
		List<List<String>> data = POIUtil.getExportData(mems, columns);
		String filePath = "D:/poi/qq.xlsx";
		InputStream inputStream = new FileInputStream(filePath);
		Workbook wb = POIUtil.initWorkbookByTemplate(data, inputStream, 1);
		FileOutputStream fileOut = new FileOutputStream(filePath);
		wb.write(fileOut);
		fileOut.close();
	}
}
