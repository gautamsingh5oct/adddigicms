package net.mycomp.portal.service;

import java.util.List;

import net.mycomp.controller.CmsController;
import net.mycomp.persist.Portal;
import net.mycomp.persist.SubscriberReg;
import net.mycomp.util.SubscriberNotFoundException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service("servicePortalService")
public class ServicePortalService implements IPortalService{

	
	private static final Logger logger = Logger.getLogger(ServicePortalService.class);

	@Autowired
	private DefaultOperatorService defaultOperatorService;
	
	
	@Autowired
	private EtisalatPortalService etisalatPortalService;
	
	
	@Autowired
	private OredooKuwaitPortalService oredooKuwaitPortalService;
	
	private IPortalService findPortalService(Integer opId){
		IPortalService portalService=null;
		switch(opId){
		case IPortal.OREDOO_KUWAIT_OPERATOR_ID:{
			logger.info("OREDOO_KUWAIT_OPERATOR_ID::::::::::: ");
			portalService=oredooKuwaitPortalService;
			break;	
		}
		case IPortal.ETISALAT_OPERATOR_ID:{
			logger.info("ETISALAT_OPERATOR_ID::::::::::: ");
			portalService=etisalatPortalService;
			break;
		}
	  default :
		  portalService=defaultOperatorService;
		break;
	}
		
	return 	portalService;
	}
	
	@Override
	public ModelAndView portal(ModelAndView modelAndView, Portal portal,Integer opId) {
		return findPortalService(opId).portal(modelAndView, portal, opId);
	}

	@Override
	public ModelAndView myAccount(ModelAndView modelAndView, Portal portal,Integer opId) {
		return findPortalService(opId).myAccount(modelAndView, portal, opId);
	}

	@Override
	public ModelAndView msisdnMissing(ModelAndView modelAndView, Portal portal,Integer opId) {
		return findPortalService(opId).msisdnMissing(modelAndView, portal, opId);
	}

	@Override
	public ModelAndView contentView(ModelAndView modelAndView, Portal portal,Integer opId) {
		return findPortalService(opId).contentView(modelAndView, portal, opId);
	}

	@Override
	public SubscriberReg isSubscribe(String msisdn,Integer portalId, 
			List<Integer> serviceIds,Integer opId) throws SubscriberNotFoundException {
		return findPortalService(opId).isSubscribe( msisdn,portalId,
				serviceIds, opId) ;
	}

	@Override
	public List<SubscriberReg> findSubscriberRegByMsisdn(Integer portalId,
			String msisdn,List<Integer> serviceIds,Integer opId) {
		return findPortalService(opId).findSubscriberRegByMsisdn(portalId,msisdn,serviceIds, opId) ;
	}
}
