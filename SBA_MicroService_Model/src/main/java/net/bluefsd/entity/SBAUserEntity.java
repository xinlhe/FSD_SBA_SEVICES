package net.bluefsd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * This Entity is use for SBA Userinfo and match table sba_smc_tab_user
 * @author XinLongHe
 * @version v1
 * @since 2019.11.29
 */
@Entity
@Table(name = "sba_smc_tab_user")
@org.hibernate.annotations.Proxy(lazy = false)
public class SBAUserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_name", length = 50)
	private String userName;

	@Column(name = "user_password", length = 100)
	private String password;

	@Column(name = "user_role", length = 10)
	private String role;

	@Column(name = "user_email", length = 50)
	private String email;

	@Column(name = "user_mobile", length = 30)
	private String mobileNumber;

	@Column(name = "user_confirmed", length = 2)
	private String confirmed = "N";

	@Column(name = "user_verifycode", length = 100)
	private String verifyCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}
