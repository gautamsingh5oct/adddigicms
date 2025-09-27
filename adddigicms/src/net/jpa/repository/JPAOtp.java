package net.jpa.repository;

import net.mycomp.persist.Otp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface JPAOtp extends JpaRepository<Otp, Long>{

	@Query("select b from Otp b where b.msisdn=:msisdn and b.otp=:otp "
			+ " and TIMESTAMPDIFF(MINUTE, b.createTime, NOW())<10 and b.send=:send")
	Otp findOtpByMsisdnAndOtp(@Param("msisdn")String msisdn,
    		@Param("otp")String otp,@Param("send")boolean send);
	

}