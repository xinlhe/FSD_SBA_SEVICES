package net.bluefsd.model;

import net.bluefsd.entity.Stock;

public class CompanyDetail {
	private String companyCd;
	private String companyName;
	private String ceoName;
	private String brief;

	private String exchCd;
	private String exchName;
	private String stockCd;
	private String director;
	private String sectorCd;
	private String sectorName;

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	public String getExchCd() {
		return exchCd;
	}

	public void setExchCd(String exchCd) {
		this.exchCd = exchCd;
	}

	public String getExchName() {
		return exchName;
	}

	public void setExchName(String exchName) {
		this.exchName = exchName;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCeoName() {
		return ceoName;
	}

	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getDirector() {
		return director;
	}

	public String getStockCd() {
		return stockCd;
	}

	public void setStockCd(String stockCd) {
		this.stockCd = stockCd;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getSectorCd() {
		return sectorCd;
	}

	public void setSectorCd(String sectorCd) {
		this.sectorCd = sectorCd;
	}

}
