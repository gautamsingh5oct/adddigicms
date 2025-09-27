package net.mycomp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jpa.repository.JPAOtp;
import net.jpa.repository.JPASubscriberregConfig;
import net.mycomp.persist.CmsContent;
import net.mycomp.persist.Otp;
import net.mycomp.persist.Portal;
import net.mycomp.persist.SubscriberReg;
import net.mycomp.portal.service.CommonService;
import net.mycomp.repositoy.CMSDataRepository;
import net.mycomp.util.MsisdnMissingException;
import net.mycomp.util.SubscriberNotFoundException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CmsController {

	private static final Logger logger = Logger.getLogger(CmsController.class);

	@Autowired
	private CMSDataRepository cmsDataRepository;

	@Autowired
	private JPASubscriberregConfig jpaSubscriberregConfig;
   
	@Autowired
	private JPAOtp jpaOtpConfig;
	
	@Autowired
	private CommonService commonService;
	

	private int numberOfContentPerPage = 6;
	@Value("${cms.content.web.path}")
	private String contentWebPrefixPath;
	@Value("${cms.content.physical.path}")
	private String contentPhysicalPrefixPath;
	
	@Value("${cms.portal.url}")
	private String cmsPortalUrl;
	@Value("${cms.portal.url.template}")
	private String cmsPortalTemplate;
	
	@Value("${unsub.url}")
	private String unsubUrl;
	@Value("${otp.url}")
	private String otpUrl;
	
	@PostConstruct
	public void init(){
		
	}

	
	@RequestMapping("/dct")
	@ResponseBody
	public String unsub(HttpServletRequest request,
			ModelAndView modelAndView,
			@RequestParam(name = "subid", defaultValue = "0") int subid) {
		
		logger.info("unsub::::::: "+subid);
		return "{\"status\":false,\"messgae\":\"Error\"}";
	}
	
	@RequestMapping("/myaccount")
	public ModelAndView myaccount(HttpServletRequest request,
			ModelAndView modelAndView,
			@RequestParam(name = "pid", defaultValue = "0") int portalId,
			@RequestParam(name = "msisdn", required = false) String msisdn) throws Exception {
	
		
		
		Portal portal=cmsDataRepository.mapPortalIdToPortal.get(portalId);
		   modelAndView.addObject("portal",portal);
			String prevUrl = request.getHeader("referer");
			if (prevUrl == null){
				prevUrl = "#";
			}	
			modelAndView.addObject("prevUrl", prevUrl);
			List<SubscriberReg> subscriberRegList =commonService.myAccountDetail( request, portal);
			modelAndView.addObject("subscriberRegList", subscriberRegList);
			modelAndView.addObject("unsubUrl", unsubUrl);
			modelAndView.addObject("mapServiceIdToService", cmsDataRepository.mapServiceIdToService);
			if(portal.getMyaccountView()!=null){
				modelAndView.setViewName(portal.getMyaccountView());
			}else{		
			    modelAndView.setViewName("myaccount");
			}
			
		//modelAndView.setViewName("myaccount");
		return modelAndView;
	}
	
	@RequestMapping("/portal")
	public ModelAndView portal(
			HttpServletRequest request,
			ModelAndView modelAndView,
			@RequestParam(name = "pid", defaultValue = "0") int portalId,
			@RequestParam(name = "tc", defaultValue = "0", required = false) int totalContnet,
			@RequestParam(name = "sub", defaultValue = "0", required = false) int sub,
			@RequestParam(name = "msisdn", required = false) String msisdn,
			@RequestParam(name = "l", defaultValue = "0") String language) throws Exception {

		   modelAndView.addObject("l",language);
		   Portal portal=cmsDataRepository.mapPortalIdToPortal.get(portalId);
            sub=commonService.userSession(request, portal);
			List<CmsContent> listCmsContent = cmsDataRepository.mapPortalIdToCmsContent
					.get(portalId);
			int fromIndex = totalContnet;
			int toIndex = totalContnet + numberOfContentPerPage;
			List<CmsContent> listSubCmsContent = null;
			if (listCmsContent != null && fromIndex <= listCmsContent.size()
					&& listCmsContent.size() > toIndex) {
				listSubCmsContent = listCmsContent.subList(fromIndex, toIndex);
			} else if (listCmsContent != null
					&& fromIndex < listCmsContent.size()) {
				listSubCmsContent = listCmsContent.subList(fromIndex,
						listCmsContent.size());
			}
			if (listSubCmsContent != null) {
				totalContnet += listSubCmsContent.size();
			}
			modelAndView.addObject("totalContent", totalContnet);
			modelAndView.addObject("listCmsContent", listSubCmsContent);
			
			modelAndView.addObject("portal",portal);
			modelAndView
					.addObject("contentWebPrefixPath", contentWebPrefixPath);
			modelAndView.addObject("contentPhysicalPrefixPath",
					contentPhysicalPrefixPath);			
			String prevUrl = request.getHeader("referer");
			if (prevUrl == null){
				prevUrl = "#";
			}	
			
			
			modelAndView.addObject("sub", sub);
			modelAndView.addObject("prevUrl", prevUrl);
			
			if(portal.getPortalView()!=null){
				modelAndView.setViewName(portal.getPortalView());
				}else{
				 modelAndView.setViewName("portal");
				}
		//modelAndView.setViewName("portal");
		return modelAndView;
	}

	@RequestMapping("/contentview/{portalId}/{contentId}")
	public ModelAndView contentView(HttpServletRequest request,HttpServletResponse response,
			ModelAndView modelAndView,
			@PathVariable(value = "portalId") int portalId,
			@PathVariable(value = "contentId") int contentId
	) throws Exception{
			
			
			
			Portal portal=cmsDataRepository.mapPortalIdToPortal.get(portalId);
			if(commonService.userSession( request, portal)!=1){
				modelAndView.setView(new RedirectView(portal.getCampaignUrl()));
				return modelAndView;
			}
			String prevUrl = request.getHeader("referer");
			if(prevUrl==null){
				String url=cmsPortalTemplate.replaceAll("<pid>", String.valueOf(portalId));
				modelAndView.setView(new RedirectView(url));
				return modelAndView;
			}			
			modelAndView.addObject("prevUrl", prevUrl);
			String msisdn = (String) request.getSession()
					.getAttribute("msisdn");
			
			List<SubscriberReg> subscriberRegList = jpaSubscriberregConfig
					.findSubscriberRegByMsisdnAndServiceId(msisdn, portal.getServiceIds());
			SubscriberReg subscriberReg=null;
			int sub = 0;
			if(subscriberRegList!=null&&subscriberRegList.size()>0){
				subscriberReg=subscriberRegList.get(0);
				sub=subscriberReg.getStatus();
			}
			
			if (portal.getIsDemoPortal()!=null&&portal.getIsDemoPortal())  {
				sub = 1;
			}

			List<CmsContent> listCmsContent = cmsDataRepository.mapPortalIdToCmsContent
					.get(portalId);
			CmsContent cmsContent = listCmsContent.stream()
					.filter(a -> a.getId() == contentId).findFirst().get();
			
			if(cmsContent.getFilePath().contains(".apk")){
				download( request, response,
						portalId,contentId);
				return new ModelAndView("#");
			}
			
			modelAndView.addObject("cmsContent", cmsContent);
			modelAndView.addObject("portal",
					cmsDataRepository.mapPortalIdToPortal.get(portalId));
			modelAndView.addObject("queryString", request.getQueryString());
			modelAndView
					.addObject("contentWebPrefixPath", contentWebPrefixPath);
			modelAndView.addObject("contentPhysicalPrefixPath",
					contentPhysicalPrefixPath);
			modelAndView.addObject("sub", sub);
			
			if(StringUtils.isEmpty((String)request.getSession().getAttribute("msisdn"))){
				throw new MsisdnMissingException("msisdn missing",portalId);
			}
		
			if(portal.getContentView()!=null){
				modelAndView.setViewName(portal.getContentView());
			}else{
			   modelAndView.setViewName("contentview");
			}
		//modelAndView.setViewName("contentview");
		return modelAndView;
	}

	@RequestMapping(value = "/download/{portalId}/{contentId}", method = RequestMethod.GET)
	public void download(HttpServletRequest request,HttpServletResponse response,
			@PathVariable int portalId, @PathVariable int contentId)
			throws Exception {

		
		List<CmsContent> listCmsContent = cmsDataRepository.mapPortalIdToCmsContent
				.get(portalId);
		CmsContent cmsContent = listCmsContent.stream()
				.filter(a -> a.getId() == contentId).findFirst().get();
		File file = new File(contentPhysicalPrefixPath
				+ cmsContent.getFilePath());
		InputStream is = new FileInputStream(file);

		// MIME type of the file
		response.setContentType("application/octet-stream");
		// Response header
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ file.getName() + "\"");
		// Read from the file and write into the response
		OutputStream os = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = is.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}
		os.flush();
		os.close();
		is.close();
	}
	
	
	@ExceptionHandler(MsisdnMissingException.class)
	@ResponseBody
	public ModelAndView msisdnMissingException(HttpServletRequest request,MsisdnMissingException ex){
		ModelAndView modelAndView=new ModelAndView("msisdn_missing");
		modelAndView.addObject("otpUrl",otpUrl);
		modelAndView.setViewName("msisdn_missing");
		modelAndView.addObject("portal",
				cmsDataRepository.mapPortalIdToPortal.get(ex.getPid()));
		modelAndView
				.addObject("contentWebPrefixPath", contentWebPrefixPath);
		modelAndView.addObject("contentPhysicalPrefixPath",
				contentPhysicalPrefixPath);
		request.getSession().setMaxInactiveInterval(12000);//Attribute("msisdn", ex.getPid());
		request.getSession().setAttribute("pid", ex.getPid());
		modelAndView.addObject("pid",ex.getPid());
		return modelAndView;
	}
	
	
	@RequestMapping(value="/validateotp")
	public ModelAndView validateOTP(HttpServletRequest request){
		
		ModelAndView modelAndView=new ModelAndView("msisdn_missing");
		String msisdn=request.getParameter("msisdn");
		String otp=request.getParameter("otp");
		modelAndView.addObject("otpUrl",otpUrl);
		String pid=request.getParameter("pid");
		if(pid==null&&request.getSession()!=null){
			pid=request.getSession().getAttribute("pid")!=null?String.valueOf(request.getSession().getAttribute("pid")):null;
		}
		
		modelAndView.addObject("portal",
				cmsDataRepository.mapPortalIdToPortal.get(pid));
		modelAndView
				.addObject("contentWebPrefixPath", contentWebPrefixPath);
		modelAndView.addObject("contentPhysicalPrefixPath",	contentPhysicalPrefixPath);
		request.getSession().setMaxInactiveInterval(12000);		
		request.getSession().setAttribute("pid", pid);
		
		Otp o=jpaOtpConfig.findOtpByMsisdnAndOtp(msisdn, otp,true);		
		if(o!=null){			
			request.getSession().setAttribute("msisdn", msisdn);
			modelAndView.setView(new RedirectView("portal?pid="+pid));
		}else{
			modelAndView.addObject("info", "Mobile number and otp does not matched. Please retry");				
		}		
		return modelAndView;
	}
	
	@ExceptionHandler(SubscriberNotFoundException.class)
	@ResponseBody
	public ModelAndView subscriberNotFoundException(HttpServletRequest request,SubscriberNotFoundException ex){
		logger.error("SubscriberNotFoundException:: error:: query  string: "+request.getQueryString()+", Exception:: ", ex);
		return new ModelAndView("redirect:" +ex.getRedirectToUrl());
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ModelAndView exception(HttpServletRequest request,Exception ex){
		logger.error("exception:: error:: query  string: "+request.getQueryString()+", Exception:: ", ex);
		return new ModelAndView("error");
	}
	
}
