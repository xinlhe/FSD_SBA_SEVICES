package net.bluefsd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sba_smc_tab_stock")
@org.hibernate.annotations.Proxy(lazy = false)
public class Stock {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@Column(name = "stock_cd",length=20)
	private String stockCd;

	@Column(name = "company_cd", length=20)
	private String companyCd;

	@Column(name = "exch_cd", length=20)
	private String exchCd;

	@Column(name = "sector_cd", length=20)
	private String sectorCd;

	public String getSectorCd() {
		return sectorCd;
	}

	public void setSectorCd(String sectorCd) {
		this.sectorCd = sectorCd;
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	public String getStockCd() {
		return stockCd;
	}

	public void setStockCd(String stockCd) {
		this.stockCd = stockCd;
	}

	public String getExchCd() {
		return exchCd;
	}

	public void setExchCd(String exchCd) {
		this.exchCd = exchCd;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	
}
