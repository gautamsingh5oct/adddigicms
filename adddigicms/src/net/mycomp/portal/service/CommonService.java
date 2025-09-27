package net.mycomp.portal.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.jpa.repository.JPASubscriberregConfig;
import net.mycomp.persist.Portal;
import net.mycomp.persist.SubscriberReg;
import net.mycomp.util.Mutility;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commonService")
public class CommonService {

	private static final Logger logger = Logger.getLogger(CommonService.class);

	@Autowired
	private JPASubscriberregConfig jpaSubscriberregConfig;
	
	public int userSession( HttpServletRequest request,
			Portal portal) {

		String msisdn=null;
		int subId=0;
		
		int sub = 0;
		
		try{
		request.getSession().setMaxInactiveInterval(12000);
		
		if (msisdn == null) {
			msisdn =  request.getParameter("msisdn");
		}
		
		if (msisdn == null) {
			msisdn =  request.getHeader("msisdn");
		}
		
		if (msisdn == null) {
			msisdn = (String) request.getSession().getAttribute("msisdn");
		}

		if (msisdn != null) {
		 request.getSession().setMaxInactiveInterval(12000);
		 request.getSession().setAttribute("msisdn", msisdn);
		}
		
		
		
		logger.info("user msisdn: "+msisdn+" session:: subId:: "+subId);
		
		SubscriberReg subscriberReg = null;
		if(msisdn!=null){		
			
//			List<SubscriberReg> subscriberRegList = jpaSubscriberregConfig
//					.findSubscriberRegByMsisdnAndStatus(msisdn, 1,
//							portal.getServiceIds());
			
			List<SubscriberReg> subscriberRegList = jpaSubscriberregConfig
					.findSubscriberRegByMsisdnAndServiceId(msisdn,portal.getServiceIds());
			logger.info("user session :: subscriberRegList:: "+subscriberRegList);
			
			if (subscriberRegList != null && subscriberRegList.size() > 0) {
				subscriberReg = subscriberRegList.get(0);
				request.getSession().setAttribute(Mutility.getSubId(portal.getId()),
						subscriberReg.getSubscriberId());
			 }		
		}
		
		
		if(subscriberReg==null){			
			subId=Mutility.toInt(request.getParameter("subid"), 0);
			if(subId==0){
				subId=Mutility.toInt(String.valueOf(request.getSession().getAttribute(Mutility.getSubId(portal.getId()))), 0);
			}
			if(subId!=0){			
			request.getSession().setAttribute(Mutility.getSubId(portal.getId()), subId);
			}			
			subscriberReg=jpaSubscriberregConfig.findSubscriberRegById(subId);			
		}
		
		
		if((subscriberReg != null&&(subscriberReg.getStatus()==1||!portal.getCheckSubStatus()))
				|| (portal.getIsDemoPortal() != null && portal
						.getIsDemoPortal())) {
			sub = 1;
			request.getSession().setAttribute("login", 1);			
		}else{
			sub=0;
			request.getSession().setAttribute("login", 0);	
		}
		
		
		}catch(Exception ex){
			logger.error("exception ",ex);
		}finally{
			try{
				if (portal != null && portal.getIsDemoPortal() != null
						&& portal.getIsDemoPortal()) {
					return 1;
				}
			}catch(Exception ex){
				logger.error("exception ",ex);
			}
		}
		return sub;
	 }
	
	
	public List<SubscriberReg> myAccountDetail( HttpServletRequest request,
			Portal portal) {

		List<SubscriberReg> subscriberRegList =new ArrayList<SubscriberReg>();
		String msisdn=null;
		try{
		
		if (msisdn == null) {
			msisdn =  request.getParameter("msisdn");
		}
		
		if (msisdn == null) {
			msisdn =  request.getHeader("msisdn");
		}
		
		if (msisdn == null) {
			msisdn = (String) request.getSession().getAttribute("msisdn");
		}

		
		
		if(msisdn!=null){		
			subscriberRegList = jpaSubscriberregConfig
					.findSubscriberRegByMsisdnAndServiceId(msisdn,
							portal.getServiceIds());
			logger.info("user session :: subscriberRegList:: "+subscriberRegList);
			
			if (subscriberRegList != null && subscriberRegList.size() > 0) {
				return subscriberRegList;
			 }		
		}
		
		int subId=0;
			
		    subId=Mutility.toInt(String.valueOf(request.getSession().getAttribute(
				   Mutility.getSubId(portal.getId()))), 0);
			
			
			if(subId!=0){
				subscriberRegList.add(jpaSubscriberregConfig.findSubscriberRegById(subId));	
			}			
					
		
		}catch(Exception ex){
			logger.error("exception ",ex);
		}
		return subscriberRegList;
	 }
	
	
}
