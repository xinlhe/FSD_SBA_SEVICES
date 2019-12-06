package com.ibm.sba.microservice.smc.upload.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ibm.sba.microservice.smc.upload.controller.SBAResultBean;
import net.bluefsd.dao.StockPriceRepository;
import net.bluefsd.entity.StockPrice;

@Service(value = "excelService")
public class SBAExcelService {
	Logger log = LoggerFactory.getLogger(SBAExcelService.class);

	@Autowired
	StockPriceRepository stockPriceRepository;

	DateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public SBAResultBean batchImport(String fileName, MultipartFile file) throws Exception {
	    
		boolean isExcel2003 = true;
		SBAResultBean rb = new SBAResultBean();

		ArrayList<String> list = new ArrayList<String>();
		
		if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
			String error = "bad format";
			rb.setCode(0);
			rb.setMsg(error);
			return rb;
		}
		
		if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
			isExcel2003 = false;
		}
		InputStream is = file.getInputStream();
		Workbook wb = null;
		if (isExcel2003) {
			wb = new HSSFWorkbook(is);
		} else {
			wb = new XSSFWorkbook(is);
		}
		Sheet sheet = wb.getSheetAt(0);

		for (int r = 1; r <= sheet.getLastRowNum(); r++) {
			Row row = sheet.getRow(r);
			if (row == null) {
				continue;
			}
			StockPrice stockPrice = new StockPrice();

			String stockCd = row.getCell(0).getStringCellValue();
			stockPrice.setStockCd(stockCd);

			double price = row.getCell(2).getNumericCellValue();
			stockPrice.setPrice(price);

			Date date = row.getCell(3).getDateCellValue();
			String time = row.getCell(4).getStringCellValue();

			String strDate = dateFormat.format(date);
			
			if(r == 1) {
			    
			    rb.setFromDate(strDate.toString());
			}
			if(r == sheet.getLastRowNum()) {
			    
			    rb.setToDate(strDate.toString());
			}
			rb.setStockCode(stockCd);
			
			// 2019/6/8 10:30:00
			String dateTime = strDate.trim() + " " + time.trim();
			// System.out.println(r + ":" + dateTime);
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(date);
			
			int w = cal.get(Calendar.DAY_OF_WEEK);
			
			if (w == Calendar.SUNDAY || w == Calendar.SATURDAY) {

			    list.add(strDate);
			}
			Date oDate=simpleFormat.parse(dateTime);
			Timestamp ts=new Timestamp(oDate.getTime());
			stockPrice.setCurTime(dateTime);

			stockPriceRepository.save(stockPrice);
		}
		
		rb.setNumber(sheet.getLastRowNum());
		rb.setMsg("import successfully. Total rows " + sheet.getLastRowNum() + "; failed rows " + list.size());
		rb.setCode(1);
		return rb;
	}
}
