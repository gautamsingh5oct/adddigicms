package net.mycomp.portal.service;

import java.util.List;

import net.jpa.repository.JPASubscriberregConfig;
import net.mycomp.persist.Portal;
import net.mycomp.persist.SubscriberReg;
import net.mycomp.util.Mutility;
import net.mycomp.util.SubscriberNotFoundException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractPortalService implements IPortalService {

	private static final Logger logger = Logger
			.getLogger(AbstractPortalService.class);

	@Autowired
	private JPASubscriberregConfig jpaSubscriberregConfig;

	@Override
	public ModelAndView portal(ModelAndView modelAndView, Portal portal,
			Integer opId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView contentView(ModelAndView modelAndView, Portal portal,
			Integer opId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView myAccount(ModelAndView modelAndView, Portal portal,
			Integer opId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView msisdnMissing(ModelAndView modelAndView, Portal portal,
			Integer opId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubscriberReg isSubscribe(String msisdn, Integer portalId,
			List<Integer> serviceIds, Integer opId)
			throws SubscriberNotFoundException {
		List<SubscriberReg> subscriberRegList = jpaSubscriberregConfig
				.findSubscriberRegByMsisdn(msisdn);
		logger.info("isSubscribe::subscriberRegList:: " + subscriberRegList);
		SubscriberReg subscriberReg = null;
		if (subscriberRegList != null && subscriberRegList.size() > 0) {
			if (serviceIds == null) {
				subscriberReg = subscriberRegList.get(0);
			} else {
				for (SubscriberReg s : subscriberRegList) {
					if (serviceIds.contains(s.getServiceId().intValue())) {
						subscriberReg=s;
						break;
					}
				}
			}
		}
		logger.info("isSubscribe::subscriberReg:: " + subscriberReg);
		Mutility.isValidSubcriber(subscriberReg);
		return subscriberReg;
	}

	@Override
	public List<SubscriberReg> findSubscriberRegByMsisdn(Integer portalId,
			String msisdn, List<Integer> serviceIds, Integer opId) {
		return jpaSubscriberregConfig.findSubscriberRegByMsisdnAndServiceId(msisdn,serviceIds);
	}
}
