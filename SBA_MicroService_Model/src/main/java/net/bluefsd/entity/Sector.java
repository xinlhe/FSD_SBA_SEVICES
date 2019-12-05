package net.bluefsd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sba_smc_tab_sector")
@org.hibernate.annotations.Proxy(lazy = false)
public class Sector {
	@Id
	@Column(name = "sector_cd",length=20)
	private String sectorCd;

	@Column(name = "sector_name")
	private String sectorName;

	@Column(name = "brief")
	private String brief;


	public String getSectorCd() {
		return sectorCd;
	}

	public void setSectorCd(String sectorCd) {
		this.sectorCd = sectorCd;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

}
