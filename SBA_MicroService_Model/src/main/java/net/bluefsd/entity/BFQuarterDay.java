package net.bluefsd.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sba_smc_tab_quarter_day")
@org.hibernate.annotations.Proxy(lazy = false)
public class BFQuarterDay extends BFInterval {
	
}
