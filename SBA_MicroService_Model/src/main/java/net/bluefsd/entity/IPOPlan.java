package net.bluefsd.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sba_smc_tab_ipo_plan")
@org.hibernate.annotations.Proxy(lazy = false)
public class IPOPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "company_cd", length = 20)
	private String companyCd;

	@Column(name = "exchange_cd", length = 20)
	private String exchangeCd;
	
	@Column(name = "stock_cd",length=20)
    private String stockCd;
	
	@Column(name = "sector_cd",length=20)
    private String sectorCd;
	
	@Column(name = "price_per_share")
	private double pricePerShare;

	@Column(name = "total_shares")
	private long totalShares;

	@Column(name = "open_date")
	private Timestamp openDate;

	@Column(name = "remarks")
	private String remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	public double getPricePerShare() {
		return pricePerShare;
	}

	public void setPricePerShare(double pricePerShare) {
		this.pricePerShare = pricePerShare;
	}

	public long getTotalShares() {
		return totalShares;
	}

	public void setTotalShares(long totalShares) {
		this.totalShares = totalShares;
	}

	public Timestamp getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Timestamp openDate) {
		this.openDate = openDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getExchangeCd() {
		return exchangeCd;
	}

	public void setExchangeCd(String exchangeCd) {
		this.exchangeCd = exchangeCd;
	}

    public String getStockCd() {
        return stockCd;
    }

    public void setStockCd(String stockCd) {
        this.stockCd = stockCd;
    }

    public String getSectorCd() {
        return sectorCd;
    }

    public void setSectorCd(String sectorCd) {
        this.sectorCd = sectorCd;
    }
    
    
	
}
