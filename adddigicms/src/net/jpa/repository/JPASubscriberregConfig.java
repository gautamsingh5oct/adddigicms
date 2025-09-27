package net.jpa.repository;


import java.util.List;

import net.mycomp.persist.SubscriberReg;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface JPASubscriberregConfig extends JpaRepository<SubscriberReg, Long>{

	@Query("select b from SubscriberReg b where b.msisdn=:msisdn and b.status=:status and"
			+ " b.serviceId in (:serviceIds)")
    List<SubscriberReg> findSubscriberRegByMsisdnAndStatus(@Param("msisdn")String msisdn,
    		@Param("status")int status,@Param("serviceIds")List<Integer> serviceIds);
	
	@Query("select b from SubscriberReg b where b.msisdn=:msisdn and  b.serviceId in (:serviceIds)")
    List<SubscriberReg> findSubscriberRegByMsisdnAndServiceId(@Param("msisdn")String msisdn,@Param("serviceIds")List<Integer> serviceIds);
	
	 @Query("select b from SubscriberReg b where b.msisdn=:msisdn")
	 List<SubscriberReg> findSubscriberRegByMsisdn(@Param("msisdn")String msisdn);
	 
	 @Query("select b from SubscriberReg b where b.subscriberId=:subscriberId")
	 SubscriberReg  findSubscriberRegById(@Param("subscriberId")Integer subscriberId);
	 
	 @Query("select b from SubscriberReg b where b.subscriberId=:subscriberId and  b.serviceId in (:serviceIds)")
	 SubscriberReg  findSubscriberRegById(@Param("subscriberId")Integer subscriberId,@Param("serviceIds")List<Integer> serviceIds);
	 

}