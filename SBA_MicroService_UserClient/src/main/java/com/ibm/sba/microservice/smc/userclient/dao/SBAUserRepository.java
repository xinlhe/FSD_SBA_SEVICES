package com.ibm.sba.microservice.smc.userclient.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bluefsd.entity.SBAUserEntity;

/**
 * SBAUserRepository
 * 
 * @author XinLongHe
 * 
 * @since 2019.11.29
 *
 */
@Repository(value="userRepository")
public interface SBAUserRepository extends JpaRepository<SBAUserEntity, Long> {
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT u FROM SBAUserEntity u WHERE userName=:account or email=:account")
	public SBAUserEntity getUserInfoByUserName(String account);

	@Transactional(readOnly = true)
	@Query(value = "SELECT u FROM SBAUserEntity u WHERE verifyCode=:verifyCode")
	public SBAUserEntity findUserByVerifyCode(String verifyCode);

}
