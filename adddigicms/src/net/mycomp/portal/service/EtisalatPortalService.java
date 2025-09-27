package net.mycomp.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jpa.repository.JPASubscriberregConfig;
import net.mycomp.persist.SubscriberReg;
import net.mycomp.util.Mutility;
import net.mycomp.util.SubscriberNotFoundException;
@Service("etisalatPortalService")
public class EtisalatPortalService extends AbstractPortalService{

	private static final Logger logger = Logger.getLogger(EtisalatPortalService.class);

	
	@Autowired
	private JPASubscriberregConfig jpaSubscriberregConfig;

	@Override
	public SubscriberReg isSubscribe(String msisdn,Integer portalId,
			List<Integer> serviceIds,Integer opId) throws SubscriberNotFoundException {
		List<SubscriberReg> subscriberRegList = jpaSubscriberregConfig.
				findSubscriberRegByMsisdn(msisdn);
		 logger.info("isSubscribe::subscriberRegList:: "+subscriberRegList);
		SubscriberReg subscriberReg=null;
		if(subscriberRegList!=null&&subscriberRegList.size()>0){			
		    for(SubscriberReg tmpSubscriberReg:subscriberRegList){
		    	if(serviceIds.contains(tmpSubscriberReg.getServiceId())){
		    		subscriberReg=tmpSubscriberReg;
		    		break;
		    	}
		    }
		}			
		 logger.info("isSubscribe::subscriberReg:: "+subscriberReg);
		Mutility.isValidSubcriber(subscriberReg);
		return subscriberReg;
	}

	@Override
	public List<SubscriberReg> findSubscriberRegByMsisdn(Integer portalId,
			String msisdn,List<Integer> serviceIds,Integer opId) {
		List<SubscriberReg> subscriberRegList=new ArrayList<SubscriberReg>();
		List<SubscriberReg> tmpSubscriberRegList = jpaSubscriberregConfig
				.findSubscriberRegByMsisdn(msisdn);
		logger.info("tmpSubscriberRegList::::::::::::: "+tmpSubscriberRegList+" ,serviceId: "+serviceIds);
		
		if(tmpSubscriberRegList!=null&&tmpSubscriberRegList.size()>0){			
		    for(SubscriberReg tmpSubscriberReg:tmpSubscriberRegList){
		    	if(serviceIds.contains(tmpSubscriberReg.getServiceId())){
		    		subscriberRegList.add(tmpSubscriberReg);
		    		break;
		    	}
		    }
		}
		return subscriberRegList;
	}

	
}
