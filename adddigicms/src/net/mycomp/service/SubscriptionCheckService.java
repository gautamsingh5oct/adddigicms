package net.mycomp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jpa.repository.JPASubscriberregConfig;
import net.mycomp.persist.SubscriberReg;
import net.mycomp.util.Mutility;

@Service("subscriptionCheckService")
public class SubscriptionCheckService {

	private static final Logger logger = Logger.getLogger(SubscriptionCheckService.class);
	
	
	
	
	 @Autowired
	private JPASubscriberregConfig jpaSubscriberregConfig;
	
	 public int userSession(HttpServletRequest request,List<Integer> serviceIds) {

			int subscribed=0;
			try{
			String msisdn=request.getHeader("msisdn");
			if(msisdn==null){
				msisdn=request.getParameter("msisdn");
			}
			if (msisdn == null) {
				msisdn = (String) request.getSession().getAttribute("msisdn");
			}
			SubscriberReg subscriberReg = null;
			List<SubscriberReg> subscriberRegList=null;
			if(msisdn!=null){
			request.getSession().setMaxInactiveInterval(12000);
			request.getSession().setAttribute("msisdn", msisdn);
			 subscriberRegList = jpaSubscriberregConfig
					.findSubscriberRegByMsisdnAndStatus(msisdn, 1,serviceIds);
			 if(subscriberRegList!=null){
				 subscriberReg=subscriberRegList.get(0);
			 }
			}else{
				Integer subId=Mutility.toInt(request.getParameter("subid"),0);
				if(subId==0){
					 subId=(Integer) request.getSession().getAttribute("subid");					 
				}
				subscriberReg=jpaSubscriberregConfig.findSubscriberRegById(subId, serviceIds);
			}
			
			if (subscriberReg != null) {				
				request.getSession().setAttribute("subid", subscriberReg.getSubscriberId());
			 }
			
			if ((subscriberReg != null&&subscriberReg.getStatus()==1)) {			
					
				subscribed=1;
			}
	 }catch(Exception ex){
		 logger.error("Exception",ex);
	 }
			return subscribed;
		}
}
