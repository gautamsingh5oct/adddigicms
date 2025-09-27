package net.mycomp.portal.service;

import java.util.List;

import net.mycomp.persist.Portal;
import net.mycomp.persist.SubscriberReg;
import net.mycomp.util.SubscriberNotFoundException;

import org.springframework.web.servlet.ModelAndView;

public interface IPortalService {

	public ModelAndView portal(ModelAndView modelAndView,Portal portal,Integer opId);
	public ModelAndView contentView(ModelAndView modelAndView,Portal portal,Integer opId);
	public ModelAndView myAccount(ModelAndView modelAndView,Portal portal,Integer opId);
	public ModelAndView msisdnMissing(ModelAndView modelAndView,Portal portal,Integer opId);
	public SubscriberReg isSubscribe(String msisdn,Integer portalId,
			List<Integer> serviceIds,Integer opId) throws SubscriberNotFoundException;
	public List<SubscriberReg> findSubscriberRegByMsisdn(Integer portalId,
			String msisdn,List<Integer> serviceIds,Integer opId);
}
