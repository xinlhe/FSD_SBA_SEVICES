package com.ibm.sba.microservice.smc.companyclient.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ibm.sba.microservice.smc.companyclient.service.SBAStockPriceService;
import net.bluefsd.comm.controller.BaseController;
import net.bluefsd.entity.Stock;
import net.bluefsd.model.SectorPriceDetail;
import net.bluefsd.model.StockPriceDetail;

@RestController
@CrossOrigin
@RequestMapping("/stock")
public class SBAStockPriceController extends BaseController {
	@Autowired
	SBAStockPriceService stockPriceService;
	
	@RequestMapping(value = { "/listpricedetail" }, method = RequestMethod.POST)
	public Map<String, List<StockPriceDetail>> ListStockPrice(String[] stockCds, String from, String to) {
		try {
		    
			return stockPriceService.listStockPrice(stockCds, from, to);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = { "/pricedetail" }, method = RequestMethod.POST)
	public Map<String, StockPriceDetail> findPriceDetail(String stockCd) {
		
	    try {
		    
			return stockPriceService.findStockPrice(stockCd);
			
		} catch (Exception e) {
		    
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = { "/listsectorprice" }, method = RequestMethod.POST)
	public Map<String, List<SectorPriceDetail>> ListSectorPrice(String[] sectorCds, String from, String to) {
		try {
		    
			return stockPriceService.listSectorPrice(sectorCds, from, to);
			
		} catch (Exception e) {
		    
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = { "/sectorprice" }, method = RequestMethod.POST)
	public Map<String, SectorPriceDetail> findSecotrPrice(String sectorCd) {
		try {
		    
			return stockPriceService.findSecotrPrice(sectorCd);
			
		} catch (Exception e) {
		    
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = { "/stocksectorprice" }, method = RequestMethod.POST)
	public Map<String, List<Object>> findStockSectorPrice(String stockCd, String from, String to) {
		try {
		    
			return stockPriceService.findStockSectorPrice(stockCd, from, to);
			
		} catch (Exception e) {
		    
			e.printStackTrace();
			
			return null;
		}
	}

	@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
	public Map add(Stock stock) {
	    
		stockPriceService.add(stock);
		return successMessage();
	}
}
