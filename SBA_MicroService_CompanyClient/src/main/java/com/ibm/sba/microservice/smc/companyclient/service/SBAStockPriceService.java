package com.ibm.sba.microservice.smc.companyclient.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bluefsd.dao.StockPriceRepository;
import net.bluefsd.dao.StockRepository;
import net.bluefsd.entity.Stock;
import net.bluefsd.entity.StockPrice;
import net.bluefsd.model.SectorPriceDetail;
import net.bluefsd.model.StockPriceDetail;
import net.bluefsd.util.FSDConstant;

@Service(value = "stockPriceService")
public class SBAStockPriceService {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	StockPriceRepository stockPriceRepository;

	@Autowired
	StockRepository stockRepository;

	public Stock add(Stock stock) {
		return stockRepository.save(stock);
	}

	private Stock findStock(String companyCd, String exchangeCd) {
		List<Stock> list = stockRepository.findByCompanyExch(companyCd, exchangeCd);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public Map<String, List<StockPriceDetail>> listStockPrice(String[] stockCds, String from, String to)
			throws ParseException {

		List<StockPriceDetail> week = listStockPriceDetails(stockCds, from, to, FSDConstant.INTERVAL_WEEK);
		List<StockPriceDetail> month = listStockPriceDetails(stockCds, from, to, FSDConstant.INTERVAL_MONTH);
		List<StockPriceDetail> quarter = listStockPriceDetails(stockCds, from, to, FSDConstant.INTERVAL_QUARTER);
		List<StockPriceDetail> year = listStockPriceDetails(stockCds, from, to, FSDConstant.INTERVAL_YEAR);

		HashMap<String, List<StockPriceDetail>> map = new HashMap<>();
		map.put("weekList", week);
		map.put("monthList", month);
		map.put("quarterList", quarter);
		map.put("yearList", year);
		return map;
	}

	private List<StockPriceDetail> listStockPriceDetails(String[] stockCds, String from, String to, String intervalType)
			throws ParseException {
		List<StockPriceDetail> detailList = new ArrayList<>();
		if (stockCds != null) {
			for (int i = 0; i < stockCds.length; i++) {
				String stockCd = stockCds[i];
				StockPriceDetail detail = findStockPriceDetail(stockCd, from, to, intervalType);
				detailList.add(detail);
			}
		}
		return detailList;
	}

	private StockPriceDetail findStockPriceDetail(String stockCd, String from, String to, String intervalType)
			throws ParseException {
		// type: week, month, year, ,
		// Stock stock = stockRepository.findById(stockCd).get();
		// detail.setCompanyCd(stock.getCompanyCd());

		Date oFrom = dateFormat.parse(from);
		Date oTo = dateFormat.parse(to);
		if (FSDConstant.INTERVAL_WEEK.equalsIgnoreCase(intervalType)) {
			List<StockPrice> priceList = stockPriceRepository.findWeekByStockCd(stockCd, oFrom, oTo);
			return composeStockPriceDetails(priceList, stockCd, FSDConstant.INTERVAL_WEEK);
		}
		if (FSDConstant.INTERVAL_MONTH.equalsIgnoreCase(intervalType)) {
			List<StockPrice> priceList = stockPriceRepository.findMonthByStockCd(stockCd, oFrom, oTo);
			return composeStockPriceDetails(priceList, stockCd, FSDConstant.INTERVAL_MONTH);
		}
		if (FSDConstant.INTERVAL_QUARTER.equalsIgnoreCase(intervalType)) {
			List<StockPrice> priceList = stockPriceRepository.findQuarterByStockCd(stockCd, oFrom, oTo);
			return composeStockPriceDetails(priceList, stockCd, FSDConstant.INTERVAL_QUARTER);
		}
		if (FSDConstant.INTERVAL_YEAR.equalsIgnoreCase(intervalType)) {
			List<StockPrice> priceList = stockPriceRepository.findYearByStockCd(stockCd, oFrom, oTo);
			return composeStockPriceDetails(priceList, stockCd, FSDConstant.INTERVAL_YEAR);
		}

		return null;
	}

	private StockPriceDetail composeStockPriceDetails(List<StockPrice> priceList, String stockCd, String intervalType)
			throws ParseException {
		StockPriceDetail detail = new StockPriceDetail();
		detail.setStockCd(stockCd);
		detail.setIntervalType(intervalType);
		String[] dates = new String[priceList.size()];
		double[] values = new double[priceList.size()];
		for (int i = 0; i < priceList.size(); i++) {
			StockPrice sp = priceList.get(i);
			String dateTime = sp.getCurTime();
			Date oDateTime = simpleFormat.parse(dateTime);
			String date = dateFormat.format(oDateTime);
			dates[i] = date;
			values[i] = sp.getPrice();
		}
		detail.setDates(dates);
		detail.setValues(values);
		return detail;
	}

	public Map<String, StockPriceDetail> findStockPrice(String stockCd) throws ParseException {
	    
		StockPriceDetail week = findStockPriceDetail(stockCd, FSDConstant.INTERVAL_WEEK);
		StockPriceDetail month = findStockPriceDetail(stockCd, FSDConstant.INTERVAL_MONTH);
		StockPriceDetail quarter = findStockPriceDetail(stockCd, FSDConstant.INTERVAL_QUARTER);
		StockPriceDetail year = findStockPriceDetail(stockCd, FSDConstant.INTERVAL_YEAR);

		HashMap<String, StockPriceDetail> map = new HashMap<>();
		
		map.put("weekList", week);
		map.put("monthList", month);
		map.put("quarterList", quarter);
		map.put("yearList", year);
		
		return map;
	}

	private StockPriceDetail findStockPriceDetail(String stockCd, String intervalType) throws ParseException {

		if (FSDConstant.INTERVAL_WEEK.equalsIgnoreCase(intervalType)) {
			List<StockPrice> priceList = stockPriceRepository.findWeekByStockCd(stockCd);
			return composeStockPriceDetails(priceList, stockCd, FSDConstant.INTERVAL_WEEK);
		}
		if (FSDConstant.INTERVAL_MONTH.equalsIgnoreCase(intervalType)) {
			List<StockPrice> priceList = stockPriceRepository.findMonthByStockCd(stockCd);
			return composeStockPriceDetails(priceList, stockCd, FSDConstant.INTERVAL_MONTH);
		}
		if (FSDConstant.INTERVAL_QUARTER.equalsIgnoreCase(intervalType)) {
			List<StockPrice> priceList = stockPriceRepository.findQuarterByStockCd(stockCd);
			return composeStockPriceDetails(priceList, stockCd, FSDConstant.INTERVAL_QUARTER);
		}
		if (FSDConstant.INTERVAL_YEAR.equalsIgnoreCase(intervalType)) {
			List<StockPrice> priceList = stockPriceRepository.findYearByStockCd(stockCd);
			return composeStockPriceDetails(priceList, stockCd, FSDConstant.INTERVAL_YEAR);
		}

		return null;
	}

	public Map<String, SectorPriceDetail> findSecotrPrice(String sectorCd) throws ParseException {

		SectorPriceDetail week = composeSectorPriceDetail(stockPriceRepository.findWeekBySectorCd(sectorCd), sectorCd,
				FSDConstant.INTERVAL_WEEK);
		SectorPriceDetail month = composeSectorPriceDetail(stockPriceRepository.findMonthBySectorCd(sectorCd), sectorCd,
				FSDConstant.INTERVAL_MONTH);
		SectorPriceDetail quarter = composeSectorPriceDetail(stockPriceRepository.findQuarterBySectorCd(sectorCd),
				sectorCd, FSDConstant.INTERVAL_QUARTER);
		SectorPriceDetail year = composeSectorPriceDetail(stockPriceRepository.findYearBySectorCd(sectorCd), sectorCd,
				FSDConstant.INTERVAL_YEAR);

		HashMap<String, SectorPriceDetail> map = new HashMap<>();
		map.put("weekList", week);
		map.put("monthList", month);
		map.put("quarterList", quarter);
		map.put("yearList", year);
		return map;
	}

	private SectorPriceDetail composeSectorPriceDetail(List<Object[]> priceList, String sectorCd, String internalType) {
		SectorPriceDetail price = new SectorPriceDetail();
		price.setSectorCd(sectorCd);
		price.setIntervalType(internalType);

		String[] dates = new String[priceList.size()];
		double[] values = new double[priceList.size()];
		for (int i = 0; i < priceList.size(); i++) {
			Object[] objs = priceList.get(i);
			values[i] = (Double) objs[0];
			dates[i] = (String) objs[1];

		}
		price.setDates(dates);
		price.setValues(values);
		return price;
	}

	public Map<String, List<SectorPriceDetail>> listSectorPrice(String[] SectorCds, String from, String to)
			throws ParseException {
		Date oFrom = dateFormat.parse(from);
		Date oTo = dateFormat.parse(to);

		List<SectorPriceDetail> weekList = new ArrayList<>();
		List<SectorPriceDetail> monthList = new ArrayList<>();
		List<SectorPriceDetail> quarterList = new ArrayList<>();
		List<SectorPriceDetail> yearList = new ArrayList<>();
		if (SectorCds != null) {
			for (int i = 0; i < SectorCds.length; i++) {
				String sectorCd = SectorCds[i];
				SectorPriceDetail week = composeSectorPriceDetail(
						stockPriceRepository.findWeekBySectorCd(sectorCd, oFrom, oTo), sectorCd,
						FSDConstant.INTERVAL_WEEK);
				SectorPriceDetail month = composeSectorPriceDetail(
						stockPriceRepository.findMonthBySectorCd(sectorCd, oFrom, oTo), sectorCd,
						FSDConstant.INTERVAL_MONTH);
				SectorPriceDetail quarter = composeSectorPriceDetail(
						stockPriceRepository.findQuarterBySectorCd(sectorCd, oFrom, oTo), sectorCd,
						FSDConstant.INTERVAL_QUARTER);
				SectorPriceDetail year = composeSectorPriceDetail(
						stockPriceRepository.findYearBySectorCd(sectorCd, oFrom, oTo), sectorCd,
						FSDConstant.INTERVAL_YEAR);

				weekList.add(week);
				monthList.add(month);
				quarterList.add(quarter);
				yearList.add(year);
			}
		}

		HashMap<String, List<SectorPriceDetail>> map = new HashMap<>();
		map.put("weekList", weekList);
		map.put("monthList", monthList);
		map.put("quarterList", quarterList);
		map.put("yearList", yearList);
		return map;
	}

	public Map<String, List<Object>> findStockSectorPrice(String stockCd, String from, String to)
			throws ParseException {
		Stock stock = stockRepository.findById(stockCd).get();
		String sectorCd = stock.getSectorCd();
		
		Date oFrom = dateFormat.parse(from);
		Date oTo = dateFormat.parse(to);

		List<Object> weekList = new ArrayList<>();
		List<Object> monthList = new ArrayList<>();
		List<Object> quarterList = new ArrayList<>();
		List<Object> yearList = new ArrayList<>();

		StockPriceDetail stockWeek = composeStockPriceDetails(
				stockPriceRepository.findWeekByStockCd(stockCd, oFrom, oTo), stockCd, FSDConstant.INTERVAL_WEEK);
		 
		StockPriceDetail stockMonth = composeStockPriceDetails(
				stockPriceRepository.findMonthByStockCd(stockCd, oFrom, oTo), stockCd, FSDConstant.INTERVAL_MONTH);
		StockPriceDetail stockQuarter = composeStockPriceDetails(
				stockPriceRepository.findQuarterByStockCd(stockCd, oFrom, oTo), stockCd,
				FSDConstant.INTERVAL_QUARTER);
		StockPriceDetail stockYear = composeStockPriceDetails(
				stockPriceRepository.findYearByStockCd(stockCd, oFrom, oTo), stockCd, FSDConstant.INTERVAL_YEAR);

		weekList.add(stockWeek);
		monthList.add(stockMonth);
		quarterList.add(stockQuarter);
		yearList.add(stockYear);

		SectorPriceDetail week = composeSectorPriceDetail(stockPriceRepository.findWeekBySectorCd(sectorCd, oFrom, oTo),
				sectorCd, FSDConstant.INTERVAL_WEEK);
		SectorPriceDetail month = composeSectorPriceDetail(
				stockPriceRepository.findMonthBySectorCd(sectorCd, oFrom, oTo), sectorCd, FSDConstant.INTERVAL_MONTH);
		SectorPriceDetail quarter = composeSectorPriceDetail(
				stockPriceRepository.findQuarterBySectorCd(sectorCd, oFrom, oTo), sectorCd,
				FSDConstant.INTERVAL_QUARTER);
		SectorPriceDetail year = composeSectorPriceDetail(stockPriceRepository.findYearBySectorCd(sectorCd, oFrom, oTo),
				sectorCd, FSDConstant.INTERVAL_YEAR);

		weekList.add(week);
		monthList.add(month);
		quarterList.add(quarter);
		yearList.add(year);

		HashMap<String, List<Object>> map = new HashMap<>();
		map.put("weekList", weekList);
		map.put("monthList", monthList);
		map.put("quarterList", quarterList);
		map.put("yearList", yearList);
		return map;
	}
}
