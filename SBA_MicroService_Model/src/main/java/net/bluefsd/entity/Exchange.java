package net.bluefsd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sba_smc_tab_exchange")
@org.hibernate.annotations.Proxy(lazy = false)
public class Exchange {
	@Id
	@Column(name = "exch_cd",length=20)
	private String exchCd;

	@Column(name = "exch_name")
	private String exchName;

	
	@Column(name = "brief")
	private String brief;

	@Column(name = "contact_addr")
	private String contactAddr;

	@Column(name = "remarks")
	private String remarks;

 

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

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContactAddr() {
		return contactAddr;
	}

	public void setContactAddr(String contactAddr) {
		this.contactAddr = contactAddr;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
 
 
}
