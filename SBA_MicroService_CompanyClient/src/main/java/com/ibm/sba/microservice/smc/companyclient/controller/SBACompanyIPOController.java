package com.ibm.sba.microservice.smc.companyclient.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ibm.sba.microservice.smc.companyclient.service.SBAComplayIPOService;
import com.ibm.sba.microservice.smc.companyclient.service.SBAStockPriceService;
import net.bluefsd.comm.controller.BaseController;
import net.bluefsd.entity.IPOPlan;
import net.bluefsd.entity.Stock;

@RestController
@CrossOrigin
@RequestMapping("/smcipo")
public class SBACompanyIPOController extends BaseController {

    @Autowired
    SBAComplayIPOService ipoService;
    
    @Autowired
    SBAStockPriceService stockPriceService;

    @RequestMapping(value = {"/add", "/create"}, method = RequestMethod.POST)
    public Map add(@RequestBody IPOPlan ipoPlan) {

        Map<String, Object> map = new HashMap<>();
        
        ipoService.add(ipoPlan);
        
        Stock stock = new Stock();
        
        stock.setCompanyCd(ipoPlan.getCompanyCd());
        stock.setExchCd(ipoPlan.getExchangeCd());
        stock.setSectorCd(ipoPlan.getSectorCd());
        stock.setStockCd(ipoPlan.getStockCd());
        
        stockPriceService.add(stock);
        
        map.put("status", "0");
        map.put("retMsg", "IPO Added Successful!!!");
        return map;
    }
    
    /*
    public Map findIPO(@RequestParam String companyCd, String companyName) {
        try {
            
            List<String> cdList = ipoService.findIPO(searchStr);
            
            Map map = successMessage();
            
            map.put("dataList", cdList);
            
            return map;

        } catch (Exception ex) {
            
            String msg = ex.getMessage();
            
            return errorMessage(msg);

        }
    }
    */


}
