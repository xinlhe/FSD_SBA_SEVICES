package net.bluefsd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sba_smc_tab_company")
@org.hibernate.annotations.Proxy(lazy = false)
public class Company {

	@Id
	@Column(name = "company_cd", unique = true, nullable = false, length = 20)
	private String companyCd;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "ceo_name")
	private String ceoName;

	@Column(name = "director")
	private String director;

	@Column(name = "exch_cd", nullable = false, length = 20)
	private String exchCd;

	@Column(name = "sector_cd", nullable = false, length = 20)
	private String sectorCd;

	@Column(name = "brief")
	private String brief;

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
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

	
	public String getDirector() {
		return director;
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

	public String getExchCd() {
		return exchCd;
	}

	public void setExchCd(String exchCd) {
		this.exchCd = exchCd;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

}
